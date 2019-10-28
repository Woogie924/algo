package six.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 보글 문제..
 * 단어를 찾고 
 */

public class BOGGLE2 {
	static char[][] board = new char[5][5];
	static final int dx[] = {1,1,0,-1,-1,-1,0,1}; //우 부터 시계방향으로돔
	static final int dy[] = {0,1,1,1,0,-1,-1,-1};
	static int N;
	static boolean[][][] dp;
	static boolean answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0; t<tc; ++t) {
			
			for(int y=0; y<5; ++y) {
				board[y] = br.readLine().toCharArray();
			}
			
			N = Integer.parseInt(br.readLine());
			
			for(int i=0; i<N; ++i) {
				char[] word = br.readLine().toCharArray();
				dp = new boolean[5][5][10];
				
				for(int y=0; y<5; ++y) {
					for(int x=0; x<5; ++x) {
						if(board[y][x] == word[0]) {
							dfs(y,x,0,1, word);
							if(answer) break;
						}
					}
				}
				sb.append(String.valueOf(word)+" "+(answer ? "YES" : "NO")+"\n");
				answer = false;
			}
			
		}
		System.out.println(sb);
	}
	private static void dfs(int y, int x, int idx, int depth, char[] word) {
		
		dp[y][x][idx] = true;
		
		if(depth == word.length) {
			answer = true;
			return;
		}
		
		for(int dir=0; dir<8; ++dir) {
			int ny = y+dy[dir];
			int nx = x+dx[dir];
			
			if(ny<0 || nx<0 || ny>=5 || nx>=5) continue;
			if(board[ny][nx] == word[idx+1]) {
				if(dp[ny][nx][idx+1]) continue;
				dfs(ny, nx, idx+1, depth+1, word);
			}
		}
		return;
	}


}
