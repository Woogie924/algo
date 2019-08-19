package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890_정종욱_X {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuffer sb = new StringBuffer();
	static int N,L,cnt,linecnt;
	static int arr[][];
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // make map
		
		for(int i=0; i<N; i++) {
			startI(i);
		} // i번만큼 돌리기
		
		for(int j=0; j<N; j++) {
			startJ(j);
		} // i번만큼 돌리기
		System.out.println(cnt);
	} // main end
	
	
	private static void startJ(int j) { // 열 검사
		linecnt = 0;
		for(int i=0;i<N-1;i++) {
			if(Math.abs(arr[i+1][j]-arr[i][j])>1) {
				//차의 절대값이 1보다 크면
				break;
				
			}else if(arr[i+1][j]-arr[i][j]==1) {
				//차가 1이면 (이동하려는 자리가 더 높은수)
				if(highj(i+1,j)) linecnt++;
				else break;
				
			}else if(arr[i+1][j]-arr[i][j]==-1) {
				//차가 -1이면 (이동하려는 자리가 더 낮은값)
				if(lowj(i,j)) linecnt++;
				else break;
				
			}else if(arr[i+1][j]-arr[i][j]==0) {
				//차가 0이면(같은값)
				linecnt++;
			}
		}
		if(linecnt==N-1) cnt++;
	}
	
	
	
	private static boolean lowj(int i, int j) {
		// 자신의 자리와 L길이만큼 같아야함
		int lcnt1=0; // 길이만큼 수가 같은지 체크
		int wall=0;
		for(int a=0;a<L;a++) {
			if(i+1+a>=N) {
				wall++;
				break; //라인아웃
			}
			if(i+L+1+a<N && arr[i+L+1+a][j]>arr[i+1+a][j])break;
			if(arr[i][j]-1 == arr[i+1+a][j]) lcnt1++;
		}
		if(lcnt1==L || (lcnt1==0 && wall==1)) return true;
		else return false;
	}
	private static boolean highj(int i, int j) {
		int lcnt1=0;
		int lcnt2=0;
		int wall=0;
		for(int a=0;a<L;a++) {

			if(i-1-a<0) {
				wall=1;
			}else {
				if(arr[i-1-a][j]==arr[i][j]-1) {
				lcnt2++;
			}
			}
			if(i+a>=N && i+a-1<0) {
				if(arr[i+a-1][j]==arr[i][j]) lcnt1=0;
				wall=1;
			}else if(arr[i+a][j]==arr[i-1][j]+1 || arr[i+a][j]==arr[i-1][j]) {
				lcnt1++;
			}
			
		}
		if((lcnt1==L || (lcnt1==0 && wall==1))&&(lcnt2==L || (lcnt2==0 && wall==1))) return true;
		else return false;
	}
	
	
	private static void startI(int i) { // 열 검사
		linecnt = 0;
		for(int j=0;j<N-1;j++) {
			if(Math.abs(arr[i][j+1]-arr[i][j])>1) {
				//차의 절대값이 1보다 크면
				break;
				
			}else if(arr[i][j+1]-arr[i][j]==1) {
				//차가 1이면 (이동하려는 자리가 더 높은수)
				if(highi(i,j+1)) linecnt++;
				else break;
				
			}else if(arr[i][j+1]-arr[i][j]==-1) {
				//차가 -1이면 (이동하려는 자리가 더 낮은값)
				if(lowi(i,j)) linecnt++;
				else break;
				
			}else if(arr[i][j+1]-arr[i][j]==0) {
				//차가 0이면(같은값)
				linecnt++;
			}
		}
		if(linecnt==N-1) cnt++;
	}
	private static boolean lowi(int i, int j) {
		// 자신의 자리와 L길이만큼 같아야함
		int wall=0;
		int lcnt1=0; // 경사로 길이
		for(int a=0;a<L;a++) {
			if(j+1+a>=N) {
				wall++;
				break;
			}//라인아웃 wall ++
			
			if(j+L+1+a<N && arr[i][j+L+1+a]>arr[i][j+1+a])break;
			// 검사하는곳에서 L길이만큼 더 뒤에 높은수가 나온다면 
			// 경사로가 겹치기때문에 break , false
			
			if(arr[i][j]-1 == arr[i][j+1+a]) lcnt1++;
			// ex) 기준점 3 -1 == 다음위치 2 이어야함, L길이만큼 반복
		}
		if(lcnt1==L || (lcnt1==0 && wall==1)) return true;
		// lcnt(경사로길이)가 0 일때는 벽이라는 조건이 함께해야함
		// 이것이 아니면 경사로 길이만큼 경사로가 놓여야함
		else return false;
	}
	private static boolean highi(int i, int j) {
		int lcnt1=0; // +방향 경사로
		int lcnt2=0; // -방향 경사로
		int wall=0;  // 벽
		for(int a=0;a<L;a++) { // 경사로 길이만큼 반복

			if(j-1-a<0) {
				wall=1;
			} else {
			if(arr[i][j-1-a]==arr[i][j]-1) {
				lcnt2++;
			}
			}
			if(j+a>=N && j+a-1<0) {
				if(arr[i][j+a-1]==arr[i][j]) lcnt1=0;
				wall=1;
			} else if(arr[i][j+a]==arr[i][j-1]+1 || arr[i][j+a]==arr[i][j-1]) {
				lcnt1++;
			}
			
		}
		if((lcnt1==L || (lcnt1==0 && wall==1))&&(lcnt2==L || (lcnt2==0 && wall==1))) return true;
		else return false;
	}
}
