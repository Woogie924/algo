import java.util.Arrays;
import java.util.Scanner;

public class 연산자끼워넣기
{

	static int T;
	static int[] op;
	static int[] nums;
	static boolean[] flags;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		nums = new int[T];
		flags = new boolean[T-1];
		op = new int[T-1]; // 0 1 2 3 + - * /
		int opCount = 0;
		for (int i = 0; i < T; i++)
		{
			nums[i] = sc.nextInt();
		}
		for (int i = 0; i < 4; i++)
		{
			int temp = sc.nextInt();
			for(int j = 0 ; j < temp ; j++)
			{
				op[opCount++] =i; 
			}
		}
		
//		System.out.println(Arrays.toString(op));
		cal(0,0);
		System.out.println(max);
		System.out.println(min);
		
	}

	private static void cal(int sum, int depth)
	{
		
		if (depth == T-1)
		{
			if(sum > max) max = sum;
			if(sum < min ) min = sum;
			return;
		}
		
		for(int i = 0 ; i < T -1 ; i++)
		{
			//System.out.println("i:"+i);
			if(depth == 0 ) sum = nums[depth];
			if(!flags[i])
			{
				flags[i] = true;
				cal(cal4(op[i],sum,depth),depth+1);
				flags[i] = false;
			}
		}
	}

	private static int cal4(int opIdx,int sum, int i)// 4칙연산
	{
		int num1 = sum;
		int num2 = nums[i+1];
		switch (opIdx)
		{
		case 0:
			return num1 + num2;
		case 1:
			return num1 - num2;
		case 2:
			return num1 * num2;
		case 3:
			return num1 / num2;
		}
		return 0;
	}

}
