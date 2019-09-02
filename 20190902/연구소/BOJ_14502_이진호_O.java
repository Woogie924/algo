import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소
{
	static int N,M;
	static int[][] map;
	static boolean[][] discoverd;
	static int zeroSum;
	static List<Loc> viruses;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		discoverd = new boolean[N][M];
		
		zeroSum = N * M;
		viruses = new ArrayList<Loc>();
		int  input;
		for(int y = 0; y < N ; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < M ; x++)
			{
				input = Integer.parseInt(st.nextToken());
				map[y][x] = input;
				if(input!=0) zeroSum--;
				if(input==2) viruses.add(new Loc(y,x));
			}
		}
		//zeroSum이 0이 되거나 min값보다 커지면 bfs를 나와야 한다.
		//그전에 bfs할 좌표는 조합함수가 필요하다.
		
		zeroSum -= 3;
		max = Integer.MIN_VALUE;
		combination(0,0,new boolean[N*M]);
		
		System.out.println(max);
	}
	
	private static void combination( int start, int depth, boolean[] visited)
	{//조합함수에서 인덱스 /N 은 행 /M 은 열
		int temp;
		if(depth == 3)
		{
			temp = bfs();
//			System.out.println(temp);
			if(max<temp) max = temp;
			
			clearDiscoverd();
			return;
		}
		
		for(int i = start; i < N*M ; i++)
		{
			if(map[i/M][i%M]!=0) continue; 
			if(visited[i]) continue;
			map[i/M][i%M] = 1;
			combination(i+1, depth+1,visited);
			map[i/M][i%M] = 0;
			visited[i] = false;
		}
	}
	
	private static void clearDiscoverd()
	{
		for(int y = 0 ; y < N ; y++)
		{
			Arrays.fill(discoverd[y], false);
		}
	}

	static int max;
	static int[] dx = {1,0,-1,0}; // 동남서북
	static int[] dy = {0,1,0,-1};
	
	private static int bfs()//이함수끝나고 discoverd 초기화
	{
		//처음에 큐에 넣어야된다.
		// zerosum이 0이되면 나간다.
		
		int zs = zeroSum;
		Loc loc;
		Queue<Loc> q = new LinkedList<연구소.Loc>();
		int vsize = viruses.size();
		for(int i = 0 ; i< vsize ; i++)
		{
			loc = viruses.get(i);
			q.add(loc);
		}
		
		int tx,ty;
		while(!q.isEmpty())
		{
			if(zs == 0) break;
			
			loc = q.poll();
			
			for(int i = 0 ; i < 4 ; i++)
			{
				tx = loc.x+dx[i];
				ty = loc.y+dy[i];
				if(ty<0||ty>=N || tx<0 || tx>=M) continue;
				if(map[ty][tx]!=0 )continue;
				if(discoverd[ty][tx]) continue;
				
				discoverd[ty][tx] = true;
				zs--;
				q.add(new Loc(ty,tx));
			}
		}
		return zs;
	}
	static class Loc
	{
		int y;
		int x;
		
		public Loc(int y, int x)
		{
			this.y = y;
			this.x = x;
		}
	}
}
