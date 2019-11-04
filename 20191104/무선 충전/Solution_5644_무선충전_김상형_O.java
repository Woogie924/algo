import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

	static int M, A;
	
	static ArrayList<Integer> Amove =  new ArrayList<>();
	static ArrayList<Integer> Bmove = new ArrayList<>();
	static ArrayList<BC> list = new ArrayList<>();
	
	static int[] dy = {0,-1,0,1,0};
	static int[] dx = {0,0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc= Integer.parseInt(br.readLine());
		
		for(int t=0; t<tc; ++t) {
			
			String[] s = br.readLine().split("\\s");
			M = Integer.parseInt(s[0]);
			A = Integer.parseInt(s[1]);
			
			Amove.clear();
			Bmove.clear();
			list.clear();
			
			s=br.readLine().split("\\s");
			String[] s1 = br.readLine().split("\\s");
			for(int i=0; i<M; ++i) {
				Amove.add(Integer.parseInt(s[i]));
				Bmove.add(Integer.parseInt(s1[i]));
			}
			
			for(int i=0; i<A; ++i) {
				s=br.readLine().split("\\s");
				int y = Integer.parseInt(s[1]);
				int x = Integer.parseInt(s[0]);				
				int c = Integer.parseInt(s[2]);
				int p = Integer.parseInt(s[3]);
				
				list.add(new BC(y,x,c,p));
				
			}
			
			int answer = move();

			System.out.println("#"+(t+1)+" "+answer);
		}
	}
	

	private static int move() {
		int ay = 1;
		int ax = 1;
		int by = 10;
		int bx = 10;
		
		int sum = max(ay,ax,by,bx);
		
		for(int i=0; i<M; ++i) {
			ay += dy[Amove.get(i)];
			ax += dx[Amove.get(i)];
			by += dy[Bmove.get(i)];
			bx += dx[Bmove.get(i)];
			
			sum+=max(ay,ax,by,bx);
		}
				
		return sum;
	}


	private static int max(int ay, int ax, int by, int bx) {
		int[][] charging = new int[2][A];
		
		for(int i=0; i<A; ++i) {
			charging[0][i] = check(ay, ax, i);
			charging[1][i] = check(by, bx, i);
		}
		
		int res = 0;
		
		for(int i=0; i<A; ++i) {
			for(int j=0; j<A; ++j) {
				if(charging[0][i]==0 && charging[1][j]==0) continue;
				int sum = charging[0][i] + charging[1][j];
				if(i==j && charging[0][i]== charging[1][j])
					sum /= 2;
				if(sum>res) res = sum;
			}
		}
		return res;
	}


	private static int check(int y, int x, int idx) {
		int a = Math.abs(y-list.get(idx).y);
		int b = Math.abs(x-list.get(idx).x);
		int dist =  a+b; 
		
		if(dist <= list.get(idx).c)
			return list.get(idx).p;
		else
			return 0;
	}




	static class BC{
		int y;
		int x;
		int c;
		int p;
	
		public BC(int y, int x, int c, int p) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}
		
		
		
	}
}