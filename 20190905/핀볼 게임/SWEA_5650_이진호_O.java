import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution
{
	static int T, N;
	static int[][] map;
	static List<Loc> worm;
	final static int start = -777;

	static class Loc
	{
		int y;
		int x;
		int v;

		public Loc(int y, int x, int v)
		{
			this.y = y;
			this.x = x;
			this.v = v;
		}

	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		int input;
		for (int tc = 1; tc <= T; tc++)
		{
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			worm = new ArrayList<>();

			for (int y = 0; y < N; y++)
			{
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++)
				{

					input = Integer.parseInt(st.nextToken());
					map[y][x] = input;
					if (input >= 6)
						worm.add(new Loc(y, x, input));
				}
			} // 입력완료

			solve();
			System.out.printf("#%d %d\n", tc, max);
		}

	}

	static int[] dx =
	{ 1, 0, -1, 0 }; // 동남서북
	static int[] dy =
	{ 0, 1, 0, -1 };

	private static void solve()
	{
		max = Integer.MIN_VALUE;
		for (int y = 0; y < N; y++)
		{
			for (int x = 0; x < N; x++)
			{
				if (map[y][x] != 0)
					continue;

				for (int dir = 0; dir < 4; dir++)
				{
					map[y][x] = start;
					isEnd = false;
					isStart = true;
					max = Integer.max(ping(y, x, dir, 0), max);
					map[y][x] = 0;
				}
			}
		}
	}

	static int max;
	static int tx, ty;
	static boolean isStart;
	static boolean isEnd;

	private static int ping(int y, int x, int dir, int pc)// 핑볼 하는 함수 ping count
	{
//		map[y][x] = 0;// 확인용

		int temp;
		ty = y;
		tx = x;
		Loc tl;// temp loc
		while (true)
		{
			if (isEnd)
				return pc;
//			show();
			ty += dy[dir];
			tx += dx[dir];
			if (ty < 0 || tx >= N || tx < 0 || ty >= N)
			{
				pc = ping(ty, tx, getDir(dir, 5), pc + 1);
//				break;
			}
			if (map[ty][tx] == -1)
			{
				isEnd = true;
				return pc;
			}
			if (map[ty][tx] == start)
			{
				isEnd = true;
				return pc;
			}
			if (map[ty][tx] > 0)
			{
				if (map[ty][tx] >= 6)
				{
					tl = tellport(ty, tx, map[ty][tx]);
					ty = tl.y;
					tx = tl.x;
				} else if (map[ty][tx] < 6)
				{
					pc = ping(ty, tx, getDir(dir, map[ty][tx]), pc + 1);
				}
//				break;
			}
//			map[ty][tx] = -5;
//			show();
		}
	}

	private static void show()
	{
		System.out.println("========================");
		for (int i = 0; i < N; i++)
		{
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("========================");
	}

	private static Loc tellport(int ty2, int tx2, int num)
	{

		for (Loc loc : worm)
		{
			if (loc.v == num)
			{
				if (loc.y == ty2 && loc.x == tx2)
					continue;
				return loc;
			}
		}
		return null;
	}

	private static int getDir(int dir, int type)
	{
		int rd = -1;// resultDir
		switch (type)
		{
		case 1:
			if (dir == 1)
				rd = 0;
			else if (dir == 2)
				rd = 3;
			else
				rd = (dir + 2) % 4;
			break;
		case 2:
			if (dir == 2)
				rd = 1;
			else if (dir == 3)
				rd = 0;
			else
				rd = (dir + 2) % 4;
			break;
		case 3:
			if (dir == 0)
				rd = 1;
			else if (dir == 3)
				rd = 2;
			else
				rd = (dir + 2) % 4;
			break;
		case 4:
			if (dir == 0)
				rd = 3;
			else if (dir == 1)
				rd = 2;
			else
				rd = (dir + 2) % 4;
			break;
		case 5:
			rd = (dir + 2) % 4;
			break;
		}
		return rd;
	}

}
