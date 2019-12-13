import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_홈방범서비스_2117_박정호_O {
	static int answer;
	static int N, M;
	static int[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
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
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; ++test_case) {
			String[] s = br.readLine().trim().split(" ");
			N = Integer.parseInt(s[0]);
			M = Integer.parseInt(s[1]);
			map = new int[N][N];
			for(int i=0; i<N; ++i) {
				s = br.readLine().trim().split(" ");
				for(int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(s[j]);
				}
			}
			
			// 최소 1개의 집이 있고, 최소 비용은 1 이므로 초기 이익은 M-1
			answer = 1;
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					bfs(i, j);
				}
			}
			System.out.println("#"+test_case+" "+answer);
		}
	}


	private static void bfs(int ir, int ic) {
		boolean[][] visit = new boolean[N][N];
		
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(ir, ic));
		
		Pair p = null;
		int r=0, c=0;
		int level = 1;		
		int cnt = map[ir][ic]==1 ? 1 : 0;
		while(!q.isEmpty()) {
			int qSize = q.size();			
			for(int i=0; i<qSize; ++i) {
				p = q.poll();
				r = p.r;
				c = p.c;
				visit[r][c] = true;
				int tr=0, tc=0;
				for(int j=0; j<4; ++j) {
					tr = r+dr[j];
					tc = c+dc[j];
					if(tr<0 || tc<0 || tr>=N || tc>=N || visit[tr][tc])
						continue;
					visit[tr][tc] = true;
					q.add(new Pair(tr, tc));
					if(map[tr][tc]==1)
						cnt++;
				}
			}
			level++;
			int cost = level*level + (level-1)*(level-1);
			int money = cnt*M-cost;
			if(money>=0)
				answer = Math.max(answer, cnt);
		}
		
		
		
	}

}
