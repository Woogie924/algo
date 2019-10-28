import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static int V;
	static int E;
	static int K;
	static ArrayList<Node>[] graph;
	static int[] distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] s = br.readLine().split("\\s");
		
		V = Integer.parseInt(s[0]);
		E = Integer.parseInt(s[1]);		
		K = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[V+1];
		for(int i=0; i<V+1; ++i) {
			graph[i] = new ArrayList<>();
		}
		distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for(int i=0; i<E; ++i) {
			s = br.readLine().split("\\s");
			int start = Integer.parseInt(s[0]);
			int index = Integer.parseInt(s[1]);
			int cost = Integer.parseInt(s[2]);
			
			graph[start].add(new Node(index, cost)); 
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		distance[K] = 0;
		pq.add(new Node(K, distance[K]));
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			for(int i=0; i<graph[curr.index].size(); ++i) {
				Node nextNode = graph[curr.index].get(i);
				
				if(distance[nextNode.index] > distance[curr.index] + nextNode.cost) {
					distance[nextNode.index] = distance[curr.index] + nextNode.cost;
					pq.offer(new Node(nextNode.index, distance[nextNode.index]));
				}
			}
		}
		for(int i =1; i<distance.length; ++i) {
			String answer = distance[i] == Integer.MAX_VALUE ? "INF" : String.valueOf(distance[i]);
			sb.append(answer+"\n");
		}
		System.out.println(sb);
	}
	static class Node implements Comparable<Node>{
		int index;
		int cost;
		
		public Node(int index, int cost) {
			super();
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost == o.cost ? this.index - o.index : this.cost - o.cost;
		}		
	}
	
}
