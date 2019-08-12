
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1260 {

	static int[][] graph;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());
		int V = Integer.parseInt(tokenizer.nextToken());

		graph = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(tokenizer.nextToken());
			int y = Integer.parseInt(tokenizer.nextToken());

			graph[x][y] = graph[y][x]=1;
		}

		arr = new int[N + 1];
		visited = new boolean[N + 1];
		DFS(V, 0, N);
		System.out.println();
		visited = new boolean[N + 1];
		BFS(V,N);

	}

	static boolean[] visited;
	static int[] arr;
	private static void DFS(int start, int depth, int n) {

		visited[start] = true;
		arr[depth] = start;
		System.out.print(arr[depth]+" ");
		/*
		 * if (depth == n - 1) { for (int i = 1; i <= n; i++) { System.out.print(arr[i]
		 * + " "); } System.out.println(); return; }
		 
		visited[start] = true;
		arr[depth] = start;*/
		for (int i = 1; i <= n; i++) {
			if (graph[start][i] != 0 && !visited[i]) {
				DFS(i, depth + 1, n);
				visited[i] = true;
			}
		}
	}

	private static void BFS(int start,int n) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start]=true;
		while(!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp+" ");
			for(int i = 1; i<=n; i++) {
				if(graph[temp][i]!=0 &&!visited[i]) {
					q.add(i);
					visited[i]=true;;
				}
			}
		}
		System.out.println();
	}

}