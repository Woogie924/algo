import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16197_이진호_O
{
	static int N, M;
	static int[][] map;

	static class Loc
	{
		int y;
		int x;

		public Loc(int y, int x)
		{
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString()
		{
			return "Loc [y=" + y + ", x=" + x + "]";
		}

	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		String is;
		Queue<Loc> q = new LinkedList<>();

		for (int y = 0; y < N; y++)
		{
			is = br.readLine();
			for (int x = 0; x < M; x++)
			{
				if (is.charAt(x) == '#')
				{
					map[y][x] = -1;
				} else if (is.charAt(x) == 'o')
				{
					q.add(new Loc(y, x));
				}
			}
		}
		Loc c1, c2, c;
		Loc r1,r2,r;
		r1 = null; r2 = null;
		int oc = 0;// outCount
		int depth = 0;
		int tx, ty;
		st:while (!q.isEmpty())
		{
			depth++;
			if (depth == 11)
				break;
			int size = q.size() / 2;
			for (int i = 0; i < size; i++)
			{
				c1 = q.poll();
				c2 = q.poll();
				for (int dir = 0; dir < 4; dir++)
				{
					oc = 0;
					for (int j = 0; j < 2; j++)
					{
						if (j % 2 == 0)
						{
							c = c1;
						}
						else
						{
							c = c2;
						}
						ty = c.y+dy[dir];
						tx = c.x+dx[dir];
						if(ty<0||ty>=N||tx<0||tx>=M) continue;
						if(map[ty][tx]==-1)
						{
							r = new Loc(c.y,c.x);
						}
						else
						{
							r = new Loc(c.y+dy[dir],c.x+dx[dir]);
						}
						if (j % 2 == 0)
						{
							r1 = r;
						}
						else
						{
							r2 = r;
						}
						oc++;
					}
					if(oc==1) break st;
					else if(oc==0) continue;
					else
					{
						q.add(r1); q.add(r2);
					}
				}

			}
		}
		if(depth==11) System.out.println(-1);
		else System.out.println(depth);
	}

	static int[] dx =
	{ 1, 0, -1, 0 };
	static int[] dy =
	{ 0, 1, 0, -1 };

}
