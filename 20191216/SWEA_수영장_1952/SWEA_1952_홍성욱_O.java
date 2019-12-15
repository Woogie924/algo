import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class swea1952_수영장 {
	static int [] cost = new int [4];	//1일, 1달, 3달, 1년
	static int [] plan = new int [12]; // 1~12월
	static int ans=0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int Test_Case = Integer.parseInt(br.readLine());
		for(int tc=1 ; tc<=Test_Case ; tc++) {
			Arrays.fill(cost, 0); Arrays.fill(plan, 0);
			ans=0;
			
			String [] input = br.readLine().split(" ");
			//cost
			for(int i=0; i<input.length; i++) {
				cost[i] = Integer.parseInt(input[i]);
			}
			input = br.readLine().split(" ");
			for(int i=0; i<input.length;i++) {
				plan[i] = Integer.parseInt(input[i]);
			}
			ans=cost[3];	//1년치 이용
			makePattern(0,0);
			System.out.println("#"+tc+" "+ans);
			
		}
	}
	static void makePattern(int cur, int sum) {
		if(cur>=12) {
			ans = Math.min(ans, sum);
			return;
		}
		
		if(plan[cur]==0) {
			makePattern(cur+1,sum);
		}else {
			makePattern(cur+1, sum+(plan[cur]*cost[0]));
			makePattern(cur+1, sum+cost[1]);
			makePattern(cur+3, sum+cost[2]);
		}
	}
}
