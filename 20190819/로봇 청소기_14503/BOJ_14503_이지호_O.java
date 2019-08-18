package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14503 {// 로봇청소기
	static int N, M;
	static int[][] map;

	static class robot {
		int x;
		int y;

		public robot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = { -1, 0, 1, 0 };// 북동남서
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 맵크기
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken());// 시작지점
		int sy = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());// 처음 방향

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0;
		// int index=0;
		map[sx][sy] = 2;// 시작
		result++;
		// dir++;
		while (true) {
			boolean clear = false;
			for (int i = 0; i < 4; i++) {
				if (map[sx + dx[i]][sy + dy[i]] == 0) {
					clear = true;
				}
			}
			if (!clear) {
				sx = sx - dx[dir];
				sy = sy - dy[dir];
				if (sx == 0 || sx == N - 1 || sy == 0 || sy == M - 1 || map[sx][sy] == 1) {
					break;
				}
			} else {
				if (map[sx + dx[(dir + 3) % 4]][sy + dy[(dir + 3) % 4]] == 0) {
					sx = sx + dx[(dir + 3) % 4];
					sy = sy + dy[(dir + 3) % 4];
					result++;
					map[sx][sy] = 2;
					dir = (dir + 3) % 4;
				} else if (map[sx + dx[(dir + 3) % 4]][sy + dy[(dir + 3) % 4]] != 0) {
					dir = (dir + 3) % 4;
				}
			}
		}
		System.out.println(result);
	}
}
