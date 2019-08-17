import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader in;
	static BufferedWriter out;
	
	static int N, M; // 세로, 가로 크기
	
	static int x, y, d; // 로봇 청소기의 위치 및 방향
	
	// 0: 청소가 되지 않은 곳
	// 1: 벽
	// 2: 청소가 완료된 곳
	static int map[][]; // 지도 정보
	
	static int dx[] = {-1, 0, 1, 0}; // 북, 동, 남, 서
	static int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 지도 크기 입력
		tokenizer = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		// 초기 위치 및 방향
		tokenizer = new StringTokenizer(in.readLine());
		x = Integer.parseInt(tokenizer.nextToken());
		y = Integer.parseInt(tokenizer.nextToken());
		d = Integer.parseInt(tokenizer.nextToken());
		
		// 지도 정보를 입력받는다.
		map= new int[N][M];
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++)
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
		}
		
		out.write(solve() + "");
		out.flush();
	}

	static int solve()
	{
		while(true)
		{
			// 현재 위치를 청소한다.
			map[x][y] = 2;
			
			boolean allCleaned = true;
			int nextD = -1, nextX = -1, nextY = -1;
			
			// 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
			for(int i = 0 ; i < 4 ; i++)
			{
				nextD = (d + 3) % 4;
				nextX = x + dx[nextD];
				nextY = y + dy[nextD];
				
				// 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진한다.
				if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M &&
						map[nextX][nextY] == 0)
				{
					allCleaned = false;
					break;
				}
				
				d = nextD;
			}
			
			if(!allCleaned)
			{
				d = nextD;
				x = nextX;
				y = nextY;
				continue;
			}
			
			// 4 방향 모두 청소가 끝났다면 후진을 시도한다.
			nextD = (d + 2) % 4;
			nextX = x + dx[nextD];
			nextY = y + dy[nextD];
			
			// 뒤쪽 방향이 벽이 아니라면 후진이 가능하다.
			if(map[nextX][nextY] != 1)
			{
				x = nextX;
				y = nextY;
				continue;
			}
			// 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
			else
				break;
		}

		int count = 0;
		for(int i = 0 ; i < N ; i++)
			for(int j = 0 ; j < M ; j++)
				if(map[i][j] == 2)
					count++;

		return count;
	}
}

