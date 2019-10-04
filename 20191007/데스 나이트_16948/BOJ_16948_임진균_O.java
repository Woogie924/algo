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
		int r;
		int c;
		int moved;
		
		public Pair(int r, int c, int moved) 
		{
			this.r = r;
			this.c = c;
			this.moved = moved;
		}
	}
	
	static int N;
	static int r1, c1, r2, c2;
	static int dr[] = {-2, -2, 0, 0, 2, 2};
	static int dc[] = {-1, 1, -2, 2, -1, 1};
	
    public static void main(String[] args) throws IOException 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = null;
        
        N = Integer.parseInt(in.readLine());
    	
        tokenizer = new StringTokenizer(in.readLine());
        r1 = Integer.parseInt(tokenizer.nextToken());
        c1 = Integer.parseInt(tokenizer.nextToken());
        r2 = Integer.parseInt(tokenizer.nextToken());
        c2 = Integer.parseInt(tokenizer.nextToken());
    	
    	out.write(solve() + "");	
        out.flush();
    }

    static int solve()
    {
    	int minMoved = -1;
    	
    	boolean discovered[][] = new boolean[N][N];
    	Queue<Pair> q = new LinkedList<Pair>();
    	
    	q.offer(new Pair(r1, c1, 0));
    	discovered[r1][c1] = true;
    	
    	while(!q.isEmpty())
    	{
    		Pair p = q.poll();
    		
    		if(p.r == r2 && p.c == c2)
    		{
    			minMoved = p.moved;
    			break;
    		}
    		
    		for(int d = 0 ; d < 6 ; d++)
    		{
    			int nr = p.r + dr[d];
    			int nc = p.c + dc[d];
    			
    			if(nr < 0 || nr >= N || nc < 0 || nc >= N || 
    					discovered[nr][nc])
    				continue;
    			
    			q.offer(new Pair(nr, nc, p.moved + 1));
    			discovered[nr][nc] = true;
    		}
    	}
    	
    	return minMoved;
    }
}
