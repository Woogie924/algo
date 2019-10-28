import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1520_홍성욱_X {
	static int M;
	static int N;
	static int [][] MAP;
	static boolean [][] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String [] input = br.readLine().split(" ");

		M = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		MAP = new int[M][N];
		//		visited = new boolean[M][N];
		for(int i=0; i<M;i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				MAP[i][j] = Integer.parseInt(input[j]);
			}
		}
		bfs();
		System.out.println(res);
	}
	static int [] dx = {0,0,-1,1};
	static int [] dy = {-1,1,0,0};
	static void bfs() {
		Queue<position> q = new LinkedList<position>();
		q.offer(new position(0,0));

		int tRow; int tColumn;
		position cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cur.row == M-1 && cur.column==N-1) {
				//도착점
				res++;
				continue;
			}
			//			visited[cur.row][cur.column] = true;

			for(int dir=0; dir<4; dir++) {
				tRow = cur.row + dy[dir];
				tColumn = cur.column + dx[dir];

				//외곽일 때
				if(tRow<0 || tColumn<0 || tRow>=M || tColumn >=N) {
					continue;
				}
				if(MAP[cur.row][cur.column] > MAP[tRow][tColumn]) {
					q.offer(new position(tRow,tColumn));
				}
			}
		}
	}
	static int res=0;
	static class position{
		int row;
		int column;
		position(int row, int column){
			this.row = row;
			this.column = column;
		}
	}

}
