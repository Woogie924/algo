import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2117_이옥주_홈방범서비스 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int caseNum = Integer.parseInt(br.readLine());
		String[] str;
		int K, house = 0;
		
		for(int i=0; i<caseNum; i++) {
			str = br.readLine().split(" ");
			int size = Integer.parseInt(str[0]);
			int cost = Integer.parseInt(str[1]);
			
			for(int j=1; ; j++) {
				if((j*j+(j-1)*(j-1)) > cost*cost) {
					K = j-1;
					break;
				}	
			}
			
			for(int j=1; j<=K; j++) {
				if(j==1) {
					house = 1;
				}
				else {
					
				}
			}
		}

	}

}
