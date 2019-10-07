import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16920 {
	static class posit {
		int x;
		int y;

		public posit(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, P;
	static int S[], count[];
	static char map[][];
	static boolean visit[][];

	public static void main(String[] args) throws IOException {// 확장게임
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		S = new int[P];
		count = new int[P];
		map = new char[N][M];
		visit = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < P; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		bfs();
		for(int i = 0; i<P; i++) {
			System.out.print(count[i]+" ");
		}
	}

	// Queue<int[]> q = new LinkedList<>();
	// q.offer(new int[]{1,2,3});
	// temp[3] = q.poll;
	// temp[0]=1; temp[1] =2; temp[2]=3;
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	private static void bfs() {
		Queue<posit> q[] = new LinkedList[P];
		for (int i = 0; i < P; i++) {
			q[i] = new LinkedList<>();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != '.' && map[i][j] != '#') {
					q[map[i][j] - '1'].add(new posit(i, j));
					count[map[i][j] - '1']++;
					visit[i][j] = true;
				}
			}
		}
		while (check(q)) {
			for (int i = 0; i < P; i++) {
				for (int ii = 0; ii < S[i]; ii++) {
					int qsize = q[i].size();
					if(q[i].isEmpty()) break;
					for (int j = 0; j < qsize; j++) {
						posit temp = q[i].poll();
						for (int dir = 0; dir < 4; dir++) {
							int tx = temp.x + dx[dir];
							int ty = temp.y + dy[dir];
							if (tx < 0 || tx >= N || ty < 0 || ty >= M || visit[tx][ty] || map[tx][ty] == '#')
								continue;
							count[i]++;
							visit[tx][ty] = true;
							map[tx][ty] = (char) (i + '1');
							q[i].add(new posit(tx, ty));

						}
					}
				}
			}
		}
	}

	private static boolean check(Queue<posit>[] q) {
		for (int i = 0; i < P; i++) {
			if (!q[i].isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
