import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1952_�̿���_������ {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int caseNum = Integer.parseInt(br.readLine());
		String[] str;
		int[] price = new int[4];
		int[] month = new int[13];
		
		for(int i=0; i<caseNum; i++) {
			int[][] result = new int[2][13];
			str = br.readLine().split(" ");
			for(int j=0; j<4; j++) {  //�̿� ���� �Է� �ޱ�(1��, 1��, 3��, 1��)
				price[j] = Integer.parseInt(str[j]);
			}
			
			str = br.readLine().split(" ");
			for(int j=1; j<=12; j++) {   //�Ѵ� �̿� Ƚ�� �ޱ�
				month[j] = Integer.parseInt(str[j-1]);
			}
			
			for(int j=1; j<=12; j++) {  //1�ϰ� 1�� ��� ���ϱ� , 3�� ��� ���ϱ�
				result[0][j] = Math.min(month[j] * price[0], price[1]);
				result[1][j] = result[1][j-1] + result[0][j];
			}
			
			for(int j=1; j<=12; j++) {
				if(j-3<0) {
					result[1][j] = Math.min(result[1][j], price[2]);
				}
				else {
					result[1][j] = Math.min(price[2]+ result[1][j-3], result[1][j-1]+result[0][j]);
				}
			}
			
			//1�� ���� ���ϱ�
			result[1][12] = Math.min(result[1][12], price[3]);
			System.out.printf("#%d %d\n",i+1,result[1][12]);	
		}
	}
}