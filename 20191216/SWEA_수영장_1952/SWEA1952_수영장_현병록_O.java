package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class SWEA1952_수영장 {
    static int cost[], months[], dp[];
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        cost = new int[4]; //1일 1개월 3개월 1년 이용권 금액
        months = new int[12];
        dp = new int[13];
        int testcase = Integer.parseInt(br.readLine());
         
        for(int tc=1; tc<=testcase; tc++) {
            Arrays.fill(dp, Integer.MAX_VALUE);
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++) cost[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<12; i++) months[i]=Integer.parseInt(st.nextToken());
            dp[12] = cost[3];
            dfs(0, 0);
            sb.append("#").append(tc).append(" ").append(dp[12]).append("\n");
        }
        System.out.println(sb.toString());
    }
    private static void dfs(int month, int val) {
        if(month>11) {
            dp[12] = Integer.min(dp[12], val);
            return;
        }
        if(dp[month]<val) return;
        dfs(month+1, val+cost[0]*months[month]);
        dfs(month+1, val+cost[1]);
        dfs(month+3, val+cost[2]);
    }
}