import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos
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

class Main {
	static BufferedReader in;
	static BufferedWriter out;
	
	static int R, C, T; // 가로, 세로 크기 및 경과 시간
	static int room[][] = new int[100][100]; // 방의 상태
	static Pos cleanerTop = null; // 위쪽 공기 청정기
	static Pos cleanerBot = null; // 아래쪽 공기 청정기
	
	static final int dx[] = {0, 1, 0, -1};
	static final int dy[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// R, C, T를 입력받는다.
		tokenizer = new StringTokenizer(in.readLine());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		T = Integer.parseInt(tokenizer.nextToken());
		
		// 방의 상태를 입력받는다.
		for(int x = 0 ; x < R ; x++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int y = 0 ; y < C ; y++)
			{
				room[x][y] = Integer.parseInt(tokenizer.nextToken());

				if(room[x][y] == -1)
				{
					if(cleanerTop == null)
						cleanerTop = new Pos(x, y);
					else
						cleanerBot = new Pos(x, y);
				}
			}
		}
		
		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		for(int time = 1 ; time <= T ; time++)
		{
			// 1. 미세먼지를 확산시킨다.
			spreadDust();
			
			// 2. 공기청정기를 가동한다.
			turnOnCleaner();
		}
		
		// 방에 남아있는 미세먼지의 양을 출력한다.
		int sum = 0;
		for(int x = 0 ; x < R ; x++)
			for(int y = 0 ; y < C ; y++)
				if(room[x][y] > 0)
					sum += room[x][y];
	
		return sum;
	}
	
	// 먼지를 확산시킨다.
	static void spreadDust()
	{
		Queue<Pos> q = new LinkedList<Pos>(); // 먼지의 위치를 가지고 있는 큐
		
		for(int x = 0 ; x < R ; x++)
			for(int y = 0 ; y < C ; y++)
				if(room[x][y] > 0)
					q.add(new Pos(x, y));

		// 확산 후 방의 상태를 저장할 배열을 생성한다.
		int nextRoom[][] = new int[R][C];
		
		// 실제로 확산시켜본다.
		while(!q.isEmpty())
		{
			Pos pos = q.poll();
			int r = pos.x;
			int c = pos.y;

			ArrayList<Pos> nextDusts = new ArrayList<Pos>();
			for(int d = 0 ; d < 4 ; d++)
			{
				int nextX = r + dx[d];
				int nextY = c + dy[d];
				
				// 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
				if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C || 
						room[nextX][nextY] == -1)
					continue;

				
				Pos next = new Pos(nextX, nextY);
				nextDusts.add(next);
			}
			
			int count = nextDusts.size();
			for(int j = 0 ; j < count ; j++)
			{
				int x = nextDusts.get(j).x;
				int y = nextDusts.get(j).y;
				
				nextRoom[x][y] += (room[r][c] / 5);
			}
			
			nextRoom[r][c] += (room[r][c] - (room[r][c] / 5) * count);
		}
		
		// 확산 후 방의 상태로 갱신한다.
		for(int x = 0 ; x < R ; x++)
			for(int y = 0 ; y < C ; y++)
				room[x][y] = nextRoom[x][y];
					
		room[cleanerTop.x][cleanerTop.y] = -1;
		room[cleanerBot.x][cleanerBot.y] = -1;
	}
	
	// 공기청정기를 가동한다.
	static void turnOnCleaner()
	{
		LinkedList<Integer> amounts = new LinkedList<Integer>();
		
		getDustAmounts(0, cleanerTop, 0, amounts);
		amounts.removeLast(); // 마지막 위치는 공기청정기로 인해 정화되므로 필요없다.
		amounts.add(1, 0); // 정화된 공기를 가장 앞에 추가한다.
		setDustAmounts(0, cleanerTop, 0, amounts);
		
		getDustAmounts(1, cleanerBot, 0, amounts);
		amounts.removeLast(); // 마지막 위치는 공기청정기로 인해 정화되므로 필요없다.
		amounts.add(1, 0); // 정화된 공기를 가장 앞에 추가한다.
		setDustAmounts(1, cleanerBot, 0, amounts);
	}
	
	// 공기청정기의 경로에 있는 먼지의 양들을 기록한다.
	static void getDustAmounts(int from, Pos pos, int d, LinkedList<Integer> amounts)
	{
		if(room[pos.x][pos.y] == -1 && amounts.size() != 0)
			return;
		
		amounts.addLast(room[pos.x][pos.y]);
		
		int nextX = pos.x + dx[d];
		int nextY = pos.y + dy[d];
		
		if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C)
		{
			// 위쪽 공기청정기
			if(from == 0)
				d = (d + 3) % 4;
			
			// 아래쪽 공기청정기
			else
				d = (d + 1) % 4;
			
			nextX = pos.x + dx[d];
			nextY = pos.y + dy[d];
		}
		
		pos.x = nextX;
		pos.y = nextY;
		getDustAmounts(from, pos, d, amounts);
	}
	
	// 공기청정기의 경로에 있는 먼지의 양들을 새로 갱신한다.
	static void setDustAmounts(int from, Pos pos, int d, LinkedList<Integer> amounts) {
		if (room[pos.x][pos.y] == -1 && amounts.size() == 0)
			return;

		room[pos.x][pos.y] = amounts.removeFirst();

		int nextX = pos.x + dx[d];
		int nextY = pos.y + dy[d];

		if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) {
			// 위쪽 공기청정기
			if (from == 0)
				d = (d + 3) % 4;

			// 아래쪽 공기청정기
			else
				d = (d + 1) % 4;

			nextX = pos.x + dx[d];
			nextY = pos.y + dy[d];
		}

		pos.x = nextX;
		pos.y = nextY;
		setDustAmounts(from, pos, d, amounts);
	}
}