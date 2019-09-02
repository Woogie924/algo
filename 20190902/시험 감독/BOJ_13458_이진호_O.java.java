import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
	static int N,B,C;
	static int[] A;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		A = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++)
		{
			A[i] = Integer.parseInt(st.nextToken());
		}//입력완료
		
		st = new StringTokenizer(br.readLine());
		
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < N ; i++)
		{
			A[i] -=B;
		}//각각의 A에 B 빼기
		
		long result = 0;
		
		for(int i = 0 ; i < N ; i++)
		{
			if(A[i]<=0)continue;
			result += A[i]/C;
			if(A[i]%C!=0) result++;
		}//부감독관수 구하기
		
		result+= N;
		System.out.println(result);
	}

}
