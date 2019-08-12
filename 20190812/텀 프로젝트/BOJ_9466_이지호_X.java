import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj9466 {

	static int[] visited;
	static StringTokenizer st;
	static ArrayList<Integer>[] graph;
	static int[]arr;
	static int starting;
	static int cnt;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			cnt=0;
			int n = Integer.parseInt(br.readLine());
			st=new StringTokenizer(br.readLine());
			graph= (ArrayList<Integer>[]) new ArrayList[n+1];

			for(int i =1; i<=n; i++) {
				graph[i] = new ArrayList<Integer>();
			}

			for(int i=1; i<=n; i++) {
				graph[i].add(Integer.parseInt(st.nextToken()));
			}

			for(int i =1; i<n+1; i++) {
				visited = new int[n + 1];
				visited[0]=i;
				starting =i;
				DFS(i,1);
			}
			System.out.println(cnt);
		}
	}

	private static void DFS(int start,int depth) {
		visited[depth]=graph[start].get(0);
		for(int i =0; i<depth; i++) {
			if(visited[i]==visited[depth]) {
				if(starting!=visited[depth]) {
					cnt++;
					return;
				}
			}
		}
		DFS(graph[start].get(0),depth+1);
	}

 

}