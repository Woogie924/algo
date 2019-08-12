package algo_hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4963_Á¤Á¾¿í_O {
	static boolean[][] visited;
	static int[][] arr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int X,Y=-1;
	static int[] fx = {-1,-1,-1,0,0,1,1,1};
	static int[] fy = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws Exception {
		
		while (true) {
		st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		arr = new int[X][Y];
		visited = new boolean[X][Y];
		if(X==0&&Y==0)break;
		
		for(int i=0; i<X; i++) {
			st = new StringTokenizer(br.readLine());			
			for(int j=0; j<Y; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// make map complete
		int sum = 0;
		for(int i=0; i<X;i++) {
			for(int j=0; j<Y; j++) {
				if(arr[i][j]==1 && visited[i][j]==false) {
					find(i,j);
					sum++;
				}
			}
		}
		System.out.println(sum);
		}// while
		
	} // main
	
	
	private static void find(int i, int j) {
		visited[i][j]=true;
		for(int b=0; b<8;b++) {
			if(i+fx[b]>=0 && i+fx[b]<X && j+fy[b] >=0 && j+fy[b]<Y) {
			if(arr[i+fx[b]][j+fy[b]]==1 && !visited[i+fx[b]][j+fy[b]]) {
				find(i+fx[b],j+fy[b]);
				}
			}
		}
	}


}
