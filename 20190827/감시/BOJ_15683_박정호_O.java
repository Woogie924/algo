import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class CctvLoc {
	int r;
	int c;

	public CctvLoc(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int[][] map;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int[] dr2 = { -1, 0, 1, 0 };
	static int[] dc2 = { 0, 1, 0, -1 };
	static int N, M;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		map = new int[N][M];

		ArrayList<CctvLoc> cctv = new ArrayList<>();
		ArrayList<CctvLoc> num5 = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
				if (map[i][j] == 5) {
					num5.add(new CctvLoc(i, j));
				} else if (map[i][j] >= 1 && map[i][j] <= 4) {
					cctv.add(new CctvLoc(i, j));
				}
			}
		}

		// 5번 cctv는 모든 방향이므로 일단 감시 되었다고 체크...
		check5(num5);
		go(cctv, 0);
		System.out.println(answer);
	}
	
	

	private static void go(ArrayList<CctvLoc> cctv, int index) {
		if(index==cctv.size()) {
			count();
			return;
		}
		int r = cctv.get(index).r;
		int c = cctv.get(index).c;
		
		
		if (map[r][c] == 1) {
			for(int i=0; i<4; ++i) {
				int[][] temp = deepCopy();
				int tr = r;
				int tc = c;
				while(true) {
					tr += dr[i];
					tc += dc[i];
					if (tr < 0 || tc < 0 || tr >= N || tc >= M || map[tr][tc] == 6)
						break;
					if (map[tr][tc] == 0)
						map[tr][tc] = -1;
				}
				go(cctv, index+1);
				map = temp;
			}
		} else if (map[r][c] == 2) {
			for(int i=0; i<2; ++i) {
				int[][] temp = deepCopy();
				for(int j=i*2; j<=i*2+1; ++j) {
					int tr = r;
					int tc = c;
					while(true) {
						tr += dr[j];
						tc += dc[j];
						if (tr < 0 || tc < 0 || tr >= N || tc >= M || map[tr][tc] == 6)
							break;
						if (map[tr][tc] == 0)
							map[tr][tc] = -1;
					}
				}
				go(cctv, index+1);
				map = temp;
			}
			
		} else if (map[r][c] == 3) {
			for(int i=0; i<4; ++i) {
				int[][] temp = deepCopy();
				for(int j=i; j<=i+1; j++) {
					int tr = r;
					int tc = c;
					while(true) {
						if(j==4) {
							tr += dr2[0];
							tc += dc2[0];
						}
						else {
							tr += dr2[j];
							tc += dc2[j];
						}
						
						if (tr < 0 || tc < 0 || tr >= N || tc >= M || map[tr][tc] == 6)
							break;
						if (map[tr][tc] == 0)
							map[tr][tc] = -1;
					}
				}
				go(cctv, index+1);
				map = temp;
			}
		} else {
			for(int i=0; i<4; ++i) {
				int[][] temp = deepCopy();
				for(int j=0; j<4; ++j) {
					if(i==j)
						continue;
					int tr = r;
					int tc = c;
					while(true) {
						tr += dr[j];
						tc += dc[j];
						if (tr < 0 || tc < 0 || tr >= N || tc >= M || map[tr][tc] == 6)
							break;
						if (map[tr][tc] == 0)
							map[tr][tc] = -1;
					}
				}
				
				go(cctv, index+1);
				map = temp;
			}
		}

	}

	private static int[][] deepCopy() {
		int[][] copy = new int[N][M];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}



	private static void count() {
		int cnt = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]==0)
					cnt++;
			}
		}
		answer = Math.min(answer, cnt);
	}

	private static void check5(ArrayList<CctvLoc> num5) {
		int r, c;
		for (int i = 0; i < num5.size(); ++i) {
			r = num5.get(i).r;
			c = num5.get(i).c;
			int tr, tc;
			for (int j = 0; j < 4; ++j) {
				tr = r;
				tc = c;
				while (true) {
					tr += dr[j];
					tc += dc[j];
					if (tr < 0 || tc < 0 || tr >= N || tc >= M || map[tr][tc] == 6)
						break;
					if (map[tr][tc] == 0)
						map[tr][tc] = -1;
				}
			}

		}

	}

}
