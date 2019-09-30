import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[][] map;
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String[] s;
		for(int i=0; i<N; ++i) {
			s = br.readLine().split("\\s");
			for(int j=0; j<N; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		dfs(0, 0, 1);
		System.out.println(answer);
	}
	private static void dfs(int dir, int r, int c) {
		if(r==N-1 && c==N-1) {
			answer++;
			return;
		}
		
		if(dir==0) {
			if(c+1<N && map[r][c+1]==0) {
				dfs(0, r, c+1);
			}
			if(c+1<N && r+1<N && map[r+1][c+1]==0 && map[r+1][c]==0 && map[r][c+1]==0) {
				dfs(2, r+1, c+1);
			}
		}else if(dir==1) {
			if(r+1<N && map[r+1][c]==0)
				dfs(1, r+1, c);
			if(c+1<N && r+1<N && map[r+1][c+1]==0 && map[r+1][c]==0 && map[r][c+1]==0)
				dfs(2, r+1, c+1);
		}else if(dir==2) {
			if(c+1<N && map[r][c+1]==0)
				dfs(0, r, c+1);
			if(r+1<N && map[r+1][c]==0)
				dfs(1, r+1, c);
			if(c+1<N && r+1<N && map[r+1][c+1]==0 && map[r+1][c]==0 && map[r][c+1]==0)
				dfs(2, r+1, c+1);
		}
	}

}