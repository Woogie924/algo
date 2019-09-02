import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {//테트로미노
	static int N, M;
	static int map[][];
	static boolean visit[][];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = true;
				DFS(i, j, 1, map[i][j]);
				visit[i][j] = false;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				BFS(i, j);
			}
		}
		System.out.println(max);
	}

	static int dxU[] = { 0, -1, 0 };
	static int dyU[] = { -1, 0, 1 };
	static int dxL[] = { 0, 1, -1 };
	static int dyL[] = { -1, 0, 0 };
	static int dxR[] = { 0, 1, -1 };
	static int dyR[] = { 1, 0, 0 };;
	static int dxD[] = { 0, 1, 0 };
	static int dyD[] = { -1, 0, 1 };

	private static void BFS(int i, int j) {
		int sum = map[i][j];
		for(int dir = 0; dir<3; dir++) {
			int tx = i+dxU[dir];
			int ty = j+dyU[dir];
			if (tx >= N || tx < 0 || ty >= M || ty < 0 ) continue;
			sum+=map[tx][ty];
		}
		max=Math.max(max, sum);
		sum = map[i][j];
		for(int dir = 0; dir<3; dir++) {
			int tx = i+dxD[dir];
			int ty = j+dyD[dir];
			if (tx >= N || tx < 0 || ty >= M || ty < 0 ) continue;
			sum+=map[tx][ty];
		}
		max=Math.max(max, sum);
		sum = map[i][j];
		for(int dir = 0; dir<3; dir++) {
			int tx = i+dxL[dir];
			int ty = j+dyL[dir];
			if (tx >= N || tx < 0 || ty >= M || ty < 0 ) continue;
			sum+=map[tx][ty];
		}
		max=Math.max(max, sum);
		sum = map[i][j];
		for(int dir = 0; dir<3; dir++) {
			int tx = i+dxR[dir];
			int ty = j+dyR[dir];
			if (tx >= N || tx < 0 || ty >= M || ty < 0 ) continue;
			sum+=map[tx][ty];
		}
		max=Math.max(max, sum);
	}

	static int temp[];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	private static void DFS(int i, int j, int cnt, int sum) {
		if (cnt == 4) {
			if (sum > max) {
				max = sum;
			}
			return;
		}
		for (int dir = 0; dir < 4; dir++) {
			int tx = i + dx[dir];
			int ty = j + dy[dir];
			if (tx >= N || tx < 0 || ty >= M || ty < 0 || visit[tx][ty])
				continue;
			visit[tx][ty] = true;
			sum += map[tx][ty];
			DFS(tx, ty, cnt + 1, sum);
			sum -= map[tx][ty];
			visit[tx][ty] = false;
		}
	}

}
