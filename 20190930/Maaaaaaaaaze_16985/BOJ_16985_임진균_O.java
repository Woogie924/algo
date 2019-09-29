import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
	
	static class Pos
	{
		int z;
		int x;
		int y;
		
		public Pos() {}
		public Pos(int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
		}
		
		public boolean equals(Pos p) {
			return (this.z == p.z && this.x == p.x && this.y == p.y);
		}
	}
	
	static class Pair
	{
		Pos pos;
		int dist;
		
		public Pair(Pos pos, int dist) {
			this.pos = pos;
			this.dist = dist;
		}
	}
	
	static int maze[][][] = new int[5][5][5];
	static int dx[] = {-1, 0, 1, 0, 0, 0};
	static int dy[] = {0, 1, 0, -1, 0, 0};
	static int dz[] = {0, 0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		for(int s = 0 ; s < 5 ; s++)
		{
			for(int x = 0 ; x < 5 ; x++)
			{
				tokenizer = new StringTokenizer(in.readLine());
				for(int y = 0 ; y < 5 ; y++)
					maze[s][x][y] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		
		int minDist = dupPerm(0);
		out.write((minDist == Integer.MAX_VALUE ? -1 : minDist) + "");
		out.flush();
	}
	
	// 각 면의 회전 횟수를 결정한다.
	static int dupPerm(int s)
	{
		if(s == 5)
			return perm(0);
		
		int minMoved = Integer.MAX_VALUE;
		for(int next = 0 ; next < 4 ; next++)
		{
			rotate(s, next);
			minMoved = Math.min(minMoved, dupPerm(s + 1));
			rotate(s, 4 - next);
		}
		
		return minMoved;
	}
	
	// 각 면을 배치할 수 있는 모든 경우의 수 중에서 미로를 탈출 할 수 있는 가장 적은 이동 횟수를 반환한다.
	static int perm(int here)
	{
		if(here == 5)
			return bfs();
		
		int minMoved = Integer.MAX_VALUE;
		for(int there = here ; there < 5 ; there++)
		{
			swap(here, there);
			minMoved = Math.min(minMoved, perm(here + 1));
			swap(there, here);
		}
		
		return minMoved;
	}
	
	// here 면과 there 면을 교환한다.
	static void swap(int here, int there)
	{
		int tempMaze[][] = new int[5][5];
		for(int x = 0 ; x < 5 ; x++)
			for(int y = 0 ; y < 5 ; y++)
				tempMaze[x][y] = maze[here][x][y];
		
		maze[here] = maze[there];
		maze[there] = tempMaze;
	}
	
	// 현재 미로에서 입구와 출구를 결정하고 이동 거리를 계산한다.
	static int bfs()
	{
		Pos from = null, to = null;
		
		if(maze[0][0][0] == 1 && maze[4][4][4] == 1)
		{
			from = new Pos(0, 0, 0);
			to = new Pos(4, 4, 4);
		}
		else if(maze[0][0][4] == 1 && maze[4][4][0] == 1)
		{
			from = new Pos(0, 0, 4);
			to = new Pos(4, 4, 0);
		}
		else if(maze[0][4][0] == 1 && maze[4][0][4] == 1)
		{
			from = new Pos(0, 4, 0);
			to = new Pos(4, 0, 4);
		}
		else if(maze[0][4][4] == 1 && maze[4][0][0] == 1)
		{
			from = new Pos(0, 4, 4);
			to = new Pos(4, 0, 0);
		}
		
		// 입출구가 없다면 이동할 수 없다.
		if(from == null || to == null)
			return Integer.MAX_VALUE;
		
		int minDist = Integer.MAX_VALUE;
		Queue<Pair> q = new LinkedList<Pair>();
		boolean discovered[][][] = new boolean[5][5][5];
		
		q.offer(new Pair(from, 0));
		discovered[from.z][from.x][from.y] = true;
		
		while(!q.isEmpty())
		{
			Pair p = q.poll();
			Pos here = p.pos;
			
			if(here.equals(to))
			{
				minDist = p.dist;
				break;
			}
			
			for(int i = 0 ; i < 6 ; i++)
			{
				int nz = here.z + dz[i];
				int nx = here.x + dx[i];
				int ny = here.y + dy[i];
				
				if(nz < 0 || nz >= 5 || nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || 
						maze[nz][nx][ny] == 0 || discovered[nz][nx][ny])
					continue;
				
				q.offer(new Pair(new Pos(nz, nx, ny), p.dist + 1));
				discovered[nz][nx][ny] = true;
			}
		}
		
		return minDist;
	}
	
	static void rotate(int s, int type)
	{
		if(type == 0 || type == 4)
			return;
		
		int nextPlane[][] = new int[5][5];
		
		switch(type)
		{
			// CW 90, CCW 270
			case 1:
			{
				for(int x = 0 ; x < 5 ; x++)
					for(int y = 0 ; y < 5 ; y++)
						nextPlane[y][(5 - 1) - x] = maze[s][x][y];
				break;
			}
			
			// CW 180, CCW 180
			case 2:
			{
				for(int x = 0 ; x < 5 ; x++)
					for(int y = 0 ; y < 5 ; y++)
						nextPlane[(5 - 1) - x][(5 - 1) - y] = maze[s][x][y];
				break;
			}
			
			// CW 270, CCW 90
			case 3:
			{
				for(int x = 0 ; x < 5 ; x++)
					for(int y = 0 ; y < 5 ; y++)
						nextPlane[(5 - 1) - y][x] = maze[s][x][y];
				break;
			}
		}
		
		maze[s] = nextPlane;
	}
}
