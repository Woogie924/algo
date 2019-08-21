import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17144_이진호_O
{
	static int R, C, T;
	static int[][] map;
	static int[][] map2;
	static int[][] airCondtion;

	static class Loc
	{
		int x;
		int y;

		public Loc(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		map2 = new int[R][C];
		airCondtion = new int[2][2];// y x 좌표

		for (int i = 0; i < R; i++)
		{
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int acCount = 0;
		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				if (map[i][j] == -1)
				{
					airCondtion[acCount][0] = i;
					airCondtion[acCount++][1] = j;
				}
			}
		}
		
		for (int i = 0; i < T; i++)
		{
			bfs();
			ariConditionOn();
		}
		getDust();
	}

	private static void getDust()
	{
		int result = 0;
		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				if (map[i][j] != -1)
				{
					result += map[i][j];
				}
			}
		}
		System.out.println(result);
	}

	private static void ariConditionOn()// 공기청정기 작동
	{
//		airCondition[0]은 상 [1]은 하 부분
		
		
		windUpper(airCondtion[0]);
		windLower(airCondtion[1]);
	}

	private static void windLower(int[] ac)
	{
		for (int i = ac[0] + 1; i < R-1; i++)// 1방향
		{
			map[i][0] = map[i + 1][0];
		}
		for (int i = 1; i < C; i++)// 2방향
		{
			map[R-1][i - 1] = map[R-1][i];
		}
		for (int i = R-1; i > ac[0]; i--)// 3방향
		{
			map[i][C-1] = map[i-1][C-1];
		}
		for (int i = C-1; i >0 ; i--)// 4방향
		{
			map[ac[0]][i] = map[ac[0]][i-1];
		}

	}

	private static void windUpper(int[] ac)
	{
		for (int i = ac[0] - 1; i > 0; i--)// 1방향
		{
			map[i][0] = map[i - 1][0];
		}
		for (int i = 1; i < C; i++)// 2방향
		{
			map[0][i - 1] = map[0][i];
		}
		for (int i = 0; i <ac[0]; i++)// 3방향
		{
			map[i][C-1] = map[i+1][C-1];
		}
		for (int i = C-1; i > 0; i--)// 4방향
		{
			map[ac[0]][i] = map[ac[0]][i-1];
		}
	}

	static int[] dx =
	{ 1, 0, -1, 0 };// 동남서북
	static int[] dy =
	{ 0, 1, 0, -1 };

	private static void bfs()
	{
		// 미세먼지 큐에 넣어주기
		Queue<Loc> q = new LinkedList<>();

		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				if (map[i][j] > 0)
					q.add(new Loc(j, i));
			}
		}
		int size = q.size();
		Loc temploc;
		
		for (int i = 0; i < size; i++)
		{
			temploc = q.poll();
			spread(temploc, q);
//			q.add(temploc);
		}
		accept();
		for (int j = 0; j < R; j++)
		{
			Arrays.fill(map2[j], 0);
		}
	}

	private static void accept()
	{
		for (int i = 0; i < R; i++)
		{
			for (int j = 0; j < C; j++)
			{
				map[i][j] = map2[i][j];
			}
		}
		
		
	}

	private static void spread(Loc loc, Queue<Loc> q)
	{
		int tx, ty;
		int center = map[loc.y][loc.x];
		int spreadValue = center / 5;
		for (int i = 0; i < 4; i++)
		{
			tx = loc.x + dx[i];
			ty = loc.y + dy[i];
			if (tx < 0 || tx >= C || ty < 0 || ty >= R)
				continue;
			if (tx == airCondtion[0][1] && ty == airCondtion[0][0])
				continue;
			if (tx == airCondtion[1][1] && ty == airCondtion[1][0])
				continue;
			// 확산된곳에 값주고 센터값은 나중에 한번에
			map2[ty][tx] += spreadValue;
			center -= spreadValue;
//			q.add(new Loc(tx, ty));
		}
		map2[loc.y][loc.x] += center;
	}

}
