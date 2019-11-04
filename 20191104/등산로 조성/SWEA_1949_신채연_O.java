import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1949_등산로조성_O {
	static int n,K;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(reader.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(reader.readLine());
			n = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[n][n];
			
			int max=1;
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(reader.readLine());
				for(int j=0;j<n;j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
					max=Math.max(max, arr[i][j]);
				}
			}
			
			//max값이면 그 지점을 list에 담기
			ArrayList<Pair> list = new ArrayList<>();
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(arr[i][j]==max) list.add(new Pair(i,j));
				}
			}
			
			int maxHeight=1;
			//모든 곳을, 0~k번 깎으면서 최장 등산로 찾기
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					for(int k=0;k<=K;k++) {
						arr[i][j]-=k;
						
						for(Pair p : list) {
							//maxHeight = Math.max(maxHeight, getMaxHeight(p.x,p.y,1));
							maxHeight = Math.max(maxHeight, getMaxHeight(p.x,p.y));
						}
						
						arr[i][j]+=k;
					}
				}
			}
			writer.write("#"+t+" "+maxHeight+"\n");
		}
		writer.flush();
	}
	
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	//private static int getMaxHeight(int i, int j, int height) {
	private static int getMaxHeight(int i, int j) {
		
		int height=1;
		
		for(int k=0;k<4;k++) {
			int tx = i+dx[k];
			int ty = j+dy[k];
			if(tx<0||ty<0||tx>=n||ty>=n||arr[i][j]<=arr[tx][ty]) continue;
			//getMaxHeight(tx, ty, height+1);
			height = Math.max(height, getMaxHeight(tx, ty)+1);
		}
		
		return height;
	}

	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
