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
	static int N, M;
	static int begin, end;
	static ArrayList<Pair> adjList[];
	static int dist[];
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		adjList = new ArrayList[N + 1];
		for(int i = 1; i <= N ;i++)
			adjList[i] = new ArrayList<Pair>();
		
		for(int i = 0 ; i < M ; i++)
		{
			tokens = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			int w = Integer.parseInt(tokens.nextToken());
			
			adjList[u].add(new Pair(v, w));
		}
		
		tokens = new StringTokenizer(in.readLine());
		begin = Integer.parseInt(tokens.nextToken());
		end = Integer.parseInt(tokens.nextToken());
		
		dist = new int[N + 1];
		Arrays.fill(dist, INF);
		
		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.offer(new Pair(begin, 0));
		dist[begin] = 0;
		
		while(!pq.isEmpty())
		{
			Pair here = pq.poll();
			
			if(here.v == end)
				break;
			
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
		
		return dist[end];
	}
	
}