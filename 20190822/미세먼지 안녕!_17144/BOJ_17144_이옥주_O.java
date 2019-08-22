import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_17144_이옥주_O {
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		
		ArrayList<position> list = new ArrayList<>();
		int[][] temp;
		int R = Integer.parseInt(str[0]);
		int C = Integer.parseInt(str[1]);
		int time = Integer.parseInt(str[2]);
		arr = new int[R][C];
		
		for(int i=0; i<R; i++) {
			str = br.readLine().split(" ");
			
			for(int j=0; j<C; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
				if(arr[i][j] == -1) {
					list.add(new position(i,j));   //공기 청정기 위치 찾기
				}
			}
		} // 입력받기
		
		int diffusion = 0;
		int[] x = {0,0,-1,1};
		int[] y = {-1,1,0,0};
		int tempX, tempY;
		int count = 0;
		
		for(int p=0; p<time; p++) {
			temp = new int[R][C];
			for(int i=0; i<R; i++) {  //미세먼지 확산
				for(int j=0; j<C; j++) {
					if(arr[i][j]==0) continue;
					count=0;
					diffusion = arr[i][j] / 5;
					
					for(int k=0; k<4; k++) {
						tempX = i+x[k];
						tempY = j+y[k];
						
						if(tempX<0 || tempX>=R || tempY<0 || tempY>=C || arr[tempX][tempY]==-1) continue;
						temp[tempX][tempY] += diffusion;
						count++;
					}
					
					temp[i][j] -= arr[i][j]/5 * count;	
				}
			}  
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					arr[i][j]+=temp[i][j];
				}
			} //미세먼지 확산 끝
			
			
			//공기 청정기 작동      1:오른쪽, 2:위쪽, 3:왼쪽, 4:아래쪽
			rotation(0, 0, list.get(0).x, list.get(0).y, 4);
			rotation(0, C-1, 0, 0, 3);
			rotation(list.get(0).x, C-1, 0, C-1, 2);
			rotation(list.get(0).x, list.get(0).y, list.get(0).x, C-1, 1);
			
			rotation(R-1, list.get(1).y, list.get(1).x, list.get(1).y, 2);
			rotation(R-1, C-1, R-1, list.get(1).y, 3);
			rotation(list.get(1).x, C-1, R-1, C-1, 4);
			rotation(list.get(1).x, list.get(1).y, list.get(1).x, C-1, 1);
		}
		int result = 0;   //미세먼지 양 구하기
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(arr[i][j] == -1) continue;
				result+=arr[i][j];
			}
		}
		
		System.out.println(result);
	}
	
	static void rotation(int x1, int y1, int x2, int y2, int dir) {
		switch(dir) {
		case 1:  //오른쪽
			for(int i=y2; i>y1; i--) {
				if(arr[x1][i-1]==-1) {
					arr[x1][i] = 0;
				}
				
				else {
					arr[x1][i] = arr[x1][i-1];
				}
			}
			break;
		case 2:  //위쪽
			int tempNum;
			if(arr[x2][y2]==-1) {
				tempNum = 0;
			}
			else {
				tempNum = arr[x2][y2];
			}
			
			for(int i=x2; i<x1; i++) {
				if(arr[i][y2]==-1) continue;
				arr[i][y2] = arr[i+1][y2];
			}
			
			arr[x1][y1] = tempNum;
			break;
		case 3:  //왼쪽
			tempNum = arr[x2][y2];
			
			for(int i=y2; i<y1; i++) {
				arr[x1][i] = arr[x1][i+1];
			}
			
			arr[x1][y1] = tempNum;
			break;
		case 4:  //아래쪽
			for(int i=x2; i>x1; i--) {
				if(arr[i][y1] == -1) continue;
				arr[i][y1] = arr[i-1][y1];
			}
			break;
		}
	}
	
	static class position{
		int x; 
		int y;
		public position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
