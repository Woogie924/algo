import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = new StringTokenizer(in.readLine());
		
		int cube[] = new int[24];
		for(int i = 0 ; i < 24 ; i++)
			cube[i] = Integer.parseInt(tokenizer.nextToken());
					
		
		out.write(solve(cube) ? "1" : "0");
		out.flush();
	}
	
	static boolean solve(int cube[])
	{
		for(int i = 0 ; i < 6 ; i++)
		{
			// 이동이 필요한 칸의 인덱스를 저장한다.
			int inner[] = new int[4];
			int outer[] = new int[8];

			switch(i)
			{
				case 0:
				{
					inner[0] = 0;
					inner[1] = 1;
					inner[2] = 3;
					inner[3] = 2;
					
					outer[0] = 21;
					outer[1] = 20;
					outer[2] = 17;
					outer[3] = 16;
					outer[4] = 5;
					outer[5] = 4;
					outer[6] = 13;
					outer[7] = 12;
					break;
				}
					
				case 1:
				{
					inner[0] = 4;
					inner[1] = 5;
					inner[2] = 7;
					inner[3] = 6;
					
					outer[0] = 2;
					outer[1] = 3;
					outer[2] = 16;
					outer[3] = 18;
					outer[4] = 9;
					outer[5] = 8;
					outer[6] = 15;
					outer[7] = 13;
					break;
				}
				case 2:
				{
					inner[0] = 8;
					inner[1] = 9;
					inner[2] = 11;
					inner[3] = 10;
					
					outer[0] = 6;
					outer[1] = 7;
					outer[2] = 18;
					outer[3] = 19;
					outer[4] = 22;
					outer[5] = 23;
					outer[6] = 14;
					outer[7] = 15;
					break;
				}
				case 3:
				{
					inner[0] = 12;
					inner[1] = 13;
					inner[2] = 15;
					inner[3] = 14;
					
					outer[0] = 0;
					outer[1] = 2;
					outer[2] = 4;
					outer[3] = 6;
					outer[4] = 8;
					outer[5] = 10;
					outer[6] = 23;
					outer[7] = 21;
					break;
				}
				case 4:
				{
					inner[0] = 16;
					inner[1] = 17;
					inner[2] = 19;
					inner[3] = 18;
					
					outer[0] = 3;
					outer[1] = 1;
					outer[2] = 20;
					outer[3] = 22;
					outer[4] = 11;
					outer[5] = 9;
					outer[6] = 7;
					outer[7] = 5;
					break;
				}
				case 5:
				{
					inner[0] = 20;
					inner[1] = 21;
					inner[2] = 23;
					inner[3] = 22;
					
					outer[0] = 1;
					outer[1] = 0;
					outer[2] = 12;
					outer[3] = 14;
					outer[4] = 10;
					outer[5] = 11;
					outer[6] = 19;
					outer[7] = 17;
					break;
				}
			}
			
			rotateCCW(cube, inner, outer);
			if(check(cube))
				return true;
			else
				rotateCW(cube, inner, outer);
			
			rotateCW(cube, inner, outer);
			if(check(cube))
				return true;
			else
				rotateCCW(cube, inner, outer);
		}
		
		return false;
	}
	
	static void rotateCW(int cube[], int inner[], int outer[])
	{
		// inner 부분 회전
		int temp = cube[inner[3]];
		for(int i = 3 ; i > 0 ; i--)
			cube[inner[i]] = cube[inner[i - 1]];
		cube[inner[0]] = temp;
		
		// outer 부분 회전
		for(int i = 0 ; i < 2 ; i++)
		{
			temp = cube[outer[7]];
			for(int j = 7 ; j > 0 ; j--)
				cube[outer[j]] = cube[outer[j - 1]];
			cube[outer[0]] = temp;
		}
	}
	
	static void rotateCCW(int cube[], int inner[], int outer[])
	{
		// inner 부분 회전
		int temp = cube[inner[0]];
		for (int i = 0; i < 3; i++)
			cube[inner[i]] = cube[inner[i + 1]];
		cube[inner[3]] = temp;

		// outer 부분 회전
		for(int i = 0 ; i < 2 ; i++)
		{
			temp = cube[outer[0]];
			for (int j = 0; j < 7; j++)
				cube[outer[j]] = cube[outer[j + 1]];
			cube[outer[7]] = temp;
		}
	}
	
	static boolean check(int cube[])
	{
		for(int i = 0 ; i < 6 ; i++)
		{
			int color = cube[4 * i];
			
			for(int j = 0 ; j < 4 ; j++)
				if(color != cube[4 * i + j])
					return false;
		}
		
		return true;
	}
}