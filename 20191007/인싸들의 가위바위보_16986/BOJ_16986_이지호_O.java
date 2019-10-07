import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16986 {
	static int relation[][];
	static int game[][];
	static int count[],turn[];;
	static int N, K;
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		relation = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				relation[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		game = new int[3][20];
		for (int i = 1; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 20; j++) {
				game[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit = new boolean[N + 1];
		count = new int[3];
		turn = new int[3];
		DFS(0, 1);
		System.out.println(check ? 1 : 0);
	}

	static boolean check;

	private static void DFS(int user1, int user2) {
		if (count[0] == K) {
			check = true;
			return;
		} else if (count[1] == K || count[2] == K)
			return;
		if (check)
			return;

		int g1 = user1;
		int g2 = user2;

		if (g1 == 0) {
			for (int i = 1; i <= N; i++) {
				if (!visit[i]) {
					int ret = decide(i, game[g2][turn[g2]], g1, g2);
					visit[i] = true;
					turn[g2]++;
					count[ret]++;
					DFS(ret, 3 - g1 - g2);
					visit[i] = false;
					turn[g2]--;
					count[ret]--;
				}
			}
		} else if (g2 == 0) {
			for (int i = 1; i <= N; i++) {
				if (!visit[i]) {
					int ret = decide(game[g1][turn[g1]], i, g1, g2);
					visit[i] = true;
					turn[g1]++;
					count[ret]++;
					DFS(ret, 3 - g1 - g2);
					visit[i] = false;
					turn[g1]--;
					count[ret]--;
				}
			}
		} else {
			int ret = decide(game[g1][turn[g1]], game[g2][turn[g2]], g1, g2);
			turn[g1]++;
			turn[g2]++;
			count[ret]++;
			DFS(ret, 3 - g1 - g2);
			turn[g1]--;
			turn[g2]--;
			count[ret]--;
		}

	}

	private static int decide(int pick1, int pick2, int g1, int g2) {
		if (relation[pick1][pick2] == 2) {
			return g1;
		} else if (relation[pick1][pick2] == 1) {
			return Math.max(g1, g2);
		} else {
			return g2;
		}
	}

}
