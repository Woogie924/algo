import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class n과m8 {
	static ArrayList<Integer> list;
	static int N, M;
	static boolean[] flags;
	static int[] nums;
	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		tokenizer = new StringTokenizer(reader.readLine());
		nums = new int[N];
		for (int i = 0 ; i < N ; i++)
		{
			nums[i] = Integer.parseInt(tokenizer.nextToken());
		}
		Arrays.sort(nums);
		list = new ArrayList<>();
		flags = new boolean[N + 1];

		solve("",0,0);

	}

	static void solve(String str,int cur, int depth) {
		if (depth == M) {
			System.out.println(str);
			return;
		}
		// 조합은 M과 관련잇는거같은뎁
		// 시작하는 인덱스 전은다 사용 불가능하게
		for (int i = cur; i < N; i++) {
			solve(str+nums[i]+" ",i,depth + 1);
		}
	}
}
