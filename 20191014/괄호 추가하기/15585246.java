import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;
	static List<Ele> list;

	static class Ele {
		char op; // + - * :: 0 1 2
		int num;// 0~9

		public Ele(char op, int num) {
			super();
			this.op = op;
			this.num = num;
		}
	}
	static int num;
	static char op;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		String input = br.readLine();
		iter = N / 2 + N % 2;
		for (int x = 0; x < N / 2 + N % 2; x++) {
			if(x==0)
			{
				op = '+';
			}
			else
			{
				op = input.charAt(x * 2 - 1);
			}
			num = input.charAt(x * 2) - '0';
			list.add(new Ele(op, num));
		}
		perm(0, new Ele('+', 0));
		System.out.println(max);
	}

	static int max = Integer.MIN_VALUE;
	static int iter;
	private static void perm(int cur, Ele sum)// curë íë ì¸ë±ì¤, sumì ì¬íê¹ì§ì í©
	{
		if (cur == iter) {
			if (sum.num > max)
				max = sum.num;
			return;
		}

		for (int i = 1; i<=2&&i + cur <= iter; i++) {
			
			Ele ele = list.get(cur);
		/*	if(i==1)
			{
				ele = cal(sum, ele);
			}*/
			for (int j = 1; j < i; j++)
				ele = cal(ele, list.get(cur + j));
			ele = cal(sum, ele);
			perm(cur + i, ele);

		}
	}

	private static Ele cal(Ele e1, Ele e2) {
		Ele result = new Ele('+', 0);
		result.op = e1.op;
		int num = 0;
		switch (e2.op) {
		case '+':
			num = e1.num + e2.num;
			break;
		case '-':
			num = e1.num - e2.num;
			break;
		case '*':
			num = e1.num * e2.num;
			break;
		}
		result.num = num;
		return result;
	}

}
