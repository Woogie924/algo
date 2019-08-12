import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2331_반복수열 {
	static ArrayList<Integer> list;
	static int p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		bw.write(repeat(start)+""); bw.flush();
	}

	private static int repeat(int num) {
		int n = cal(num, p);
		if(list.contains(num)) return list.indexOf(num);
		list.add(num);
		return repeat(n);
	}

	private static int cal(int num, int p2) {
		int rtv = 0;
		do {
			rtv+=Math.pow(num%10, p);
			num/=10;
		}while(num!=0);
		return rtv;
	}
}
