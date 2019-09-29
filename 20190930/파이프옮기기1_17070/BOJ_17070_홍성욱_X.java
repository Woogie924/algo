import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N=0;
	static boolean [][] visit;
	static int [][] MAP;
	static int [][] res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		res = new int [N][N];
		visit = new boolean[N][N];
		MAP = new int[N][N];
		
		String [] input;
		for(int i=0; i<N;i++) {		//맵 초기화
			input = br.readLine().split(" ");
			for(int j=0; j<N;j++) {
				MAP[i][j] = Integer.parseInt(input[j]);
			}
		}
		bfs(0,1);		//파이프 끝점을 기준으로~
		
//		for(int i=0; i<N;i++) {
//			for(int j=0; j<N;j++) {
//				System.out.print(res[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println(res[N-1][N-1]);
	}
	static int [] dx = {1,1,0};
	static int [] dy = {0,1,1};
	static void bfs(int curRow, int curColumn) {
		Queue<position> q = new LinkedList<>();
		position cur = new position(curRow,curColumn);
		q.offer(cur);
		visit[curRow][curColumn] = true;
		visit[0][0] = true;
		
		while(!q.isEmpty()) {
			int row = q.peek().row;
			int column = q.peek().column;
			q.poll();
			visit[row][column] =true;
			
			a:for(int dir=0; dir<3; dir++) {
				int tRow= row+dy[dir];
				int tColumn = column+dx[dir];
				if(tRow<0 || tRow>=N || tColumn<0 || tColumn>=N) {
					//외곽,,,
					break;
				}
				if(dir==2) {
					//대각선일때
					for(int dd=0; dd<3;dd++) {
						if(MAP[row+dy[dd]][column+dx[dd]]==1) {
							continue a;
						}
					}
				}
				if(MAP[tRow][tColumn] ==1 ) {
					break;
				}
				
				if(MAP[tRow][tColumn]==0 && !visit[tRow][tColumn]) {
					res[tRow][tColumn]++;
					q.offer(new position(tRow,tColumn));
				}
			}
			
			
		}
	}
	static class position{
		int row;
		int column;
		position(){}
		position(int row, int column){
			this.row = row;
			this.column = column;
		}
	}
}
