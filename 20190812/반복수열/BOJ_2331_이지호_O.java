import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2331 {//반복수열
	static int[] arr;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		arr = new int[1000000];
		arr[0] = A;
		DFS(1, P);
		System.out.println(result);
	}

	private static void DFS(int start, int p) {
		String temp = Integer.toString(arr[start - 1]);
		int num = 0;
		for (int i = 0; i < temp.length(); i++) {
			num =num+multiple(temp.charAt(i) - '0', p);
		}
		arr[start] = num;
		boolean flag=false;
		for (int i = 0; i < start; i++) {
			if(arr[i]==num) {
				flag=true;
				result=i;
				return;
			}
		}
		if(!flag) DFS(start + 1, p);
	}
	private static int multiple(int temp, int p) {
		if (p == 0) {
			return 1;
		}
		return temp * multiple(temp, p - 1);
	}
}
