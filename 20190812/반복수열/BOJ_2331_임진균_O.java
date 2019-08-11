import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	static int A;
	static int P;
	
	static int visited[];
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		// A, P 입력
		A = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		// visited 배열 생성 및 초기화
		visited = new int[236196 + 1]; // A = 9999, P = 5일 때 수열 D가 가질 수 있는 최대 값
		Arrays.fill(visited, 2);
		
		writer.write(solve() + "\n");
		writer.flush();
	}
	
	public static int solve()
	{
		HashSet<Integer> vertices = new HashSet<Integer>();
		
		visited[A]--;
		vertices.add(A);
		return dfs(A, vertices);
	}
	
	public static int dfs(int here, HashSet<Integer> vertices)
	{
		int there = getNextD(here);
		
		// 기저 사례 : 한 노드에 3번 째 방문하려는 경우
		if(visited[there] == 0)
		{
			int count = 0;
			
			Iterator<Integer> iter = vertices.iterator();
			while(iter.hasNext())
			{
				// 수열에 남게 되는 수인 경우
				if(visited[iter.next()] > 0)
					count++;
			}
			
			return count;
		}
		
		visited[there]--;
		vertices.add(there);
		return dfs(there, vertices);
	}
	
	public static int getNextD(int value)
	{
		int ret = 0;
		while(value > 0)
		{
			ret += ((int)Math.pow(value % 10, P));
			value /= 10;
		}
		
		return ret;
	}

}