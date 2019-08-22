
import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_17140_이옥주_O {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int x, y, k;
		String[] str = br.readLine().split(" ");
		int[][] arr = new int[101][101];
		int count = 0;
		int rowSize = 3;
		int colSize = 3;
		int[][] cntArr = new int[2][101];
		int max = Integer.MIN_VALUE;

		x = Integer.parseInt(str[0]);
		y = Integer.parseInt(str[1]);
		k = Integer.parseInt(str[2]);

		for (int i = 1; i <= 3; i++) {
			str = br.readLine().split(" ");

			for (int j = 1; j <= 3; j++) {
				arr[i][j] = Integer.parseInt(str[j-1]);
			}
		} // 입력받기

		while (arr[x][y] != k) { // 정해진 곳에 값이 정해진 값과 같은지
			max = Integer.MIN_VALUE;
			if(count>100) {
				count=-1;
				break;
			}
			if (rowSize >= colSize) 
			{
				for (int i = 1; i <= rowSize; i++) 
				{
					cntArr = new int[101][2];
					for (int p = 1; p <= 100; p++) {
						cntArr[p][1] = p; // 인덱스 저장
					}

					for (int j = 1; j <=100; j++) {
						cntArr[arr[i][j]][0]++;
					} // 숫자 갯수 세기
	
					Arrays.sort(cntArr, new Comparator<int[]>() {

						@Override
						public int compare(int[] o1, int[] o2) {
							return o1[0] - o2[0];
						}

					});
						
					int[] temp = new int[101];
					int pointer = 0;
					
					for (int j = 0; j <= 100; j++) {
						if (cntArr[j][0] == 0 || cntArr[j][1] == 0)
							continue;
						temp[pointer++] = cntArr[j][1];// 인덱스
						temp[pointer++] = cntArr[j][0];// 값
					}
						
					//원래배열에 덮어씌우기
					if(pointer<=100) {
						for(int j=1; j<=pointer; j++) {
							arr[i][j] = temp[j-1];
						}
						
						for(int j=pointer+1; j<=100; j++) {
							arr[i][j] = 0;
						}
					}
					
					else {
						for(int j=1; j<=100; j++) {
							arr[i][j] = temp[j-1];
						}
						pointer = 100;
					}
						
					if (pointer > max) {
						max = pointer;
					}
				}
				colSize = max;
			}

			else {
				for (int i = 1; i <= colSize; i++) 
				{
					cntArr = new int[101][2];
					for (int p = 1; p <= 100; p++) {
						cntArr[p][1] = p; // 인덱스 저장
					}

					for (int j = 1; j <= 100; j++) {
						cntArr[arr[j][i]][0]++;
					} // 숫자 갯수 세기

					Arrays.sort(cntArr, new Comparator<int[]>() {

						@Override
						public int compare(int[] o1, int[] o2) {
							return o1[0] - o2[0];
						}

					});
					
					int[] temp = new int[101];
					int pointer=0;

					for (int j = 1; j <= 100; j++) {
						if (cntArr[j][0] == 0 || cntArr[j][1] == 0)
							continue;
						temp[pointer++] = cntArr[j][1]; // 인덱스
						temp[pointer++] = cntArr[j][0]; // 값
					}

					if(pointer<=100) {
						//원래배열에 덮어씌우기
						for(int j=1; j<=pointer; j++) {
							arr[j][i] = temp[j-1];
						}
						
						for(int j=pointer+1; j<=100; j++) {
							arr[j][i] = 0;
						}
					}
					
					else {
						for(int j=1; j<=100; j++) {
							arr[j][i] = temp[j-1];
						}
						pointer = 100;
					}
						
					if (pointer > max) {
						max = pointer;
					}
				}
				rowSize = max;
			}
			count++;
		}
		
		/*for(int i=1; i<=100; i++) {
			for(int j=1; j<=100; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}*/
		if(count>100) count = -1;
		System.out.println(count);

	}
}
