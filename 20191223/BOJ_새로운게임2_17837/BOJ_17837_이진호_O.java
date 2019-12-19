import java.util.Scanner;

public class BOJ_17837_이진호_O {
	static int[][] map;
	static int N, K;
	static Horse[] hs;
	static int[][] visited;

	static class Horse {
		public Horse() {
			super();
		}

		int index;
		int y;
		int x;
		int dir;
		Horse up;
		Horse down;

		public Horse(int index, int y, int x, int dir, Horse up, Horse down) {
			super();
			this.index = index;
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.up = up;
			this.down = down;
		}

		@Override
		public String toString() {
			return "Horse [index=" + index + ", y=" + y + ", x=" + x + ", dir=" + dir + ", up=" + up + ", down=" + down
					+ "]";
		}

	}

	static int[] dx = { 0, 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		hs = new Horse[K + 1];
		map = new int[N + 1][N + 1];
		visited = new int[N + 1][N + 1];
		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				visited[y][x] = -1;
			}
		}

		for (int y = 1; y <= N; y++) {
			for (int x = 1; x <= N; x++) {
				map[y][x] = sc.nextInt();
			}
		}

		for (int i = 1; i <= K; i++) {
			int y, x, d;
			y = sc.nextInt();
			x = sc.nextInt();
			d = sc.nextInt();
			visited[y][x] = i;
			hs[i] = new Horse(i, y, x, d, null, null);
		}

		Horse h;
		int tx, ty;
		int result = -1;
		st: for (int time = 1; time <= 1000; time++) {
			for (int o = 1; o <= K; o++) {
				h = hs[o];
				/*
				 * if (h.down != null) h.down = null;
				 */
				// 밑에있는건 같이 가는게 아니기 떄문에 null
				ty = h.y + dy[h.dir];
				tx = h.x + dx[h.dir];
				if (ty < 1 || ty > N || tx < 1 || tx > N || map[ty][tx] == 2)// 범위 넘어가거나 파랑색이면
				{
					h.dir = h.dir <= 2 ? 3 - h.dir : 7 - h.dir;// 방향 바꾸고
					int tty, ttx;
					tty = ty + 2 * dy[h.dir];
					ttx = tx + 2 * dx[h.dir];
					if (tty < 1 || tty > N || ttx < 1 || ttx > N || map[tty][ttx] == 2)// 그 반대편이 범위를넘거나 파랑색이지 않으면(!)
					{
						continue;
					} else {
						ty = tty;
						tx = ttx;
					}
				}
				if (h.down == null)// 밑에 없이 이동하면 visited 초기화
				{
					visited[h.y][h.x] = -1;
				} else// 밑에 있으면 연결 끊기
				{
					h.down.up = null;
					h.down = null;
				}
				// 이동시에는 밑에꺼와 관계 x
				// 빨강 흰색은 기존에 있는거 고려해야함

				if (map[ty][tx] == 1)// 빨강이면
				{
					h = swap(h);
				}

				if (visited[ty][tx] != -1)// 도착곳에 다른 말이 존재하면
				{
					Horse temp = hs[visited[ty][tx]];
					while (temp.up != null) {
						temp = temp.up;
					}
					h.down = temp;
					temp.up = h;
				} else// 도착곳에 다른 말이 존재하지 않으면
				{
					visited[ty][tx] = h.index;
				}

				h = hs[visited[ty][tx]];
				int count = 0;
				while (h != null)// 동기화(내위 말들을 전부 옮겨주면서 해당 위치 갯수를 세주는 작업)
				{
					count++;
					h.y = ty;
					h.x = tx;

					h = h.up;
				}
				if (count >= 4) {
					result = time;
					break st;
				}
			}
		}
		System.out.println(result);
	}
	private static Horse swap(Horse h) {

		if (h.up == null) {
			h.up = h.down;
			h.down = null;
			return h;
		}
		Horse temp = h.up;
		h.up = h.down;
		h.down = temp;
		return swap(h.down);

	}

}
