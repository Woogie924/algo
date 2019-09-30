import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][][] dp;
	static boolean[][] wall;
	static int[] dx = {-1,-1,0};//서 대각 북
	static int[] dy = {0,-1,-1};
	static class Loc
	{
		int x;
		int y;
		int dir;
		public Loc(int y, int x, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][N+1][3];//rc가 1부터시작
		wall = new boolean[N+1][N+1];
		
		for(int y = 1 ; y <= N ; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x = 1 ; x <= N ;x++)
			{
				if(st.nextToken().charAt(0)=='1') wall[y][x]= true;
			}
		}
		dp[1][2][0] =1;
		for(int y = 1 ; y <= N ; y++)
		{
			for(int x = 1 ; x <= N ;x++)
			{
				range(y,x,0);
				range(y,x,1);
				range(y,x,2);
			}
		}
		System.out.println(dp[N][N][0]+dp[N][N][1]+dp[N][N][2]);
	}
	static Queue<Loc> q;
	private static void range(int y,int x,int dir) {
		int tx = x+dx[dir];
		int ty = y + dy[dir];
		if(tx<1||tx>N||ty<1||ty>N) return;
		if(wall[y][x]) return;
		if(dir==0)
		{
			dp[y][x][0] += dp[ty][tx][0] + dp[ty][tx][1];
		}
		else if(dir==1)
		{
			if(wall[y][x-1]||wall[y-1][x]) return;
			dp[y][x][1] += dp[ty][tx][0] + dp[ty][tx][1]+dp[ty][tx][2];
		}
		else if(dir ==2)
		{
			dp[y][x][2] += dp[ty][tx][2] + dp[ty][tx][1];
		}

	}

}
