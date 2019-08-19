package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_정종욱_O {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuffer sb = new StringBuffer();
	static int N,M,K,k;
	static int[][] arr;
	static int[] dice = new int[7];
	static int[] mdice = new int[7];
	static int dx[]= {0,0,0,-1,1}; // 0-x , 1-동 , 2-서, 3-북, 4-남
	static int dy[]= {0,1,-1,0,0};
	static int tx,ty;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr=new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}	//make map
		
		st = new StringTokenizer(br.readLine());
		for(int a=0;a<K;a++) {
			k = Integer.parseInt(st.nextToken());
			tx=x+dx[k];		// x=x+dx[k] 로 할 경우 라인 밖으로 나가게되도 x값이 누적되어 예외발생
			ty=y+dy[k];
			if(tx>=N || tx<0 || ty>=M || ty<0) continue;
			go();
			
			if(arr[tx][ty]!=0) {
				dice[6]=arr[tx][ty];
				arr[tx][ty]=0;
			}else arr[tx][ty]=dice[6];
			x = tx;
			y = ty;
			sb.append(dice[1]+"\n");
			
		} 	// 명령어 입력
		System.out.println(sb);
	}
	private static void go() {
		for(int a=0;a<7;a++) {
		mdice[a] = dice[a];
		}
		
		switch(k) {
		case 1 : 
			dice[1]=mdice[4];
			dice[3]=mdice[1];
			dice[6]=mdice[3];
			dice[4]=mdice[6];
			break;
		case 2 :
			dice[1]=mdice[3];
			dice[3]=mdice[6];
			dice[6]=mdice[4];
			dice[4]=mdice[1];
			break;
		case 3 :
			dice[1]=mdice[5];
			dice[5]=mdice[6];
			dice[6]=mdice[2];
			dice[2]=mdice[1];
			break;
		case 4 :
			dice[1]=mdice[2];
			dice[2]=mdice[6];
			dice[6]=mdice[5];
			dice[5]=mdice[1];
			break;
		}
	}

}
