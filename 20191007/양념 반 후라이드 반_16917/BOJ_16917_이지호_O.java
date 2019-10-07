import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16917 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());
		
		long init = A*X+B*Y;
		long temp1=0;
		if(X>Y) {
			temp1= 2*C*Y+A*(X-Y);
		}else {
			temp1= 2*C*X+B*(Y-X);
		}
		long temp2 = C*2*Math.max(X,Y);
		long ret = Math.min(init,Math.min(temp1, temp2));
		System.out.println(ret);
	}

}
