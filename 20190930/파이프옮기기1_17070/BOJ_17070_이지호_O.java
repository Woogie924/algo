import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070 {
	static int N, ans, status, count;
	static int map[][];
	static boolean visit[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		map[0][0] = 2;
		map[0][1] = 2;
		ans = 0;
		status = 0;// 0 = 가로, 1 = 대각선, 2 = 세로
		DFS(0, 1, status);
		System.out.println(count);
	}

	static int dx[] = { 0, 1, 1 };
	static int dy[] = { 1, 1, 0 };

	private static void DFS(int x, int y, int status) {
		if (x == N - 1 && y == N - 1) {
			count++;
			return;
		}
		if (status == 0) {
			for (int i = 0; i < 2; i++) {
				int tx = x + dx[i];
				int ty = y + dy[i];
				if (isCheck(tx, ty)) {
					if (i == 1) {
						if(map[tx-1][ty]==1||map[tx][ty-1]==1) {
							continue;
						}
					}
					DFS(tx, ty, i);
				}
			}
		} else if (status == 1) {
			for (int i = 0; i < 3; i++) {
				int tx = x + dx[i];
				int ty = y + dy[i];
				if (isCheck(tx, ty)) {
					if (i == 1) {
						if(map[tx-1][ty]==1||map[tx][ty-1]==1) {
							continue;
						}
					}

					DFS(tx, ty, i);
				}
			}
		} else if (status == 2) {
			for (int i = 2; i >= 1; i--) {
				int tx = x + dx[i];
				int ty = y + dy[i];
				if (isCheck(tx, ty)) {
					if (i == 1) {
						if(map[tx-1][ty]==1||map[tx][ty-1]==1) {
							continue;
						}
					}
					DFS(tx, ty, i);
				}
			}
		}

	}

	private static boolean isCheck(int tx, int ty) {
		if (tx < 0 || ty < 0 || tx >= N || ty >= N || visit[tx][ty] || map[tx][ty] == 1) {
			return false;
		} else
			return true;
	}

}
