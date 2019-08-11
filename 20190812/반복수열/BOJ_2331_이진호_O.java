import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 반복수열2331
{

	static int A,P;
	static List<Integer> list;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		list = new ArrayList<Integer>();
		A = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		boolean iterFlag = false;
		list.add(A);
		int temp = A;
		while(true)
		{
			temp = getNext(temp);
			if(list.contains(temp))
			{
				if(list.indexOf(temp)==list.size()-1)
				{
					break;
				}
			}
			else
			{
				list.add(temp);
			}
			/*System.out.println(temp);*/
			/*
			if(list.contains(o))*/
		}
		while(list.contains(temp))
		{
			list.remove(list.indexOf(temp));
			temp = getNext(temp);
		}
		System.out.println(list.size());
	}
	private static int getNext(int input)
	{
		int result = 0;
		int temp;
		int multiplier;
		while(input!=0)
		{
			temp = 1;
			multiplier = (input%10);
			for(int i = 0 ; i < P ; i++)
			{
				temp *= multiplier;
			}
			result += temp;
			input /= 10;
		}
		return result;
	}


}
