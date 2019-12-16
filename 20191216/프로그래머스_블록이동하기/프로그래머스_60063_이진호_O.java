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

/*

블록 이동하기

두 날개중 한쪽이 n-1이면 종료
drone 클래스는 typed과 양쪽날개 위치가 이차원 배열로 저장
1. 단순이동 해본다 -> 자신의 type은 그대로 이동만
2. 날개를 돌려본다. -> ㅡ,| 타입에 따라 다르게 회전시켜준다.
3. 방문체크는 이차원배열에서 세로 가로 -> 삼차원 배열로 확인


 *14~44
Drone 클래스 선언
Type - 드론이 세로인지 가로인지 0: 가로 // 1: 세로
P - 드론 양 날개 위치

처음위치는 0,0 / 0,1 ㅡ모양 방문 선언 
51~68 초기값 정의 

74~77 bfs 기저 조건 정의

80~100 단순이동 type 그대로 위치는 상하좌우로 이동

101~160 : 한 날개 기준으로 회전하는 이동
109~133 : |모양 움직이기 
115 : 대각선 확인 
117 : 최종위치 확인 
119 : 방문여부 확인
122~130 : 날개 양쪽 만들어주고 방문체크해주고 q에 넣어준다.
 * 
 */
