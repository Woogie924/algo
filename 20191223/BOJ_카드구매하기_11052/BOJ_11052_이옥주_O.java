import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11052_�̿���_O {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());  //ī�� ���� �ޱ�
		String[] str = br.readLine().split(" ");
		int[] card = new int[size+1];  
		int[] arr = new int[size+1];
		
		for(int i=1; i<=size; i++) {  //ī�带 ��µ� ��� ��� �ޱ�
			card[i] = Integer.parseInt(str[i-1]);
			arr[i] = Integer.MIN_VALUE;
		}
		
		for(int i=1; i<=size; i++) {  // �ִ� ��� ���ϱ�
			for(int j=i; j>=1; j--) { 
				arr[i] = Math.max(arr[i-j]+card[j], arr[i]);
			}
		}
		System.out.println(arr[size]);		
	}
}