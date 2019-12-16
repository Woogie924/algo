import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 /*
  * 메모리 	37,232kb
  * 실행시간 	359ms
  */
public class Se2117_홈방범서비스 {
    static int N, M, cost;              //N:도시크기 M:지불비용 cost:운영비용
    static int result;                  //최종 집들의 수
    static int[][] city;                //도시정보
    static void solve(int k, int i, int j) {
        int top = i-k+1;
        int left = j-k+1;
        int right = j+k-1;
        int hcnt = 0;
        for(int x=0; x<k; x++) {
            for(int y=j-x; y<=j+x; y++) {
                if(top+x < 0 || y < 0 || top+x >= N || y >= N || city[top+x][y] == 0) continue;
                hcnt++;
            }
        }
        for(int x=1; x<k; x++) {
            for(int y=left+x; y<=right-x; y++) {
                if(i+x < 0 || y < 0 || i+x >= N || y >= N || city[i+x][y] == 0) continue;
                hcnt++;
            }
        }
 
        if(hcnt * M >= cost && hcnt > result) result = hcnt;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            city = new int[N][N];
            result = 0;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    city[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            for(int k=1; k<=N+1; k++) {
                cost = k*k + (k-1)*(k-1);
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        solve(k,i,j);
                    }
                }
            }
     
            System.out.println("#"+t+" "+result);
        }
    }
}