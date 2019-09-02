import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
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
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int y = 0 ; y < N ; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x = 0 ; x< M ;  x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		//입력완료
		
		for(int y = 0 ; y < N ; y++)
		{
			for(int x = 0 ; x< M ;  x++)
			{
				dfs(y,x,0,0);
			}
		}
		System.out.println(max);
	}
	static int[] dx = {-0,1,0};// 동남서북
	static int[] dy = {1,0,-1};
	static int max = Integer.MIN_VALUE;
	
	static int cnt = 1;
	private static void dfs(int y,int x,int depth,int score)
	{
		if(depth == 4)
		{
			/*System.out.println(cnt++);
			System.out.printf("y: %d, x: %d",y,x);*/
//			System.out.println("s:"+score);
			if(max<score) max = score;
			return;
		}
		int tx,ty;
		for(int dir = 0 ; dir < 3; dir++)
		{
			ty = y+dy[dir];
			tx = x+dx[dir];
			if(ty<0||ty>=N||tx<0||tx>=M) continue;
			if(visited[ty][tx]) continue;
			
			visited[ty][tx] = true;
			dfs(ty,tx,depth+1,score+map[ty][tx]);
			visited[ty][tx] = false;
		}
		int tx2,ty2;
		if(depth ==2)
		{
			for(int dir = 0 ; dir < 3; dir++)
			{
				ty = y+dy[dir];
				tx = x+dx[dir];
				if(ty<0||ty>=N||tx<0||tx>=M) continue;
				if(visited[ty][tx]) continue;
				visited[ty][tx] = true;
				for(int dir2 = 0 ; dir2 < 3; dir2++)
				{
					ty2 = y+dy[dir2];
					tx2 = x+dx[dir2];
					if(ty2<0||ty2>=N||tx2<0||tx2>=M) continue;
					if(visited[ty2][tx2]) continue;
					dfs(ty,tx,depth+2,score+map[ty][tx]+map[ty2][tx2]);
				}
				visited[ty][tx] = false;
			}
		}
	}

}
