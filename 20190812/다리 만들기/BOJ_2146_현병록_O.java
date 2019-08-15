import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146_현병록_O {
	static int size,number;
	static int[][] map;
	static int[] dx= {1,-1,0,0}, dy= {0,0,1,-1};
	static boolean flag, visit[][];
	static Queue<Integer> node;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		visit = new boolean[size][size];
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		number = 2; node = new LinkedList<Integer>();
		//넘버링해주기 1-방문안한 육지, 0-바다, 2~ - 방문한 섬 넘버링
		for(int i =0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j]==1) {
					numberingBFS(i,j,number);
					number++;
				}
			}
		}
		/*for(int[] m : map) System.out.println(Arrays.toString(m));
		System.out.println(node.toString());*/
		if(number<=3) {System.out.println(0); return;}
		//다리 만들기 시작하기
		System.out.println(makeBridge());
	}

	private static int makeBridge() {
		int qsize, y, x, ny, nx, cnt = 0;
		int min = Integer.MAX_VALUE;
		while (!node.isEmpty()) {

			cnt++;
			qsize = node.size() / 2;
			for (int i = 0; i < qsize; i++) {
				y = node.poll();
				x = node.poll();
				for (int j = 0; j < 4; j++) {
					ny = y + dy[j];
					nx = x + dx[j];
					if (ny < 0 || ny >= size || nx < 0 || nx >= size || map[ny][nx] == map[y][x])
						continue;
					if (map[ny][nx] == 0) {
						node.offer(ny);
						node.offer(nx);
						map[ny][nx] = map[y][x];
					} else {// 다른 섬으로부터 온 다리를 만났을 때
							// map의 값이 클수록 먼저 출발한거니까 자기보다 낮은 애를 만났는경우는 2*n-1이고 자기보다 큰값과 만난 경우는 2*n으로 해준다.
						if (map[y][x] > map[ny][nx]) {
							if (min > (2 * cnt - 1))
								min = 2 * cnt - 1;
						} else {
							if (min > (2 * (cnt - 1)))
								min = 2 * (cnt - 1);
						}
					}

				}
			}
			if (min != Integer.MAX_VALUE)
				return min;
		}
		return 0;
	}
	private static void numberingBFS(int sy, int sx, int isnumber) {
		Queue<Integer> q = new LinkedList<Integer>();
		int ny,nx, y, x;
		q.offer(sy); q.offer(sx); //시작점 큐에 삽입
		map[sy][sx] = isnumber;
		while(!q.isEmpty()) {
			y = q.poll(); x = q.poll();
			for(int i=0; i<4; i++) {
				ny = y+ dy[i]; nx = x+dx[i];
				if(ny<0||ny>=size||nx<0||nx>=size) continue;
				if(map[ny][nx]==0&&!visit[y][x]) {
					node.offer(y); node.offer(x);
					map[y][x]=isnumber;
					visit[y][x]=true;
				}
				
				if(map[ny][nx]==1) {
					map[ny][nx]=isnumber;
					q.offer(ny); q.offer(nx);
				}
			}
			
		}
	}
}
