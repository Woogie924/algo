import java.io.*;
import java.util.*;

public class Main {
	
	static class Pair 
	{
		int x;
		int y;
		int k;
		
		public Pair() {}
		public Pair(int x, int y, int k) 
		{
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
	
	static int K, W, H, map[][];
	
	static final int INF = 987654321;
	static int dx1[] = {-1, 0, 1, 0};
	static int dy1[] = {0, 1, 0, -1};
	static int dx2[] = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int dy2[] = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		K = Integer.parseInt(in.readLine());
		
		tokens = new StringTokenizer(in.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		
		map = new int[H][W];
		for(int i = 0 ; i < H ; i++)
		{
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < W ; j++)
				map[i][j] = Integer.parseInt(tokens.nextToken());
		}
			
		out.write(solve() + "");
		out.flush();
	}

	static int solve()
	{
		int count[][][] = new int[H][W][K + 1];
		for(int i = 0 ; i < H ; i++)
			for(int j = 0 ; j < W ; j++)
				Arrays.fill(count[i][j], INF);
		
		Queue<Pair> q = new LinkedList<Pair>();
		
		q.offer(new Pair(0, 0, K));
		count[0][0][K] = 0;
		
		while(!q.isEmpty())
		{
			Pair p = q.poll();
			
			if(p.x == H - 1 && p.y == W - 1)
				return count[p.x][p.y][p.k];
			
			for(int d = 0 ; d < 4 ; d++)
			{
				int nx = p.x + dx1[d];
				int ny = p.y + dy1[d];
				int nk = p.k;
				
				if(nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;
					
				if(map[nx][ny] == 1 || count[p.x][p.y][p.k] + 1 >= count[nx][ny][nk])
					continue;
				
				q.offer(new Pair(nx, ny, nk));
				count[nx][ny][nk] = count[p.x][p.y][p.k] + 1;
			}
			
			if(p.k > 0)
			{
				for(int d = 0 ; d < 8 ; d++)
				{
					int nx = p.x + dx2[d];
					int ny = p.y + dy2[d];
					int nk = p.k - 1;
					
					if(nx < 0 || nx >= H || ny < 0 || ny >= W)
						continue;
						
					if(map[nx][ny] == 1 || count[p.x][p.y][p.k] + 1 >= count[nx][ny][nk])
						continue;
					
					q.offer(new Pair(nx, ny, nk));
					count[nx][ny][nk] = count[p.x][p.y][p.k] + 1;
				}
			}
		}
		
		return -1;
	}
}