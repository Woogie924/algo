import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class coin{
		int x;
		int y;
		public coin(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<coin> list;
	static int N, M;
	static char map[][];
	static boolean visit1[][], visit2[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		list = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			for(int j = 0 ; j<M; j++) {
				map[i][j]=input.charAt(j);
				if(map[i][j]=='o') {
					list.add(new coin(i,j));
				}
			}
		}
		DFS(list.get(0).x,list.get(0).y,list.get(1).x,list.get(1).y,1);
		if(min>10) System.out.println(-1);
		else System.out.println(min);
	}
	static int min = Integer.MAX_VALUE;
	static int count;
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	private static void DFS(int x, int y, int x2, int y2,int cnt) {
		if(cnt>10) {
			min=Math.min(min, cnt);
			return;
		}
		for(int i = 0; i<4; i++) {
			int tx1 = x+dx[i];
			int ty1 = y+dy[i];
			int tx2 = x2+dx[i];
			int ty2 = y2+dy[i];
			
			boolean c1 = check(tx1,ty1);
			boolean c2 = check(tx2,ty2);
			
			if(c1&&c2) {
				if(map[tx1][ty1]=='#') {
					tx1=x;
					ty1=y;
				}
				if(map[tx2][ty2]=='#') {
					tx2=x2;
					ty2=y2;
				}
				DFS(tx1,ty1,tx2,ty2,cnt+1);
			}else if(!c1&&c2) {
				min=Math.min(min, cnt);
			}else if (!c2&&c1) {
				min=Math.min(min, cnt);
			}else if (!c1&&!c2) {
				continue;
			}
		}
	}
	private static boolean check(int x, int y) {
		if(x<0||x>=N||y<0||y>=M) {
			return false;
		}else return true;
	}
}
