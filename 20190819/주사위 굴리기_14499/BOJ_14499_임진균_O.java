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
	static int x, y; // 주사위의 위치
	static int K; // 명령어의 개수
	static int commands[]; // 명령어들
	static int map[][]; // 지도
	
	// 주사위 배열 구조
	// --------------------------------------------------
	// |0       |1      |2      |3      |4      |5      |
	// --------------------------------------------------
	// |Up      |Down   |Forward|Back   |Left   |Right  |
	// --------------------------------------------------
	static int dice[] = new int[6];
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// N, M, x, y, K 정보 입력
		tokenizer = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		x = Integer.parseInt(tokenizer.nextToken());
		y = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
	
		// 지도 정보 입력
		map = new int[N][M];
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++)
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
		}
		
		// 명령어 입력
		commands = new int[K];
		tokenizer = new StringTokenizer(in.readLine());
		for(int i = 0 ; i < K ; i++)
			commands[i] = Integer.parseInt(tokenizer.nextToken());
		
		out.write(solve());
		out.flush();
	}
	
	static String solve()
	{
		String ret = new String();
		
		int size = commands.length;
		for(int i = 0 ; i < size ; i++)
		{
			// 명령어의 방향으로 주사위를 한 칸 굴린다.
			boolean success = roll(commands[i]);
			
			// 주사위가 정상적으로 굴려진 경우
			if(success)
			{
				// 이동한 칸에 쓰여 있는 수가 0인 경우.
				if(map[x][y] == 0)
					copyTo();
				else
					copyFrom();
				
				// 주사위의 상단을 출력한다.
				ret += (dice[0] + "\n");
			}
		}
		
		return ret;
	}
	
	// 주사위를 dir 방향으로 굴린다.
	// 정상적으로 굴려졌는지 여부를 반환한다.
	static boolean roll(int dir)
	{
		switch (dir) 
		{
			// 동쪽 : 위쪽 -> 오른쪽 -> 아래쪽 -> 왼쪽 -> 위쪽
			case 1 : 
			{
				int nextY = y + 1;
				if(nextY < 0 || nextY >= M)
					return false;
				
				int temp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[1];
				dice[1] = dice[5];
				dice[5] = temp;
				
				y = nextY;
				break;
			}
			// 서쪽 : 위쪽 -> 왼쪽 -> 아래쪽 -> 오른쪽 -> 위쪽
			case 2 : 
			{
				int nextY = y - 1;
				if(nextY < 0 || nextY >= M)
					return false;
				
				int temp = dice[0];
				dice[0] = dice[5];
				dice[5] = dice[1];
				dice[1] = dice[4];
				dice[4] = temp;
				
				y = nextY;
				break;
			}
			// 북쪽 : 위쪽 -> 뒤쪽 -> 아래쪽 -> 앞쪽 -> 위쪽
			case 3 : 
			{
				int nextX = x - 1;
				if(nextX < 0 || nextX >= N)
					return false;
				
				int temp = dice[0];
				dice[0] = dice[2];
				dice[2] = dice[1];
				dice[1] = dice[3];
				dice[3] = temp;
				
				x = nextX;
				break;
			}
			// 남쪽 : 위쪽 -> 앞쪽 -> 아래쪽 -> 뒤쪽 -> 위쪽
			case 4 : 
			{
				int nextX = x + 1;
				if(nextX < 0 || nextX >= N)
					return false;
				
				int temp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[1];
				dice[1] = dice[2];
				dice[2] = temp;
				
				x = nextX;
				break;
			}
		}
		
		return true;
	}
	
	// 주사위의 바닥면에 쓰여 있는 수를 칸에 복사한다.
	static void copyTo()
	{
		map[x][y] = dice[1];
	}
	
	// 칸에 쓰여 있는 수를 주사위의 바닥면으로 복사한다.
	// 또한, 칸에 쓰여 있는 수를 0으로 바꾼다.
	static void copyFrom()
	{
		dice[1] = map[x][y];
		map[x][y] = 0;
	}
	
}