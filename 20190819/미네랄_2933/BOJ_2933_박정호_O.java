package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_Mineral_2933 {
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static String[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		int r = Integer.parseInt(s[0]);
		int c = Integer.parseInt(s[1]);
		map = new String[r][c];
		for (int i = 0; i < r; ++i) {
			s = br.readLine().split("");
			for (int j = 0; j < c; ++j) {
				map[i][j] = s[j];
			}
		}
		int n = Integer.parseInt(br.readLine());
		s = br.readLine().split("\\s");
		int loc = 0;
		for (int i = 0; i < n; ++i) {
			// 미네랄 제거
			loc = Integer.parseInt(s[i]);
			// 왼쪽부터 날림
			if (i % 2 == 0) {
				for (int j = 0; j < c; ++j) {
					if (map[r - loc][j].equals("x")) {
						map[r - loc][j] = ".";
						break;
					}
				}
			}
			// 오른쪽에서 날림
			else {
				for (int j = c - 1; j >= 0; --j) {
					if (map[r - loc][j].equals("x")) {
						map[r - loc][j] = ".";
						break;
					}
				}
			}

			// 클러스터가 있다면 내리기...
			cluster(r, c);
			

		}
		for (int k = 0; k < r; ++k) {
			for (int j = 0; j < c; ++j) {
				System.out.print(map[k][j]);
			}
			System.out.println();
		}
	}

	private static void cluster(int r, int c) {
		// 모든 미네랄 2로 표시
		int[][] check = new int[r][c];
		boolean[][] visit = new boolean[r][c];
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				if (map[i][j].equals("x"))
					check[i][j] = 2;
			}
		}

		// 바닥에 붙어있는 미네랄 좌표 큐에 넣기
		Queue<Pair> q = new LinkedList<Pair>();
		for (int i = 0; i < c; ++i) {
			if (check[r - 1][i] == 2) {
				q.add(new Pair(r - 1, i));
			}
		}

		// 바닥과 붙어있는 미네랄 모두 1로 바꾸기
		Pair p;
		while (!q.isEmpty()) {
			p = q.poll();
			int tr = p.r;
			int tc = p.c;
			visit[tr][tc] = true;
			check[tr][tc] = 1;
			int ttr, ttc;
			for (int i = 0; i < 4; ++i) {
				ttr = tr + dr[i];
				ttc = tc + dc[i];
				if (ttr < 0 || ttc < 0 || ttr >= r || ttc >= c)
					continue;
				if (!visit[ttr][ttc] && check[ttr][ttc] == 2) {
					visit[ttr][ttc] = true;
					check[ttr][ttc] = 1;
					q.add(new Pair(ttr, ttc));
				}
			}
		}
		
		// 바닥  또는 다른 클러스터와 가장 가까운 길이 찾기
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				if(check[i][j]==2) {
					int cnt = 0;
					for(int k=i+1; k<r; ++k) {
						if(check[k][j]==1 || k==r)
							break;
						cnt++;
					}
					min = Math.min(min, cnt);
				}
			}
		}

		// 공중에 떠 있는 cluster가 있다면 아래로 내린다...
		for (int i = r-1; i >=0; --i) {
			for (int j = c-1; j>=0; --j) {
				if (check[i][j] == 2) {
					map[i][j] = ".";
					map[i + min][j] = "x";
				}
			}
		}

	}

}

class Pair {
	int r;
	int c;

	public Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}

}
