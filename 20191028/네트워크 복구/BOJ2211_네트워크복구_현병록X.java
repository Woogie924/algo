import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2211_네트워크복구 {
	static class Node implements Comparable<Node>{
		int s, e, v;

		public Node(int s, int e, int v) {
			super();
			this.s = s;
			this.e = e;
			this.v = v;
		}

		@Override
		public int compareTo(Node o) {
			return this.v - o.v;
		}
	}
	static int n,m,parent[], start, dest, val, sum;
	static List<Node> nodes;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		nodes = new ArrayList<>();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		makeSet(n+1);
		sum=0;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			dest = Integer.parseInt(st.nextToken());
			val = Integer.parseInt(st.nextToken());
			nodes.add(new Node(start, dest, val));
		}
		Collections.sort(nodes);
		sb.append(n-1).append("\n");
		for(Node n : nodes) {
			if(findSet(n.s)!=findSet(n.e)) {
				unionSet(n.s, n.e);
				sb.append(n.s).append(" ").append(n.e).append("\n");
				sum+=n.v;
			}
		}
		System.out.println(sb.toString());
	}
	private static void unionSet(int num, int comp) {
		int x = findSet(num);
		int y = findSet(comp);
		if(x!=y) {
			parent[x]=y;
		}
	}
	private static int findSet(int num) {
		if(parent[num]!=num)
			parent[num] = findSet(parent[num]);
		return parent[num];
	}
	private static void makeSet(int size) {
		parent = new int[size];
		for(int i=0; i<size; i++) parent[i] = i;
	}
}
