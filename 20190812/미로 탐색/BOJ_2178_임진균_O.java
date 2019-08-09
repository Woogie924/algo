import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	static int N, M;
	static int maze[][];
	static boolean discovered[][];
	
	static final int dx[] = {-1, 0, 1, 0};
	static final int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 미로의 크기 N, M 입력
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		// 미로 생성
		maze = new int[N + 1][M + 1];
		
		// 미로 정보 입력
		for (int i = 1; i <= N; i++) 
		{
			String temp = reader.readLine();
			for (int j = 1; j <= M; j++)
				maze[i][j] = temp.charAt(j - 1) - '0';
		}

		// discovered 배열 생성
		discovered = new boolean[N + 1][M + 1];
		
		writer.write(solve() + "\n");
		
		writer.flush();
	}

	public static int solve() 
	{
		int minDist = -1;
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(1); // x
		q.add(1); // y
		q.add(1); // dist
		discovered[1][1] = true;
		
		while(!q.isEmpty())
		{
			int x = q.poll();
			int y = q.poll();
			int dist = q.poll();
			
			// 종료 조건 : (N, M)에 도달한 경우
			if(x == N && y == M)
			{
				minDist = dist;
				break;
			}
			
			for(int dir = 0 ; dir < 4 ; dir++)
			{
				int nextX = x + dx[dir];
				int nextY = y + dy[dir];
				
				if(nextX < 0 || nextX > N || nextY < 0 || nextY > M ||
						maze[nextX][nextY] == 0 || discovered[nextX][nextY])
					continue;
				
				q.add(nextX);
				q.add(nextY);
				q.add(dist + 1);
				discovered[nextX][nextY] = true;
			}
		}
		
		return minDist;
	}
	
}
