import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사{
	static int N;
	static int[] T;
	static int[] P;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		T = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		
		
		for(int tc = 0 ; tc < N ; tc++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[tc] = Integer.parseInt(st.nextToken());
			P[tc] = Integer.parseInt(st.nextToken());
		}
		
		solve(0,0);
		System.out.println(max);
	}
	static int max = Integer.MIN_VALUE;
	private static void solve(int bene,int ef)
	{
		if(ef==N)
		{
			if(bene>max) max = bene;
			return;
		}
		solve(bene,ef+1);
		if((ef+T[ef])>N) return;
		solve(bene+P[ef],ef+T[ef]);//일할때
		
	}
	
}
