import java.util.Scanner;

public class 더하기123
{

	static int T;
	static int N;
	static int count = 0;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		N=-33;
		for (int i = 0; i < T; i++)
		{
			N = sc.nextInt();
			
			cal(0);
			System.out.println(count);
			count =0;
		}
		
		
	}

	private static void cal(int n)
	{
		if(n == N)
		{
			
			count++;
			//System.out.println(count);
			return;
		}
		for(int i = 1 ; i <= 3 && n<=N  ; i++)
		{
			//System.out.printf("i: %d n : %d\n",i,n);
			
			cal(i+n);
		}
	}


}
