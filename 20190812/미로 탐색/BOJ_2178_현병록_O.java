import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2178_미로탐색 {
	static StringTokenizer st;
	static int n,m;
	static char map[][];
	static int minCnt, dx[] = {1, -1, 0, 0}, dy[] = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for(int y=0; y<n; y++) {
			map[y] = br.readLine().toCharArray();
		}
		minCnt = Integer.MAX_VALUE;
		//1,1에서 시작 , count
		map[0][0]='0';
//		dfs(0, 0, 1);
		bfs(0, 0, 1);
		System.out.println(minCnt);
	}
	private static void bfs(int y, int x, int cnt) {
		Queue<int[]> q = new LinkedList<int[]>();
		int ny, nx, temp[];
		q.offer(new int[] {y, x, cnt});
		while(!q.isEmpty()) {
			 temp = q.poll();
			 if(temp[0]==n-1&&temp[1]==m-1) {if(minCnt>temp[2]) minCnt = temp[2]; continue;}
			 for(int i=0; i<4; i++) {
					ny = temp[0] + dy[i]; nx = temp[1] + dx[i];
					if(ny<0||ny>=n||nx<0||nx>=m) continue;
					if(map[ny][nx]=='1') {
						q.offer(new int[] {ny, nx, temp[2]+1});
						map[ny][nx]='0';
					}
				}	 
		}
	}
	private static void dfs(int y, int x, int cnt) {
		if(y==n-1 && x==m-1) {
			if(minCnt>cnt) minCnt = cnt;
			return;
		}
		int ny, nx;
		for(int i=0; i<4; i++) {
			ny = y + dy[i]; nx = x + dx[i];
			if(ny<0||ny>=n||nx<0||nx>=m) continue;
			if(map[ny][nx]=='1') {
				map[ny][nx]='0';
				dfs(ny, nx, cnt+1);
				map[ny][nx]='1';
			}
		}
	}

}
