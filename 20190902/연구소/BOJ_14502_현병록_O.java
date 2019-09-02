import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_현병록_O {
	static int sero, garo;
	static char[][] map;
	static int[] combi;
	static ArrayList<mPoint> points, virus;
	static boolean[][] visited;
	static int min;
	static Queue<mPoint> q;
	static class mPoint {
		int y, x;

		public mPoint(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		char c;
		sero = Integer.parseInt(st.nextToken());
		garo = Integer.parseInt(st.nextToken());
		map = new char[sero][garo];
		combi = new int[3];
		points = new ArrayList<>();
		virus = new ArrayList<>();
		q = new LinkedList<>();
		
		for (int i = 0; i < sero; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < garo; j++) {
				c = st.nextToken().charAt(0);
				if (c == '0')
					points.add(new mPoint(i, j));
				else if (c == '2')
					virus.add(new mPoint(i, j));
				map[i][j] = c;
			}
		}

		min = Integer.MAX_VALUE;
		combination(0, 0);
		System.out.println(points.size() - min-3);
	}

	private static void combination(int depth, int index) {
		if (depth == 3) {
			mPoint p;
			for(int i=0; i<3; i++) {
				p = points.get(combi[i]);
				map[p.y][p.x]='1';
			}

			bfs();
			for(int i=0; i<3; i++) {
				p = points.get(combi[i]);
				map[p.y][p.x]='0';
			}
			return;
		}

		for (int i = index; i < points.size(); i++) {
			combi[depth]=i;
			combination(depth + 1, i + 1);
		}
	}

	static int[] dy = { 1, -1, 0, 0 }, dx = { 0, 0, 1, -1 };

	private static void bfs() {
		int y, x, ny, nx, count = 0;
		mPoint temp;
		visited = new boolean[sero][garo];

		for (mPoint p : virus) {
			q.clear();
			q.offer(p);
			visited[p.y][p.x] = true;
			while (!q.isEmpty()) {
				temp = q.poll();
				y = temp.y;
				x = temp.x;
				for (int d = 0; d < 4; d++) {
					ny = y + dy[d];
					nx = x + dx[d];
					if (ny < 0 || nx < 0 || ny >= sero || nx >= garo)
						continue;
					if (!visited[ny][nx] && map[ny][nx] == '0') {
						q.offer(new mPoint(ny, nx));
						visited[ny][nx] = true;
						if (++count > min)
							return;
					}
				}
			}
		}
		if (min > count) {
			min = count;
		}
	}
}