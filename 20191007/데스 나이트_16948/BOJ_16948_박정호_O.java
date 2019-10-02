import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class BOJ_데스나이트_16948_박정호_O {
	static boolean[][] visit;
	static int N;
	static int sr, sc, dr, dc;
	static int[] ddr = {-2, -2, 0, 0, 2, 2};
	static int[] ddc = {-1, 1, -2, 2, -1, 1};
	static int level = 1;
	
	static class Pair{
		int r;
		int c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split("\\s");
		sr = Integer.parseInt(s[0]);
		sc = Integer.parseInt(s[1]);
		dr = Integer.parseInt(s[2]);
		dc = Integer.parseInt(s[3]);
		visit = new boolean[N][N];
		
		if(solution())
			System.out.println(level);
		else
			System.out.println(-1);
	}

	private static boolean solution() {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sr, sc));
		visit[sr][sc] = true;
		
		Pair p = null;
		int r, c;
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int i=0; i<qSize; ++i) {
				p = q.poll();
				r = p.r;
				c = p.c;
				int tr, tc;
				for(int j=0; j<6; ++j) {
					tr = r + ddr[j];
					tc = c + ddc[j];
					if(tr<0 || tc<0 || tr>=N || tc>=N || visit[tr][tc])
						continue;
					if(tr==dr && tc==dc) {
						return true;
					}
					visit[tr][tc] = true;
					q.add(new Pair(tr, tc));
				}
			}
			level++;
		}
		return false;
	}
}
