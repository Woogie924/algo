import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1952_이옥주_수영장 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int caseNum = Integer.parseInt(br.readLine());
		String[] str;
		int[] price = new int[4];
		int[] month = new int[13];
		
		for(int i=0; i<caseNum; i++) {
			int[][] result = new int[2][13];
			str = br.readLine().split(" ");
			for(int j=0; j<4; j++) {  //이용 가격 입력 받기(1일, 1달, 3달, 1년)
				price[j] = Integer.parseInt(str[j]);
			}
			
			str = br.readLine().split(" ");
			for(int j=1; j<=12; j++) {   //한달 이용 횟수 받기
				month[j] = Integer.parseInt(str[j-1]);
			}
			
			for(int j=1; j<=12; j++) {  //1일과 1달 비용 비교하기 , 3달 비용 비교하기
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
			
			//1년 비용과 비교하기
			result[1][12] = Math.min(result[1][12], price[3]);
			System.out.printf("#%d %d\n",i+1,result[1][12]);	
		}
	}
}