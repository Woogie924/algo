import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] cost;
	static int[] monthly;
	static int answer=Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0; t<tc; ++t) {
			cost = new int[4];
			monthly = new int[12];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<4; ++i) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<12; ++i) {
				monthly[i] = Integer.parseInt(st.nextToken());
			}
			
			
			dfs(0,0);
			answer = Math.min(cost[3], answer);
			System.out.println("#"+(t+1)+" "+answer);
			answer = Integer.MAX_VALUE;
		}
	}
	private static void dfs(int month, int sum) {
		
		if(month>11) {
			answer = Math.min(answer, sum);
			return;
		}
		
		dfs(month+1, sum+monthly[month]*cost[0]);
		dfs(month+1, sum+cost[1]);
		dfs(month+3, sum+cost[2]);			
		
	}
}