import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N;
	static int map[][];
	static int cache[][];
	
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		tokens = new StringTokenizer(in.readLine());
		M = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		
		map = new int[M][N];
		for(int i = 0 ; i < M ; i++)
		{
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++)
				map[i][j] = Integer.parseInt(tokens.nextToken());
		}
		
		cache = new int[M][N];
		for(int i = 0 ; i < M ; i++)
			Arrays.fill(cache[i], -1);
		
		out.write(solve(0, 0) + "");
		out.flush();
	}
	
	// (x, y)에서 (M - 1, N - 1)까지 이동 가능한 경로의 수를 반환한다.
	static int solve(int x, int y)
	{
		if(x == M - 1 && y == N - 1)
			return 1;
		
		if(cache[x][y] != -1)
			return cache[x][y];
		
		int result = 0;
		for(int d = 0 ; d < 4 ; d++)
		{
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || nx >= M || ny < 0 || ny >= N || 
					map[x][y] <= map[nx][ny])
				continue;
			
			result += solve(nx, ny);
		}
		
		return cache[x][y] = result;
	}
}