import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader in;
	static BufferedWriter out;
	
	static int V, E;
	static ArrayList<ArrayList<Integer> > adjList;
	static boolean discovered[];
	static int color[];
	
	// 각 정점에 부여할 흰색과 검은색
	static final int black = 0;
	static final int white = 1;

	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		adjList = new ArrayList<ArrayList<Integer> >();
		for(int v = 0 ; v <= 20000 ; v++)
			adjList.add(new ArrayList<Integer>());
		
		discovered = new boolean[20000 + 1];
		color = new int[20000 + 1];
		
		int T = Integer.parseInt(in.readLine());
		for(int tc = 0 ; tc < T ; tc++)
		{
			// 정점 및 간선의 개수
			tokenizer = new StringTokenizer(in.readLine());
			V = Integer.parseInt(tokenizer.nextToken());
			E = Integer.parseInt(tokenizer.nextToken());
			
			// 간선 및 discovered 배열 초기화
			for(int v = 1 ; v <= V ; v++)
			{
				adjList.get(v).clear();
				discovered[v] = false;
				color[v] = -1;
			}
			
			// 간선 정보
			for(int e = 1 ; e <= E ; e++)
			{
				tokenizer = new StringTokenizer(in.readLine());
				int u = Integer.parseInt(tokenizer.nextToken());
				int v = Integer.parseInt(tokenizer.nextToken());
				
				adjList.get(u).add(v);
				adjList.get(v).add(u);
			}
			
			out.write((solve() ? "YES" : "NO") + "\n");
		}

		out.flush();
	}
	
	public static boolean solve()
	{
		for(int start = 1 ; start <= V ; start++)
			if(!discovered[start])
				if(!bfs(start))
					return false;
		
		return true;
	}

	// start 정점을 포함하는 그래프가 이분 그래프인지 여부를 반환한다.
	public static boolean bfs(int start)
	{
		Queue<Integer> q = new LinkedList<Integer>();

		// start 정점부터 시작하며, 흰색으로 시작한다.
		q.add(start); 
		discovered[start] = true;
		color[start] = white;

		while (!q.isEmpty()) 
		{
			int here = q.poll();

			int size = adjList.get(here).size();
			for (int i = 0; i < size; i++) 
			{
				int there = adjList.get(here).get(i);

				// 다음 정점이 이미 방문했던 곳인데, 현재 정점과 같은 색인 경우
				if(discovered[there] && color[there] == color[here])
					return false;
				
				if (!discovered[there]) 
				{
					q.add(there);
					discovered[there] = true;
					color[there] = (color[here] == black ? white : black);
				}
			}
		}
		
		return true;
	}
	
}