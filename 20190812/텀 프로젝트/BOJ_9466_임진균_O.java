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
	static ArrayList<ArrayList<Integer> > adjList;
	
	static int visited[]; // 정점에 방문할 수 있는 횟수
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		// 인접 리스트 객체 할당
		adjList = new ArrayList<ArrayList<Integer> >();
		for(int i = 0 ; i < 100000 + 1 ; i++)
			adjList.add(new ArrayList<Integer>());
		
		// visited 배열 객체 할당
		visited = new int[100000 + 1];

		// 테스트케이스의 수
		int T = Integer.parseInt(reader.readLine());
		for(int tc = 0 ; tc < T ; tc++)
		{
			// 학생의 수 N
			N = Integer.parseInt(reader.readLine());
			
			// 초기화
			for(int i = 1 ; i <= N ; i++)
			{
				adjList.get(i).clear();
				visited[i] = 2;
			}
			
			// 선택된 학생들의 번호를 입력받고, 인접 리스트를 생성한다.
			st = new StringTokenizer(reader.readLine());
			for(int i = 1 ; i <= N ; i++)
			{
				int here = i;
				int there = Integer.parseInt(st.nextToken());
				
				adjList.get(here).add(there);
			}

			writer.write(solve() + "\n");
		}

		writer.flush();
	}
	
	public static int solve()
	{
		int count = 0; // 프로젝트 팀에 속하지 못한 학생 수
		ArrayList<Integer> students = new ArrayList<Integer>(); // 학생 번호를 저장하는 리스트
		
		for(int start = 1 ; start <= N ; start++)
		{
			// 한번도 방문하지 않은 정점인 경우
			if(visited[start] == 2)
			{
				visited[start]--;
				students.add(start);
				count += dfs(start, students);
				
				students.clear();
			}
		}
		
		return count;
	}

	public static int dfs(int here, ArrayList<Integer> students)
	{
		int there = adjList.get(here).get(0);
		
		// 기저 사례 : 더 이상 다음 정점으로 이동할 수 없는 경우
		if(visited[there] == 0)
		{
			// visited 배열에 양수가 저장되어 있는 정점은
			// 프로젝트 팀에 속하지 못한 학생이다.
			int count = 0;
			int size = students.size();
			for(int i = 0 ; i < size ; i++)
			{
				if(visited[students.get(i)] > 0)
				{
					count++;
					visited[students.get(i)] = 0;
				}
			}
			
			return count;
		}
		
		visited[there]--;
		students.add(there);
		return dfs(there, students);
	}
	
}

