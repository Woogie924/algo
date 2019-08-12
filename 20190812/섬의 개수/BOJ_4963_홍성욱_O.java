import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

 

public class Main {
	static int w;
	static int h;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*
		 *  0 0 입력시 종료
		 */
		String [] input;
		
		while(true) {

			input = br.readLine().split(" ");
			if(input[0].equals("0") && input[1].equals("0")) {
				return;
			}
			w =Integer.parseInt(input[0]);
			h =Integer.parseInt(input[1]);
			MAP = new int[h][w];
			visited = new boolean[h][w];
			
			for(int i=0; i<h;i++) {
				String [] row = br.readLine().split(" ");
				for(int j=0; j<w;j++) {
					MAP[i][j]  = Integer.parseInt(row[j]);
				}
			}

			int cnt=0;
			for(int i=0; i<h; i++) {
				for(int j=0; j<w;j++) {
					if(MAP[i][j] == 1 && visited[i][j] == false) {
						bfs(i,j, cnt);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
			cnt=0;
		}
	}

	static int cnt=0;
	static int [][] MAP;
	static boolean [][] visited ;
	static int [] dx = {0, 0, -1, 1, -1, 1, -1, 1};	//8방향 핸들링
	static int [] dy = {-1, 1, 0, 0, -1, 1, 1, -1};
	static void bfs(int x,int y, int cnt) {
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		visited[x][y] = true;
		qx.offer(x);
		qy.offer(y);
//		if( MAP[x][y] ==1) {
//			cnt++;
//		}

		while(!qx.isEmpty() && !qy.isEmpty()) {
			x = qx.poll();
			y = qy.poll();
			for(int k=0; k<8 ; k++) {

				int tx = x + dx[k];
				int ty = y + dy[k];
				if(0>tx || 0>ty || tx >= h || ty >=w) { //외곽일때
					continue;
				}
				if(MAP[tx][ty] == 1 && visited[tx][ty] == false) {
					qx.offer(tx);
					qy.offer(ty);
					visited[tx][ty] = true;
				}
			}
		}
	}

	static class position{
		int x;
		int y;
	}

}