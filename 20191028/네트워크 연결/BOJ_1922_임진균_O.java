import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge
	{
		int u;
		int v;
		int cost;
		
		public Edge() {}
		public Edge(int u, int v, int cost) 
		{
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
	}
	
	static int N, M;
	static ArrayList<Edge> edgeList = new ArrayList<Edge>();

	static int parent[];
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		for(int i = 0 ; i < M ; i++)
		{
			tokens = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			int cost = Integer.parseInt(tokens.nextToken());
			
			edgeList.add(new Edge(u, v, cost));
		}
		
		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		// 간선 비용의 오름차순으로 정렬한다.
		edgeList.sort(new Comparator<Edge>() {
			public int compare(Edge o1, Edge o2) {
				return o1.cost - o2.cost;
			}
		});
		
		makeSet();
		
		int picked = 0;
		int minCost = 0;
		
		for(int i = 0 ; i < edgeList.size() ; i++)
		{
			if(picked == N)
				break;
			
			Edge e = edgeList.get(i);
			
			if(findSet(e.u) != findSet(e.v))
			{
				unionSet(e.u, e.v);
				picked++;
				minCost += e.cost;
			}
		}
		
		return minCost;
	}
	
	static void makeSet()
	{
		parent = new int[N + 1];
		for(int v = 1 ; v <= N ; v++)
			parent[v] = v;
	}
	
	static int findSet(int v)
	{
		if(parent[v] == v)
			return v;
		
		return parent[v] = findSet(parent[v]);
	}
	
	static void unionSet(int u, int v)
	{
		u = findSet(u);
		v = findSet(v);
		
		if(u != v)
			parent[u] = v;
	}
	
}