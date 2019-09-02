import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출
{
	static int N, M;
	static char[][] box;
	static boolean[][] cl;// current Location
	static Loc redBall;
	static Loc blueBall;
	static Loc goal;

	static class Loc
	{
		int y;
		int x;

		public Loc(int y, int x)
		{
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		box = new char[N][M];
		cl = new boolean[N][M];
		String str;

		for (int y = 0; y < N; y++)
		{
			str = br.readLine();
			for (int x = 0; x < M; x++)
			{
				box[y][x] = str.charAt(x);
				if (str.charAt(x) == 'R')
				{
					redBall = new Loc(y, x);
				} else if (str.charAt(x) == 'B')
				{
					blueBall = new Loc(y, x);
				} else if (str.charAt(x) == 'O')
				{
					goal = new Loc(y, x);
				}
			}
		} // 입력완료

		System.out.println(bfs());

	}

	private static int bfs()
	{
		int result = -1;
		Queue<Loc> redq = new LinkedList<>();
		Queue<Loc> blueq = new LinkedList<>();
		redq.add(redBall);
		blueq.add(blueBall);
		int time = 0;
		Loc rb, bb;
		Loc rbnext;
		Loc bbnext;
		boolean redGoal, blueGoal;
		while (!redq.isEmpty())
		{

			if (time == 10)
				break;
			int size = redq.size();
			time++;
			for (int i = 0; i < size; i++)
			{
				rb = redq.poll();
				bb = blueq.poll();
				st: for (int dir = 0; dir < 4; dir++)
				{
					blueGoal = false;
					redGoal = false;
					// 가는방향에 다른 구슬이 잇나 확인해야됨
					box[rb.y][rb.x] = 'R';
					box[bb.y][bb.x] = 'B';

					rbnext = new Loc(rb.y, rb.x);
					bbnext = new Loc(bb.y, bb.x);

					if (isExist(rb, 'B', dir))// rb의 진행방향에 B가존재시
					{
						if (lean(bbnext, dir))
							blueGoal = true;
						box[bb.y][bb.x] = '.';
						box[bbnext.y][bbnext.x] = 'B';
						if (lean(rbnext, dir))
							redGoal = true;
						box[bbnext.y][bbnext.x] = '.';
						box[rb.y][rb.x] = '.'; 
						
					} else if (isExist(bb, 'R', dir))// bb의 진행방향에 R이 존재시
					{
						if (lean(rbnext, dir))
							redGoal = true;
						box[rb.y][rb.x] = '.';
						box[rbnext.y][rbnext.x] = 'R';
						if (lean(bbnext, dir))
							blueGoal = true;
						box[rbnext.y][rbnext.x] = '.';
						box[bb.y][bb.x] = '.';
					} else
					{
						if (lean(bbnext, dir))
							blueGoal = true;
						if (lean(rbnext, dir))
							redGoal = true;
						box[rb.y][rb.x] = '.';
						box[bb.y][bb.x] = '.';
					}

					if (redGoal || blueGoal)
					{
						if (redGoal&&blueGoal)
							continue st;
						else if (blueGoal)
							continue st;
						else
						{
							return time;
						}
					} else
					{
						blueq.add(bbnext);
						redq.add(rbnext);
					}

				}
			}
		}
		return result;
	}

	private static boolean isExist(Loc ball, char c, int dir)
	{
		int tx = ball.x;
		int ty = ball.y;
		while (true)
		{
			tx += dx[dir];
			ty += dy[dir];
			if (tx < 0 || tx >= M || ty < 0 || ty >= N)
				return false;
			if (box[ty][tx] == c)
				return true;
		}

	}

	private static boolean lean(Loc next, int dir)// true : O에 들어감 false : 안들어감
	{
		boolean result = false;
		int ty, tx;
		ty = next.y + dy[dir];
		tx = next.x + dx[dir];
		while (true)
		{
			if (box[ty][tx] == 'O')
			{
				result = true;
				next.y = 0;
				next.x = 0;
				return result;
			}
			if (box[ty][tx] != '.')
				break;

			ty += dy[dir];
			tx += dx[dir];
		} // 움직이지 않았다면 목표로 가는길이 아예없거나 더빠른길이 존재한다는것을 의미 이건 나중에 반영
			// 좀이따
		next.y = ty - dy[dir];
		next.x = tx - dx[dir];
		return false;
	}

	static int[] dx =
	{ 1, 0, -1, 0 };// 동남서북
	static int[] dy =
	{ 0, 1, 0, -1 };
}
