import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static class Pos
	{
		int x;
		int y;
		
		public Pos() {}
		public Pos(int x, int y) 
		{
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge
	{
		int u;
		int v;
		int dist;
		
		public Edge() {}
		public Edge(int u, int v, int dist) 
		{
			this.u = u;
			this.v = v;
			this.dist = dist;
		}
	}
	
	static int M, N;
	static int map[][] = new int[10][10];
	static final int INF = Integer.MAX_VALUE;
	
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		String tokens[] = in.readLine().split(" ");
		
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);

		for(int i = 0 ; i < N ; i++)
		{
			tokens = in.readLine().split(" ");
			for(int j = 0 ; j < M ; j++)
				map[i][j] = Integer.parseInt(tokens[j]);
		}
		
		out.write(solve() + "");
		out.flush();
	}

	static int solve()
	{
		// 모든 섬을 찾는다.
		ArrayList<ArrayList<Pos>> islands = findIslands();
		
		// 섬 사이의 거리를 구한다.
		int V = islands.size();
		int dists[][] = new int[V + 1][V + 1];
		for(int u = 1 ; u <= V ; u++)
			for(int v = 1 ; v <= V ; v++)
				dists[u][v] = INF;
		
		for(int i = 0 ; i < islands.size() ; i++)
		{
			ArrayList<Pos> island = islands.get(i);
			
			for(int j = 0 ; j < island.size(); j++)
			{
				int x = island.get(j).x;
				int y = island.get(j).y;
				
				for(int d = 0 ; d < 4 ; d++)
				{
					int nx = x + dx[d];
					int ny = y + dy[d];
					int dist = 0;
					
					while(nx >= 0 && nx < N && ny >= 0 && ny < M)
					{
						if(map[x][y] == map[nx][ny])
							break;
						
						if(map[nx][ny] > 0 && map[x][y] != map[nx][ny])
						{
							if(dist > 1)
								dists[map[x][y]][map[nx][ny]] = Math.min(dists[map[x][y]][map[nx][ny]], dist);
							
							break;
						}
							
						nx += dx[d];
						ny += dy[d];
						dist++;
					}
				}
			}
		}
		
		// 크루스칼을 사용해 정답을 계산한다.
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		for(int u = 1 ; u <= V ; u++)
			for(int v = u + 1 ; v <= V ; v++)
				if(dists[u][v] != INF)
					edgeList.add(new Edge(u, v, dists[u][v]));
		
		int parent[] = new int[V + 1];
		makeSet(parent);
		
		edgeList.sort(new Comparator<Edge>() {
			public int compare(Edge o1, Edge o2) {
				return o1.dist - o2.dist;
			}
		});
		
		int result = 0;
		int picked = 0;
		for(int i = 0 ; i < edgeList.size() ; i++)
		{
			Edge edge = edgeList.get(i);
			
			if(picked == V - 1)
				break;
			
			if(findSet(parent, edge.u) != findSet(parent, edge.v))
			{
				unionSet(parent, edge.u, edge.v);
				result += edge.dist;
				picked++;
			}
		}
		
		return (picked == V - 1 ? result : -1);
	}
	
	static ArrayList<ArrayList<Pos>> findIslands()
	{
		ArrayList<ArrayList<Pos>> islands = new ArrayList<ArrayList<Pos>>();
		boolean discovered[][] = new boolean[N][M];
		
		for(int sx = 0 ; sx < N ; sx++)
		{
			for(int sy = 0 ; sy < M ; sy++)
			{
				if(map[sx][sy] == 0 || discovered[sx][sy])
					continue;
				
				Queue<Pos> q = new LinkedList<Pos>();
				ArrayList<Pos> island = new ArrayList<Pos>();
				
				q.offer(new Pos(sx, sy));
				discovered[sx][sy] = true;
				island.add(new Pos(sx, sy));
				map[sx][sy] = islands.size() + 1;
				
				while(!q.isEmpty())
				{
					Pos p = q.poll();
					
					for(int d = 0 ; d < 4 ; d++)
					{
						int nx = p.x + dx[d];
						int ny = p.y + dy[d];
						
						if(nx < 0 || nx >= N || ny < 0 || ny >= M || 
								map[nx][ny] == 0 || discovered[nx][ny])
							continue;
						
						q.offer(new Pos(nx ,ny));
						discovered[nx][ny] = true;
						island.add(new Pos(nx, ny));
						map[nx][ny] = islands.size() + 1;
					}
				}
				
				islands.add(island);
			}
		}
		
		return islands;
	}
	
	static void makeSet(int parent[])
	{
		for(int v = 0 ; v < parent.length ; v++)
			parent[v] = v;
	}
	
	static int findSet(int parent[], int v)
	{
		if(parent[v] == v)
			return v;
		
		return parent[v] = findSet(parent, parent[v]);
	}
	
	static void unionSet(int parent[], int u, int v)
	{
		u = findSet(parent, u);
		v = findSet(parent, v);
		
		if(u != v)
			parent[u] = v;
	}
}
