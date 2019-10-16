import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair implements Comparable<Pair>
	{
		int v;
		int w;
		
		public Pair() {}
		public Pair(int v, int w) 
		{
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Pair o) {
			return this.w - o.w;
		}
	}
	
	static final int INF = 987654321;
	static int V, E, K;
	static ArrayList<Pair> adjList[];
	static int dist[];
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		tokens = new StringTokenizer(in.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		
		K = Integer.parseInt(in.readLine());
		
		adjList = new ArrayList[V + 1];
		for(int i = 1; i <= V ;i++)
			adjList[i] = new ArrayList<Pair>();
		
		for(int i = 0 ; i < E ; i++)
		{
			tokens = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			int w = Integer.parseInt(tokens.nextToken());
			
			adjList[u].add(new Pair(v, w));
		}
		
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		
		solve();
		
		for(int i = 1 ; i <= V ; i++)
			out.write((dist[i] != INF ? (dist[i] + "") : "INF") + "\n");
		out.flush();
	}
	
	static void solve()
	{
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.offer(new Pair(K, 0));
		dist[K] = 0;
		
		while(!pq.isEmpty())
		{
			Pair here = pq.poll();
			
			for(int i = 0 ; i < adjList[here.v].size() ; i++)
			{
				Pair there = adjList[here.v].get(i);
				
				if(dist[here.v] + there.w < dist[there.v])
				{
					dist[there.v] = dist[here.v] + there.w;
					pq.offer(new Pair(there.v, dist[there.v]));
				}
			}
		}
	}
	
}