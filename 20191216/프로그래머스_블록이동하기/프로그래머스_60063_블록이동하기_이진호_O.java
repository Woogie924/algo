import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_60063_블록이동하기_이진호_O {
	public static void main(String[] args) {
		프로그래머스_60063_블록이동하기_이진호_O s = new 프로그래머스_60063_블록이동하기_이진호_O();
		int[][] board = { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 },
				{ 0, 0, 0, 0, 0 } };
		int result = s.solution(board);
		System.out.println(result);
	}

	class Drone {
		int type;
		int[][] p;

		public Drone(int type, int[][] p) {
			super();
			this.type = type;
			this.p = p;
		}

		@Override
		public String toString() {
			return "Drone [type=" + type + ", p=" + Arrays.toString(p) + "]";
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int[][] getP() {
			return p;
		}

		public void setP(int[][] p) {
			this.p = p;
		}
	}

	int[] dx = { 1, 0, -1, 0 };// 동남서북
	int[] dy = { 0, 1, 0, -1 };
	boolean[][][] discoverd;

	public int solution(int[][] board) {
		int n = board.length;
		int[][] answer = {};

		discoverd = new boolean[n][n][2];// 0이 가로 // 1이 세로
		discoverd[0][0][0] = true;
		discoverd[0][1][0] = true;

		Queue<Drone> q = new LinkedList<>();
		int[][] input = new int[2][2];
		input[0][0] = 0;// y x
		input[0][1] = 0;
		input[1][0] = 0;
		input[1][1] = 1;
		Drone drone = new Drone(0, input);
		q.add(drone);

		int level = -1;
		int size;
		end: while (!q.isEmpty()) {
			level++;
			size = q.size();
			for (int step = 0; step < size; step++) {
				Drone bd = q.poll();
				if (bd.p[0][0] == n - 1 && bd.p[0][1] == n - 1)
					break end;
				if (bd.p[1][0] == n - 1 && bd.p[1][1] == n - 1)
					break end;
				int tx, ty;

				st: for (int dir = 0; dir < 4; dir++) {// 단순이동
					int[][] next = new int[2][2];
					for (int i = 0; i < 2; i++) {
						ty = bd.p[i][0] + dy[dir];
						tx = bd.p[i][1] + dx[dir];
						if (ty < 0 || ty >= n || tx < 0 || tx >= n)
							continue st;
						if (board[ty][tx] == 1)
							continue st;
						next[i][0] = ty;
						next[i][1] = tx;
					}

					if (discoverd[next[0][0]][next[0][1]][bd.type] && discoverd[next[1][0]][next[1][1]][bd.type])
						continue;
					drone = new Drone(bd.type, next);
					discoverd[next[0][0]][next[0][1]][bd.type] = true;
					discoverd[next[1][0]][next[1][1]][bd.type] = true;
					q.add(drone);
				}
				int[] start, dest;
				for (int i = 0; i < 2; i++) {
					if (i % 2 == 0) {
						start = bd.p[0];
						dest = bd.p[1];
					} else {
						start = bd.p[1];
						dest = bd.p[0];
					}
					if (start[0] == dest[0])// y가 같다
					{
						for (int dir = -1; dir <= 1; dir += 2) {
							ty = dest[0] + dir;
							if (ty < 0 || ty >= n)
								continue;
							if (board[ty][dest[1]] == 1)
								continue;
							if (board[ty][start[1]] == 1)
								continue;
							if (discoverd[ty][start[1]][1] && discoverd[start[0]][start[1]][1])
								continue;

							int[][] next = new int[2][2];
							next[0][0] = start[0];
							next[0][1] = start[1];
							next[1][0] = ty;
							next[1][1] = start[1];
							drone = new Drone(1, next);
							discoverd[next[0][0]][next[0][1]][1] = true;
							discoverd[next[1][0]][next[1][1]][1] = true;
							q.add(drone);
						}

					}
					if (start[1] == dest[1])// x가 같다 -> ㅡ이 생김
					{
						for (int dir = -1; dir <= 1; dir += 2) {
							tx = dest[1] + dir;
							if (tx >= 0 && tx < n) {
								if (board[dest[0]][tx] == 0) {
									if (board[start[0]][tx] == 0) {
										if (discoverd[start[0]][tx][0] && discoverd[start[0]][start[1]][1])
											continue;
										int[][] next = new int[2][2];
										next[0][0] = start[0];
										next[0][1] = start[1];
										next[1][0] = start[0];
										next[1][1] = tx;
										drone = new Drone(0, next);
										discoverd[next[0][0]][next[0][1]][0] = true;
										discoverd[next[1][0]][next[1][1]][0] = true;
										q.add(drone);
									}
								}
							}
						}
					}
				}

			}
		}
		return level;

	}

}