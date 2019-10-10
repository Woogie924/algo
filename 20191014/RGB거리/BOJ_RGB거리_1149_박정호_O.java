import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_RGB거리_1149_박정호_O {
	static int N;
	static int[][] map;
	static int[][] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		memo = new int[N][3];
		String[] s;
		for (int i = 0; i < N; ++i) {
			s = br.readLine().split("\\s");
			map[i][0] = Integer.parseInt(s[0]);
			map[i][1] = Integer.parseInt(s[1]);
			map[i][2] = Integer.parseInt(s[2]);
		}
		
		memo[0][0] = map[0][0];
		memo[0][1] = map[0][1];
		memo[0][2] = map[0][2];
		for(int i=1; i<N; ++i) {
			memo[i][0] = Math.min(memo[i-1][1]+map[i][0], memo[i-1][2]+map[i][0]);
			memo[i][1] = Math.min(memo[i-1][0]+map[i][1], memo[i-1][2]+map[i][1]);
			memo[i][2] = Math.min(memo[i-1][0]+map[i][2], memo[i-1][1]+map[i][2]);
		}
		int answer = Math.min(memo[N-1][0], memo[N-1][1]);
		answer = Math.min(answer, memo[N-1][2]);
		System.out.println(answer);
	}
}
