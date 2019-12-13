import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1952_이진호_O {
	static int T;
	static int[] cost;
	static int[] plan;
	static int[] result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			cost = new int[4];// 1일 1달 3달 1년
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			int min = cost[3];
			st = new StringTokenizer(br.readLine());
			plan = new int[12 + 1];
			result = new int[12 + 3];
			Arrays.fill(result, Integer.MAX_VALUE);
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			int cur;
			int temp = 0;
			for (int month = 1; month <= 12; month++) {

				cur = (plan[month] * cost[0] > cost[1] ? cost[1] : plan[month] * cost[0]);
				temp = result[month - 1] != Integer.MAX_VALUE?  result[month - 1] : 0;
				
				cur += temp;
				result[month + 2] = temp + cost[2];
				result[month] = result[month] > cur ? cur : result[month];
			}
			int fr = Integer.MAX_VALUE;
			for (int j = 12; j <= 14; j++) {
				fr = fr > result[j] ? result[j] : fr;
			}
			System.out.println("#" + tc + " " + (min > fr ? fr : min));
		}
	}

}
