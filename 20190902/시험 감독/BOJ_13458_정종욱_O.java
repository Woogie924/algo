import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458_시험감독_O {
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N,B,C;
	static int[] A;
	static long cnt;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int a=0;a<N;a++) {
			A[a] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		for(int a=0;a<N;a++) {
			A[a]-=B;
			cnt++;
		}
		for(int a=0;a<N;a++) {
			if(0>A[a]) continue;
			cnt+=A[a]/C;
			if(A[a]%C==0)continue;
			cnt++;
		}
		
		System.out.println(cnt);
	}
}
