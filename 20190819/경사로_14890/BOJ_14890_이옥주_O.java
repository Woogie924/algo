import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	static int[][] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int size = Integer.parseInt(str[0]);
		int len = Integer.parseInt(str[1]);
		arr = new int[size][size];
		int result = 0;
		int count;
		int flag = 0; //1: 큰거에서 작은거, 2: 작은거에서 큰거
		boolean isSuccess;
		
		for(int i=0; i<size; i++) {
			str = br.readLine().split(" ");
			
			for(int j=0; j<size; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i=0; i<size; i++) {
			isSuccess = true;
			count = 1;
			flag = 0;
			for(int j=0; j<size-1; j++) {
				if(arr[i][j] == arr[i][j+1]) {
					count++;
				}
				
				else if(arr[i][j] == arr[i][j+1]+1) {  //앞이 1만큼 클때
					if(flag == 1) {
						isSuccess = false;
						break;
					}
					isSuccess = false;
					flag = 1;
					count = 1;
				}
				
				else if(arr[i][j]+1 == arr[i][j+1]){  //뒤가 1만큼 클때
					if(flag == 1) {
						isSuccess = false;
						break;
					}
					if(count < len) {
						isSuccess = false;
						break;
					}
					
					else {
						count = 1;
					}
				}
					
				else {
					isSuccess = false;
					break;
				}
				
				if(flag == 1) {
					if(count == len) {
						count = 0;
						flag = 0;
						isSuccess = true;
					}
				}
			}
			
			if(isSuccess == true) {
				//System.out.println("i:"+i);
				result++;
			}
		}
		
		for(int j=0; j<size; j++) {
			isSuccess = true;
			count = 1;
			flag = 0;
			for(int i=0; i<size-1; i++) {
				if(arr[i][j] == arr[i+1][j]) {
					count++;
				}
				
				else if(arr[i][j] == arr[i+1][j]+1) {  //앞이 1만큼 클때
					if(flag == 1) {
						isSuccess = false;
						break;
					}
					isSuccess = false;
					flag = 1;
					count = 1;
				}
				
				else if(arr[i][j]+1 == arr[i+1][j]){  //뒤가 1만큼 클때
					if(flag == 1) {
						isSuccess = false;
						break;
					}
					
					if(count < len) {
						isSuccess = false;
						break;
					}
					
					else {
						count = 1;
					}
				}
					
				else {
					isSuccess = false;
					break;
				}
				
				if(flag == 1) {
					if(count == len) {
						count = 0;
						flag = 0;
						isSuccess = true;
					}
				}
			}
			
			if(isSuccess == true) {
				//System.out.println("j:"+j);
				result++;
			}
		}
		System.out.println(result);
		
	}
	

}
