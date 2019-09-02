import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main
{
	static int N, M;// 행수 열수
	static char[][] map;
	// 뿌요종류 다섯개 R G B P Y // .은 빈공간

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 12;
		M = 6;
		map = new char[N][M];

		for (int y = 0; y < N; y++)
		{
			map[y] = br.readLine().toCharArray();
		} // 입력완료

		int level = 0;
		visited = new boolean[N][M];
		while (true)
		{
			if (!bombPuyo())
				break;
//			show();
			downPuyo();
//			show();
			level++;
			clearVisited();
			
		}
		System.out.println(level);
	}

	private static void show()
	{
		System.out.println("====================");
		for (int y = 0; y < N; y++)
		{
			System.out.println(Arrays.toString(map[y]));
		}
		System.out.println("====================");
	}

	private static void clearVisited()
	{
		for (int y = 0; y < N; y++)
		{
			Arrays.fill(visited[y], false);
		}
	}

	private static void downPuyo()// 뿌요 다터지고 떨어트리는 함수
	{
		for (int x = 0; x < M; x++)
		{
			for (int y = N - 1; y >= 0; y--)
			{
				if (map[y][x] != '.')
				{
					down1row(y, x);
					break;
				}
			}
		}

	}

/*	private static void down1row(int y, int x)
	{
		int maxIdx = -1;
		for (int i = y; i >= 0; i--)
		{
			if (map[i][x] == '.')
			{
				maxIdx = i + 1;
				break;
			}
		}
		int diff = N - y - 1;
		for (int i = y; i >= maxIdx; i--)
		{
			map[i + diff][x] = map[i][x];
			map[i][x] = '.';
		}
	}*/

	private static void down1row(int y, int x)
	{
		boolean island = true;
		boolean isChange = false;
		for(int ty = N-1 ; ty>=0 ; ty--)
		{
			if(map[ty][x]!='.')
			{
				island = true;
				isChange = false;
				for(int tty = ty+1 ; tty<N;tty++)
				{
					if(map[tty][x]!='.')
					{
						if(tty-1==ty) break;
						map[tty-1][x] = map[ty][x];
						map[ty][x] = '.';
						island = false;
						break;
					}
					isChange = true;//밑에 한칸이라도 빈칸잇는경우 ischange는 true
				}
				if(island&&isChange)//island는 이미 무엇을 만나 내려간경우는 이조건문에 걸리지 않는다.
				{
					map[N-1][x] = map[ty][x];
					map[ty][x] = '.';
				}
			}
		}
	}
	static boolean[][] visited;

	private static boolean bombPuyo()// 맵전체를 돌면서 뿌요여부 확인후 터트리는 함수
	{
		boolean result = false;
		char kind;
		Loc loc;
		for (int y = 0; y < N; y++)
		{
			for (int x = 0; x < M; x++)
			{
				if (map[y][x] != '.')
				{
					if (visited[y][x])
						continue;
					kind = map[y][x];
					visited[y][x] = true;
					List<Loc> list = new ArrayList<Loc>();
					list.add(new Loc(y, x));
					linkPuyo(y, x, kind, list);// 뿌요를 하나찾은경우 인접한 뿌요그룹을 찾음
					if (list.size() >= 4)
					{
						for (int i = 0; i < list.size(); i++)
						{
							loc = list.get(i);
							map[loc.y][loc.x] = '.';
							result = true;
						}
					}
				}
			}
		}
		return result;
	}

	static int[] dx =
	{ 1, 0, -1, 0 };// 동남서북
	static int[] dy =
	{ 0, 1, 0, -1 };

	private static void linkPuyo(int y, int x, int kind, List<Loc> list)// y , x, 종류
	{
		// 네개이상되면 터트려야한다.
		// 기저조건 딱히...
		int ty, tx;
		for (int dir = 0; dir < 4; dir++)
		{
			ty = y + dy[dir];
			tx = x + dx[dir];
			if (ty < 0 || ty >= N || tx < 0 || tx >= M)
				continue;
			if (map[ty][tx] != kind)
				continue;
			if (visited[ty][tx])
				continue;

			visited[ty][tx] = true;
			list.add(new Loc(ty, tx));
			linkPuyo(ty, tx, kind, list);
		}
	}

	static class Loc
	{
		int y;
		int x;

		public Loc(int y, int x)
		{
			this.y = y;
			this.x = x;
		}

	}
}
