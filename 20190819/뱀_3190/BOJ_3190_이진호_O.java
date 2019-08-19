import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀3190 {
	static int N, K, L;
	static int[][] map;
	static int[] command;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		
		StringTokenizer st; 
		int x,y,c;
		for(int tc = 0 ; tc < K ; tc++)
		{
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			map[y][x] = 1;//사과 위치 1
		}
		
		L = Integer.parseInt(br.readLine());
		command = new int[2*L+1];//짝수 - 명령 카운트 홀수 - 방향
		int comCount= 0;
		for(int tc = 0 ; tc < L ; tc++)
		{
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			if(st.nextToken().charAt(0)=='L') c = -1;//왼쪽 : -1 ==== 오른쪽 : 1
			else c =1;
			command[comCount++] = x;
			command[comCount++] = c;
		}
		
		command[command.length-1] = -1;
		System.out.println(go());

	}
	static int[] dy = {0,1,0,-1}; // 동 남 서 북
	static int[] dx = {1,0,-1,0};
	private static int go()
	{
		Queue<Loc> q = new LinkedList<Loc>();
		
		int time = 0;//진행된시간
		int comCount= 0;//명령어 순서
		int nextCom = command[comCount++];//다음 명령어까지 남은 시간
		int d = 0;//방향
		
		Loc cur = new Loc(1,1);
		q.add(new Loc(1,1));
		map[1][1] = 2;//2면 뱀의몸
		Loc temp;
		while(true)
		{
			//이동시작 1 3 5 
			if(nextCom==time&&comCount<2*L)//시작부터 이걸넣어줌
			{
				d = (4+d+command[comCount++])%4;
				nextCom = command[comCount++]; 
			}
			cur.x = cur.x+dx[d];
			cur.y = cur.y+dy[d];
//			nextCom--;
			time++;
			if(cur.x<1||cur.x>N||cur.y<1||cur.y>N)//종료조건
				return time;
			if(map[cur.y][cur.x]==2)// 자기몸을만나면
			{
				return time;
			}
			else if(map[cur.y][cur.x]==1 )// 사과를 만나면
			{
				q.add(new Loc(cur.x,cur.y));
				map[cur.y][cur.x]= 2; 
			}
			else if(map[cur.y][cur.x]==0 )//그냥이동시
			{
				q.add(new Loc(cur.x,cur.y));
				map[cur.y][cur.x]= 2; 
				temp = q.poll();
				map[temp.y][temp.x]= 0; 
			}
			/*System.out.println("======="+time+"==========");
			for(int i = 0 ; i <= N ;i++)
			{
				System.out.println(Arrays.toString(map[i]));
			}
			System.out.println("======================");*/
		}
	}
	static class Loc
	{
		int x;
		int y;
		public Loc(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}