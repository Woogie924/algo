import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class SWEA_2117_이옥주_홈방범서비스 {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = Integer.parseInt(br.readLine());
        String[] str;
        int K, house = 0, tempCount = 0;
         
        for(int i=0; i<caseNum; i++) {
            str = br.readLine().split(" ");
            int size = Integer.parseInt(str[0]);
            int cost = Integer.parseInt(str[1]);
            int[][] arr = new int[size][size];
            int count = 0;
            int maxCount = Integer.MIN_VALUE;
             
            //도시 정보 입력 받기
            for(int j=0; j<size; j++) {
                str = br.readLine().split(" ");
                 
                for(int k=0; k<size; k++) {
                    arr[j][k] = Integer.parseInt(str[k]);
                    if(arr[j][k] == 1) {
                        count++;
                    }
                }
            }
             
            for(int j=1; ; j++) {
                if((j*j+(j-1)*(j-1)) > count*cost) {
                    K = j-1;
                    break;
                } 
            }
 
            for(int j=1; j<=K; j++) {
                if(j==1) {
                    maxCount = 1;
                }
                else {
                    for(int k=0; k<size; k++) {
                        for(int p=0; p<size; p++) {  //도시 배열
                            tempCount = 0;
                             
                            for(int q=k-j+1; q<k+j+1; q++) {
                                for(int w=p-j+1; w<p+j+1; w++) {
                                    if(q<0 || w<0 || q>=size || w>=size) continue;
                                    if(arr[q][w]==0) continue;  //빈 곳이면 안봄
                                    if(Math.abs(q-k)+Math.abs(w-p) >= j) continue;
                                    tempCount++;
                                }
                            }
                             
                            if((j*j+(j-1)*(j-1)) <= tempCount * cost) {
                                if(maxCount < tempCount) {
                                    maxCount = tempCount;
                                }
                            } 
                        }
                    }
                }
            }
             
            System.out.printf("#%d %d\n",i+1, maxCount);
        }
    }
}
