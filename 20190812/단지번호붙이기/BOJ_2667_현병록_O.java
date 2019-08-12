import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2667_단지번호붙이기 {
	static char map[][];
	static ArrayList<Integer> list;
	static int[] dx= {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	static int size, danzi;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		size = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		map = new char[size][size];
		danzi = 0;
		for(int i=0; i<size; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int y=0; y<size; y++) {
			for(int x=0; x<size; x++) {
				if(map[y][x]=='1') {
					danzi++;
					list.add(bfs(y, x, 0));
				}
			}
		}
		Collections.sort(list);
		bw.write(danzi+"\n"); 
		for(Integer integer : list) bw.write(integer+"\n");
		bw.flush();
	}
	private static int bfs(int y, int x, int cnt) {
		Queue<int[]> q = new LinkedList<int[]>();
		int temp[], ny, nx;
		q.offer(new int[] {y,x});
		map[y][x] = '0';
		while(!q.isEmpty()) {
			temp = q.poll();
			cnt++;
			for(int i=0; i<4; i++) {
				ny = temp[0] + dy[i]; nx = temp[1] + dx[i];
				if(nx<0|| nx>=size || ny<0 || ny>=size) continue;
				if(map[ny][nx]=='1') {
					q.offer(new int[] {ny, nx});
					map[ny][nx] = '0';
				}
			}
		}
		return cnt;
	}
}
