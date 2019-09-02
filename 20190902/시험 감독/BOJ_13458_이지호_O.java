import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458{
	static int N, B, C;
	static int A[];
	static long cnt;
	public static void main(String[] args) throws IOException {// 시험감독
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			A[i] = A[i] - B;
			cnt++;
			if (A[i] > 0) {
				if (A[i] % C == 0) {
					cnt += (A[i] / C);
				} else {
					cnt += (A[i] / C) + 1;
				}
			}
		}
		System.out.println(cnt);
	}

}
