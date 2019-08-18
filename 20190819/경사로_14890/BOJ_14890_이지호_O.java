package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14890 {
	static int MapR[][];
	static int MapC[][];
	static int N;
	static int L;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		MapR=new int[N][N];
		MapC=new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				MapR[i][j]=MapC[j][i]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i<N; i++) {
			check(MapR,i);
			check(MapC,i);
		}
		System.out.println(result);
	}
	static boolean visit[];
	private static void check(int[][] map, int index) {
		visit= new boolean[N];
		for(int i = 0; i<N-1; i++) {
			if(map[index][i]!=map[index][i+1]) {//다음칸과 다를때
				if(map[index][i]-map[index][i+1]==1) {//다음칸과 차이가 1일때(내리막길)
					int temp=map[index][i+1];
					for(int j=i+1; j<i+1+L; j++) {//L길이만큼 경사로
						if(j==N) return; //경사로 범위 벗어날경우
						if(map[index][j]!=temp||visit[j]) {//또 경사가 있으면 못 놓는다.
							return;
						}else {
							visit[j]=true;
						}
					}
					i=i+L-1;
				}else if(map[index][i]-map[index][i+1]==-1) {//오르막길 일때
					int temp=map[index][i];
					for(int j=i; j>i-L; j-- ) {
						if(j<0) return;//경사로 범위 벗어날경우
						if(map[index][j]!=temp||visit[j]) {//또 경사가 있으면 못 놓는다.
							return;
						}else {
							visit[j]=true;
						}
					}
				}else return;// 차이가 2이상 날때
				
			}
		}
		result++;
	}
}
