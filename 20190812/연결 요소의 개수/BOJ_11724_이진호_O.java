import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 연결요소갯수
{
	static int M, N;
	static boolean[] visited;
	static int[][] map;
	static int nCount;

	public static void main(String[] args) throws NumberFormatException, IOException
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 정점
		M = Integer.parseInt(st.nextToken());// 간선
		N += 1;// 1~N까지 사용 0 X

		visited = new boolean[N];
		map = new int[N][N];

		for (int tc = 0; tc < M; tc++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			map[s][d] = map[d][s] = 1;

		}
		int count = 0;
		for (int i = 1; i < N; i++)
		{
			if (!visited[i])
			{
				count += dfs(i);
			}
		}
		System.out.println(count);
	}

	private static int dfs(int index)
	{
		int count = 0;
		
		for(int i = 1 ; i < N ;i++)
		{
			if(map[index][i] ==1 && !visited[i])//dfs네
			{
				visited[i] = true;
				dfs(i);
			}
		}
		return 1;
	}

	static class Data
	{
		int start;
		int des;

		public Data(int start, int des)
		{
			this.start = start;
			this.des = des;
		}
	}
}