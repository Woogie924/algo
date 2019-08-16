package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_Dice_14499 {
	// 동, 서, 북, 남 순서
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int[] dice = { 0, 0, 0, 0, 0, 0 }; // 전개도상 상, 중, 하, 바닥, 좌, 우 순서

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		int r = Integer.parseInt(s[2]);
		int c = Integer.parseInt(s[3]);
		int k = Integer.parseInt(s[4]);

		// board 입력받기
		int[][] board = new int[n][m];
		for (int i = 0; i < n; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < m; ++j) {
				board[i][j] = Integer.parseInt(s[j]);
			}
		}

		// 명령 입력 받기
		s = br.readLine().split("\\s");
		int[] command = new int[k];
		for (int i = 0; i < k; ++i) {
			command[i] = Integer.parseInt(s[i]);
		}

		for (int i = 0; i < command.length; ++i) {
			// 주사위가 범위를 벗어나면 명령 무시
			if (r + dr[command[i] - 1] < 0 || c + dc[command[i] - 1] < 0 | r + dr[command[i] - 1] >= n
					|| c + dc[command[i] - 1] >= m)
				continue;
			r = r + dr[command[i] - 1];
			c = c + dc[command[i] - 1];

			if (command[i] == 1) { // 동쪽으로 움직이는경우
				change(1);
				if (board[r][c] == 0) { // board가 0이면 주사위 바닥면 복사
					board[r][c] = dice[3];
				} else {
					dice[3] = board[r][c];
					board[r][c] = 0;
				}

			} else if (command[i] == 2) { // 서쪽으로 움직이는 경우
				change(2);
				if (board[r][c] == 0) { // board가 0이면 주사위 바닥면 복사
					board[r][c] = dice[3];
				} else {
					dice[3] = board[r][c];
					board[r][c] = 0;
				}
			} else if (command[i] == 3) { // 북쪽으로 움직이는 경우
				change(3);
				if (board[r][c] == 0) { // board가 0이면 주사위 바닥면 복사
					board[r][c] = dice[3];
				} else {
					dice[3] = board[r][c];
					board[r][c] = 0;
				}
			} else { // 남쪽으로 움직이는 경우
				change(4);
				if (board[r][c] == 0) { // board가 0이면 주사위 바닥면 복사
					board[r][c] = dice[3];
				} else {
					dice[3] = board[r][c];
					board[r][c] = 0;
				}
			}
			System.out.println(dice[1]);

		}

	}

	private static void change(int cmd) {
		int temp = 0;
		switch (cmd) {
		case 1: // 동
			temp = dice[4];
			dice[4] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[1];
			dice[1] = temp;
			break;
		case 2: // 서
			temp = dice[4];
			dice[4] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[3];
			dice[3] = temp;
			break;
		case 3: // 북
			temp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[3];
			dice[3] = temp;
			break;
		case 4: // 남
			temp = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[2];
			dice[2] = dice[1];
			dice[1] = temp;
			break;
		}

	}

}
