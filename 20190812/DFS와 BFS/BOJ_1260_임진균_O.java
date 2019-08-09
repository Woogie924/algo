import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	static int N; // 정점의 개수
	static int M; // 간선의 개수
	static int V; // 탐색을 시작할 정점의 번호
	
	static boolean graph[][];
	
	static boolean visited[];
	static boolean discovered[];
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		V = Integer.parseInt(tokenizer.nextToken());
		
		// 그래프 초기화
		graph = new boolean[N + 1][N + 1];
		for(int i = 0 ; i < M ; i++)
		{
			tokenizer = new StringTokenizer(reader.readLine());
			int here = Integer.parseInt(tokenizer.nextToken());
			int there = Integer.parseInt(tokenizer.nextToken());
			
			graph[here][there] = graph[there][here] = true;
		}
		
		// DFS, BFS를 위한 체크 배열 초기화
		visited = new boolean[N + 1];
		discovered = new boolean[N + 1];
		
		dfs(V);
		writer.newLine();
		bfs(V);
		writer.newLine();
		
		writer.flush();
	}
	
	static void dfs(int here) throws IOException
	{
		writer.write(here + " ");
		visited[here] = true;
		
		for(int there = 1 ; there <= N ; there++)
			if(graph[here][there] && !visited[there])
				dfs(there);
	}
	
	static void bfs(int start) throws IOException
	{
		Queue<Integer> q= new LinkedList<Integer>();
		
		// 초기 상태 지정
		q.add(start);
		discovered[start] = true;
		
		while(!q.isEmpty())
		{
			int here = q.poll();
			writer.write(here + " ");
			
			for(int there = 1 ; there <= N ; there++)
			{
				if(graph[here][there] && !discovered[there])
				{
					discovered[there] = true;
					q.add(there);
				}
			}
		}
	}
} 
