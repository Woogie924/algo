import java.io.*;
import java.util.*;

public class Main {
	
	static class Pair implements Comparable<Pair>
	{
		int v;
		int cost;
		
		public Pair() {}
		public Pair(int v, int cost) 
		{
			this.v = v;
			this.cost = cost;
		}
		
		public int compareTo(Pair o) {
			return this.cost - o.cost;
		}
	}
	
	static BufferedReader in;
	static BufferedWriter out;
	
	static final int INF = 987654321;
	static int N, M;
	static ArrayList<Pair> adjList[];
	
	public static void main(String[] args) throws IOException 
	{
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		adjList = new ArrayList[N + 1];
		for(int i = 1 ; i <= N ; i++)
			adjList[i] = new ArrayList<Pair>();
		
		for(int i = 0 ; i < M ; i++)
		{
			tokens = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			int cost = Integer.parseInt(tokens.nextToken());
			
			adjList[u].add(new Pair(v, cost));
			adjList[v].add(new Pair(u, cost));
		}
		
		solve();
		out.flush();
	}
	
	static void solve() throws IOException
	{
		int minDist[] = new int[N + 1];	// 두 컴퓨터 사이의 최소거리를 저장한다.
		int prev[] = new int[N + 1]; 	// 현재 정점들이 어떻게 연결되어 있는가에 대한 정보
		
		for(int v = 1 ; v <= N ; v++)
		{
			minDist[v] = INF;
			prev[v] = -1;
		}
		
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.offer(new Pair(1, 0));
		minDist[1] = 0;
		
		while(!pq.isEmpty())
		{
			Pair p = pq.poll();
			int here = p.v;
			
			for(int i = 0 ; i < adjList[here].size() ; i++)
			{
				int there = adjList[here].get(i).v;		// there 컴퓨터
				int cost = adjList[here].get(i).cost;	// here과 there사이의 통신 시간
				
				if(minDist[here] + cost < minDist[there])
				{
					minDist[there] = minDist[here] + cost;
					pq.offer(new Pair(there, minDist[there]));
					prev[there] = here;
				}
			}
		}
		
		int K = 0;
		for(int v = 1 ; v <= N ; v++)
			if(prev[v] != -1)
				K++;
		
		out.write(K + "\n");
		for(int v = 1 ; v <= N ; v++)
			if(prev[v] != -1)
				out.write(v + " " + prev[v] + "\n");
	}
}