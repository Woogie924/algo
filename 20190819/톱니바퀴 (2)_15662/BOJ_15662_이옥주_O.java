import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15662_이옥주_O {
	static int[][] arr;
	static int index;
	static int size;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str;
		size = Integer.parseInt(br.readLine());
		arr = new int[size+1][9];
		
		for(int i=1; i<=size; i++) {
			str = br.readLine().split("");
			
			for(int j=1; j<=8; j++) {
				arr[i][j] = Integer.parseInt(str[j-1]);
			}
		}  //톱니바퀴 상태받기
		
		int num = Integer.parseInt(br.readLine());  //회전 횟수
		int dir;  //톱니바퀴 번호, 방향 (1:시계방향, -1 :  반시계방향)
		
		for(int i=0; i<num; i++) {
			str = br.readLine().split(" ");
			
			index = Integer.parseInt(str[0]);
			dir = Integer.parseInt(str[1]);
			
			sol1(index-1,-dir);
			sol2(index+1,-dir);
			rotation(index,dir);
		}
		
		int sum = 0;
		
		for(int k=1; k<=size; k++) {
			if(arr[k][1] == 1) {
				sum++;
			}
		}
		
		System.out.println(sum);
	}

	static void sol1(int idx,int d) {
		if(idx<=0) {
			return;
		}
		
		if(arr[idx][3] != arr[idx+1][7]) {
			sol1(idx-1,-d);
			rotation(idx,d);
		}
	}
	
	static void sol2(int idx, int d) {
		if(idx > size) {
			return;
		}
		
		if(arr[idx-1][3] != arr[idx][7]) {
			sol2(idx+1,-d);
			rotation(idx,d);
		}
	}

	static void rotation(int index, int dir) {
		
		int temp;
		
		switch(dir) {
		case 1:
			temp = arr[index][8];
			
			for(int i=7; i>=1; i--) {
				arr[index][i+1] = arr[index][i];
			}
			arr[index][1] = temp;
			break;
		case -1:
			temp = arr[index][1];
			
			for(int i=1; i<8; i++) {
				arr[index][i] = arr[index][i+1];
			}
			arr[index][8] = temp;
			break;
		}
	}
}
