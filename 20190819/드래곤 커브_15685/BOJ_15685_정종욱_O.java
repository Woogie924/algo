package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15685_정종욱_O {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] arr;
	static int dx[] = {0,-1,0,1}; //동북서남
	static int dy[] = {1,0,-1,0};
	static int fdx[] = {-1,-1,0,1,1,1,0,-1}; // 12시부터 시계방향
	static int fdy[] = {0,1,1,1,0,-1,-1,-1};
	static ArrayList<Integer> list;
	static int cnt,scnt;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new int[101][101];
		
		for(int a=0;a<N;a++) {
		st = new StringTokenizer(br.readLine());
		int y=Integer.parseInt(st.nextToken()); // 열
		int x=Integer.parseInt(st.nextToken()); // 행
		int d=Integer.parseInt(st.nextToken()); // 방향 direction
		int g=Integer.parseInt(st.nextToken()); // 세대 generation
		
		list = new ArrayList<>();
		list.add(d);		
		for(int z=1;z<=g;z++) {
			cnt = list.size();
			for(int s=1;s<=cnt;s++) { 	// 한번반복
				switch(list.get(cnt-s)) { // a=1, get(0)
				case 0 : list.add(1); break;
				case 1 : list.add(2); break;
				case 2 : list.add(3); break;
				case 3 : list.add(0); break;
				}
			}
		}
		// 3 3 0 1
		cnt=0;
		arr[x][y] = 1;
		go(x,y);
		}
		for(int i=1;i<100;i+=2) {
			for(int j=1;j<100;j+=2) {
				cnt=0;
				if(arr[i][j]==1) {
					for(int a=0;a<=2;a++) { // 우측 상단 스퀘어 확인
						if(arr[i+fdx[a]][j+fdy[a]]==1)cnt++;
					}
					if(cnt==3) {
						cnt=0;
						scnt++;
					}else cnt=0;
				
				
					for(int a=2;a<=4;a++) { // 우측 하단 스퀘어 확인 
						if(arr[i+fdx[a]][j+fdy[a]]==1)cnt++;
					}
					if(cnt==3) {
						cnt=0;
						scnt++;
					}else cnt=0;
					
					
					for(int a=4;a<=6;a++) { // 좌측 하단 스퀘어 확인 
						if(arr[i+fdx[a]][j+fdy[a]]==1)cnt++;
					}
					if(cnt==3) {
						cnt=0;
						scnt++;
					}else cnt=0;
					
					
					for(int a=6;a<=8;a++) { // 좌측 상단 스퀘어 확인 
						if(arr[i+fdx[a%8]][j+fdy[a%8]]==1)cnt++;
					}
					if(cnt==3) {
						cnt=0;
						scnt++;
					}else cnt=0;
					
					
				
				}
			}
		}
		
		System.out.println(scnt);
		
		
	}

	private static void go(int x, int y) {
		if(cnt==list.size())return; // 카운트가 리스트사이즈면 리턴 
		int tx = x+dx[list.get(cnt)];
		int ty = y+dy[list.get(cnt)];
		arr[tx][ty] = 1;	// 이동 후 1저장
		cnt++;	
		go(tx,ty);
		}
	}

