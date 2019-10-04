import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
  
public class Main {
  
	static class Pos
	{
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int R, C, N;
	static int board[][];
	
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	
    public static void main(String[] args) throws IOException 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        
        // 1. 가장 처음에 봄버맨은 일부 칸에 폭탄을 설치해 놓는다.
        board = new int[R][C];
        for(int r = 0 ; r < R ; r++)
        {
        	String temp = in.readLine();
        	for(int c = 0 ; c < C ; c++)
        		board[r][c] = (temp.charAt(c) == 'O' ? 3 : -1);
        }
        
        solve();
        
        for(int r = 0 ; r < R ; r++)
        {
        	for(int c = 0 ; c < C ; c++)
        		out.write(board[r][c] == -1 ? "." : "O");
        	out.newLine();
        }
        
        out.flush();
    }
      
    static void solve()
    {
    	// 2. 다음 1초 동안 봄버맨은 아무것도 하지 않는다.
    	for(int r = 0 ; r < R ; r++)
    		for(int c = 0 ; c < C ; c++)
    			if(board[r][c] - 1 > 0)
    				board[r][c] -= 1;
    	
    	// <t초 후>
    	for(int t = 2 ; t <= N ; t++)
    	{
    		// 3. 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다.
    		if(t % 2 == 0)
    		{
    			for(int r = 0 ; r < R ; r++)
    				for(int c = 0 ; c < C ; c++)
    					board[r][c] = (board[r][c] == -1 ? 3 : board[r][c] - 1);
    		}
    		// 4. 1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
    		else
    		{
    			ArrayList<Pos> bombList = new ArrayList<Pos>();
    			
    			// 이번 시간에 터지는 폭탄을 찾는다.
    			for(int r = 0 ; r < R ; r++)
    			{
    				for(int c = 0 ; c < C ; c++)
    				{
    					if(board[r][c] - 1 == 0)
    						bombList.add(new Pos(r, c));
    					else if(board[r][c] - 1 > 0)
    						board[r][c] -= 1;
    				}
    			}
    			
    			// 폭탄을 터뜨린다.
    			for(int i = 0 ; i < bombList.size() ; i++)
    			{
    				int r = bombList.get(i).r;
    				int c = bombList.get(i).c;
    				
    				board[r][c] = -1;
    				for(int d = 0 ; d < 4 ; d++)
    				{
    					int nr = r + dr[d];
    					int nc = c + dc[d];
    					
    					if(nr < 0 || nr >= R || nc < 0 || nc >= C)
    						continue;
    					
    					board[nr][nc] = -1;
    				}
    			}
    		}
    	}
    }
}
