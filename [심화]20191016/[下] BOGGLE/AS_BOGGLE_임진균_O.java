import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static char board[][] = new char[5][5];
	static int cache[][][];
	
	static int dx[] = {1, -1, 0, 1, 0, -1, -1, 1};
	static int dy[] = {-1, 0, 1, 0, -1, 1, -1, 1};
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(in.readLine());
		for(int tc = 0 ; tc < T ; tc++)
		{
			for(int i = 0 ; i < 5 ; i++)
				board[i] = in.readLine().toCharArray();
			
			int N = Integer.parseInt(in.readLine());
			for(int i = 0 ; i < N ; i++)
			{
				String word = in.readLine();
				out.write(word + " " + (solve(word) ? "YES" : "NO") + "\n");
			}
		}
		
		out.flush();
	}
	
	static boolean solve(String word)
	{
		// 메모이제이션을 위한 캐시 초기화
		cache = new int[5][5][10 + 1];
		for(int x = 0 ; x < 5 ; x++)
			for(int y = 0 ; y < 5 ; y++)
				for(int r = 0 ; r < 10 + 1 ; r++)
					cache[x][y][r] = -1;
		
		for(int x = 0 ; x < 5 ; x++)
			for(int y = 0 ; y < 5 ; y++)
				if(hasWord(word, x, y) == 1)
					return true;
		
		return false;
	}
	
	static int hasWord(String word, int x, int y)
	{
		int len = word.length();
		
		// 기저 사례 : word가 한 글자만 남은 경우
		if(len == 1)
			return (board[x][y] == word.charAt(0) ? 1 : 0);
		
		// 기저 사례 : 이미 한번이라도 계산한 경우
		if(cache[x][y][len] != -1)
			return cache[x][y][len];
		
		int find = 0;
		if(board[x][y] == word.charAt(0))
		{
			for(int d = 0 ; d < 8 ; d++)
			{
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || len <= 1)
					continue;
				
				if((find = hasWord(word.substring(1), nx, ny)) == 1)
					break;
			}
		}
		
		return cache[x][y][len] = find;
	}
}
