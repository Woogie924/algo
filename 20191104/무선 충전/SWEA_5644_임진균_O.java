import java.io.*;
import java.util.*;

public class Solution {
	
	static class Pos
	{
		int x;
		int y;
		
		public Pos() {}
		public Pos(int x, int y) 
		{
			this.x = x;
			this.y = y;
		}
	}
	
	static class BatteryCharger
	{
		Pos loc;
		int coverage;
		int perfomance;
		int userCount;
		
		public BatteryCharger() {}
		public BatteryCharger(Pos l, int c, int p) 
		{
			loc = l;
			coverage = c;
			perfomance = p;
		}
	}
	
	static int M, A;
	static int moveA[], moveB[];
	static BatteryCharger batteryChargers[];
	
	static final int INF = 987654321;
	static int dx[] = {0, 0, 1, 0, -1};
	static int dy[] = {0, -1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int tc = 0 ; tc < T ; tc++)
		{
			tokens = new StringTokenizer(in.readLine());
			M = Integer.parseInt(tokens.nextToken());
			A = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(in.readLine());
			moveA = new int[M + 1];
			for(int i = 0 ; i < M ; i++)
				moveA[i] = Integer.parseInt(tokens.nextToken());
			moveA[M] = 0;
			
			tokens = new StringTokenizer(in.readLine());
			moveB = new int[M + 1];
			for(int i = 0 ; i < M ; i++)
				moveB[i] = Integer.parseInt(tokens.nextToken());
			moveB[M] = 0;
			
			batteryChargers = new BatteryCharger[A + 1];
			batteryChargers[0] = new BatteryCharger(new Pos(0, 0), INF, 0);
			for(int i = 1 ; i <= A ; i++)
			{
				tokens = new StringTokenizer(in.readLine());
				int X = Integer.parseInt(tokens.nextToken());
				int Y = Integer.parseInt(tokens.nextToken());
				int C = Integer.parseInt(tokens.nextToken());
				int P = Integer.parseInt(tokens.nextToken());
				
				batteryChargers[i] = new BatteryCharger(new Pos(X, Y), C, P);
			}
			
			out.write("#" + (tc + 1) + " " + solve() + "\n");
		}
		
		out.flush();
	}

	static int solve()
	{
		int result = 0;
		Pos userA = new Pos(1, 1);		// 유저 A의 현재 위치
		Pos userB = new Pos(10, 10);	// 유저 B의 현재 위치
		
		for(int t = 0 ; t <= M ; t++)
		{
			boolean inA[] = inBatteryChargers(userA);	// 유저 A를 포함하는 BC들
			boolean inB[] = inBatteryChargers(userB);	// 유저 B를 포함하는 BC들
			
			int sum = 0;
			for(int i = 0 ; i <= A ; i++)
			{
				if(!inA[i]) 
					continue;
				
				batteryChargers[i].userCount++;
				
				for(int j = 0 ; j <= A ; j++)
				{
					if(!inB[j])
						continue;
					
					batteryChargers[j].userCount++;
					
					int chargedA = batteryChargers[i].perfomance / batteryChargers[i].userCount;
					int chargedB = batteryChargers[j].perfomance / batteryChargers[j].userCount;
					sum = Math.max(sum, chargedA + chargedB);
					
					batteryChargers[j].userCount--;
				}
				
				batteryChargers[i].userCount--;
			}
			
			result += sum;
			
			userA.x += dx[moveA[t]];
			userA.y += dy[moveA[t]];
			userB.x += dx[moveB[t]];
			userB.y += dy[moveB[t]];
		}
		
		return result;
	}
	
	// 커버리지가 user의 위치를 포함하는 BC들을 찾아서 반환한다. 
	static boolean[] inBatteryChargers(Pos user)
	{
		boolean check[] = new boolean[A + 1];
		
		for(int i = 0 ; i <= A ; i++)
		{
			Pos bc = batteryChargers[i].loc;
			
			int D = Math.abs(bc.x - user.x) + Math.abs(bc.y - user.y);
			
			if(D <= batteryChargers[i].coverage)
				check[i] = true;
		}
		
		return check;
	}
}