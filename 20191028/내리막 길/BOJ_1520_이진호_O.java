import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1520_이진호_O
{
	static int N,M;
	static int[][] dp;// -1 안와봄  0 갈데없음 1 이상 가짓수가 존재
	static int[][] map;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		for(int y= 0 ; y < N; y++)
		{
			Arrays.fill(dp[y], -1);
		}
		String is;
		for(int y= 0 ; y < N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x = 0 ; x< M ; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = 1;
		dp[N-1][M-1] = 0;
		System.out.println(solve(N-1,M-1));
	}
	static int[] dx= {1,0,-1,0};
	static int[] dy= {0,1,0,-1};
	private static int solve(int y, int x)
	{
		int result = 0;
		int tx,ty;
		for(int dir = 0 ; dir < 4 ; dir++)
		{
			tx = x+dx[dir];
			ty = y+dy[dir];
			if(ty<0||ty>=N||tx<0||tx>=M) continue;
			if(map[ty][tx]<=map[y][x]) continue;
			result += dp[ty][tx]==-1?solve(ty,tx):dp[ty][tx]; 
		}
		dp[y][x] =result;
		return result;
	}
}
