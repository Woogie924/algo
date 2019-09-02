import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458_현병록_O {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int size = Integer.parseInt(br.readLine());
		int count[] = new int[size];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<size; i++) {
			count[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
		long sum=0;
		for(int i=0; i<size; i++) {
			sum += Math.ceil((double)(count[i]-b)/c)>0 ? Math.ceil((double)(count[i]-b)/c) : 0;
		}
		System.out.println(sum+size);
	}
}