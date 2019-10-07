import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16948 {
	static class posit {
		int x;
		int y;

		public posit(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N, r1, c1, r2, c2,count;
	static int map[][];
	static boolean visit[][],check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visit = new boolean[N][N];
		bfs(r1, c1);
		if(!check) {
			System.out.println(-1);
		}else {
			System.out.println(map[r2][c2]);
		}
	}

	static int dx[] = { -2, -2, 0, 0, 2, 2 };
	static int dy[] = { -1, 1, -2, 2, -1, 1 };

	private static void bfs(int sx, int sy) {
		Queue<posit> q = new LinkedList<>();
		q.add(new posit(sx,sy));
		visit[sx][sy]= true;
		while(!q.isEmpty()) {
			posit temp = q.poll();
			for(int i = 0; i<6; i++) {
				int tx = temp.x+dx[i];
				int ty = temp.y+dy[i];
				if(tx>=N||tx<0||ty>=N||ty<0||visit[tx][ty]) continue;
				map[tx][ty]=map[temp.x][temp.y]+1;
				if(tx==r2&&ty==c2) {
					check=true;
					return;
				}
				visit[tx][ty]=true;
				q.add(new posit(tx,ty));
			}
			
		}
	}

}
