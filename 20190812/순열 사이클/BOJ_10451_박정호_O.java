package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PermutationCycle_10451 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] map;
	static boolean[] visited;
	static int cnt;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; ++test_case) {
			cnt = 0;
			int n = Integer.parseInt(br.readLine());
			map = new int[n+1];
			visited = new boolean[n+1];
			String[] s = br.readLine().split("\\s");
			for(int i=1; i<=n; ++i) {
				map[i] = Integer.parseInt(s[i-1]);
			}
			
			// 모든 node 하나씩 검색
			for (int i = 1; i <= n; ++i) {
				if (!visited[i]) {
					dfs(n, i);	// dfs 하면서 연결요소 체크
					cnt++;		// 한 연결 요소를 모드 체크하면 cnt하나 증가
				}
			}
			System.out.println(cnt);
		}
		
	}

	private static void dfs(int n, int node) {
		if (visited[node]) {
			return;
		}
		visited[node] = true;
		dfs(n, map[node]);
	}
}
