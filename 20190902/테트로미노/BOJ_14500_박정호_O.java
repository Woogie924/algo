import java.util.Scanner;

public class Main {
	static int[][][] check = { 
			{ { 0, 1 }, { 1, 1 }, { 1, 0 } }, 
			{ { 0, 1 }, { 0, 2 }, { 0, 3 } },
			{ { 1, 0 }, { 2, 0 }, { 3, 0 } }, 
			{ { -1, 0 }, { 0, -1 }, { 0, 1 } }, 
			{ { -1, 0 }, { 1, 0 }, { 0, 1 } },
			{ { -1, 0 }, { 1, 0 }, { 0, -1 } }, 
			{ { 1, 0 }, { 0, -1 }, { 0, 1 } },
			{ { -1, 0 }, { -2, 0 }, { 0, 1 } }, 
			{ { -1, 0 }, { 0, -1 }, { 0, -2 } },
			{ { 0, -1 }, { 1, 0 }, { 2, 0 } }, 
			{ { 1, 0 }, { 0, 1 }, { 0, 2 } }, 
			{ { 0, -1 }, { 0, -2 }, { 1, 0 } },
			{ { 1, 0 }, { 2, 0 }, { 0, 1 } }, 
			{ { 0, 1 }, { 0, 2 }, { -1, 0 } },
			{ { 0, -1 }, { -1, 0 }, { -2, 0 } }, 
			{ { -1, 0 }, { 0, 1 }, { 1, 1 } },
			{ { 0, 1 }, { 1, 0 }, { -1, 1 } }, 
			{ { 0, -1 }, { 1, 0 }, { 1, 1 } }, 
			{ { 0, 1 }, { 1, 0 }, { 1, -1 } }
	};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		int[][] board = new int[r][c];
		
		for(int i=0; i<r; ++i) {
			for(int j=0; j<c; ++j) {
				board[i][j] = sc.nextInt();
			}
		}
		int max = Integer.MIN_VALUE;
		for(int i=0; i<r; ++i) {
			for(int j=0; j<c; ++j) {
				for(int k=0; k<check.length; ++k) {
					int temp = board[i][j];
					for(int l=0; l<3; ++l) {
						int x = check[k][l][0];
						int y = check[k][l][1];
						if(i+x<0 || i+x>=r || j+y<0 || j+y>=c) {
							break;
						}
						temp += board[i+x][j+y];
					}
					if(max < temp)
						max = temp;
				}
			}
		}
		
		System.out.println(max);
	}

}
