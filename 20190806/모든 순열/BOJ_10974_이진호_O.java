import java.util.Arrays;
import java.util.Scanner;

public class 모든순열
{
	public static int T;
	public static boolean[] flags;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		flags = new boolean[T + 1];
		int[] result = new int[T];
		getPermu(result, 1, 0);
	}

	private static void getPermu(int[] result, int idx, int depth)
	{
		if (depth == T)
		{
			for (int i : result)
			{
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 1; i <= T; i++)
		{
			if (!flags[i])
			{
				result[depth] = i;
				flags[i] = true;
				getPermu(result, i, depth + 1);
				flags[i] = false;
			}
		}

	}
}
