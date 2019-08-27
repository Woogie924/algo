import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class SharkLoc {
	int r;
	int c;

	public SharkLoc(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int n;
	static int[][] map;
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };
	static int sharkR = 0;
	static int sharkC = 0;
	static int sharkSize = 2;
	static int eatCnt = 0;
	static int time = 0;
	static Queue<SharkLoc> q = new LinkedList<SharkLoc>();;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; ++i) {
			String[] s = br.readLine().split("\\s");
			for (int j = 0; j < n; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
				// 상어 위치 저장
				if (map[i][j] == 9) {
					sharkR = i;
					sharkC = j;
					q.add(new SharkLoc(sharkR, sharkC));
					map[i][j] = 0;
				}
			}
		}
		go();
		System.out.println(time);
	}

	private static void go() {
		boolean[][] visit;
		SharkLoc sl;
		while (!q.isEmpty()) {
			// 더 먹을 물고기가 없는 경우 체크
			if (check())
				break;
			int qSize = q.size();
			visit = new boolean[n][n];
			for (int t = 0; t < qSize; ++t) {
				sl = q.poll();
				int r = sl.r;
				int c = sl.c;
				visit[r][c] = true;
				int tr, tc;
				for (int i = 0; i < 4; ++i) {
					tr = r + dr[i];
					tc = c + dc[i];
					// 범위 벗어나는 경우
					if (tr < 0 || tc < 0 || tr >= n || tc >= n || visit[tr][tc])
						continue;
					// 상어보다 큰 물고기를 만나는 경우
					if (map[tr][tc] > sharkSize)
						continue;
					// 상어와 크기가 같거나, 작은경우 일단 q에 집어넣음
					q.add(new SharkLoc(tr, tc));
					visit[tr][tc] = true;
				}
			}
			sort();
			// 상어보다 작은 물고기를 만나는 경우, 잡아먹음
			qSize = q.size();
			for (int t = 0; t < qSize; ++t) {
				sl = q.poll();
				int r = sl.r;
				int c = sl.c;
				if (map[r][c] == 0 || map[r][c] == sharkSize) {
					q.add(new SharkLoc(r, c));
					continue;
				}
				map[r][c] = 0;
				eatCnt++;
				// 잡아먹은 물고기 수가 상어크기와 같으면 상어크기 1증가
				if (eatCnt == sharkSize) {
					eatCnt = 0;
					sharkSize++;
				}
				while (!q.isEmpty())
					q.poll();
				q.add(new SharkLoc(r, c));
				break;
			}
			time++;
		}

	}

	private static void sort() {
		ArrayList<int[]> temp = new ArrayList<>();
		SharkLoc sl;
		while (!q.isEmpty()) {
			sl = q.poll();
			int[] temp2 = { sl.r, sl.c };
			temp.add(temp2);
		}
		Collections.sort(temp, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0])
					return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
		});

		for (int i = 0; i < temp.size(); ++i) {
			q.add(new SharkLoc(temp.get(i)[0], temp.get(i)[1]));
		}

	}

	private static boolean check() {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (map[i][j] != 0 && map[i][j] < sharkSize)
					return false;
			}
		}
		return true;
	}

}
