import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class n과m1 {
	static ArrayList<Integer> list;
	static int N,M;
	static boolean[] flags;
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		list = new ArrayList<>();
		flags = new boolean[N+1];
		
		solve(0);
		
	}
	static void solve(int depth)
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
		//조합은 M과 관련잇는거같은뎁
		//시작하는 인덱스 전은다 사용 불가능하게
		for(int i = 1; i <= N ; i++)
		{
			if(!flags[i])
			{
				flags[i] =true;
				list.add(i);
				//if(depth==0) continue;
//				System.out.println("Add :"+i);
				solve(depth+1);
//				System.out.println("Del :"+(i-1));
				list.remove(list.indexOf(i));
				flags[i] = false;
			}
			
		}
	}
}
