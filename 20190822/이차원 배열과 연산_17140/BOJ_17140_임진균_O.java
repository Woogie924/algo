import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

class Elem
{
	int number;
	int count;
	
	public Elem() { }
	public Elem(int number, int count) 
	{
		this.number = number;
		this.count = count;
	}
}

class Main {
	static BufferedReader in;
	static BufferedWriter out;

	static int r, c, k;
	static int R, C; // 배열의 행, 열 크기
	
	static int A[][] = new int[100 + 1][100 + 1];
	
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;

		// r, c, k를 입력받는다.
		tokenizer = new StringTokenizer(in.readLine());
		r = Integer.parseInt(tokenizer.nextToken());
		c = Integer.parseInt(tokenizer.nextToken());
		k = Integer.parseInt(tokenizer.nextToken());
		
		// 초기 배열 상태를 입력받는다.
		R = C = 3;
		for(int i = 1 ; i <= R ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 1 ; j <= C ; j++)
				A[i][j] = Integer.parseInt(tokenizer.nextToken());
		}
		
		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		int t = 0;
		while(t <= 100)
		{
			if(A[r][c] == k)
				break;
			
			if(R >= C)
				doOperationR();
			else
				doOperationC();
			
			t++;
		}
		
		return (t <= 100 ? t : -1);
	}
	
	static void doOperationR()
	{
		int maxC = 0;

		for(int i = 1 ; i <= R ; i++)
		{
			ArrayList<Elem> row = new ArrayList<Elem>();
			for(int j = 0 ; j < 100 + 1 ; j++)
				row.add(new Elem(j, 0));
			
			for(int j = 1 ; j <= C ; j++)
				row.get(A[i][j]).count++;
			
			row.sort(new Comparator<Elem>() {
				@Override
				public int compare(Elem o1, Elem o2) {
					if(o1.count - o2.count != 0)
						return o1.count - o2.count;
					else
						return o1.number - o2.number;
				}
			});
			
			int len = 0;
			int p = 1;
			for(int q = 0 ; q < 100 + 1 ; q++)
			{
				if(row.get(q).count != 0 && row.get(q).number != 0)
				{
					A[i][p] = row.get(q).number;
					A[i][p + 1] = row.get(q).count;
					p += 2;
					len += 2;
				}
			}
			
			if(len < R)
			{
				for( ; p <= R ; p++)
					A[i][p] = 0;
			}
			
			maxC = Math.max(maxC, len);
		}
		
		C = maxC;
	}

	static void doOperationC()
	{
		int maxR = 0;

		for(int j = 1 ; j <= C ; j++)
		{
			ArrayList<Elem> col = new ArrayList<Elem>();
			for(int i = 0 ; i < 100 + 1 ; i++)
				col.add(new Elem(i, 0));
			
			for(int i = 1 ; i <= R ; i++)
				col.get(A[i][j]).count++;
			
			col.sort(new Comparator<Elem>() {
				@Override
				public int compare(Elem o1, Elem o2) {
					if(o1.count - o2.count != 0)
						return o1.count - o2.count;
					else
						return o1.number - o2.number;
				}
			});
			
			int len = 0;
			int p = 1;
			for(int q = 0 ; q < 100 + 1 ; q++)
			{
				if(col.get(q).count != 0 && col.get(q).number != 0)
				{
					A[p][j] = col.get(q).number;
					A[p + 1][j] = col.get(q).count;
					p += 2;
					len += 2;
				}
			}
			
			if(len < C)
			{
				for( ; p <= C ; p++)
					A[p][j] = 0;
			}
			
			maxR = Math.max(maxR, len);
		}
		
		R = maxR;
	}
}