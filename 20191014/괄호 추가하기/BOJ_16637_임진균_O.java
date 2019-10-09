import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N;
	static int operand[];
	static char operator[];
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(in.readLine());
		operand = new int[N / 2 + 1];
		operator = new char[N / 2];
		
		char expr[] = in.readLine().toCharArray();
		for(int i = 0, j = 0, k = 0 ; i < N ; i++)
		{
			if(i % 2 == 0)
				operand[j++] = expr[i] - '0';
			else
				operator[k++] = expr[i];
		}
		
		out.write(solve(operand[0], 0, 1) + "");
		out.flush();
	}
	
	// result : 중간 계산 결과
	// i : 배열 operator에 대한 인덱스
	// j : 배열 operand에 대한 인덱스
	static int solve(int result, int i, int j)
	{
		if(i == N / 2)
			return result;
		
		// 현재 연산자에 대해서 괄호를 생성하지 않는 경우
		int part = solve(calc(result, operand[j], operator[i]), i + 1, j + 1);
		
		if(j + 1 < (N / 2 + 1))
		{
			int temp = calc(operand[j], operand[j + 1], operator[i + 1]);
			part = Math.max(part, solve(calc(result, temp, operator[i]), i + 2, j + 2));
		}
		
		return part;
	}

	static int calc(int x, int y, char op)
	{
		int result = 0;
		if(op == '+')
			result = x + y;
		else if(op == '-')
			result = x - y;
		else if(op == '*')
			result = x * y;
		
		return result;
	}

}