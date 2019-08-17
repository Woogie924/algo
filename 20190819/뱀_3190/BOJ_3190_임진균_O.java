import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// (x, y) 위치 정보
class Pos 
{
	int x;
	int y;

	public Pos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

// 뱀의 방향 변환 정보 
class DirInfo
{
	int X;
	char C;

	public DirInfo(int X, char C) 
	{
		this.X = X;
		this.C = C;
	}
}

public class Main
{
	static BufferedReader in;
	static BufferedWriter out;

	static int N; // 보드의 크기
	
	static int K; // 사과의 개수
	static int board[][] = new int[100 + 1][100 + 1]; // 보드판
	
	static int L; // 뱀의 방향 변환 횟수
	static Queue<DirInfo> dirInfoQ = new LinkedList<DirInfo>(); // 뱀의 방향 변환 정보들을 가지고 있는 큐
	
	static LinkedList<Pos> snake = new LinkedList<Pos>(); // 뱀의 위치 정보를 가지고 있는 리스트
	static int dir; // 현재 뱀이 향하는 방향, |0:북쪽|1:동쪽|2:남쪽|3:서쪽|
	
	static final int dx[] = {-1, 0, 1, 0}; // 북, 동, 남, 서 순서
	static final int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 보드의 크기를 입력받는다.
		N = Integer.parseInt(in.readLine());
		
		// 사과의 개수와 사과의 위치를 입력받는다.
		K = Integer.parseInt(in.readLine());
		for(int i = 0 ; i < K ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(tokenizer.nextToken());
			int y = Integer.parseInt(tokenizer.nextToken());
			
			board[x][y] = 2;
		}
		
		// 뱀의 방향 변환 횟수와 뱀의 방향 변환 정보들을 입력받는다.
		L = Integer.parseInt(in.readLine());
		for(int i = 0 ; i < L ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			int X = Integer.parseInt(tokenizer.nextToken());
			char C = tokenizer.nextToken().charAt(0);
		
			dirInfoQ.add(new DirInfo(X, C));
		}
		
		// 뱀의 초기 상태를 지정한다.
		snake.addFirst(new Pos(1, 1));
		dir = 1;
		board[1][1] = 1;

		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		int time = 1;
		
		while(true)
		{
			int hx = snake.getFirst().x;
			int hy = snake.getFirst().y;
			int nextHx = hx + dx[dir];
			int nextHy = hy + dy[dir];
			
			// 뱀이 벽과 부딪히거나 자기자신의 몸과 부딪히는 경우
			if(nextHx <= 0 || nextHx > N || nextHy <= 0 || nextHy > N ||
					board[nextHx][nextHy] == 1)
				break;
			
			// 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			snake.addFirst(new Pos(nextHx, nextHy));
			
			// 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
			if(board[nextHx][nextHy] != 2)
			{
				board[snake.getLast().x][snake.getLast().y] = 0;
				snake.removeLast();
			}
			
			// 이동한 뱀의 머리를 지도에 표시한다.
			board[nextHx][nextHy] = 1;
			
			// 방향 전환이 필요하다면 시행한다.
			if(!dirInfoQ.isEmpty() && time == dirInfoQ.peek().X)
			{
				dir = (dirInfoQ.peek().C == 'D' ? (dir + 1) % 4 : (dir + 3) % 4);
				dirInfoQ.poll();
			}
			
			time++;
		}
		
		return time;
	}
}