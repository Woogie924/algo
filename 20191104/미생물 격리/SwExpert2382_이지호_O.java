package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;
/*
 * SWEA 2382 모의 테스트 미생물 격리
 */
public class SwExpert2382 {

	static class micro {
		int x;
		int y;
		int count;
		int dir;// 1 상 2 하 3 좌 4 우

		public micro(int x, int y, int count, int dir) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.dir = dir;
		}

	}

	static int N, M, K, map[][];
	static int dx[] = { 0, -1, 1, 0, 0 };
	static int dy[] = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());// 크기
			M = Integer.parseInt(st.nextToken());// 시간
			K = Integer.parseInt(st.nextToken());// 미생물 군집 수

			map = new int[N][N];
			ArrayList<micro> list = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				list.add(new micro(x, y, count, dir));
			}
			for (int time = 0; time < M; time++) {
				int size = list.size();
				for (int i = 0; i < size; i++) {
					micro m = list.get(i);
					int tx = m.x + dx[m.dir];
					int ty = m.y + dy[m.dir];
					if (tx == 0 || tx == N - 1 || ty == 0 || ty == N - 1) {
						m.count = m.count / 2;
						if (m.dir == 1) {
							m.dir = 2;
						} else if (m.dir == 2) {
							m.dir = 1;
						} else if (m.dir == 3) {
							m.dir = 4;
						} else if (m.dir == 4) {
							m.dir = 3;
						}
					}
					list.set(i, new micro(tx, ty, m.count, m.dir));
					map[tx][ty]++;
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] > 1) {
							int sum = 0;
							int temp = 0;
							int dir = 0;
							ArrayList<micro> remove = new ArrayList<>();
							for (int k = 0; k < list.size(); k++) {
								if (list.get(k).x == i && list.get(k).y == j) {
									micro tmp = list.get(k);
									remove.add(tmp);
									if (temp < tmp.count) {
										temp = tmp.count;
										dir = tmp.dir;
									}
									sum = sum + tmp.count;
								}
							}
							for(micro rmv: remove) {
								list.remove(rmv);
							}
							/*
							 * Iterator<micro> iter = list.iterator(); while (iter.hasNext()) { micro it =
							 * iter.next(); if (it.x == i && it.y == j) { iter.remove(); } }
							 */
							list.add(new micro(i, j, sum, dir));
						}
					}
				}
				for(int i =0 ; i<N; i++) {
					Arrays.fill(map[i],0);
				}
			}
			int ret = 0;
			for(micro result : list) {
				ret+=result.count;
			}
			System.out.printf("#%d %d\n",tc,ret);
		}

	}

}
