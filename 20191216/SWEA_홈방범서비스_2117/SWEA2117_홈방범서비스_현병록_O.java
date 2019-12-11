package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA2117_홈방범서비스 {
    static int size, cost, max, qSize, cycle, temp[];
    static boolean map[][], visited[][];
    static final String one = "1";
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int testcase = Integer.parseInt(br.readLine());
         
        for(int tc=1; tc<=testcase; tc++) {//testcase
             
            st = new StringTokenizer(br.readLine());
            size = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
             
            //map 입력 - 0과 1로만 입력이 주어지므로 2차원 boolean배열을 통하여 입력을 받았다.
             
            map = new boolean[size][size];
            for(int i=0; i<size; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<size; j++) {
                    if(st.nextToken().equals(one))
                        map[i][j]=true;
                }
            }//map 입력
             
            max = Integer.MIN_VALUE;
            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    //모든 위치에서 시작 할 수 있다.
                    bfs(i,j);
                }
            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }//testcase
        System.out.println(sb.toString());
    }
    static int[] dy= {-1,0,1,0}, dx= {0,1,0,-1};
    private static void bfs(int sy, int sx) {
        // 시작 지점 부터 계속 영역을 넓혀가며 회사가 손해를 보지않고 서비스 가능한 최대집을 반환해주는 함수
        int ny, nx, cycle = 1, houseNum = 0;
        visited = new boolean[size][size];
        q.offer(new int[] {sy,sx});
        visited[sy][sx] = true;
        if(map[sy][sx]) houseNum++;
         
        checkPossible(cycle, houseNum);
         
        while(!q.isEmpty()) {
            qSize = q.size();
            cycle++;
            for(int i=0; i<qSize; i++) {
                    temp = q.poll();
                    for(int d=0; d<4; d++) {
                    ny = temp[0]+dy[d];
                    nx = temp[1]+dx[d];
                    if(ny<0||nx<0||ny>=size||nx>=size||visited[ny][nx]) continue;
                    q.offer(new int[] {ny,nx});
                    visited[ny][nx] = true;
                    if(map[ny][nx]) houseNum++;
                }
            }
             
            checkPossible(cycle, houseNum);
        }
    }
 
    private static int calcOperationCost(int num) {
        //서비스 영역에 따른 운영비용을 반환해주는 함수
        return 2*num*num -(2*num) +1;
    }
     
    private static void checkPossible(int cycle, int houseNum) {
        //서비스 영역에 포함되어 있는 집들에서 내는 서비스비용의 합이 서비스 운영비용보다 큰 경우 max값을 갱신하여주는 함수
        if(calcOperationCost(cycle)<=houseNum*cost)
            max = Integer.max(max, houseNum);
    }
}
