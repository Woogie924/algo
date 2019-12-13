package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1952_수영장 {
	static int day, month, months, year;
	static int[] plan, dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			months = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			plan = new int[12];
			for(int i = 0; i < 12; i++) plan[i] = Integer.parseInt(st.nextToken());
			
			dp = new int[13];
			
			for(int i = 1; i < 13; i++) {

				// day, month
				int min = dp[i-1] + Math.min(plan[i-1] * day, month);
				
				// 3month
				if(i - 3 >= 0) min = Math.min(min, dp[i-3] + months);
				
				dp[i] = min;
			}
			
			sb.append("#" + t + " " + Math.min(year, dp[12]) + "\n");
		}
		System.out.println(sb.toString());
	}

}
