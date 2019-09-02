import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 캐슬디펜스
{
	static int N,M,D;
	static int[][] map;
	static int ec;//enemyCount
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int [N+1][M];//N층은 궁수자리
		cm = new int[N+1][M];
		
		ec = 0; 
		for(int y = 0 ; y < N ; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < M ; x++ )
			{
				map[y][x] = Integer.parseInt(st.nextToken());
				if(map[y][x] ==1) ec++;
			}
		}//입력완료
		
		combination(new int[3],0,0,new boolean[M]);
		
		System.out.println(max);
	}
	static int max = Integer.MIN_VALUE;
	static boolean gameEnding = false;
	private static void combination(int[] result, int start, int depth, boolean[] visited)
	{
		if(depth==3)
		{
			int value = -1;
//			System.out.println(Arrays.toString(result));
			initialMap();
			value=gameStart(result);
			if(value>max)
			{
				max = value;
			}
			if(max == ec) gameEnding = true;
			return;
		}
		for(int i = start ; i < M ; i++)
		{
			if (gameEnding)return;//게임엔딩시 그다음값은 필요없다.
			if(!visited[i])
			{
				result[depth] = i;
				visited[i] = true;
				combination(result, i, depth+1, visited);
				if (gameEnding) return;//게임엔딩시 그다음값은 필요없다.
				visited[i] = false;
			}
		}
	}
	private static void initialMap()
	{
		//성안에위치는 초기화 안해도되나?
		for(int y = 0 ; y < N ; y++)
		{
			for(int x = 0 ; x < M ; x++ )
			{
				cm[y][x] = map[y][x];
			}
		}//입력
	}
	static int[][] cm;//copied map
	private static int gameStart(int[] result)
	{
		int tempEC = ec;
		int kc = 0;//total kill count
		int tck = 0;//turn마다의 killcount
//		show();
		while(tempEC>0)
		{
			tck = arrow(result);
//			show();
			tempEC -= tck;
			kc += tck;
			killed();
			tempEC -= move();
			
		}
		
		return kc;
	}
	private static void show()
	{
		System.out.println("==================");
		for(int y = 0 ; y <N ; y++)
		{
			System.out.println(Arrays.toString(cm[y]));
		}
		System.out.println("==================");
	}
	private static int move()
	{
		int kc = 0;
		for(int x = M-1; x >=0 ; x--)
		{
			if(cm[N-1][x]==1) //성에 다가간애들 죽이기
			{
				kc++;
				cm[N-1][x] = 0;
			}
			for(int y = N-2 ; y >=0 ; y--)
			{
				cm[y+1][x] = cm[y][x];
				cm[y][x] = 0;
			}
		}
		
		return kc;
	}
	private static void killed()
	{
		for(int y = 0 ; y < N ; y++)
		{
			for(int x = 0 ; x < M ; x++ )
			{
				if(cm[y][x] <0) cm[y][x] = 0;
			}
		}//입력
		
	}
	static int[] dx = {-1,0,1};//서 북 동
	static int[] dy = {0,-1,0};//서 북 동
	static boolean[][] discovord;
	private static int arrow(int[] result)
	{//동시에 쏜다.
		int kc = 0;//kill count
		
		// bfs로 거리만큼 각각 찾음 for문 세번돌려서
		// 죽인애들은 -로 처리하고 killed함수에서 -인애들은 무시
		int tx,ty;
		Loc loc;
		st:for(int archer = 0 ; archer <3 ; archer++)
		{
			Queue<Loc> q = new LinkedList<캐슬디펜스.Loc>();
			discovord = new boolean[N][M];
			q.add(new Loc(N,result[archer]));
			
			int dt = 1;//거리 값
			int size =q.size();
			
			loc = q.peek();
			if(cm[loc.y-1][loc.x]!=0) //바로앞에 몬스터가 있을경우
			{
				if(cm[loc.y-1][loc.x]==1)
				{
					cm[loc.y-1][loc.x] = -cm[loc.y-1][loc.x];
					kc++;
				}
				continue;
			}
			loc.y -=1;
			discovord[loc.y][loc.x]= true; 
			//바로앞만 예외처리
			while(!q.isEmpty())
			{
				if(dt==D) break;
				dt++;
				size =q.size();
				for(int i = 0 ; i < size ; i++)
				{
					loc = q.poll();
					
					for(int dir = 0 ; dir < 3 ; dir++)
					{
						tx = loc.x+dx[dir];
						ty = loc.y+dy[dir];
						
						if(tx<0||tx>=M||ty<0||ty>=N) continue;
						if(discovord[ty][tx]) continue;
						
						if(cm[ty][tx]!=0) 
						{
							if(cm[ty][tx]==1)
							{
								cm[ty][tx] = -cm[ty][tx];
								kc++;
							}
							continue st;
						}
						discovord[ty][tx] = true;
						q.add(new Loc(ty,tx));
					}
				}
			}
		}
		return kc;
	}
	static class Loc
	{
		int y;
		int x;
		public Loc(int y , int x)
		{
			this.y = y;
			this.x = x;
		}
	}
}
