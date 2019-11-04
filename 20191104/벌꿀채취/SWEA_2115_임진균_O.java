import java.io.*;
import java.util.*;

public class Solution {
	
	static class Pos
	{
		int x;
		int y;
	}
	
	static int N, honeycomb[][];
	static int M, C;
	static int cache[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int tc = 0 ; tc < T ; tc++)
		{
			tokens = new StringTokenizer(in.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			
			honeycomb = new int[N][N];
			for(int i = 0 ; i < N ; i++)
			{
				tokens = new StringTokenizer(in.readLine());
				for(int j = 0 ; j < N ; j++)
					honeycomb[i][j] = Integer.parseInt(tokens.nextToken());
			}
			
			cache = new int[N][N];
			for(int i = 0 ; i < N ; i++)
				Arrays.fill(cache[i], -1);
			
			out.write("#" + (tc + 1) + " " + solve(new ArrayList<Pos>()) + "\n");
		}
		
		out.flush();
	}

	static int solve(ArrayList<Pos> picked)
	{
		// 기저 사례 : 각각의 일꾼이 채취할 벌통들의 시작위치를 모두 결정한 경우
		if(picked.size() == 2)
			return harvest(picked.get(0)) + harvest(picked.get(1));
		
		int result = 0;
		Pos smallest = new Pos();
		
		if(picked.isEmpty())
		{
			smallest.x = 0;
			smallest.y = 0;
		}
		else
		{
			smallest.x = picked.get(picked.size() - 1).x;
			smallest.y = picked.get(picked.size() - 1).y + M;
			
			// 마지막 벌통의 y좌표는 N보다 작아야 한다.
			if(smallest.y + M - 1 >= N)
			{
				smallest.x += 1;
				smallest.y = 0;
			}
		}
		
		for(Pos next = smallest ; next.x < N ; )
		{
			picked.add(next);
			result = Math.max(result, solve(picked));
			picked.remove(picked.get(picked.size() - 1));
			
			// for문의 증감문 부분을 이곳에 작성한다.
			next.y += 1;
			if(next.y + M - 1 >= N)
			{
				next.x += 1;
				next.y = 0;
			}
		}
		
		return result;
	}
	
	static int harvest(Pos pos)
	{
		// cache[x][y] : 한 일꾼이 (x, y) ~ (x, y + M - 1)범위의 벌통에서 얻을 수 있는 최대 수익을 저장한다.
		if(cache[pos.x][pos.y] != -1)
			return cache[pos.x][pos.y];
		
		int result = 0;
		// 한 일꾼이 채취할 수 있는 모든 경우의 수를 확인한다.
		for(int bitmask = 0 ; bitmask < (1 << M) ; bitmask++)
		{
			int amount = 0; // 채취한 꿀의 양
			
			for(int i = 0 ; i < M ; i++)
				if((bitmask & (1 << i)) > 0)
					amount += honeycomb[pos.x][pos.y + i];
			
			if(amount <= C)
			{
				int profit = 0; // 수익
				
				for(int i = 0 ; i < M ; i++)
					if((bitmask & (1 << i)) > 0)
						profit += (honeycomb[pos.x][pos.y + i] * honeycomb[pos.x][pos.y + i]);
				
				result = Math.max(result, profit);
			}
		}
		
		return cache[pos.x][pos.y] = result;
	}
}