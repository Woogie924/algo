import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_DragonCurve_15685 {
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[101][101];
		String[] s;
		int r, c, dir, g;
		for (int i = 0; i < n; ++i) {
			ArrayList<Integer> list = new ArrayList<>();
			s = br.readLine().split("\\s");
			c = Integer.parseInt(s[0]);
			r = Integer.parseInt(s[1]);
			dir = Integer.parseInt(s[2]);
			g = Integer.parseInt(s[3]);

			list.add(dir);
			int temp = 0;
			for (int j = 1; j <= g; ++j) {
				for (int k = list.size() - 1; k >= 0; --k) {
					temp = (list.get(k) + 1) % 4;
					list.add(temp);
				}
			}

			map[r][c] = 1;
			for (int j = 0; j < list.size(); ++j) {
				r += dr[list.get(j)];
				c += dc[list.get(j)];
				if (r < 0 || c < 0 || r > 100 || c > 100)
					break;
				map[r][c] = 1;
			}

		}

		// 정사각형 개수 세기
		int answer = 0;
		for (int i = 0; i <= 99; ++i) {
			for (int j = 0; j <= 99; ++j) {
				if (map[i][j] == 1 && map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1)
					answer++;
			}
		}
		System.out.println(answer);
	}
}
