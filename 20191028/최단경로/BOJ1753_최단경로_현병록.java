import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753_최단경로 {
	static class Node implements Comparable<Node>{
		int end, val;

		public Node(int end, int val) {
			super();
			this.end = end;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			return this.val-o.val;
		}
		
	}
	static ArrayList<Node>[] nodes;
	static int size, times, start, d[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		times = Integer.parseInt(st.nextToken());
		nodes = new ArrayList[size+1];
		d = new int[size+1];
		for(int i=0; i<=size; i++) nodes[i] = new ArrayList<>();
		start = Integer.parseInt(br.readLine());
		for(int i = 0; i<times; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Arrays.fill(d, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start]=0;
		Node temp;
		while(!pq.isEmpty()) {
			temp = pq.poll();
			for(Node n : nodes[temp.end]) {
				if(d[n.end] > temp.val + n.val) {
					d[n.end] = temp.val+n.val;
					pq.offer(new Node(n.end, d[n.end]));
				}
			}
		}
		for(int i=1; i<=size; i++)
		System.out.println((d[i]!=Integer.MAX_VALUE )? d[i] : "INF");
	}
}
