import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5644_이진호_O {
	static int T, M, A;
	static int[] user1;
	static int[] user2;
	static Loc us1, us2;
	static int[][][] map;

	static class BC {
		int c;
		int p;

		public BC(int c, int p) {
			super();
			this.c = c;
			this.p = p;
		}

		@Override
		public String toString() {
			return "BC [c=" + c + ", p=" + p + "]";
		}
	}

	static class Loc {
		int y;
		int x;

		public Loc(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Loc [y=" + y + ", x=" + x + "]";
		}

	}

	// 지도 10x10
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			user1 = new int[M];
			user2 = new int[M];
			map = new int[11][11][100];

			int[] user;

			for (int i = 0; i < 2; i++) {
				user = i % 2 == 0 ? user1 : user2;
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					user[j] = Integer.parseInt(st.nextToken());
				}
			}
			int x, y, c, p;// c가 범위 p가 파워
			plist = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				p = Integer.parseInt(st.nextToken());
				plist.add(new BC(c, p));

				discoverd = new boolean[11][11];

				map[y][x][++map[y][x][0]] = i;
				discoverd[y][x] = true;

				Queue<Loc> q = new LinkedList<>();
				q.add(new Loc(y, x));
				Loc loc;
				int tx, ty;

				for (int qi = 0; qi < c; qi++) {
					int size = q.size();
					for (int qj = 0; qj < size; qj++) {
						loc = q.poll();
						for (int dir = 1; dir < 5; dir++) {
							ty = loc.y + dy[dir];
							tx = loc.x + dx[dir];
							if (ty <= 0 || ty > 10 || tx <= 0 || tx > 10)
								continue;
							if (discoverd[ty][tx])
								continue;
							discoverd[ty][tx] = true;
							map[ty][tx][++map[ty][tx][0]] = i;
							q.add(new Loc(ty, tx));
						}
					}
				}
			}
			us1 = new Loc(1, 1);
			us2 = new Loc(10, 10);
			int result = 0;
			result += check();
			for (int i = 0; i < M; i++) {
				us1.x += dx[user1[i]];
				us2.x += dx[user2[i]];
				us1.y += dy[user1[i]];
				us2.y += dy[user2[i]];
				result += check();
			}
			System.out.println("#" + tc + " " + result);
		}
	}

	static List<BC> plist;

	private static int check() {// 유저위치의 BC확인 겹치면 이전값비교
		IP[] r1 = new IP[map[us1.y][us1.x][0]];
		IP[] r2 = new IP[map[us2.y][us2.x][0]];
		for (int i = 0; i < map[us1.y][us1.x][0]; i++) {
			r1[i] = new IP(map[us1.y][us1.x][i+1], plist.get(map[us1.y][us1.x][i+1]).p);
		}
		Arrays.sort(r1, new Comparator<IP>() {

			@Override
			public int compare(IP o1, IP o2) {
				return o2.p - o1.p;
			}
		});
		for (int i = 0; i < map[us2.y][us2.x][0]; i++) {
			r2[i] = new IP(map[us2.y][us2.x][i+1], plist.get(map[us2.y][us2.x][i+1]).p);
		}
		Arrays.sort(r2, new Comparator<IP>() {

			@Override
			public int compare(IP o1, IP o2) {
				return o2.p - o1.p;
			}
		});
		if (r1.length == 0 && r2.length == 0) {
			return 0;
		} else if (r1.length == 0)
			return r2[0].p;
		else if (r2.length == 0)
			return r1[0].p;
		else {
			int next1,next2;
			if (r1[0].i == r2[0].i) {
				next1 = r1.length==1 ? 0 : r1[1].p;
				next2 = r2.length==1 ? 0 : r2[1].p;
				return next1 > next2 ? r1[0].p + next1 : r2[0].p + next2;
			} else {
				return r1[0].p + r2[0].p;
			}
		}
	}

	static class IP {
		int i;
		int p;

		public IP(int i, int p) {
			super();
			this.i = i;
			this.p = p;
		}

		@Override
		public String toString() {
			return "IP [i=" + i + ", p=" + p + "]";
		}

	}

	static int[] dx = {0, 0, 1, 0, -1 };
	static int[] dy = {0, -1, 0, 1, 0 };
	static boolean[][] discoverd;

}
