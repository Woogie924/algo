import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_10844_����ȣ_O {
	static int N;
	static long[][] dp;
	static int[] dx = { -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[100 + 1][10];//1���� �����Ѵ�

		Arrays.fill(dp[1], 1);//1�� ���� 1�� �ʱ�ȭ
		int tx;
		for (int y = 2; y <= N; y++) {//���� y������ �밢�������� ���� ��
			for (int x = 0; x <= 9; x++) {
				for (int dir = 0; dir < 2; dir++) {
					tx = x + dx[dir];
					if (tx < 0 || tx > 9)
						continue;
					dp[y][x] += dp[y - 1][tx];
					dp[y][x] %= 1000000000;
				}
			}
		}
		long result = 0;
		for (int x = 1; x <= 9; x++) {
			result += dp[N][x];
			result %= 1000000000;
		}
		System.out.println(result);
	}

}
