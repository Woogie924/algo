import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16926_이옥주_O {
	static int rotateCnt, count, x, y, R, i;
	static int row, col;
	static boolean isSuccess = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		row = Integer.parseInt(str[0]);
		col = Integer.parseInt(str[1]);
		R = Integer.parseInt(str[2]);
		int[][] arr = new int[row+1][col+1];
		int[][] result = new int[row+1][col+1];
		
		for(int i=1; i<=row; i++) {
			str = br.readLine().split(" ");
			
			for(int j=0; j<col; j++) {
				arr[i][j+1] = Integer.parseInt(str[j]);
			}
		}  //입력받기
		
		if(row<col) {
			count = row/2;
		}
		
		else {
			count = col/2;
		}
		
		for(i=0; i<count; i++) {
			aa1:for(int j=i+1; j<=col-i; j++) {  //윗줄
				rotateCnt = 0;
				isSuccess = false;
				x = i+1;
				y = j;
				
				while(isSuccess==false) {
					rotateLeft();
					if(isSuccess == true) {
						result[x][y] = arr[i+1][j];
						continue aa1;
					}
					
					rotateDown();
					if(isSuccess == true) {
						result[x][y] = arr[i+1][j];
						continue aa1;
					}
					
					rotateRight();
					if(isSuccess == true) {
						result[x][y] = arr[i+1][j];
						continue aa1;
					}
					
					rotateUp();
					if(isSuccess == true) {
						result[x][y] = arr[i+1][j];
						continue aa1;
					}
				}
			}
			
			aa2:for(int j=i+1; j<=row-i; j++) {  //왼쪽줄
				rotateCnt = 0;
				isSuccess = false;
				x = j;
				y = i+1;
				
				while(isSuccess==false) {
					rotateDown();
					if(isSuccess == true) {
						result[x][y] = arr[j][i+1];
						continue aa2;
					}
						
					rotateRight();
					if(isSuccess == true) {
						result[x][y] = arr[j][i+1];
						continue aa2;
					}
					
					rotateUp();
					if(isSuccess == true) {
						result[x][y] = arr[j][i+1];
						continue aa2;
					}
					
					rotateLeft();
					if(isSuccess == true) {
						result[x][y] = arr[j][i+1];
						continue aa2;
					}
				}
			}
			
//		for(int k=1; k<=row; k++) {
//			for(int l=1; l<=col; l++) {
//				System.out.print(result[k][l] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
			aa3:for(int j=i+1; j<=col-i; j++) {  //아랫줄
				rotateCnt = 0;
				isSuccess = false;
				x = row-i;
				y = j;
				
				while(isSuccess==false) {
					rotateRight();
					if(isSuccess == true) {
						result[x][y] = arr[row-i][j];
						continue aa3;
					}
					
					rotateUp();
					if(isSuccess == true) {
						result[x][y] = arr[row-i][j];
						continue aa3;
					}
					
					rotateLeft();
					if(isSuccess == true) {
						result[x][y] = arr[row-i][j];
						continue aa3;
					}
					
					rotateDown();
					if(isSuccess == true) {
						result[x][y] = arr[row-i][j];
						continue aa3;
					}
				}
			}
		
			aa4:for(int j=i+1; j<=row-i; j++) {  //오른쪽줄
				rotateCnt = 0;
				isSuccess = false;
				x = j;
				y = col-i;
				
				while(isSuccess==false) {
					rotateUp();
					if(isSuccess == true) {
						result[x][y] = arr[j][col-i];
						continue aa4;
					}
					
					rotateLeft();
					if(isSuccess == true) {
						result[x][y] = arr[j][col-i];
						continue aa4;
					}
					
					rotateDown();
					if(isSuccess == true) {
						result[x][y] = arr[j][col-i];
						continue aa4;
					}
					
					rotateRight();
					if(isSuccess == true) {
						result[x][y] = arr[j][col-i];
						continue aa4;
					}
				}
			}
		}
		
		for(int i=1; i<=row; i++) {
			for(int j=1; j<=col; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void rotateLeft() {
		while(y>i+1) {
			rotateCnt++;
			y--;
			
			if(rotateCnt == R) {
				isSuccess = true;
				break;
			}
		}
	}
	
	private static void rotateRight() {
		while(y<col-i) {
			rotateCnt++;
			y++;
			
			if(rotateCnt == R) {
				isSuccess = true;
				break;
			}
		}
	}
	
	private static void rotateDown() {
		while(x<row-i) {
			rotateCnt++;
			x++;
			
			if(rotateCnt == R) {
				isSuccess = true;
				break;
			}
		}
	}
	
	private static void rotateUp() {
		while(x>1+i) {
			rotateCnt++;
			x--;
			
			if(rotateCnt == R) {
				isSuccess = true;
				break;
			}
		}
	}

}
