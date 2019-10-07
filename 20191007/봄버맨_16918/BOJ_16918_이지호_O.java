import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16918 {
	static class posit {
		int x;
		int y;

		public posit(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, time;
	static char map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int time = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		BFS(time);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	private static void BFS(int time) {
		Queue<posit> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'O') {
					q.add(new posit(i, j));
				}
			}
		}
		for (int t = 1; t < time; t++) {
			if (t % 2 == 1) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						if (map[j][k] == '.') {
							map[j][k] = 'O';
						}
					}
				}
			} else {
				int qsize = q.size();
				for (int i = 0; i < qsize; i++) {
					posit temp = q.poll();
					for (int dir = 0; dir < 4; dir++) {
						int tx = temp.x + dx[dir];
						int ty = temp.y + dy[dir];
						map[temp.x][temp.y]='.';
						if (tx < 0 || tx >= N || ty < 0 || ty >= M)
							continue;
						
						map[tx][ty] = '.';
					}
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (map[i][j] == 'O') {
							q.add(new posit(i, j));
						}
					}
				}
			}
		}
	}

}
