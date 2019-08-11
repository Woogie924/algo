import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 입력
 * N 정점
 * M 간선
 * V 시작할 정점
 * ::
 * 출력
 * V로 시작하여 DFS결과 BFS결과
 * 
 */
public class dfs와bfs
{
	static int N, M, V;
	static int[][] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		N = N + 1;
		graph = new int[N][N];
		visited = new boolean[N];
		int s, d;
		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			graph[s][d] = graph[d][s] = 1;
		}
		dfs(V, N - 2);
		System.out.println();
		Arrays.fill(visited, false);
		bfs(V);
	}
	// 0 안간데 1 현재 진행중 2 갓던데

	private static void bfs(int here)
	{
		Queue<Integer> q = new LinkedList<>();

		q.add(here);
		visited[here] = true;
		int cur = -1;
		while (!q.isEmpty())
		{
			cur = (int) q.poll();
			System.out.print(cur + " ");
			for (int i = 1; i < N; i++)
			{
				if (graph[cur][i] == 1)
				{
					if (!visited[i])
					{
						visited[i] = true;
						q.add(i);
					}
				}
			}

		}

	}
	static boolean flag = false;

	private static void dfs(int here, int toPick)
	{
		// record에 없는데 visited된곳
		if (toPick == 0)
		{
			flag = true;
			
			return;
		}
		if (toPick == N - 2)
		{
			System.out.print(here + " ");
			visited[here] = true;
		}
		
		for (int i = 1; i < N; i++)
		{
			if (flag) return;
			if (graph[here][i] == 1)
			{
				if (!visited[i])
				{
					visited[i] = true;// 여기서 하지말자
					System.out.print(i+" ");
					dfs(i, toPick - 1);
				}
			}
		}
	}
}
