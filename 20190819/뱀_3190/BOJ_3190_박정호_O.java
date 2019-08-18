import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_Snake_3190 {
	static int[][] map;
	static boolean[][] apple;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// NxN board 생성
		map = new int[n][n];
		for (int i = 0; i < n; ++i)
			Arrays.fill(map[i], -1);
		apple = new boolean[n][n];

		// apple좌표 입력받아 apple배열에 표시하기
		int appleNum = Integer.parseInt(br.readLine());
		String[] s;
		int r, c;
		for (int i = 0; i < appleNum; ++i) {
			s = br.readLine().split("\\s");
			r = Integer.parseInt(s[0]);
			c = Integer.parseInt(s[1]);
			apple[r - 1][c - 1] = true; // 사과는 true로 표시
		}

		r = c = 0; // 뱀의 머리 위치 저장
		int dir = 1; // 뱀의 머리 방향 저장
		int len = 1; // 뱀의 길이 저장
		map[r][c] = 0; // 초기 뱀의 위치
		int cmdNum = Integer.parseInt(br.readLine());
		int cmdT = 0;
		String cmd = null;
		int now = 0;
		for (int i = 0; i <= cmdNum; ++i) {
			cmdT = Integer.MAX_VALUE;
			if (i < cmdNum) {
				s = br.readLine().split("\\s");
				cmdT = Integer.parseInt(s[0]);
				cmd = s[1];
			}
			while (now < cmdT) {
				now++;
				// 뱀 위치 이동
				r += dr[dir];
				c += dc[dir];
				// map 밖으로 벗어나면 종료
				if (r < 0 || c < 0 || r >= n || c >= n) {
					System.out.println(now);
					return;
				}
				// 뱀 몸에 부딪히면 종료
				if (map[r][c] != -1 && now - map[r][c] <= len) {
					System.out.println(now);
					return;
				}
				// 사과가 있다면 없애고, 뱀 길이 증가
				if (apple[r][c]) {
					apple[r][c] = false;
					len++;
				}
				map[r][c] = now;
			}
			if (cmd.equals("D")) {
				dir = (dir + 1) % 4;
			} else {
				dir = (dir + 3) % 4;
			}
		}

	}
}
