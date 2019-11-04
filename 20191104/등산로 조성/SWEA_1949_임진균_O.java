import java.io.*;
import java.util.*;

public class Solution {

	static class Pos
	{
		int x;
		int y;
		
		public Pos() {}
		public Pos(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, K, map[][];
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int tc = 0 ; tc < T ; tc++)
		{
			tokens = new StringTokenizer(in.readLine());
			N = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			
			map = new int[N][N];
			for(int i = 0 ; i < N ; i++)
			{
				tokens = new StringTokenizer(in.readLine());
				for(int j = 0 ; j < N ; j++)
					map[i][j] = Integer.parseInt(tokens.nextToken());
			}
			
			out.write("#" + (tc + 1) + " " + solve() + "\n");
		}
		
		out.flush();
	}

	static int solve()
	{
		int top = -1;
		for(int x = 0 ; x < N ; x++)
			for(int y = 0 ; y < N ; y++)
				top = Math.max(top, map[x][y]);
		
		// 초기 봉우리 위치를 저장한다.(즉, 출발 위치를 찾는다)
		ArrayList<Pos> tops = new ArrayList<Pos>();
		for(int x = 0 ; x < N ; x++)
			for(int y = 0 ; y < N ; y++)
				if(top == map[x][y])
					tops.add(new Pos(x, y));
		
		// 모든 지형에 대해서 0부터 최대 K까지 지형을 깎아보고, 최대 등산로의 길이를 구해본다.
		int result = 0;
		for(int x = 0 ; x < N ; x++)
		{
			for(int y = 0 ; y < N ; y++)
			{
				for(int k = 0 ; k <= K ; k++)
				{
					map[x][y] -= k;
					
					for(int i = 0 ; i < tops.size() ; i++)
					{
						Pos p = tops.get(i);
						result = Math.max(result, getRouteLen(p.x, p.y));
					}
					
					map[x][y] += k;
				}
			}
		}
		
		return result;
	}
	
	// (x, y)에서 시작하는 등산로의 길이를 반환한다.
	static int getRouteLen(int x, int y)
	{
		int len = 1;
		for(int d = 0 ; d < 4 ; d++)
		{
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || 
					map[x][y] <= map[nx][ny])
				continue;
			
			len = Math.max(len, getRouteLen(nx, ny) + 1);
		}
		
		return len;
	}
}