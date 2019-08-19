package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890_현병록_O {
	static int size, l, road[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken()); l = Integer.parseInt(st.nextToken());
		road = new int[size][size];
		for(int i =0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				road[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(solve());
	}
	private static int solve() {
		int cnt=0;
		for(int i=0;i<size; i++) {
			if(garo(i)) cnt++;//가로줄 검사
			if(sero(i)) cnt++;//세로줄 검사
		}
		return cnt;
	}
	private static boolean garo(int i) {
		boolean visit[] = new boolean[size];
		for(int j=0; j<size-1; j++) {
			//1.2차이 나면 걸러라
			if(Math.abs(road[i][j]-road[i][j+1])>=2) return false;
			//2.같은경우 계속 진행해라
			else if(road[i][j]==road[i][j+1]) continue;
			//3.1차이가 나는 경우에서 더 작아지는 경우 - l만큼 다리를 놓아보기 (조건/높이가 같고 다리가 안놓여있고) 
			else if(road[i][j]-road[i][j+1]==1) {
				//if(j+1==size-1) return false;
				for(int lc = 1; lc<=l; lc++) {
					if(j+lc>=size) return false;
					if(road[i][j+1]==road[i][j+lc] && !visit[j+lc]) {
						visit[j+lc]=true;
						continue;
					}
					return false;
				}
				j=j+l-1;
			}
			//4.1차이가 나는 경우에서 더 커지는 경우 - 뒤로 l번만큼 다리 놓아보기 
			else if(road[i][j]-road[i][j+1]==-1) {
				for(int lc=0; lc<l; lc++) {
					if(j-lc<0) return false;
					if(road[i][j]==road[i][j-lc] && !visit[j-lc]) {
						visit[j-lc]=true;
						continue;
					}
					return false;
				}
			}
		}
		System.out.println(i+"행 성공");
		return true;
	}
	private static boolean sero(int i) {
		boolean visit[] = new boolean[size];
		for(int j=0; j<size-1; j++) {
			//1.2차이 나면 걸러라
			if(Math.abs(road[j][i]-road[j+1][i])>=2) return false;
			//2.같은경우 계속 진행해라
			else if(road[j][i]==road[j+1][i]) continue;
			//3.1차이가 나는 경우에서 더 작아지는 경우 - l만큼 다리를 놓아보기 (조건/높이가 같고 다리가 안놓여있고) 
			else if(road[j][i]-road[j+1][i]==1) {
				//if(j+1==size-1) return false;
				for(int lc = 1; lc<=l; lc++) {
					if(j+lc>=size) return false;
					if(road[j+1][i]==road[j+lc][i] && !visit[j+lc]) {
						visit[j+lc]=true;
						continue;
					}
					return false;
				}
				j=j+l-1;
			}
			//4.1차이가 나는 경우에서 더 커지는 경우 - 뒤로 l번만큼 다리 놓아보기 
			else if(road[j][i]-road[j+1][i]==-1) {
				for(int lc=0; lc<l; lc++) {
					if(j-lc<0) return false;
					if(road[j][i]==road[j-lc][i] && !visit[j-lc]) {
						visit[j-lc]=true;
						continue;
					}
					return false;
				}
			}
		}
		System.out.println(i+"열 성공");
		return true;
	}
	
}
