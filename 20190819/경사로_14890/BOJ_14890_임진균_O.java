import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main
{
	static BufferedReader in;
	static BufferedWriter out;
	
	static int N; // 지도의 크기
	static int L; // 경사로의 가로 길이
	static int map[][]; // 지도 정보
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// N, L 입력
		tokenizer = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		L = Integer.parseInt(tokenizer.nextToken());
		
		// 지도 정보 입력
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++)
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
		}
		
		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		int count = 0; // 지나갈 수 있는 길의 개수
		int path[] = new int[N]; // 길의 높이를 저장한다.
		
		// 가로 방향 길 검사
		for(int x = 0 ; x < N ; x++)
		{
			for(int y = 0 ; y < N ; y++)
				path[y] = map[x][y];
			
			if(check(path))
				count++;
		}
		
		// 세로 방향 길 검사
		for(int y = 0 ; y < N ; y++)
		{
			for(int x = 0 ; x < N ; x++)
				path[x] = map[x][y];
			
			if(check(path))
				count++;
		}
		
		return count;
	}
	
	static boolean check(int path[])
	{
		boolean installed[] = new boolean[N]; // 해당 지점에 경사로가 설치 되었는지?
		
		for(int i = 1 ; i < N ; i++)
		{
			int diff = path[i] - path[i - 1];
			
			// 경사로 설치가 불가능하다.
			if(Math.abs(diff) > 1)
				return false;
			
			// 내려가는 경사로 설치가 필요하다.
			else if(diff == -1)
			{
				// 내려가는 경사로 설치가 가능한지 확인한다.
				for(int j = i ; j <= (i - 1 + L) ; j++)
					if(j < 0 || j >= N || path[j] != (path[i - 1] - 1) || installed[j])
						return false;
				
				// 경사로를 설치한다.
				for(int j = i ; j <= (i - 1 + L) ; j++)
					installed[j] = true;
			}
			
			// 올라가는 경사로 설치가 필요하다.
			else if(diff == 1)
			{
				// 올라가는 경사로 설치가 가능한지 확인한다.
				for(int j = (i - L) ; j <= (i - 1) ; j++)
					if(j < 0 || j >= N || path[j] != (path[i] - 1) || installed[j])
						return false;
				
				// 경사로를 설치한다.
				for(int j = (i - L) ; j <= (i - 1) ; j++)
					installed[j] = true;
			}
		}

		return true;
	}
}