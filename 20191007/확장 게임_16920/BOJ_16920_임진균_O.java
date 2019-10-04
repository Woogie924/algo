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
		int x;
		int y;
		
		public Pos() {}
		public Pos(int x, int y) 
		{
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, P;
	static int S[];
	static char map[][];
	static int remains; // 남아있는 빈 공간의 수
	
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	static Queue<Pos> castles[];
	static int count[];
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		P = Integer.parseInt(tokenizer.nextToken());
		
		S = new int[P + 1];
		tokenizer = new StringTokenizer(in.readLine());
		for(int i = 1 ; i <= P ; i++)
			S[i] = Integer.parseInt(tokenizer.nextToken());
		
		castles = new Queue[P + 1];
		for(int i = 1 ; i <= P ; i++)
			castles[i] = new LinkedList<Pos>();
		
		count = new int[P + 1];
		
		map = new char[N][M];
		remains = N * M;
		for(int i = 0 ; i < N ; i++)
		{
			String temp = in.readLine();
			for(int j = 0 ; j < M ; j++)
			{
				map[i][j] = temp.charAt(j);
				
				if(map[i][j] != '.')
					remains--;
					
				if(map[i][j] >= '1' && map[i][j] <= '9')
				{
					castles[map[i][j] - '0'].offer(new Pos(i, j));
					count[map[i][j] - '0']++;
				}
			}
		}
		
		solve();
		
		for(int p = 1 ; p <= P ; p++)
			out.write(count[p] + " ");
		
		out.flush();
	}
	
	static void solve()
	{
		while(!isDone())
		{
			for(int p = 1 ; p <= P ; p++)
			{
				for(int s = 1 ; s <= S[p] ; s++)
				{
					int qSize = castles[p].size();
					
					if(qSize == 0)
						break;
					
					while(qSize-- > 0)
					{
						Pos pos = castles[p].poll();
						
						for(int d = 0 ; d < 4 ; d++)
						{
							int nx = pos.x + dx[d];
							int ny = pos.y + dy[d];
							
							if(nx < 0 || nx >= N || ny < 0 || ny >= M ||
									map[nx][ny] != '.')
								continue;
							
							castles[p].offer(new Pos(nx, ny));
							map[nx][ny] = (char)('0' + p);
							count[map[nx][ny] - '0']++;
							remains--;
						}
					}
				}
			}
		}
	}
	
	static boolean isDone()
	{
		if(remains == 0)
			return true;
		
		for(int p = 1 ; p <= P ; p++)
			if(castles[p].size() > 0)
				return false;
			
		return true;
	}
}
