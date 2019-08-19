import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14499_이옥주_O {
	static int X,Y;
	static int N,M;
	static int[] dir = {0,0,0,0,0,0};
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);  //세로
		M = Integer.parseInt(str[1]);  //가로
		X = Integer.parseInt(str[2]);  //x좌표
		Y = Integer.parseInt(str[3]);  //y좌표
		int k = Integer.parseInt(str[4]);  //명령의 개수
		arr = new int[N][M];
		int num;
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		str = br.readLine().split(" ");
		for(int i=0; i<k; i++) {
			num = Integer.parseInt(str[i]);
			
			xy(num);  //좌표 이동
		}

	}
	static void xy(int num) {
		switch(num) {
		case 1:
			if(Y+1 <0 || Y+1>=M) return;
			Y++;
			break;
		case 2:
			if(Y-1 <0 || Y-1>=M) return;
			Y--;
			break;
		case 3:
			if(X-1 <0 || X-1>=N) return;
			X--;
			break;
		case 4:
			if(X+1 <0 || X+1>=N) return;
			X++;
			break;
		}
		dice(num);
	}
	static int[] temp = new int[6];
	
	static void dice(int num) {
		switch(num) {
		case 1:
			temp[0] = dir[3];
			temp[1] = dir[1];
			temp[2] = dir[2];
			temp[3] = dir[5];
			temp[4] = dir[0];
			temp[5] = dir[4];
			break;
		case 2:
			temp[0] = dir[4];
			temp[1] = dir[1];
			temp[2] = dir[2];
			temp[3] = dir[0];
			temp[4] = dir[5];
			temp[5] = dir[3];
			break;
		case 3:
			temp[0] = dir[2];
			temp[1] = dir[0];
			temp[2] = dir[5];
			temp[3] = dir[3];
			temp[4] = dir[4];
			temp[5] = dir[1];
			break;
		case 4:
			temp[0] = dir[1];
			temp[1] = dir[5];
			temp[2] = dir[0];
			temp[3] = dir[3];
			temp[4] = dir[4];
			temp[5] = dir[2];
			break;
		}
		
		for(int i=0; i<6; i++) {
			dir[i] = temp[i];
		}
		
		if(arr[X][Y] == 0) {
			arr[X][Y] = dir[5];
		}
		else {
			dir[5] = arr[X][Y];
			arr[X][Y] = 0;
		}
		
		System.out.println(dir[0]);
	}
}
