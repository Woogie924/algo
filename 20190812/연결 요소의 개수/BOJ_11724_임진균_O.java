import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	static int N; // 정점의 개수
	static int M; // 간선의 개수
	static ArrayList<ArrayList <Integer> > adjList; // 인접 리스트
	
	static boolean visited[];
	static boolean discovered[];
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// N, M 입력
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		// 인접 리스트 초기화
		adjList = new ArrayList<ArrayList<Integer> >();
		for(int i = 0 ; i < N + 1 ; i++)
			adjList.add(new ArrayList<Integer>());
		
		// 간선 정보 입력
		for(int i = 0 ; i < M ; i++)
		{
			tokenizer = new StringTokenizer(reader.readLine());
			int u = Integer.parseInt(tokenizer.nextToken());
			int v = Integer.parseInt(tokenizer.nextToken());
				
			// 양방향 간선
			adjList.get(u).add(v);
			adjList.get(v).add(u);
		}
		
		// dfs를 위한 체크 배열 초기화
		visited = new boolean[N + 1];
		discovered = new boolean[N + 1];
		
		writer.write(solve() + "");
		writer.flush();
	}

	public static int solve()
	{
		int count = 0;
		for(int start = 1 ; start <= N ; start++)
		{
//			if(!visited[start])
//			{
//				count++;
//				dfs(start);
//			}
			
			if(!discovered[start])
			{
				count++;
				bfs(start);
			}
		}
		
		return count;
	}
	
	// here 노드와 연결된 모든 노드를 방문한다.
	public static void dfs(int here)
	{
		visited[here] = true;
		
		int size = adjList.get(here).size();
		for(int i = 0 ; i < size ; i++)
		{
			int there = adjList.get(here).get(i);
			
			if(!visited[there])
				dfs(there);
		}	
	}
	
	// here 노드와 연결된 모든 노드를 방문한다.
	public static void bfs(int start)
	{
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(start);
		discovered[start] = true;
		
		while(!q.isEmpty())
		{
			int here = q.poll();
			
			int size = adjList.get(here).size();
			for(int i = 0 ; i < size ; i++)
			{
				int there = adjList.get(here).get(i);
				
				if(!discovered[there])
				{
					q.add(there);
					discovered[there] = true;
				}
			}	
		}
	}
}
