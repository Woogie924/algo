import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
	
	static class Pair
	{
		int here;
		int rolled;
		
		public Pair(int here, int rolled) {
			this.here = here;
			this.rolled = rolled;
		}
	}
	
	static int ladder[] = new int[100 + 1];
	static int snake[] = new int[100 + 1];
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());
		
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(tokenizer.nextToken());
			int y = Integer.parseInt(tokenizer.nextToken());
			
			ladder[x] = y;
		}
				
		for(int i = 0 ; i < M ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(tokenizer.nextToken());
			int v = Integer.parseInt(tokenizer.nextToken());
			
			snake[u] = v;
		}
		
		out.write(solve() + "");
		out.flush();
	}

	static int solve() 
	{
		int minRolled = -1;
		
		boolean discovered[] = new boolean[100 + 1];
		Queue<Pair> q = new LinkedList<Pair>();
		
		q.offer(new Pair(1, 0));
		discovered[1] = true;
		
		while(!q.isEmpty())
		{
			Pair p = q.poll();
			int here = p.here;
			int rolled = p.rolled;
			
			if(here == 100)
			{
				minRolled = rolled;
				break;
			}
			
			for(int i = 1 ; i <= 6 ; i++)
			{
				int there = here + i;
				
				if(there > 100)
					continue;
				
				if(ladder[there] != 0)
					there = ladder[there];
				else if(snake[there] != 0)
					there = snake[there];
				
				if(!discovered[there])
				{
					q.offer(new Pair(there, rolled + 1));
					discovered[there] = true;
				}
			}
		}
		
		return minRolled;
	}
}
