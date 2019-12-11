package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA2117_Ȩ������� {
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
             
            //map �Է� - 0�� 1�θ� �Է��� �־����Ƿ� 2���� boolean�迭�� ���Ͽ� �Է��� �޾Ҵ�.
             
            map = new boolean[size][size];
            for(int i=0; i<size; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<size; j++) {
                    if(st.nextToken().equals(one))
                        map[i][j]=true;
                }
            }//map �Է�
             
            max = Integer.MIN_VALUE;
            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    //��� ��ġ���� ���� �� �� �ִ�.
                    bfs(i,j);
                }
            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }//testcase
        System.out.println(sb.toString());
    }
    static int[] dy= {-1,0,1,0}, dx= {0,1,0,-1};
    private static void bfs(int sy, int sx) {
        // ���� ���� ���� ��� ������ �������� ȸ�簡 ���ظ� �����ʰ� ���� ������ �ִ����� ��ȯ���ִ� �Լ�
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
        //���� ������ ���� ������ ��ȯ���ִ� �Լ�
        return 2*num*num -(2*num) +1;
    }
     
    private static void checkPossible(int cycle, int houseNum) {
        //���� ������ ���ԵǾ� �ִ� ���鿡�� ���� ���񽺺���� ���� ���� ���뺸�� ū ��� max���� �����Ͽ��ִ� �Լ�
        if(calcOperationCost(cycle)<=houseNum*cost)
            max = Integer.max(max, houseNum);
    }
}
