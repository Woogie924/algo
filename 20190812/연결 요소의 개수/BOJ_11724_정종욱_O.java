package algo_hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11724_Á¤Á¾¿í_O {
	static boolean[] visitnum;
	static boolean[][] visited;
	static int[][] arr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, T;
	static int x, y;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		visited = new boolean[N][N];
		visitnum = new boolean[N];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			arr[x][y] = 1;
		}
		int cnt = 0;
		for (int a = 0; a < N; a++) {
			for (int b = 0; b < N; b++) {
				if (arr[a][b] == 1 && !visited[a][b]) {
					visited[a][b] = true;
					go(a);
					go(b);
					cnt++;
				}
			}
		}
		int visitcount = 0;
		for (int d = 0; d < N; d++) {
			if (visitnum[d] == false)
				visitcount++;
		}
		if (T == 0)
			cnt = 0;
		System.out.println(cnt + visitcount);
	}

	private static void go(int f) {
		for (int c = 0; c < N; c++) {
			visitnum[f] = true;
			if (arr[f][c] == 1 && !visited[f][c]) {
				visited[f][c] = true;
				go(c);

			}
		}
	}
}
