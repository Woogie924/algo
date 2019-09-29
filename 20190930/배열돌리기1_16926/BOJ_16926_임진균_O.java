import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
	
	static int N, M, R;
	static int arr[][];
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		R = Integer.parseInt(tokenizer.nextToken());
		
		arr = new int[N][M];
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++)
				arr[i][j] = Integer.parseInt(tokenizer.nextToken());
		}
		
		solve();

		for(int i = 0 ; i < N ; i++)
		{
			out.write(arr[i][0] + "");
			for(int j = 1 ; j < M ; j++)
				out.write(" " + arr[i][j]);
			out.newLine();
		}
		
		out.flush();
	}

	static void solve() 
	{
		int maxDepth = Math.min(N, M) / 2;
		
		for(int r = 0 ; r < R ; r++)
			for(int d = 0 ; d < maxDepth ; d++)
				rotate(d);
	}

	static void rotate(int depth) 
	{
		int sx = depth;
		int sy = depth;
		int H = N - depth * 2;
		int W = M - depth * 2;
		
		int temp = arr[sx][sy];
		for(int y = sy + 1 ; y < sy + W ; y++)
			arr[sx][y - 1] = arr[sx][y];
		
		for(int x = sx + 1 ; x < sx + H ; x++)
			arr[x - 1][sy + W - 1] = arr[x][sy + W - 1];
		
		for(int y = sy + W - 1 ; y > sy ; y--)
			arr[sx + H - 1][y] = arr[sx + H - 1][y - 1];
		
		for(int x = sx + H - 1 ; x > sx + 1; x--)
			arr[x][sy] = arr[x - 1][sy];
		
		arr[sx + 1][sy] = temp;
	}
}
