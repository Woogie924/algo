import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
//이분그래프

public class 이분그래프
{
	static int T, V, E;
	static int[] state;
	static List<List<Integer>> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		state = new int[20002];// 0 안들림 1,2 - 1팀,2팀
		list = new ArrayList<>();
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 0; tc < T; tc++)
		{
//			Arrays.fill(state, 0);
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			Arrays.fill(state, 0);
			list.clear();
			for (int i = 0; i <= V; i++)
			{
				list.add(new ArrayList<>()); 
			}
			
			int start;
			int destini;
			for (int i = 0; i < E; i++)
			{
				st = new StringTokenizer(br.readLine());
				start = Integer.parseInt(st.nextToken());
				destini = Integer.parseInt(st.nextToken());
				
				list.get(start).add(destini);
				list.get(destini).add(start);
			}
			flag = false;
			count = 0;
			for (int i = 1; i <= V; i++)// 1 / 3 1 / 1 2
			{
				if(count == V) break;
				dfs(i);
			}
			if(flag) System.out.println("NO");
			else System.out.println("YES");
		}
		
	}

	static boolean flag;
	static int count;
	private static void dfs(int cur)
	{
		if (state[cur] == 0)// 처음 시작시 팀1로 초기화
		{
			state[cur] = 1;
			count++;
		} 
		for (int dd : list.get(cur))
		{
			if (state[dd] == 0)
			{
				state[dd] = 3 - state[cur];
				count++;
				dfs(dd);
				
			} else
			{
				if (state[dd] == state[cur])
				{
					flag = true;
					return;
				}
			}
		}
	}

	static class Edge
	{
		int s;
		int d;

		public Edge(int start, int destination)
		{
			s = start;
			d = destination;
		}
	}
}