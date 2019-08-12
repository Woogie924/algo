import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7576 {
	static class location {
		int x;
		int y;
		public location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static StringTokenizer st;
	static int[][] map;
	static int N, M;

	public static void main(String[] args) throws IOException {//토마토
		input();
		start();
		int max=0;
		aa:for(int i = 0; i<M; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j]==0) {
					max=0;
					break aa;
				}
				if(map[i][j]>max) {
					max=map[i][j];
				}
			}
		}
		System.out.println(max-1);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void start() {
		Queue<location> q = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					q.add(new location(i, j));
				}
			}
		}
		BFS(q);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void BFS(Queue<location> q) {
		while (!q.isEmpty()) {
			location temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int tx = temp.x + dx[i];
				int ty = temp.y + dy[i];
				if (tx < M && tx >= 0 && ty >= 0 && ty < N && map[tx][ty] == 0) {
					map[tx][ty] = map[temp.x][temp.y]+1;
					q.add(new location(tx,ty));
				}
			}
		}
	}
}
