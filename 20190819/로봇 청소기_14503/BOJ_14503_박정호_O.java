import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_RobotCleaner_14503 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		int r = Integer.parseInt(s[0]);
		int c = Integer.parseInt(s[1]);
		int[][] map = new int[r][c];

		s = br.readLine().split("\\s");
		int robotR = Integer.parseInt(s[0]); // 로봇 row 위치
		int robotC = Integer.parseInt(s[1]); // 로봇 column 위치
		int robotD = Integer.parseInt(s[2]); // 로봇이 바라보는 방향(시계방향으로 0123)

		// 빈칸은 0, 벽은 1
		for (int i = 0; i < r; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < c; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		// 각 방향의 왼쪽
		int[] dr = { 0, -1, 0, 1 };
		int[] dc = { -1, 0, 1, 0 };
		// 현재 위치를 청소한다.(청소하면 2로 바꿈)
		map[robotR][robotC] = 2;
		int answer = 1;
		boolean flag = false; // 청소 했는지 확인하는 flag
		while (true) {
			flag = false;
			
			/*for (int i = 0; i < r; ++i) {
				for (int j = 0; j < c; ++j) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();*/
			
			// 현재 방향을 기준으로 왼쪽 방향부터 차례로 탐색
			for (int i = 0; i < 4; ++i) {// 4방향(4번) 모두 검사할동안 청소할곳이 없다면 탈출
				if (map[robotR + dr[robotD]][robotC + dc[robotD]] == 0) {
					// 로봇 위치 이동
					robotR += dr[robotD];
					robotC += dc[robotD];
					// 현재 자리 청소
					map[robotR][robotC] = 2;
					robotD -= 1;
					if (robotD < 0)
						robotD = 3;
					flag = true;
					answer++;
					break;
				} else {
					robotD -= 1;
					if (robotD < 0)
						robotD = 3;
				}
			}
			// 청소 안했다면 뒤로 후진
			if (!flag) {
				// 로봇이 바라보는 방향의 반대방향
				int temp = (robotD + 3) % 4;
				// 후진할 곳이 벽이면 break
				if (map[robotR + dr[temp]][robotC + dc[temp]] == 1)
					break;
				// 로봇의 위치만 바꾸고, 방향은 바꾸지 않음
				robotR += dr[temp];
				robotC += dc[temp];
			}
			
		}
		System.out.println(answer);
	}

}
