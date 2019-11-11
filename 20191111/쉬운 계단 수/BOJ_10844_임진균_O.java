import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int cache[][];
	static final int devisor = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(in.readLine()); 
		int result = 0;
		
		cache = new int[N][9 + 1];
		for(int i = 0 ; i < N ; i++)
			Arrays.fill(cache[i], -1);
		
		for(int start = 1 ; start <= 9 ; start++)
			result = ((result + solve(0, start)) % devisor);
		
		out.write(result + "");
		out.flush();
	}

	static int solve(int index, int height)
	{
		if(index == N - 1)
			return 1;
		
		if(cache[index][height] != -1)
			return cache[index][height];
		
		int result = 0;
		if(height > 0)
			result = (result + solve(index + 1, height - 1)) % devisor;
		
		if(height < 9)
			result = (result + solve(index + 1, height + 1)) % devisor;
		
		return cache[index][height] = result;
	}
}