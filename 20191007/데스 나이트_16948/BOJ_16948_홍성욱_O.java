import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int [][] MAP;
	static boolean [][] visited;
	static int N;
	static position start;
	static position goal;
	static boolean flag=false;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		String [] input = br.readLine().split(" ");
		MAP = new int[N][N];
		visited= new boolean[N][N];
		start = new position(Integer.parseInt(input[0]), Integer.parseInt(input[1]),0);
		goal = new position(Integer.parseInt(input[2]), Integer.parseInt(input[3]),0);
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N ; j++) {
				MAP[i][j] = -1;
			}
		}
		bfs();
		if(flag) {
			System.out.println("-1");
		}
		
	}
	
	static int [] dx = {-1,1,-2,2,-1,1};
	static int [] dy = {-2,-2,0,0,2,2};
	static void bfs() {
		Queue<position> q = new LinkedList<>();
		q.offer(start);
		visited[start.row][start.column]= true;
		int row;
		int column;
		int phase;
		while(!q.isEmpty()) {
			row = q.peek().row;
			column = q.peek().column;
			phase = q.peek().phase;
			if(goal.row == row && goal.column == column) {
				//목표지점 도착
				System.out.println(phase);
				return;
			}
//			MAP[row][column]++;
			
			q.poll();
			
			for(int dir=0; dir<6; dir++) {
				int tRow = row + dy[dir];
				int tColumn = column + dx[dir];
			
				if(tRow<0 || tColumn<0 || tRow >= N || tColumn >= N) {
					//외곽일 때
					continue;
				}

				if(!visited[tRow][tColumn]){
//					MAP[tRow][tColumn]++;
					visited[tRow][tColumn] = true;
					q.offer(new position(tRow,tColumn, phase+1));
				}
			}
		}
		flag=true;
	}


	static class position{
		int row;
		int column;
		int phase;
		position(int row, int column, int phase) {
			this.row = row;
			this.column = column;
			this.phase = phase;
		}
	}

}