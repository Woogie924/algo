import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int leftRow;
	static int rightRow;
	static int leftColumn;
	static int rightColumn;
	static int [][] MAP;
	static Queue<Integer> list = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String [] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int R = Integer.parseInt(input[2]);
		leftRow=0;	rightRow = N-1;
		leftColumn=0;	rightColumn=M-1;
		MAP = new int[N][M];
		
		for(int i=0; i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<M;j++) {
				MAP[i][j] = Integer.parseInt(input[j]);
			}
		}
		for(int i=0; i<R; i++) {
				rotate();
				leftRow=0;	rightRow = N-1;
				leftColumn=0;	rightColumn=M-1;
		}
		for(int i=0; i<N;i++) {
			for(int j=0; j<M;j++) {
				System.out.print(MAP[i][j]+ " ");
			}
			System.out.println();
		}
		
		
	}
	static boolean checkBoundary() {
		boolean flag=true;
		if(leftRow>=rightRow || leftColumn>=rightColumn) {
			flag = false;
		}//커서가 역전되면 ~
		
		return flag;
	}
	static void rotate() {
		/*
		 * leftRow, leftColumn이 시작점!!
		 */
		while(checkBoundary()) {
			column1();
			row2();
			column2();
			row1();
			
			for(int i=leftRow+1; i<=rightRow ; i++) {
				MAP[i][leftColumn]= list.poll();
			}
			for(int i=leftColumn+1; i<=rightColumn; i++) {
				MAP[rightRow][i]= list.poll();
			}
			for(int i=rightRow-1; i>=leftRow; i--) {
				MAP[i][rightColumn] = list.poll();
			}
			for(int i=rightColumn-1; i>=leftColumn ; i--) {
				MAP[leftRow][i] = list.poll();
			}
			leftRow++; rightRow--;
			leftColumn++; rightColumn--;
		}
		
	}

	static void column1() {
		for(int i=leftRow; i<rightRow ; i++) {
			list.offer(MAP[i][leftColumn]);
		}
	}
	static void row2() {
		for(int i=leftColumn; i<rightColumn; i++) {
			list.offer(MAP[rightRow][i]);
		}
	}
	static void column2() {
		for(int i=rightRow; i>leftRow; i--) {
			list.offer(MAP[i][rightColumn]);
		}
	}
	static void row1() {
		for(int i=rightColumn; i>leftColumn ; i--) {
			list.offer(MAP[leftRow][i]);
		}
	}
}
