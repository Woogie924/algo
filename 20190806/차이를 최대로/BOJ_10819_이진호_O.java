import java.util.Scanner;

public class 차이를최대로
{
	static int[] nums;
	static boolean[] flags;
	static int T;
	static int best = -1;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		nums = new int[T];
		flags = new boolean[T];
		for (int i = 0; i < T; i++)
		{
			nums[i] = sc.nextInt();
		}

		cal(0, 0, 0, 0);
		System.out.println(best);
	}

	private static int cal(int total, int pre, int next, int depth)
	{
		// total += Math.abs(pre - next);
		if(depth == 1)
		{
			total =0;
		}
		if (depth == T)
		{
			if (total > best)
			{
				best = total;
				//System.out.println("best"+total);
			}
			return 0;
		}

		for (int i = 0; i < T; i++)
		{
			if (!flags[i])
			{	
				//System.out.println(depth+"!===="+i+"======");
				flags[i] = true;
				int pV = nums[next];
				int nV = nums[i];
				//System.out.println(total);
				cal(total + Math.abs(pV - nV) , next, i, depth + 1);
				flags[i] = false;
			}
		}
		return 0;
	}

}
