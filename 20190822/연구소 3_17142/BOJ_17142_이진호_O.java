import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소3
{

	static class Loc
	{
		int y;
		int x;

		public Loc()
		{
			x = -1;
			y = -1;
		}

		public Loc(int y, int x)
		{
			this.y = y;
			this.x = x;
		}
	}

	static int N, M;
	static int[][] map;
	static boolean[][] discovered;
	static List<Loc> virusList;
	static int left;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		discovered = new boolean[N][N];
		;
//		virusList = new Loc[20];// M보다 크고 10보다 작거나 같은자연수
		virusList = new ArrayList<Loc>();
		int wallCount = 0;

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());// 0 빈칸 1 벽 2 바이러스
				if (map[i][j] == 1)// 원래 wallcount는 벽갯수지만 바이러스까지 같이세줘야겟다
					wallCount++;// 입력받은게 1이면 wallcount 일더해줌
				if (map[i][j] == 2)// 바이러스 놓을수 있는 자리는 list에 추가
				{
					wallCount++;
					virusList.add(new Loc(i, j));
				}
			}
		}
		left = N * N - wallCount;// left는 가야될 빈칸갯수이므로 전체에서 바이러스와 벽개수를 빼고 M개는 더해준다.
		selectVirus(new boolean[20], 0, 0, new int[20]);
		if(ischange) System.out.println(min);
		else System.out.println(-1);
	}

	static int min = Integer.MAX_VALUE;
	static boolean ischange;
	static private void selectVirus(boolean[] visited, int depth, int start, int[] selected)// 조합함수
	{
		if (depth == M)
		{
			for (int i = 0; i < N; i++)
			{
				Arrays.fill(discovered[i], false);
			}
			int next = spreadVirus(selected);
			if(next <0) return;
			if (min > next)// 선택된 바이러스 인덱스 ;조심 나온값이 더 작으면
			{
				min = next;
				ischange = true;
			}
//			System.out.println(Arrays.toString(selected));
			return;
		}
		for (int i = start; i < virusList.size(); i++)
		{
			if (!visited[i])
			{
				selected[depth] = i;
				visited[i] = true;
				selectVirus(visited, depth + 1, i, selected);
				visited[i] = false;
			}
		}
	}

	static int[] dy =
	{ 0, 1, 0, -1 };// 동남서북
	static int[] dx =
	{ 1, 0, -1, 0 };

	static private int spreadVirus(int[] selected)// selected애들로 bfs
	{
		boolean isComplete = false;
		// 기준 넘어갔으면 안해도된다.
		int goal = 0;

		Queue<Loc> q = new LinkedList<Loc>();
		for (int i = 0; i < M; i++)
		{
			Loc loc = virusList.get(selected[i]);
			q.add(loc);
			discovered[loc.y][loc.x] = true;
		}
		Loc loc;
		int tx, ty;
		int time = 0;
		if (goal == left)
			return time;// 맨처음 바이러스가 가득할때
		while (!q.isEmpty())
		{
			if (goal >= left)
			{
				isComplete =true;
				break;
			}
			if(time>=min)
				return time;
			time++;
			int size = q.size();

//			if(time>=min) break;//현재걸리는시간이 min보다 크거나 같다면 의미가 없다.
			for (int i = 0; i < size; i++)
			{
				loc = q.poll();
				for (int j = 0; j < 4; j++)
				{
					tx = loc.x + dx[j];
					ty = loc.y + dy[j];
					if (tx < 0 || tx >= N || ty < 0 || ty >= N)
						continue;
					if (map[ty][tx] == 1)
						continue;
					if (!discovered[ty][tx])
					{
						discovered[ty][tx] = true;
						if (map[ty][tx] == 0)
							goal++;
						q.add(new Loc(ty, tx));
						// goal ==

					}
				}
			}
		
		}
		if(isComplete) return time;
		else return -1;
	}
}
