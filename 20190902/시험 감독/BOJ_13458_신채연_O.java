import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458_시험감독_O {
	static BufferedReader reader;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(reader.readLine());
		for(int i=0;i<n;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(reader.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		long count=0;
		for(int i=0;i<n;i++) {
			count++;
			if(arr[i]-b>0) {
				if((arr[i]-b)%c==0) count+=(arr[i]-b)/c;
				else count+=(arr[i]-b)/c+1;
			}
		}
		System.out.println(count);
	}
}
