import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260_Çöº´·Ï_O {
	static boolean visit[];
	static ArrayList<Integer> graph[];
	static StringBuffer sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), m=Integer.parseInt(st.nextToken()), v=Integer.parseInt(st.nextToken());
		graph = new ArrayList[n+1];
		for(int i =0; i<n+1; i++) {
			graph[i] = new ArrayList<>();
		}
		visit = new boolean[n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
			graph[x].add(y); graph[y].add(x);
		}
		for(int i=0; i<n; i++) {
			Collections.sort(graph[i]);
		}
		sb = new StringBuffer();
		dfs(v);
		System.out.println(sb);
		visit = new boolean[n+1];
		bfs(v);
		System.out.println(sb);
	}
	private static void dfs(int v) {
		visit[v]=true;
		sb.append(v+" ");
		Iterator<Integer> iterator = graph[v].iterator();
		while(iterator.hasNext()) {
			int next = iterator.next();
			if(!visit[next])
				dfs(next);
		}
	}
	private static void bfs(int v) {
		sb = new StringBuffer();
		Queue<Integer> queue = new LinkedList<>();//queue
		queue.offer(v);
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int j = 0; j<size; j++) {
				int idx = queue.poll();
				visit[idx] = true;
				sb.append(idx+" ");
				Iterator<Integer> iterator = graph[idx].iterator();
				while(iterator.hasNext()) {
					int next = iterator.next();
					if(!visit[next]) {
						queue.add(next);
						visit[next]=true;
					}
				}
			}
		}
	}

}
