import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
	
	static int N;
	static int house[][];
	static int cache[][][];
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		N = Integer.parseInt(in.readLine());
		
		house = new int[N][N];
		for(int x = 0 ; x < N ; x++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int y = 0 ; y < N ; y++)
				house[x][y] = Integer.parseInt(tokenizer.nextToken());
		}
		
		cache = new int[3][N][N];
		for(int d = 0 ; d < 3 ; d++)
			for(int x = 0 ; x < N ; x++)
				for(int y = 0 ; y < N ; y++)
					cache[d][x][y] = -1;
		
		out.write(solve(0, 0, 1) + "");
		out.flush();
	}
	
	static int solve(int d, int x, int y)
	{
		if(x == N - 1 && y == N - 1)
			return 1;
		
		if(cache[d][x][y] != -1)
			return cache[d][x][y];
		
		int count = 0;
		if(d != 1 && check(0, x, y + 1))
			count += solve(0, x, y + 1);
		
		if(d != 0 && check(1, x + 1, y))
			count += solve(1, x + 1, y);
		
		if(check(2, x + 1, y + 1))
			count += solve(2, x + 1, y + 1);
		
		return cache[d][x][y] = count;
	}
	
	static boolean check(int d, int x, int y)
	{
		switch(d)
		{
			case 0:
			{
				if(y >= N)
					return false;
				
				for(int j = y ; j >= y - 1 ; j--)
					if(house[x][j] == 1)
						return false;
				
				break;
			}
			case 1:
			{
				if(x >= N)
					return false;
				
				for(int i = x ; i >= x - 1 ; i--)
					if(house[i][y] == 1)
						return false;
				
				break;
			}
			case 2:
			{
				if(x >= N || y >= N)
					return false;
				
				for(int i = x ; i >= x - 1 ; i--)
					for(int j = y ; j >= y - 1 ; j--)
						if(house[i][j] == 1)
							return false;
				
				break;
			}
		}
		
		return true;
	}
}
