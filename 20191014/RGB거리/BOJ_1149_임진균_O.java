import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N;
	static int cost[][];
	static int cache[][];
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		String line[] = null;
		
		N = Integer.parseInt(in.readLine());
		
		cost = new int[N][3];
		for(int i = 0 ; i < N ; i++)
		{
			line = in.readLine().split(" ");
			for(int j = 0 ; j < 3 ; j++)
				cost[i][j] = Integer.parseInt(line[j]);
		}
		
		cache = new int[N + 1][3];
		for(int i = 0 ; i < N + 1 ; i++)
			for(int j = 0 ; j < 3 ; j++)
				cache[i][j] = -1;
		
		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		int result = 98654321;
		for(int statColor = 0 ; statColor < 3 ; statColor++)
			result = Math.min(result, fill(0, statColor));
		
		return result;
	}
	
	static int fill(int index, int color)
	{
		if(index == N)
			return 0;
		
		if(cache[index][color] != -1)
			return cache[index][color];
		
		int result = 98654321;
		for(int nextColor = 0 ; nextColor < 3 ; nextColor++)
			if(color != nextColor)
				result = Math.min(result, fill(index + 1, nextColor) + cost[index][color]);
		
		return cache[index][color] = result;
	}

}