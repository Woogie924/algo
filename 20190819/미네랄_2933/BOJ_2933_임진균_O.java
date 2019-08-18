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
	
	public Pos(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static BufferedReader in;
	static BufferedWriter out;
	
	static int R, C; // 동굴의 크기
	static char cave[][] = new char[100][100]; // 동굴
	
	static int N; // 막대를 던진 횟수
	static ArrayList<Integer> heights = new ArrayList<Integer>(); // 막대를 던진 높이
	
	static final int dx[] = {-1, 0, 1, 0};
	static final int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 동굴의 크기를 입력 받는다.
		tokenizer = new StringTokenizer(in.readLine());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken()); 
		
		// 동굴의 상태 입력 받는다.
		for(int i = 0 ; i < R ; i++)
		{
			String temp = in.readLine();
			for(int j = 0 ; j < C ; j++)
				cave[i][j] = temp.charAt(j);
		}
		
		// 막대를 던진 횟수와 각각의 높이를 입력 받는다. 
		N = Integer.parseInt(in.readLine());
		tokenizer = new StringTokenizer(in.readLine());
		for(int i = 0 ; i < N ; i++)
			heights.add(R - Integer.parseInt(tokenizer.nextToken()));
		
		solve();
		
		for(int i = 0 ; i < R ; i++)
		{
			for(int j = 0 ; j < C ; j++)
				out.write(cave[i][j] + "");
			out.newLine();
		}
		
		out.flush();
	}
	
	static void solve()
	{
		int size = heights.size();
		for(int i = 0 ; i < size ; i++)
		{
			// 0 : 왼쪽 -> 오른쪽
			// 1 : 오른쪽 -> 왼쪽
			int to = (i % 2 == 0 ? 0 : 1);
			int height = heights.get(i);

			// 상대를 향해 막대기를 던진다.
			Pos brokenPos = attack(to, height);

			// 만약, 부서진 미네랄이 존재한다면
			// 인접한 클러스터를 확인해서 중력 효과를 적용한다.
			if (brokenPos.y != -1)
				fallCluster(brokenPos);
		}
	}
	
	// to에게 height 높이로 막대를 던졌을 때, 
	// 부서지는 미네랄의 좌표를 반환한다.
	static Pos attack(int to, int height)
	{
		int y = -1;
		
		// 왼쪽 -> 오른쪽
		if(to == 0)
		{
			for(int j = 0 ; j < C ; j++)
			{
				if(cave[height][j] == 'x')
				{
					cave[height][j] = '.';
					y = j;
					break;
				}
			}
		}
		// 오른쪽 -> 왼쪽
		else
		{
			for(int j = C - 1 ; j >= 0 ; j--)
			{
				if(cave[height][j] == 'x')
				{
					cave[height][j] = '.';
					y = j;
					break;
				}
			}
		}
		
		return new Pos(height, y);
	}

	static void fallCluster(Pos brokenPos)
	{	
		boolean discovered[][] = new boolean[100][100];

		// 미네랄이 파괴된 곳에서 상, 하, 좌, 우로 인접한 클러스터를 확인한다.
		for(int dir = 0 ; dir < 4 ; dir++)
		{
			int startX = brokenPos.x + dx[dir];
			int startY = brokenPos.y + dy[dir];

			// 인접한 방향에 클러스터가 없거나
			// 이미 확인한 클러스터를 또 확인하려고 하는 경우
			if (startX < 0 || startX >= R || startY < 0 || startY >= C ||
					cave[startX][startY] != 'x' || discovered[startX][startY])
					continue;
			
			// 1. 클러스터를 찾는다.
			ArrayList<Pos> cluster = findCluster(startX, startY, discovered);
			
			// 2. 지도에서 현재 클러스터를 지운다.
			int size = cluster.size();
			for (int i = 0; i < size; i++) 
			{
				int x = cluster.get(i).x;
				int y = cluster.get(i).y;
				cave[x][y] = '.';
			}
			
			// 3. 클러스터가 최대 몇 칸 내려갈 수 있는지 확인한다.
			int maxMove = 0;
			for (int move = 1; move <= R; move++)
			{
				boolean possible = true;

				for (int i = 0; i < size; i++)
				{
					int nextX = cluster.get(i).x + move;
					int nextY = cluster.get(i).y;

					if (nextX >= R || cave[nextX][nextY] == 'x')
					{
						possible = false;
						break;
					}
				}

				if (!possible)
				{
					maxMove = move - 1;
					break;
				}
			}
			
			// 4. 새로운 클러스터의 위치를 지도에 표시한다.
			for (int i = 0; i < size; i++)
			{
				int x = cluster.get(i).x + maxMove;
				int y = cluster.get(i).y;
				cave[x][y] = 'x';
			}
			
			// 5. 클러스터가 1칸 이상 움직였다면 반복문을 탈출한다.
			if (maxMove > 0)
				break;
		}		
	}
	
	// (startX, startY) 지점의 미네랄을 포함하는 클러스터를 찾아서 반환한다.
	static ArrayList<Pos> findCluster(int startX, int startY, boolean discovered[][])
	{
		ArrayList<Pos> cluster = new ArrayList<Pos>();
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(startX);
		q.offer(startY);
		discovered[startX][startY] = true;
		
		while(!q.isEmpty())
		{
			int x = q.poll();
			int y = q.poll();
			
			cluster.add(new Pos(x, y));
			
			for(int dir = 0 ; dir < 4 ; dir++)
			{
				int nextX = x + dx[dir];
				int nextY = y + dy[dir];
				
				if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C ||
						cave[nextX][nextY] != 'x' || discovered[nextX][nextY])
					continue;
				
				q.offer(nextX);
				q.offer(nextY);
				discovered[nextX][nextY] = true;
			}
		}
		
		return cluster;
	}
}