import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Fish
{
	int x;
	int y;
	int s; // 크기
	
	public Fish() {}
	public Fish(int x, int y, int s) 
	{
		this.x = x;
		this.y = y;
		this.s = s;
	}
}

class Main {
	static BufferedReader in;
	static BufferedWriter out;

	static int N; // 공간의 크기
	static int map[][] = new int[20][20];

	static Fish shark;
	
	static final int dx[] = {-1, 0, 1, 0};
	static final int dy[] = {0, 1, 0, -1};
	
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;

		N = Integer.parseInt(in.readLine());
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++)
			{
				map[i][j] = Integer.parseInt(tokenizer.nextToken());

				if(map[i][j] == 9)
					shark = new Fish(i, j, 2);
			}
		}
		
		out.write(solve() + "");
		out.flush();
	}

	static int solve()
	{
		int time = 0;
		int eat = 0; // 상어가 먹은 물고기의 수
		
		while(true)
		{
			// 다음에 먹을 물고기를 찾는다.
			int baitX = N + 1, baitY = N + 1; // 다음 희생양의 위치
			int minDist = Integer.MAX_VALUE; // 희생양까지의 거리
			
			boolean discovered[][] = new boolean[20][20];
			Queue<Integer> q = new LinkedList<Integer>();

			q.add(shark.x);
			q.add(shark.y);
			q.add(0);
			discovered[shark.x][shark.y] = true;
			
			while(!q.isEmpty())
			{
				int x = q.poll();
				int y = q.poll();
				int dist = q.poll();
				
				if(dist > minDist)
					continue;
				
				// 현재 좌표가 먹을 수 있는 물고기인 경우
				if(map[x][y] > 0 && map[x][y] < 9 && map[x][y] < shark.s)
				{
					if(dist < minDist)
					{
						minDist = dist;
						baitX = x;
						baitY = y;
					}
					else if(dist == minDist)
					{
						if(x < baitX)
						{
							baitX = x;
							baitY = y;
						}
						// 가장 위에 있는 물고기가 여러마리라면...
						else if(x == baitX)
							baitY = (y < baitY ? y : baitY);
					}
				}
				
				for(int dir = 0 ; dir < 4 ; dir++)
				{
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N || 
							map[nx][ny] > shark.s || discovered[nx][ny])
						continue;
						 
					q.add(nx);
					q.add(ny);
					q.add(dist + 1);
					discovered[nx][ny] = true;
				}
			}
			
			// 더 이상 먹을 수 있는 물고기가 공간에 없다면 엄마 상어에게 도움을 요청한다.
			if(minDist == Integer.MAX_VALUE)
				break;
			
			// 물고기를 먹는다.
			map[shark.x][shark.y] = 0;
			map[baitX][baitY] = 9;
			shark.x = baitX;
			shark.y = baitY;
			eat++;
			if(eat == shark.s)
			{
				shark.s++;
				eat = 0;
			}
			
			time += minDist;
		}

		return time;
	}
}