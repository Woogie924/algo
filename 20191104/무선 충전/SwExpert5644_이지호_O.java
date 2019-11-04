package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * SWEA 5644 모의테스트 무선충전
 */
public class SwExpert5644 {
	static class bc{
		int x;
		int y;
		int c;
		int p;
		public bc(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		
	}
	static int move[][];
	static int M,A;
	static ArrayList<bc> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1 ; tc<= T ; tc++) {
			sum=0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			move = new int[2][M+1];
			for(int i = 0; i<2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j<=M; j++) {
					move[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			for(int i = 0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken())-1;
				int x = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				list.add(new bc(x,y,c,p));
			}
			solve(0,0,9,9);
			System.out.printf("#%d %d\n",tc, sum);
		}
	}
	static int dx[] = {0,-1, 0, 1,0};
	static int dy[] = {0, 0, 1,0,-1};
	static int sum;
	private static void solve(int x1, int y1, int x2, int y2) {
		for(int time = 0; time<=M; time++) {
			int tx1= x1+dx[move[0][time]];
			int ty1= y1+dy[move[0][time]];
			int tx2= x2+dx[move[1][time]];
			int ty2 =y2+dy[move[1][time]];
			
			boolean a_pos[] = new boolean[A];
			boolean b_pos[] = new boolean[A];
			
			for(int i = 0; i<A; i++) {
				a_pos[i] = check(tx1,ty1,i);
				b_pos[i] = check(tx2,ty2,i);
			}
			sum+= Max(a_pos,b_pos);
			x1=tx1;
			y1=ty1;
			x2=tx2;
			y2=ty2;
		}
		
	}
	private static int Max(boolean[] a_pos, boolean[] b_pos) {
		int max = 0;
		for(int i = 0; i<A; i++) {
			for(int j = 0; j<A; j++) {
				int temp = 0;
				if(a_pos[i]) {
					if(b_pos[j]) {
						if(i!=j) {
							temp = list.get(i).p+list.get(j).p;
						}else {
							temp =list.get(j).p;
						}
					}else {
						temp = list.get(i).p;
					}
				}else {
					if(b_pos[j]) {
						temp= list.get(j).p;
					}
				}
				max = Math.max(max,temp);
			}
		}
		return max;
	}
	private static boolean check(int x, int y, int i) {
		int dist = Math.abs(x-list.get(i).x)+Math.abs(y-list.get(i).y);
		if(dist<=list.get(i).c) {
			return true;
		}else return false; 
		
	}
}
