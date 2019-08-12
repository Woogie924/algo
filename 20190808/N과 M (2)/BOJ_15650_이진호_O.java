import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class n과m2 {
	static ArrayList<Integer> list;
	static int N,M;
	static boolean[] flags;
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		list = new ArrayList<>();
		flags = new boolean[N+1];
		
		solve(1,0);
		
	}
	static void solve(int start, int depth)
	{
		if(depth == M) 
		{
			for(int i : list)
			{
				System.out.print(i+" ");
			}
			System.out.println();
			return;
		}
		for(int i = start; i <= N ; i++)
		{
			if(!flags[i])
			{
				flags[i] =true;
				list.add(i);
				solve(i,depth+1);
				list.remove(list.indexOf(i));
				flags[i] = false;
			}
		}
	}
}
