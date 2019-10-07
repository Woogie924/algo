import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16917_신채연_O {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(reader.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int max=Math.max(x, y);	//양념 후라이드 중 더 많은 것의 갯수
		int min=Integer.MAX_VALUE; //최소 금액
		for(int i=max;i>=0;i--) {
			int temp = i*2*c;
			if(x-i>0) temp+=(x-i)*a;
			if(y-i>0) temp+=(y-i)*b;
			if(temp<min) min=temp;
		}
		writer.write(min+"");
		writer.flush();
	}
}
