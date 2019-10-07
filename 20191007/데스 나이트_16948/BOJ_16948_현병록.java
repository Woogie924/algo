import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16948_현병록 {
	static int size, sy, sx, gy, gx, ny ,nx, count, qsize;
	static boolean visited[][];
	static int[] dy= {-2, -2, 0, 0, 2, 2}, dx= {-1, 1, -2, 2, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		visited = new boolean[size][size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		gy = Integer.parseInt(st.nextToken());
		gx = Integer.parseInt(st.nextToken());
		
		if(bfs())
			System.out.println(count);
		else
			System.out.println(-1);
	}
	private static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sy, sx});
		visited[sy][sx] = true;
		
		while(!q.isEmpty()) {
			qsize = q.size();
			count++;
			for(int i=0; i<qsize; i++) {
				int[] temp = q.poll();
				for(int d=0; d<6; d++) {
					ny = temp[0]+dy[d];
					nx = temp[1]+dx[d];
					if(ny<0||nx<0||ny>=size||nx>=size||visited[ny][nx]) continue;
					if(ny==gy && nx==gx) {
						return true;
					}
					q.offer(new int[] {ny, nx});
					visited[ny][nx]=true;
				}
			}
		}
		return false;
	}
}
