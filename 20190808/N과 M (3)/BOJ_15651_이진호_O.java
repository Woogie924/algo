import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class n과m3 {
	static int N, M;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		String s = " ";
		solve(s,0);

	}

	static void solve(String str,int depth) {
		if (depth == M) {
			System.out.println(str);
			return;
		}
		// 조합은 M과 관련잇는거같은뎁
		// 시작하는 인덱스 전은다 사용 불가능하게
		for (int i = 1; i <= N; i++) {

			solve(str+i+" ",depth + 1);
		}
	}
}
