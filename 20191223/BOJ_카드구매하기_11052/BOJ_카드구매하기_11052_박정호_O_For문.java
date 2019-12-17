/*
 * For문 : Bottom-Top 방식
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_카드구매하기_11052_박정호_O_For문 {
	static int N;
	static int[] pack, memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		s = br.readLine().split(" ");
		pack = new int[N+1];
		memo = new int[N+1];
		
		for(int i=1; i<=N; ++i) {
			pack[i] = Integer.parseInt(s[i-1]);
		}
		memo[1] = pack[1];
		
		for(int i=2; i<=N; ++i) {
			for(int j=1; j<=i; ++j) {
				memo[i] = Math.max(memo[i], Math.max(memo[i-j]+pack[j], pack[i]));
			}
		}
		System.out.println(memo[N]);
		
	}

}
