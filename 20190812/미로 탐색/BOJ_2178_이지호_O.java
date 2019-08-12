import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2178 {
	static class coordinate {
		int x;
		int y;
		public coordinate(int x, int y) {//미로탑색
			this.x = x;
			this.y = y;
		}
		public coordinate() {}
	}
	static boolean[][]visited;
	static int[][] map;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {//미로찾기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 2][M + 2];
		visited = new boolean[N+2][M+2];
		for (int i = 1; i <= N; i++) {
			String temp = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = temp.charAt(j - 1) - '0';
			}
		}
		BFS(1,1);
		/*
		 * for(int i =1; i<=N; i++) { for(int j = 1; j<=M; j++) {
		 * System.out.print(map[i][j]+" "); } System.out.println(); }
		 */
	}
	
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	private static void BFS(int i, int j) {
		Queue<coordinate> q = new LinkedList<>();
		q.add(new coordinate(i, j));
		visited[i][j]=true;
		while (!q.isEmpty()) {
			coordinate temp = new coordinate();
			temp = q.poll();
			if(temp.x==N&&temp.y==M) {
				System.out.println(map[N][M]);
			}
			for (int search = 0; search < 4; search++) {
				int tx = temp.x+dx[search];
				int ty = temp.y+dy[search];
				if (map[tx][ty] == 1&&!visited[tx][ty]) {
					q.add(new coordinate(tx, ty));
					visited[tx][ty]=true;
					map[tx][ty]=map[temp.x][temp.y]+1;
				}
			}
			
		}
	}
}
