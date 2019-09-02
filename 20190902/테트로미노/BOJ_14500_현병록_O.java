import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_현병록_O {
	static int[][][] ㅗ = {
			{{0,1}, {0,2}, {1,1}},
			{{1,0}, {2,0}, {1,1}},
			{{1,0}, {2,0}, {1,-1}},
			{{0,1}, {0,2}, {-1,1}}
	};
	static int sero, garo, max;
	static int map[][];
	static int[] dy = { 1, 0, 0 }, dx = { 0, 1, -1 };
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sero = Integer.parseInt(st.nextToken());
		garo = Integer.parseInt(st.nextToken());
		map = new int[sero][garo];
		visited = new boolean[sero][garo];
		for (int i = 0; i < sero; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < garo; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = Integer.MIN_VALUE;
		for (int i = 0; i < sero - 1; i++) {
			for (int j = 0; j < garo; j++) {
				dfs(0, i, j, 0);
				fuckU(i,j);
			}
		}
		System.out.println(max);
	}

	private static void fuckU(int y, int x) {
		int sum=0, ny, nx;
		here : for(int i=0; i<4; i++) {
			sum=map[y][x];
			for(int j=0; j<3; j++) {
				ny = y+ㅗ[i][j][0]; nx = x+ㅗ[i][j][1];
				if (ny < 0 || nx < 0 || ny >= sero || nx >= garo)
					continue here;
				sum += map[ny][nx];
			}
			if(max<sum) max = sum;
		}
	}

	private static void dfs(int depth, int y, int x, int sum) {
		if (depth == 4) {
			// to-do 값비교
			if(max < sum) max = sum;
			return;
		}
		int ny, nx;
		for (int d = 0; d < 3; d++) {
			ny = y + dy[d];
			nx = x + dx[d];
			if (ny < 0 || nx < 0 || ny >= sero || nx >= garo)
				continue;
			if(visited[ny][nx]) continue;
			visited[ny][nx] = true;
			dfs(depth+1, ny, nx, sum+map[ny][nx]);
			visited[ny][nx] = false;
		}
	}
}