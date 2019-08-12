import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class n과m12 {
	static int N, M;
	static int[] nums;
	static int maxNum;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[10001];
		maxNum = Integer.MIN_VALUE;
				
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < N ; i++)
		{
			int tempIndex = Integer.parseInt(st.nextToken());
			nums[tempIndex]++;
			if(tempIndex>maxNum) maxNum = tempIndex;
		}
		count = N;
		solve("",1,M);//str , start , M- depth
	}
	static int count;
	private static void solve(String str, int start,int ep)
	{
		if(ep==0)
		{
			System.out.println(str);
			return;
		}
		if(count>0)
		{
			for(int i = start ; i < maxNum+1; i++)
			{
				if(nums[i]>0)
				{

					count--;
					solve(str+i+" ",i,ep-1);
					count++;

				}
			}
		}
		
	}
	
}
