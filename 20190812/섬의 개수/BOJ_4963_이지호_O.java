import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4963 {//섬의 개수
	static class coordinate {
		int x;
		int y;

		public coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public coordinate() {
		}
	}

	static StringTokenizer st;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			if(w==0&&h==0) {
				break;
			}
			map = new int[h + 2][w + 2];
			for(int i = 1; i<=h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j<=w; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			int cnt = 0;
			for (int i = 1; i <= h; i++) {
				for (int j = 1; j <= w; j++) {
					if (map[i][j] == 1) {
						map[i][j]=0;
						DFS(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}

	
	static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1 };//상하 좌우
	static int[] dy = { 0, 0, -1, 1, -1, -1, 1, 1 };
	private static void DFS(int x, int y) {
	
		coordinate temp = new coordinate(x, y);
		for (int search = 0; search < 8; search++) {
			int tx = temp.x + dx[search];
			int ty = temp.y + dy[search];
			if (map[tx][ty] == 1) {
				map[tx][ty]=0;
				DFS(tx, ty);
			}
		}
	}

}
