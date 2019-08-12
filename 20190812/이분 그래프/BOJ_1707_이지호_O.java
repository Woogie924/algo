import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1707 {
	static int[] visited;
	static boolean[] vDetect;
	static StringTokenizer st;
	static boolean flag;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception {// 이분 그래프

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());// 정점수
			int E = Integer.parseInt(st.nextToken());// 간선 개수

			visited = new int[V + 1];
			vDetect = new boolean[V + 1];
			graph= (ArrayList<Integer>[]) new ArrayList[V+1];
			for(int i =1; i<=V; i++) {
				graph[i] = new ArrayList<Integer>();
			}
			for (int i = 0; i < E; i++) {
				
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				graph[u].add(v);
				graph[v].add(u);

			}

			flag = true;
			for (int i = 1; i < visited.length; i++) {
				if(visited[i]==0) {
					visited[i] = 1;
					DFS(i, 1);
				}
			}
			for (int i = 1; i < visited.length; i++) {
				if(!vDetect[i]) {
					vDetect[i] = true;
					Detect(i, 1, 1);
				}
			}
			if (flag == false) {
				System.out.println("NO");
			} else
				System.out.println("YES");
		}
	}

	private static void DFS(int start, int color) {
		color = -color;
		for (int i = 0; i < graph[start].size(); i++) {
			if (visited[graph[start].get(i)] == 0) {
				visited[graph[start].get(i)] = color;
				DFS(graph[start].get(i), color);
			}
		}
	}

	private static void Detect(int start, int depth, int color) {
		if (depth == visited.length - 1) {
			return;
		}
		for (int i = 0; i < graph[start].size(); i++) {
				if (color == 1 && visited[graph[start].get(i)] == -1) {
					if (!vDetect[graph[start].get(i)]) {
						vDetect[graph[start].get(i)] = true;
						Detect(graph[start].get(i), depth + 1, visited[graph[start].get(i)]);
					}
				} else if (color == -1 && visited[graph[start].get(i)] == 1) {
					if (!vDetect[graph[start].get(i)]) {
						vDetect[graph[start].get(i)] = true;
						Detect(graph[start].get(i), depth + 1, visited[graph[start].get(i)]);
					}
				} else flag = false;
			}
		}
}
