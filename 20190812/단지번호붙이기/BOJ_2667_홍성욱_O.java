import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static int [][] MAP;
	static boolean [][] visited;
	static int cnt=0;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		MAP = new int [N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N;i++) {
			String [] input = br.readLine().split("");
			for(int j=0; j<N;j++)
			{
				MAP[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N;j++) {
				if(visited[i][j] == false && MAP[i][j] == 1) {		// 방문 안한곳 + 단지 가능지역 첫방문
					visited[i][j] = true;
					cnt++;
					sol(i,j);
				}
			}
		}
		int [] res = new int[cnt];
		for(int i=0; i<N ; i++)
		{
			for(int j=0; j<N ; j++) {
				if(MAP[i][j]!=0) {
					res[MAP[i][j]-1]++;
				}
			//	System.out.print(MAP[i][j] + " ");
			}
			//System.out.println();
		}
		Arrays.sort(res);
		System.out.println(res.length);
		for(int n : res) {
			System.out.println(n);
		}
		
	}
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static void sol(int x, int y) {		//cnt가 몇단지인지 나타냄
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();
		qx.offer(x);
		qy.offer(y);
		
		
		while(!qx.isEmpty() && !qy.isEmpty()) {
			x=qx.poll();
			y=qy.poll();
			MAP[x][y] =cnt;
			
			for(int dir=0; dir <4 ;dir++) {
				int tx= x+dx[dir];
				int ty= y+dy[dir];
				
				if(tx<0 || ty<0 || tx>=N || ty>=N) {	//외곽일때
					continue; 
				}
				if(MAP[tx][ty] == 1 && visited[tx][ty] ==false ) {
					qx.offer(tx);
					qy.offer(ty);
					visited[tx][ty] = true;
				}
			}
		}
	}
	
}
