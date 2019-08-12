import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4963_섬의개수 {
	static int[][] 	map;
	static StringTokenizer st;
	static int height, width, cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(true) {
			st = new StringTokenizer(br.readLine());
			//지도 크기 입력
			width = Integer.parseInt(st.nextToken()); height = Integer.parseInt(st.nextToken()); 
			if(width==0&&height==0) break;
			map = new int[height][width];
			//지도 입력받기
			for(int h=0; h<height;h++) {
				st = new StringTokenizer(br.readLine());
				for(int w=0; w<width; w++) {
					map[h][w] = Integer.parseInt(st.nextToken());
				}
			}
			cnt = 0;
			for(int h=0; h<height;h++) {
				for(int w=0; w<width; w++) {
					if(map[h][w]==1) { //육지라면 섬인곳(인접한땅)을 다 바다로 만들어주기
						bfs(new int[] {h,w});
						cnt++;
					}
				}
			}
			bw.write(cnt+"");
			bw.newLine();
		}
		bw.flush();
	}
	private static void bfs(int[] p) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] xy,np;
		
		q.offer(new int[] {p[0], p[1]});
		map[p[0]][p[1]] = 0;
		while(!q.isEmpty()) {
			xy=q.poll();
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					np = new int[2];
					if(i==0&&j==0) continue;
					System.arraycopy(xy, 0, np, 0, xy.length);
					np[0] = xy[0]+i;
					np[1] = xy[1]+j;
					if (np[0] < 0 || np[0] >= height || np[1] < 0 || np[1] >= width)
						continue;
					if (map[np[0]][np[1]] == 1) {
						q.offer(np);
						map[np[0]][np[1]] = 0;
					}
				}
			}
		}
	}
}
