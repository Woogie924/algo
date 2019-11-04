import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2382_이진호_O {
	static int T, N, M, K;
	static int[][][] map;
	static List<Msm> list;

	static class Msm {
		int y;
		int x;
		int c;
		int d;

		public Msm(int y, int x, int c, int d) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Msm [y=" + y + ", x=" + x + ", c=" + c + ", d=" + d + "]";
		}

	}

	static int[] dx = { -99, 0, 0, -1, 1 };// 상하 좌 우
	static int[] dy = { -99, -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

//			map = new int[N][N];
			list = new ArrayList<>();
			int y, x, c, d;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				list.add(new Msm(y, x, c, d));
			}
			for (int t = 0; t < M; t++) {
				int tx, ty;
				map = new int[N][N][5];
				int size = list.size();
				Msm msm;
				for (int i = 0; i < size; i++)// 이동
				{
					msm = list.get(i);
					y = msm.y = msm.y + dy[msm.d];
					x = msm.x = msm.x + dx[msm.d];
					map[y][x][++map[y][x][0]] = i;
				}
				for (int i = 0; i < size; i++)// 위치확인
				{
					msm = list.get(i);
					if(msm.c==0) continue;
					y = msm.y;
					x = msm.x;
					if (y <= 0 || y >= N - 1 || x <= 0 || x >= N - 1) {

						msm.c /= 2;
						if (msm.d <= 2) {
							msm.d = msm.d == 1 ? 2 : 1;
						} else {
							msm.d = msm.d == 3 ? 4 : 3;
						}
						continue;
					}
					if (map[y][x][0] != 1) {// 모인게 잇 으면
						int dir = -1;
						int max = 0;
						int sum = 0;
						Msm next;
						for (int ms = 1; ms <= map[y][x][0]; ms++) {
							next = list.get(map[y][x][ms]);
							sum += next.c;
							if (max < next.c) {
								max = next.c;
								dir = next.d;
							}
							if (map[y][x][ms] != i) {
								list.get(map[y][x][ms]).c = 0;
							}
						}
						msm.c = sum;
						msm.d = dir;
					}
				}
				for (int i = 0; i < size; i++)// 삭제
				{
					msm = list.get(i);
					if (msm.c == 0) {
						list.remove(i);
						size--;
						i--;
					}
				}
			}
			int result = 0;
			for (Msm m : list) {
				result += m.c;
			}
			System.out.println("#" + tc + " " + result);
		}

	}
}
