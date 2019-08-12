import java.util.Arrays;
import java.util.Scanner;

public class 외판원순회
{

	static int T;
	static int[][] map;
	static boolean[] flags;
	static int min = Integer.MAX_VALUE;
	static int start;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		map = new int[T][T];
		flags = new boolean[T];
		for (int i = 0; i < T; i++)
		{
			for (int j = 0; j < T; j++)
			{
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int start = 0 ; start < T ; start++) {
			flags[start] = true;
			go(start, 0, 0);
			flags[start] = false;
		}
		System.out.println(min);

	}

	private static void go(int pre, int cost, int depth)
	{
		if (depth == T-1)
		{
			if(pre == start) return;
			cost += map[pre][start];
			
			System.out.println(cost);
			if (cost < min)
				min = cost;
			return;
		}
		for (int i = 0; i < T; i++)
		{
			
			if (!flags[i])
			{
				
				flags[i] = true;
				//System.out.println(cost);
				go(i, cost + map[pre][i], depth + 1);
				flags[i] = false;
			}
		}
	}

}
