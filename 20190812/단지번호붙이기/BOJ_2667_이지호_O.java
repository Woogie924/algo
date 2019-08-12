import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class coordinate {
	int x;
	int y;
	public coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public coordinate() {}
}

public class Boj2667 {//단지 번호 붙이기
	static int[] apt;
	static int count = 0;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N + 2][N + 2];
		apt= new int[N*N];
		visited = new boolean[N + 2][N + 2];
		Arrays.fill(apt, 99999);
		for (int i = 1; i <= N; i++) {
			String temp = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = temp.charAt(j - 1) - '0';
			}
		}
		int aptcnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <=N; j++) {
				if (map[i][j] == 1) {
					BFS(i, j);
					aptcnt++;
				}
			}
		}
		System.out.println(aptcnt);
		Arrays.sort(apt);
		for (int i = 0; i < count; i++) {
			System.out.println(apt[i]);
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void BFS(int i, int j) {
		Queue<coordinate> q = new LinkedList<>();
		q.add(new coordinate(i, j));
		int sum = 1;
		map[i][j] = 0;
		while (!q.isEmpty()) {
			coordinate temp = new coordinate();
			temp = q.poll();
			
			for (int search = 0; search < 4; search++) {
				int tx = temp.x + dx[search];
				int ty = temp.y + dy[search];
				if (map[tx][ty] == 1) {
					q.add(new coordinate(tx, ty));
					map[tx][ty] = 0;
					sum++;
				}
			}
		}
		apt[count++] = sum;
	}

}
