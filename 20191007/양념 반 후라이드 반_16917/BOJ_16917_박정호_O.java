import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class BOJ_양념반후라이드반_16917_박정호_O {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		int yang = Integer.parseInt(s[0]);
		int fried = Integer.parseInt(s[1]);
		int half = Integer.parseInt(s[2]);
		int x = Integer.parseInt(s[3]);
		int y = Integer.parseInt(s[4]);
		
		long answer = 0;
		if (yang + fried > half * 2) {
			int min = Math.min(x, y);
			x -= min;
			y -= min;
			answer += (min*2)*half;
			
			if(yang>half*2)
				answer += (half*2)*x;
			else
				answer += yang*x;
			
			if(fried>half*2)
				answer += (half*2)*y;
			else
				answer += fried*y;
			
		}else {
			answer += x*yang;
			answer += y*fried; 
		}
		System.out.println(answer);
	}
}
