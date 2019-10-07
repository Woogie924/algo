import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int R;
	static int C;
	static int N;
	static String [][] MAP;
	static Queue<position> boomList = new LinkedList<>();
	static ArrayList<position> boom = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");

		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		N = Integer.parseInt(input[2]);
		MAP = new String[R][C];

		for(int i=0; i<R; i++) {
			input = br.readLine().split("");
			for(int j=0; j<C;j++) {
				MAP[i][j]= input[j];
			}
		}
		sol();
	}
	
	static void sol() {
		int time=1;
		int i=1;
		// 0초... 맵셋팅
		// 1초... 봄버맨 활동.. 나머지 구역 폭탄설치
		// 2초... 0초에서의 폭탄 뿌우움!
		// 3초... 봄버맨 활동.. 나머지 구역 폭탄설치
		addBoom();
		//printBoard();
		
		while(i<N) {
			time = i%2;
			//System.out.print(i+"초...");
			switch(time) {

			case 1:
				//System.out.println("폭탄 설치");
				install();
				break;
			case 0:
				//System.out.println("폭탄 터뜨리기");
				boom();
				clearList();
				addBoom();
				break;
			}
			i++;
		}

		printBoard();
	}
	static void printBoard() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C;j++) {
				System.out.print(MAP[i][j]);
			}
			System.out.println();
		}
	}
	static void clearList() {
		boom.removeAll(boom);
		while(!boomList.isEmpty()) {
			boomList.poll();
		}
	}
	static void install() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(MAP[i][j].equalsIgnoreCase("O")) {
					continue;
				}
				MAP[i][j] = "O";
			}
		}
	}
	static void addBoom() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(MAP[i][j].equalsIgnoreCase("O")) {
					boom.add(new position(i,j));
					boomList.offer(new position(i,j));
				}
			}
		}
	}
	static void boom() {
		int row, col;
		int tRow, tCol;
		while(!boomList.isEmpty()) {
			row=boomList.peek().row;
			col = boomList.peek().column;
			boomList.poll();
			MAP[row][col]=".";
			for(int dir=0; dir<4;dir++) {
				tRow = row + dy[dir];
				tCol = col + dx[dir];

				if(tRow<0 || tCol<0 || tRow>=R || tCol>=C) {
					//외곽일 떄
					continue;
				}
				if(check(tRow,tCol)) {
					continue;
				}
				if(MAP[tRow][tCol].equalsIgnoreCase("O")) {
						MAP[tRow][tCol] =".";
				}
			}

		}
	}
	static boolean check(int row, int col) {
		boolean flag=false;
		for(int i=0; i<boom.size(); i++) {
			if(boom.get(i).row==row && boom.get(i).column==col) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	static int [] dx = {0,0,-1,1};
	static int [] dy= {-1,1,0,0};
	static class position{
		int row;
		int column;
		position(int row, int column){
			this.row = row;
			this.column = column;
		}
	}

}