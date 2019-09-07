import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution
{
	static int T, N, M, K;
	static int[][] map;
	static List<Cell> lc;// live cell

	static class Cell
	{
		int y;
		int x;
		int life;
		int cl; // continue life

		public Cell(int y, int x, int life)
		{
			this.y = y;
			this.x = x;
			this.life = life;
			cl = life - 1;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++)
		{
			map = new int[1000][1000];
			StringTokenizer st;

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int input = -1;
			lc = new LinkedList<Solution.Cell>();
			for (int y = 500; y < 500 + N; y++)
			{
				st = new StringTokenizer(br.readLine());
				for (int x = 500; x < 500 + M; x++)
				{
					input = Integer.parseInt(st.nextToken());
					if(input==0) continue;
					map[y][x] = input+1;
					lc.add(new Cell(y, x, map[y][x]));
				}
			}
//			show(10);
//			show(5);
			for (int time = 0; time < K; time++)
			{
//				System.out.println("========="+(time+1)+"=========");
				next();
//				show(10);
			}
			System.out.printf("#%d %d\n", tc, lc.size());
		}
	}

	static int[] dx =
	{ 1, 0, -1, 0 };// 동남서북
	static int[] dy =
	{ 0, 1, 0, -1 };

	private static void next()
	{
		Cell cell;
		List<Cell> tl = new LinkedList<Solution.Cell>();
		int tx, ty;
		int size = lc.size();
		int tlsize = -1;
		boolean ismake = false;
		for (int i = 0; i < size; i++)// 세포마다 라이프 1깍고 0이되면 번식시키는 반복문
		{
			cell = lc.get(i);
			cell.life--;
			if (cell.life <= 0)// 번식리스트에 추가 활성화가 됫다.
			{
				cell.cl--;
				for (int dir = 0; dir < 4; dir++)
				{
					ty = cell.y + dy[dir];
					tx = cell.x + dx[dir];
					if (map[ty][tx] == 0)
					{
						ismake = true;
						for (Cell c : tl)
						{
							if (c.x == tx && c.y == ty)// 같은공간안에 줄기세포가 배양되면
							{
								ismake = false;
								if (c.life < map[cell.y][cell.x])// 라이프 큰걸로준다.
								{
									c.life = map[cell.y][cell.x];
									c.cl = c.life-1;
								}
								break;
							}
						}
						if(ismake)tl.add(new Cell(ty, tx, map[cell.y][cell.x]));// 같은게 없다면 tl에 해당 세포를 넣어준다.
					}
				}
			}
		}
		
		Iterator<Cell> it = lc.iterator();
		while(it.hasNext())
		{
			cell = it.next();
			
			if (cell.cl <= 0)
				it.remove();
		
		}
//		size = lc.size();
//		for (int i = 0; i < size; i++) // 0인 애들 삭제
//		{
//			cell = lc.get(i);
//			if (cell.cl <= 0)
//			{
//				lc.remove(cell);
//				i--;
//				size--;
//			}
//		}
		tlsize = tl.size();
		for (int i = 0; i < tlsize; i++) // 번식된애들 리스트에 넣어준다.
		{
			cell = tl.get(i);
//			show(5);
			map[cell.y][cell.x] = cell.life;
			lc.add(new Cell(cell.y,cell.x,cell.life));
		}
	}

	private static void show(int i)
	{
		System.out.println("================================");
		for (int y = 500 - i; y < 500 + i; y++)
		{
			for (int x = 500 - i; x < 500 + i; x++)
			{
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println("================================");
	}
}
