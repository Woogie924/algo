import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_수영장_1952_박정호_O {
	static int answer;
	static int[] cost;
	static int[] month;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; ++test_case) {
			cost = new int[4];
			month = new int[13];
			String[] s = br.readLine().trim().split(" ");
			for (int i = 0; i < 4; ++i) {
				cost[i] = Integer.parseInt(s[i]);
			}
			s = br.readLine().trim().split(" ");
			for (int i = 1; i <= 12; ++i) {
				month[i] = Integer.parseInt(s[i - 1]);
			}
			// 1년권으로 퉁~ answer의 최댓값
			answer = cost[3];
			// 현재 월, 현재 금액
			dfs(1, 0);
			System.out.println("#" + test_case + " " + answer);
		}
	}

	private static void dfs(int now, int sum) {
		if (now > 12) {
			answer = Math.min(answer, sum);
			return;
		}
		dfs(now + 1, sum + cost[0] * month[now]);
		dfs(now + 1, sum + cost[1]);
		dfs(now + 3, sum + cost[2]);
	}

}
