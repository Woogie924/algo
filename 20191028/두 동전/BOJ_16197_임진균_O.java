import java.io.*;
import java.util.*;

public class Main {
	
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
	
	static final int INF = 987654321;
	
	static int N, M;
	static char board[][];
	static Pos starts[];
	
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		board = new char[N][M];
		starts = new Pos[2];
		
		for(int i = 0 ; i < N ; i++)
		{
			board[i] = in.readLine().toCharArray();
			
			for(int j = 0 ; j < M ; j++)
			{
				if(board[i][j] == 'o')
				{
					board[i][j] = '.';
					
					if(starts[0]== null)
						starts[0] = new Pos(i, j);
					else
						starts[1] = new Pos(i, j);
				}
			}
		}
		
		out.write(solve() + "");
		out.flush();
	}

	static int solve() 
	{
		Queue<Pos[]> q = new LinkedList<Pos[]>();
		int dists[][][][] = new int[N][M][N][M];
		
		for(int i = 0 ; i < N ; i++)
			for(int j = 0 ; j < M ; j++)
				for(int k = 0 ; k < N ; k++)
					for(int l = 0 ; l < M ; l++)
						dists[i][j][k][l] = -1;
		
		q.add(starts);
		setDists(starts, dists, 0);
		
		while(!q.isEmpty())
		{
			Pos coins[] = q.poll();
			int dist = getDists(coins, dists);
			
			if(dist >= 10)
				break;
			
			for(int d = 0 ; d < 4 ; d++)
			{
				Pos nextCoins[] = new Pos[2];
				for(int i = 0 ; i < 2 ; i++)
				{
					nextCoins[i] = new Pos(coins[i].x + dx[d], coins[i].y + dy[d]);
					
					if(inBoundary(nextCoins[i]) && inWall(nextCoins[i]))
						nextCoins[i] = new Pos(coins[i].x, coins[i].y);
				}
				
				if(inBoundary(nextCoins[0]) && inBoundary(nextCoins[1]))
				{
					// 두 동전의 위치가 변하지 않은 경우
					if(compare(coins[0], nextCoins[0]) && compare(coins[1], nextCoins[1]))
						continue;
					
					// 이미 확인했던 곳인 경우
					if(getDists(nextCoins, dists) != -1)
						continue;
				}
				else if(!inBoundary(nextCoins[0]) && !inBoundary(nextCoins[1]))
					continue;
				else
					return dist + 1;
				
				q.add(nextCoins);
				setDists(nextCoins, dists, dist + 1);
			}
		}
		
		return -1;
	}
	
	static boolean inBoundary(Pos p)
	{
		return (p.x >= 0 && p.x < N && p.y >= 0 && p.y < M);
	}
	
	static boolean inWall(Pos p)
	{
		return (board[p.x][p.y] == '#');
	}
	
	static boolean compare(Pos p1, Pos p2)
	{
		return (p1.x == p2.x && p1.y == p2.y);
	}
	
	static int getDists(Pos p[], int dists[][][][])
	{
		return dists[p[0].x][p[0].y][p[1].x][p[1].y];
	}
	
	static void setDists(Pos p[], int dists[][][][], int value)
	{
		dists[p[0].x][p[0].y][p[1].x][p[1].y] = value;
	}
}