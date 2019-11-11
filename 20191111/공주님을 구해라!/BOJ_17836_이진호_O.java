import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17836_이진호_O {
	static int N, M, T;
	static int[][] map;

	static class Loc {
		int y;
		int x;

		public Loc(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Loc [y=" + y + ", x=" + x + "]";
		}

	}

	static boolean[][] discovered;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		discovered = new boolean[N][M];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<Loc> q = new LinkedList<>();
		q.add(new Loc(0, 0));
		discovered[0][0] = true;
		Loc loc;
		int tx, ty;
		int turn = 0;
		int limit = Integer.MAX_VALUE;
		int state = -1; // -1 : 도착못함 1: 검집음 2: 직접 만남

		st: while (!q.isEmpty()) {// 큐가 빌떄까지 BFS(시간이 검을찾은후의 도착시간같거나 //제한시간초과시 반복문을 끝냄)
			turn++;
			if (turn == limit) {// 검을찾은후 도착까지의 시간
				break;
			}

			if (turn > T) { // 제한시간 초과할때
				state = -1;
				break;
			}

			int size = q.size();
			for (int i = 0; i < size; i++) {
				loc = q.poll();
				for (int dir = 0; dir < 4; dir++) {
					ty = loc.y + dy[dir];
					tx = loc.x + dx[dir];
					if (ty == N - 1 && tx == M - 1) {// 공주를 찾음
						state = 2;
						break st;
					}

					if (ty < 0 || ty >= N || tx < 0 || tx >= M)// 바운더리 체크
						continue;
					if (discovered[ty][tx])// 방문 체크
						continue;
					if (map[ty][tx] == 2)// 검찾음
					{
						int least = turn + Math.abs(ty - N + 1) + Math.abs(tx - M + 1);
						limit = limit > least ? least : limit;
						state = 1;
					} else if (map[ty][tx] == 1)// 벽만남
						continue;

					discovered[ty][tx] = true;
					q.add(new Loc(ty, tx));
				}
			}
		}

		if (state == 2)//
		{
			System.out.println(turn);
		}

		if (state == 1) {
			System.out.println(limit);
		} else if (state == -1) {
			System.out.println("Fail");
		}

	}

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
}
