import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11052_이옥주_O {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());  //카드 갯수 받기
		String[] str = br.readLine().split(" ");
		int[] card = new int[size+1];  
		int[] arr = new int[size+1];
		
		for(int i=1; i<=size; i++) {  //카드를 사는데 드는 비용 받기
			card[i] = Integer.parseInt(str[i-1]);
			arr[i] = Integer.MIN_VALUE;
		}
		
		for(int i=1; i<=size; i++) {  // 최대 비용 구하기
			for(int j=i; j>=1; j--) { 
				arr[i] = Math.max(arr[i-j]+card[j], arr[i]);
			}
		}
		System.out.println(arr[size]);		
	}
}