import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2151_이진호_O {
	static int N;
	static char[][] map;

	static class Loc {
		int y;
		int x;
		int d;

		@Override
		public String toString() {
			return "Loc [y=" + y + ", x=" + x + ", d=" + d + "]";
		}

		public Loc(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}

	}

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		char tc;

		int ti = 0;
		Loc[] door = new Loc[2];
		map = new char[N][N];
		visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		for (int y = 0; y < N; y++) {
			String input = sc.next();
			for (int x = 0; x < N; x++) {
				tc = input.charAt(x);
				if (tc == '#') {
					door[ti++] = new Loc(y, x, -1);
				}
				map[y][x] = tc;
			}
		}
		Loc cur;
		char next = 0;
		// 한 거울에 두번 가는 경우는 최솟값이 아니다.
		Queue<Loc> q = new LinkedList<Loc>();
		boolean resultFlag = false;
		int result = Integer.MAX_VALUE;
		int tx, ty;
		for (int dir = 0; dir < 4; dir++) {
			cur = door[0];
			/*
			 * cur.y+=dy[dir]; cur.x+=dx[dir];
			 */
			cur.d = dir;
			q.add(cur);
			int level = -1;
			resultFlag = false;
			end: while (!q.isEmpty()) {
				level++;
				int size = q.size();
				for (int i = 0; i < size; i++) {
					cur = q.poll();
					/*
					 * cur.y += dy[dir]; cur.x += dx[dir]; next = map[cur.y][cur.x];
					 */
					ty = cur.y;
					tx = cur.x;
					while (true) {
						ty += dy[cur.d];
						tx += dx[cur.d];
						if (ty < 0 || ty >= N || tx < 0 || tx >= N)
							break;
						next = map[ty][tx];
						if (next == '!') {
							if (visited[ty][tx] > level) {
								visited[ty][tx] = level;
							} else {
								continue;
							}
							q.add(new Loc(ty, tx, (cur.d + 4 - 1) % 4));
							q.add(new Loc(ty, tx, (cur.d + 1) % 4));
						} else if (next == '*')
							break;
						else if (next == '#') {
							if (ty == door[1].y && tx == door[1].x) {
								resultFlag = true;
								q.clear();
								break end;
							} else
								break;
						}
					}
				}
			}
			if (resultFlag)
				result = result > level ? level : result;
		}
		System.out.println(result);

	}

}
