import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static int N; // 구역의 개수
	static int people[] = new int[10 + 1]; // 각 구역의 인구수
	static ArrayList<Integer> adjList[] = new ArrayList[10 + 1]; // 인접 리스트
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		String tokens[] = null;
		
		N = Integer.parseInt(in.readLine());
		
		tokens = in.readLine().split(" ");
		for(int i = 1 ; i <= N ; i++)
			people[i] = Integer.parseInt(tokens[i - 1]);
		
		for(int i = 1 ; i <= N ; i++)
		{
			tokens = in.readLine().split(" ");
			adjList[i] = new ArrayList<Integer>();
			
			int count = Integer.parseInt(tokens[0]);
			for(int j = 1 ; j <= count ; j++)
				adjList[i].add(Integer.parseInt(tokens[j]));
		}
		
		int result = solve();
		out.write((result == Integer.MAX_VALUE ? -1 : result) + "");
		out.flush();
	}

	static int solve()
	{
		int minDiff = Integer.MAX_VALUE;
		int count = (N % 2 == 0 ? N / 2 : N / 2 + 1);
		
		for(int toPick = 1 ; toPick <= count ; toPick++)
			minDiff = Math.min(minDiff, pick(toPick, new ArrayList<Integer>()));
		
		return minDiff;
	}
	
	static int pick(int toPick, ArrayList<Integer> picked)
	{
		if(toPick == 0)
		{
			int area[] = new int[N + 1];
			
			// picked에 저장된 선거구를 배열에 표시한다.
			for(int i = 0 ; i < picked.size() ; i++)
				area[picked.get(i)] = 1;

			Queue<Integer> q = new LinkedList<Integer>();
			boolean discovered[] = new boolean[N + 1];
			int count[] = new int[2];
			
			for(int v = 1 ; v <= N ; v++)
			{
				if(discovered[v])
					continue;
				
				count[area[v]]++;
				
				q.add(v);
				discovered[v] = true;
				
				while(!q.isEmpty())
				{
					int here = q.poll();
					
					for(int i = 0 ; i < adjList[here].size() ; i++)
					{
						int there = adjList[here].get(i);
						
						if(area[here] != area[there] || discovered[there])
							continue;
						
						q.add(there);
						discovered[there] = true;
					}
				}
			}
			
			if(count[0] > 1 || count[1] > 1)
				return Integer.MAX_VALUE;
			
			int sum[] = new int[2];
			for(int v = 1 ; v <= N ; v++)
				sum[area[v]] += people[v];
				
			return Math.abs(sum[0] - sum[1]);
		}
		
		int minDiff = Integer.MAX_VALUE;
		int smallest = picked.isEmpty() ? 1 : picked.get(picked.size() - 1);
		for(int next = smallest ; next <= N ; next++)
		{
			picked.add(next);
			minDiff = Math.min(minDiff, pick(toPick - 1, picked));
			picked.remove(picked.size() - 1);
		}
		
		return minDiff;
	}
}
