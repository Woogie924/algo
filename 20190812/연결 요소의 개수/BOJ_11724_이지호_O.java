import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11724 {//연결 요소의 개수
	static StringTokenizer st;
	static int[][] graph;
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new int[N + 1][N + 1];
		arr = new int[N + 1];
		visited = new boolean[N + 1];
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[u][v] = graph[v][u]=1;
		}
		// boolean flag = false;
		int index = 1;
		for (int i = 1; i < visited.length; i++) {
			if (visited[i] == false) {
				index = i;
				DFS(index, 0, N);
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static void DFS(int start, int depth, int n) {

		visited[start] = true;
		arr[depth] = start;
		for (int i = 1; i <= n; i++) {
			if (graph[start][i] != 0 && !visited[i]) {
				DFS(i, depth + 1, n);
				visited[i] = true;
			}
		}
	}
}
