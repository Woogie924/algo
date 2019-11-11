import java.io.*;
import java.util.*;

public class Main {
	
	static class Pair 
	{
		int x;
		int y;
		int g;
		
		public Pair() {}
		public Pair(int x, int y, int g) 
		{
			this.x = x;
			this.y = y;
			this.g = g;
		}
	}
	
	static int N, M, T, map[][];
	
	static final int INF = 987654321;
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		for(int i = 0 ; i < N ; i++)
		{
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++)
				map[i][j] = Integer.parseInt(tokens.nextToken());
		}
			
		out.write(solve());
		out.flush();
	}

	static String solve()
	{
		int time[][][] = new int[N][M][2];
		for(int i = 0 ; i < N ; i++)
			for(int j = 0 ; j < M ; j++)
				Arrays.fill(time[i][j], INF);
		
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(0, 0, 0));
		time[0][0][0] = 0;
		
		while(!q.isEmpty())
		{
			Pair p = q.poll();
			
			if(time[p.x][p.y][p.g] > T)
				continue;
			
			if(p.x == N - 1 && p.y == M - 1)
				return Integer.toString(time[p.x][p.y][p.g]);
			
			for(int d = 0 ; d < 4 ; d++)
			{
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				int ng = -1;
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				
				// 그람을 구하지 못한 경우
				if(p.g == 0)
				{
					ng = (map[nx][ny] == 2 ? 1 : 0);
					if(map[nx][ny] == 1 || time[p.x][p.y][p.g] + 1 >= time[nx][ny][ng])
						continue;
				}
				// 그람을 구한 경우
				else 
				{
					ng = p.g;
					if(time[p.x][p.y][p.g] + 1 >= time[nx][ny][ng])
						continue;
				}
				
				q.offer(new Pair(nx, ny, ng));
				time[nx][ny][ng] = time[p.x][p.y][p.g] + 1;
			}
		}
		
		return "Fail";
	}
}