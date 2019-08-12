import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10451 {//순열 사이클
	static StringTokenizer st;
	static int[][] graph;
	static int[] arr;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T= Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			graph=new int[N+1][N+1];
			visited=new boolean[N+1];
			for(int i = 1; i<=N; i++) {
				int vertex=Integer.parseInt(st.nextToken());
				graph[i][vertex]=graph[vertex][i]=1;
			}
			int cnt=0;
			for (int i = 1; i < visited.length; i++) {
				if (visited[i] == false) {
					DFS(i, N);
					cnt++;
				}
			}
			System.out.println(cnt);
		}
		
	}
	private static void DFS(int start, int n) {
		visited[start]=true;
		
		for(int i =1; i<=n; i++) {
			if(graph[start][i]!=0&&!visited[i]) {
				DFS(i,n);
				visited[i]=true;
			}
		}
	}
}
