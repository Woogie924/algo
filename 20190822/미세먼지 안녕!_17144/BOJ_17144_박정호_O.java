import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_ByeDust_17144 {
	static int[][] map;
	static int r, c, t;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");

		r = Integer.parseInt(s[0]);
		c = Integer.parseInt(s[1]);
		t = Integer.parseInt(s[2]);
		map = new int[r][c];

		for (int i = 0; i < r; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < c; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		for (int time = 0; time < t; ++time) {
			// 1. 미세먼지 확산
			flood();
			// 2. 공기청정기 작동
			clear();
		}

		 getDust();
		 System.out.println(answer);
	}

	private static void getDust() {

		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				if (map[i][j] > 0) {
					answer += map[i][j];
				}
			}
		}
	}

	private static void clear() {
		// 청소기 위치 저장...
		int cr1 = 0;
		int cr2 = 0;
		for (int i = 0; i < r; ++i) {
			if (map[i][0] == -1) {
				cr1 = i;
				cr2 = i + 1;
				break;
			}
		}

		// 공기청정기와 가까운 위치부터 청소!!
		// 1. 윗부분 청소...
		// 1.1. 위에서 아래 청소..
		if (cr1 == 1) {
			map[0][0] = 0;
		} else {
			for (int i = cr1 - 1; i > 0; --i) {
				map[i][0] = map[i - 1][0];
				map[i - 1][0] = 0;
			}
		}
		// 1.2. 오른쪽에서 왼쪽으로 청소...
		for (int i = 0; i < c - 1; ++i) {
			map[0][i] = map[0][i + 1];
			map[0][i + 1] = 0;
		}
		// 1.3. 아래서 위로 청소...
		for (int i = 0; i < cr1; ++i) {
			map[i][c - 1] = map[i + 1][c - 1];
			map[i + 1][c - 1] = 0;
		}
		// 1.4. 왼쪽에서 오른쪽 청소...
		for (int i = c - 1; i > 1; --i) {
			map[cr1][i] = map[cr1][i - 1];
			map[cr1][i - 1] = 0;
		}

		// 2. 아랫부분 청소
		// 2.1. 아래서 위로 청소...
		if (cr2 == r - 2) {
			map[r - 1][0] = 0;
		} else {
			for (int i = cr2 + 1; i < r - 1; ++i) {
				map[i][0] = map[i + 1][0];
				map[i + 1][0] = 0;
			}
		}
		// 2.2. 오른쪽에서 왼쪽으로 청소...
		for (int i = 0; i < c - 1; ++i) {
			map[r-1][i] = map[r-1][i + 1];
			map[r-1][i + 1] = 0;
		}
		// 2.3. 아래서 위로 청소...
		for (int i = r-1; i > cr2; --i) {
			map[i][c - 1] = map[i - 1][c - 1];
			map[i - 1][c - 1] = 0;
		}
		// 2.4. 왼쪽에서 오른쪽 청소...
		for (int i = c - 1; i > 1; --i) {
			map[cr2][i] = map[cr2][i - 1];
			map[cr2][i - 1] = 0;
		}
	}

	private static void print() {
		System.out.println();
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void flood() {
		// 미세먼지가 있는 좌표 저장...
		Queue<Loc> q = new LinkedList<>();
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				if (map[i][j] > 0) {
					q.add(new Loc(i, j, map[i][j]));
					map[i][j] = 0;
				}
			}
		}

		// 주변으로 확산
		Loc loc;
		while (!q.isEmpty()) {
			loc = q.poll();
			int tr = loc.r;
			int tc = loc.c;
			int d = loc.data;
			int cnt = 0; // 얼마나 확산되었는지 저장하는 변수
			int ttr, ttc;
			for (int i = 0; i < 4; ++i) {
				ttr = tr + dr[i];
				ttc = tc + dc[i];
				// 범위 밖이면 확산 안됨
				if (ttr < 0 || ttc < 0 || ttr >= r || ttc >= c)
					continue;
				// 공기청정기 있으면 확산 안됨
				if (map[ttr][ttc] == -1)
					continue;
				// /5만큼 확산
				map[ttr][ttc] += d / 5;
				cnt += d / 5;
			}
			// 확산되고 남은먼지 저장
			map[tr][tc] += (d - cnt);
		}

	}

}

class Loc {
	int r;
	int c;
	int data;

	public Loc(int r, int c, int data) {
		super();
		this.r = r;
		this.c = c;
		this.data = data;
	}
}
