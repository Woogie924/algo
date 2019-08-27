import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234_현병록_O {
	static int size, l, r;
	static int[][] nations;
	static boolean[][] visited;
	static ArrayList<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		nations = new int[size][size];
		visited = new boolean[size][size];
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				nations[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(movement());
	}
	private static int movement() {//인구이동횟수를 리턴
		int times=0, cnt;
		
		while(true) {
			for(boolean[] v : visited) Arrays.fill(v, false);
			cnt=0;
			for(int i=0; i<size; i++) {
				for(int j=0; j<size; j++) {
					if(!visited[i][j] && bfs(i,j))
						cnt++;
				}
			}
			if(cnt==0)
				break;
			times++;
		}
		return times;
	}
	static int[] dy= {1,-1,0,0}, dx= {0,0,1,-1};
	private static boolean bfs(int starty, int startx) {
		Queue<Integer> q = new LinkedList<Integer>();
		list.clear();
		int y,x,ny,nx;
		int cnt=1, sum=nations[starty][startx];
		q.offer(starty); q.offer(startx);
		visited[starty][startx] = true;
		list.add(new int[] {starty, startx});
		while(!q.isEmpty()) {
			y=q.poll(); x=q.poll();
			for(int d=0; d<4; d++) {
				ny = y+dy[d]; nx=x+dx[d];
				if(ny<0||nx<0||ny>=size||nx>=size||visited[ny][nx]) continue;
				if(isUnion(y,x,ny,nx)) {
					q.offer(ny); q.offer(nx);
					visited[ny][nx]=true;
					cnt++; sum+=nations[ny][nx];
					list.add(new int[] {ny, nx});
				}
			}
		}
		int val = sum/cnt;
		for(int i=0; i<list.size(); i++	) {
			int[] point = list.get(i);
			nations[point[0]][point[1]] = val;
		}
		if(cnt!=1)
			return true;
		return false;
	}
	private static boolean isUnion(int y, int x, int ny, int nx) {
		int temp = Math.abs(nations[y][x]-nations[ny][nx]);
		if(temp >= l && temp <= r) {
			return true;
		}
		return false;
	}
}
