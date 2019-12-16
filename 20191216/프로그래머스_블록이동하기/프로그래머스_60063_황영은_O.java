package programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P_60063_블록이동하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Solution sol = new Solution();
		System.out.println(sol.solution(board));
	}

	public static class Solution {
		int[][] right_h = { { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } }; // 90도 방향
		int[][] h = { { -1, 0 }, { 1, 0 } }; // 가로일 때

		int[][] right_v = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
		int[][] v = { { 0, -1 }, { 0, 1 } }; // 세로일 때

		int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상하좌우

		public int solution(int[][] board) {
			int answer = 0;

			int N = board.length;
			Queue<Point> q = new LinkedList<>();
			q.add(new Point(0, 0, 0, 1, 0)); // 시작 지점
			int[][][] visited = new int[N][N][2]; // 0: 가로, 1: 세로
			visited[0][0][0] = -1;
			exit : while (!q.isEmpty()) {
				Point temp = q.poll();
				
				for (int i = 0; i < 4; i++) {
					int dx1 = temp.x1 + dir[i][0];
					int dy1 = temp.y1 + dir[i][1];
					int dx2 = temp.x2 + dir[i][0];
					int dy2 = temp.y2 + dir[i][1];
					
					int d = 1;
					if (dy1 != dy2) d = 0;
					
					if(dy1 > dy2 || dx1 > dx2) {
						int t = dx1;
						dx1 = dx2;
						dx2 = t;
						t = dy1;
						dy1 = dy2;
						dy2 = t;
					}
					
					if (dx1 < 0 || dy1 < 0 || dx2 < 0 || dy2 < 0 || dx2 < 0 || dy2 < 0) continue;
					if (dx1 >= N || dy1 >= N || dx2 >= N || dy2 >= N || dx2 >= N || dy2 >= N) continue;
					if (board[dx1][dy1] == 1 || board[dx2][dy2] == 1) continue;
					
					Point p = new Point(dx1, dy1, dx2, dy2, temp.cnt + 1);
					
					if (visited[dx1][dy1][d] == -1 || (visited[dx1][dy1][d] != 0 && visited[dx1][dy1][d] <= temp.cnt + 1)) {
						continue;
					}
					if ((dx1 == N - 1 && dy1 == N - 1) || (dx2 == N - 1 && dy2 == N - 1)) {
						answer = temp.cnt + 1;
						break exit;
					}
					
					visited[dx1][dy1][d] = temp.cnt + 1;
					
					q.add(p);
				}

				for (int i = 0; i < 4; i++) {
					int dx1 = 0, dx2 = 0, dy1 = 0, dy2 = 0; // dx1, dy1가 고정
					int cx = 0, cy = 0;
					
					if (temp.x1 == temp.x2) {
						if (i < 2) {
							dx1 = temp.x1;
							dy1 = temp.y1;
							dx2 = temp.x2 + right_h[i][0];
							dy2 = temp.y2 + right_h[i][1];
							cx = temp.x2 + h[i][0];
							cy = temp.y2 + h[i][1];
						} else {
							dx1 = temp.x2;
							dy1 = temp.y2;
							dx2 = temp.x1 + right_h[i][0];
							dy2 = temp.y1 + right_h[i][1];
							cx = temp.x1 + h[i % 2][0];
							cy = temp.y1 + h[i % 2][1];
						}
					} else {
						if (i < 2) {
							dx1 = temp.x1;
							dy1 = temp.y1;
							dx2 = temp.x2 + right_v[i][0];
							dy2 = temp.y2 + right_v[i][1];
							cx = temp.x2 + v[i][0];
							cy = temp.y2 + v[i][1];
						} else {
							dx1 = temp.x2;
							dy1 = temp.y2;
							dx2 = temp.x1 + right_v[i][0];
							dy2 = temp.y1 + right_v[i][1];
							cx = temp.x1 + v[i % 2][0];
							cy = temp.y1 + v[i % 2][1];
						}
					}

					int d = 1;
					if (dy1 != dy2) d = 0;
					
					if(dy1 > dy2 || dx1 > dx2) {
						int t = dx1;
						dx1 = dx2;
						dx2 = t;
						t = dy1;
						dy1 = dy2;
						dy2 = t;
					}
					if (dx1 < 0 || dy1 < 0 || dx2 < 0 || dy2 < 0 || dx2 < 0 || dy2 < 0) continue;
					if (dx1 >= N || dy1 >= N || dx2 >= N || dy2 >= N || dx2 >= N || dy2 >= N) continue;
					if (board[dx1][dy1] == 1 || board[dx2][dy2] == 1 || board[cx][cy] == 1) continue;
					
					Point p = new Point(dx1, dy1, dx2, dy2, temp.cnt + 1);
					
					if (visited[dx1][dy1][d] == -1 || (visited[dx1][dy1][d] != 0 && visited[dx1][dy1][d] <= temp.cnt + 1)) {
						continue;
					}
					if ((dx1 == N - 1 && dy1 == N - 1) || (dx2 == N - 1 && dy2 == N - 1)) {
						answer = temp.cnt + 1;
						break exit;
					}
					
					visited[dx1][dy1][d] = temp.cnt + 1;
					q.add(p);

				}
			}
			return answer;
		}
	}
	
	public static class Point {
		int x1, y1, x2, y2, cnt;

		public Point(int x1, int y1, int x2, int y2, int cnt) {
			super();
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "(" + x1 + ", " + y1 + ") , (" + x2 + ", " + y2 + ") | cnt : " + cnt;
		}

	}
}