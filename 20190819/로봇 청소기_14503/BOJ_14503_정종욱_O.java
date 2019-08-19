package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_정종욱_O {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,X,Y,d;
	static int arr[][];
	static int cnt,dcnt;
	static int dx[] = {-1,0,1,0};	// 북동남서
	static int dy[] = {0,1,0,-1};   // 북동남서
	static int dir;
	static boolean stop;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}// make map
		
		go(r,c,d);
		System.out.println(cnt);
	}

	private static void go(int r, int c, int d) {
		// ㅅ ㅣ ㅈ ㅏ ㄱ
		while(stop==false) {
		if(arr[r][c]==0)cnt++;
		arr[r][c]=2;
		d=(d+3)%4;
		X=r+dx[d];
		Y=c+dy[d];
		dir=arr[X][Y];
		if(dir==0) {
			dcnt=0;
			go(X,Y,d); // a
		}
		else if(dcnt<4 && (dir==1 || dir==2)) {
			dcnt++;
			go(r,c,d); // b
		}
		else if(dcnt==4 && (dir==1 || dir==2)) {
			if(arr[r+dx[(d+3)%4]][c+dy[(d+3)%4]]!=1) {
				dcnt=0;
				go(r+dx[(d+3)%4],c+dy[(d+3)%4],d+1);
			} else stop=true;
		}
		
	}
	}

}
