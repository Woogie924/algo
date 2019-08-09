import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	static int W, H;
	static int map[][];
	static boolean visited[][];
	
	static final int dx[] = {-1, 0, 1, 0, -1, 1, 1, -1};
	static final int dy[] = {0, 1, 0, -1, 1, 1, -1, -1};
	
	public static void main(String[] args) throws IOException
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 지도 생성
		map = new int[50][50];
		
		// visited 배열 생성
		visited = new boolean[50][50];
		
		while(true)
		{
			// 지도의 크기 w, h 입력
			tokenizer = new StringTokenizer(reader.readLine());
			W = Integer.parseInt(tokenizer.nextToken());
			H = Integer.parseInt(tokenizer.nextToken());
			
			// visited 배열 초기화
			for(int i = 0 ; i < H ; i++)
				Arrays.fill(visited[i], false);			
			
			// 종료 조건
			if(W == 0 && H == 0)
				break;

			// 지도 정보 입력
			for(int i = 0 ; i < H ; i++)
			{
				tokenizer = new StringTokenizer(reader.readLine());
				for(int j = 0 ; j < W ; j++)
					map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}

			writer.write(solve() + "\n");
		}
		
		writer.flush();
	}

	public static int solve() 
	{
		int islands = 0;
		
		for(int x = 0 ; x < H ; x++)
		{
			for(int y = 0 ; y < W ; y++)
			{
				if(map[x][y] == 1 && !visited[x][y])
				{
					islands++;
					visited[x][y] = true;
					dfs(x, y);
				}
			}
		}

		return islands;
	}
	
	public static void dfs(int x, int y)
	{
		for(int dir = 0 ; dir < 8 ; dir++)
		{
			int nextX = x + dx[dir];
			int nextY = y + dy[dir];
			
			if(nextX < 0 || nextX >= H || nextY < 0 || nextY >= W ||
					map[nextX][nextY] == 0 || visited[nextX][nextY])
				continue;
			
			visited[nextX][nextY] = true;
			dfs(nextX, nextY);
		}
	}
}
