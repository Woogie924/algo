import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int A,B,C,X,Y;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		int curX,curY;
		curX = curY = 0;
		
		int money = 0;
		while(curX <X || curY < Y)
		{
			if(curX<X && curY < Y)
			{
				if(A+B>2*C)
				{
					money+= 2*C;
				}
				else 
				{
					money += A+B;
				}
				curX+=1;
				curY+=1;
			}
			else if(curX<X)//X완성되면
			{
				if(A>2*C)
				{
					money+= 2*C;
				}
				else
				{
					money += A;
				}
				curX+=1;
				
			}
			else if(curY<Y)
			{
				if(B>2*C)
				{
					money+= 2*C;
				}
				else
				{
					money += B;
				}
				curY+=1;
			}
		}
		System.out.println(money);
	}

}
