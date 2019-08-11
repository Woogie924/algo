import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	static int N;
	static ArrayList<ArrayList <Integer> > adjList; // 인접 리스트
	
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 인접 리스트 생성
		adjList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 1000 + 1; i++)
			adjList.add(new ArrayList<Integer>());
		
		// visited 배열 생성
		visited = new boolean[1000 + 1];
		
		// 테스트케이스의 수
		int T = Integer.parseInt(reader.readLine());
		for(int tc = 0 ; tc < T ; tc++)
		{
			// 순열의 크기 N
			N = Integer.parseInt(reader.readLine());
	
			// 인접 리스트 초기화
			for (int i = 1; i <= N; i++)
				adjList.get(i).clear();
			
			// visited 배열 초기화
			Arrays.fill(visited, false);
			
			// 순열
			tokenizer = new StringTokenizer(reader.readLine());
			for(int i = 1 ; i <= N ; i++)
			{
				int here = i;
				int there = Integer.parseInt(tokenizer.nextToken());
				
				// i에서 πi로의 단방향 간선 생성
				adjList.get(here).add(there);
			}
		
			writer.write(solve() + "\n");
		}

		writer.flush();
	}

	public static int solve()
	{
		int count = 0;
		for(int start = 1 ; start <= N ; start++)
		{
			if(!visited[start])
			{
				count++;
				
				visited[start] = true;
				dfs(start);
			}
		}
		
		return count;
	}
	
	public static void dfs(int here)
	{
		int there = adjList.get(here).get(0);
		
		if(!visited[there])
		{
			visited[there] = true;
			dfs(there);
		}
	}
	
}
