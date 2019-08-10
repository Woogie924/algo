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
	
	static int M, N;
	static int box[][];
	static Queue<Integer> q = new LinkedList<Integer>();
	static boolean discovered[][];
	
	static final int dx[] = {-1, 0, 1, 0};
	static final int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 상자의 크기 N, M 입력
		tokenizer = new StringTokenizer(reader.readLine());
		M = Integer.parseInt(tokenizer.nextToken());
		N = Integer.parseInt(tokenizer.nextToken());

		// 상자 생성
		box = new int[N][M];
		
		// discovered 배열 생성
		discovered = new boolean[N][M];
		
		// 상자안의 토마토 상태 입력받는다.
		// 만약, 토마토가 익었다면 BFS의 시작 상태로 지정한다.
		for (int i = 0; i < N; i++) 
		{
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < M; j++)
			{
				box[i][j] = Integer.parseInt(tokenizer.nextToken());
				
				if(box[i][j] == 1)
				{
					q.add(i); // 익은 토마토의 x좌표
					q.add(j); // 익은 토마토의 y좌표
					q.add(0); // 경과된 날짜
					discovered[i][j] = true;
				}
			}
		}

		writer.write(solve() + "\n");
		
		writer.flush();
	}

	public static int solve() 
	{
		int minDays = -1;
		
		// 전이를 완료시킨다.
		while(!q.isEmpty())
		{
			int x = q.poll();
			int y = q.poll();
			int days = q.poll();
			
			minDays = Math.max(minDays, days);
			
			for(int dir = 0 ; dir < 4 ; dir++)
			{
				int nextX = x + dx[dir];
				int nextY = y + dy[dir];
				
				if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M ||
						box[nextX][nextY] == -1 || discovered[nextX][nextY])
					continue;
				
				q.add(nextX);
				q.add(nextY);
				q.add(days + 1);
				discovered[nextX][nextY] = true;
				box[nextX][nextY] = 1;
			}
		}
		
		// 상자안의 토마토가 모두 익었는지 확인한다.
		int unripe = 0;
		for(int x = 0 ; x < N ; x++)
			for(int y = 0 ; y < M ; y++)
				if(box[x][y] == 0)
					unripe++;
		
		return (unripe > 0 ? -1 : minDays);
	}
	
}
