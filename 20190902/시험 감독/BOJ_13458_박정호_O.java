import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split("\\s");
		int[] room = new int[N];
		String[] s2 = br.readLine().split("\\s");
		int B = Integer.parseInt(s2[0]);
		int C = Integer.parseInt(s2[1]);
		for (int i = 0; i < N; ++i) {
			room[i] = Integer.parseInt(s[i])-B;
		}
		
		long answer = N;
		for (int i = 0; i < N; ++i) {
			if (room[i] > 0) {
				answer += room[i] / C;
				if (room[i] % C != 0)
					answer++;
			}
		}
		
		System.out.println(answer);

	}

}