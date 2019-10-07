import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16986_이진호_X
{
	static int N, K;
	static int[][] map;
	static int[] gh;
	static int[] mh;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];

		for (int y = 1; y <= N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for (int x = 1; x <= N; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		gh = new int[20];
		mh = new int[20];

		st = new StringTokenizer(br.readLine());
		for (int x = 0; x < 20; x++)
		{
			gh[x] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int x = 0; x < 20; x++)
		{
			mh[x] = Integer.parseInt(st.nextToken());
		}

		perm(new boolean[N + 1], new int[N + 1], 0);
	}
	private static void perm(boolean[] visited, int[] result, int depth)
	{
		if (depth == N)
		{
			check(result);
			return;
		}

		for (int i = 1; i <= N; i++)
		{
			if (!visited[i])
			{
				visited[i] = true;
				result[depth] = i;
				perm(visited, result, depth + 1);
				visited[i] = false;

			}
		}
	}
	private static boolean check(int[] result)// 지우가 이기는 수가 잇으면 true
	{
		int[] turnCount = new int[3];
		int[] winCount = new int[3];
		int user1 = 0;
		int user2 = 1;
		int userNext = 3 - user1 - user2;
		
		for(int i = 0 ; i < 20 ; i++)
		{
			sovle();
		}
		
		
		return false;
	}


}
