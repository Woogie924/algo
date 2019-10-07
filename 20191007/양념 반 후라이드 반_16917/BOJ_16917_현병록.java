import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16917_현병록 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()), b=Integer.parseInt(st.nextToken()), c=Integer.parseInt(st.nextToken()),
				x=Integer.parseInt(st.nextToken()), y=Integer.parseInt(st.nextToken());
		int result = 0;
		if(a+b>c*2) {
			result += c*2*Math.min(y, x);
			if(y<x) {
				if(a>c*2)
					a = c*2;
				result += (x-y)*a;
			}
			else {
				if(b>c*2)
					b=c*2;
				result +=(y-x)*b;
			}
		}else {
			result = x*a + y*b;
		}
		System.out.println(result);
	}
}
