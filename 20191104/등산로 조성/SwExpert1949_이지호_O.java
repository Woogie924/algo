package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * SWEA 1949 모의 테스트 등산로 조성
 */
public class SwExpert1949 {
	static class posit{
		int x; 
		int y;
		public posit(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N , K;
	static int map[][];
	static boolean visit[][];
	static ArrayList<posit> maxList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxList = new ArrayList<>();
			ans = 0;
			int max = 0;
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(max<map[i][j]) {
						max= map[i][j];
						maxList.clear();
						maxList.add(new posit(i,j));
					}else if(max==map[i][j]) {
						maxList.add(new posit(i,j));
					}
				}
			}
			for(int i = 0; i<maxList.size(); i++) {
				visit = new boolean[N][N];
				visit[maxList.get(i).x][maxList.get(i).y] = true;
				dfs(maxList.get(i).x,maxList.get(i).y,1,false);
				visit[maxList.get(i).x][maxList.get(i).y] = false;
			}
			System.out.printf("#%d %d\n",tc,ans);
		}
	}
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static int ans;
	private static void dfs(int x, int y, int len, boolean flag) {
		
		ans=Math.max(ans, len);
		if(ans == 9) {
			//System.out.print(x+" "+ y+" "+ans+"\n");
		}
		for(int i = 0; i<4; i++) {
			int tx = x+dx[i];
			int ty = y+dy[i];
			if(tx<0||tx>=N||ty<0||ty>=N||visit[tx][ty]) {
				continue;
			}
			if(map[x][y]>map[tx][ty]) {
				//System.out.print(tx+" "+ty+"\n");
				visit[tx][ty]=true;
				dfs(tx,ty,len+1,flag);
				visit[tx][ty]=false;
			}else {//다음 길이가 같거나 클때
				if(!flag) {
					for(int k=1; k<=K; k++) {
						map[tx][ty]-=k;
						if(map[x][y]>map[tx][ty]) {
							//System.out.print(tx+" "+ty+"\n");
							visit[tx][ty]=true;
							dfs(tx,ty,len+1,true);
							visit[tx][ty]=false;
						}
						map[tx][ty]+=k;
					}
				}
			}
			
		}
	}

}
