import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11559 {
	static class Posit {
		int x;
		int y;

		public Posit(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static char map[][];
	static ArrayList<Posit> list;
	static boolean visit[][];
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];

		for (int i = 0; i < 12; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			map[i] = input.toCharArray();
		}
		while (true) {
			boolean flag=false;
			visit = new boolean[12][6];
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (!visit[i][j] && map[i][j] != '.') {
						list = new ArrayList<Posit>();
						dfs(i, j, map[i][j]);
						if(list.size()>=4) {
							flag=true;
							for(int k =0; k<list.size(); k++) {
								Posit temp = list.get(k);
								map[temp.x][temp.y] = '.';
							}
							//print();
							
						}
					}
				}
			}
			if(!flag) {
				break;
			}
			cnt++;
			draw();
		}
		System.out.println(cnt);
	}
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	private static void dfs(int i, int j, char c) {
		for(int dir = 0; dir< 4; dir++) {
			int tx= i+dx[dir];
			int ty= j+dy[dir];
			if(tx<0||tx>=12||ty<0||ty>=6||visit[tx][ty]||map[tx][ty]!=c) {
				continue;
			}
			list.add(new Posit(tx,ty));
			visit[tx][ty]=true;
			dfs(tx,ty,c);
		}	
	}
	private static void draw() {
		for(int i = 10; i>=0; i--) {
			for(int j = 0; j<6; j++) {
				if(map[i][j]=='.') continue;
				
				int temp = i;
				while(true) {
					if(temp==11||map[temp+1][j]!='.') break;
					map[temp+1][j]= map[temp][j];
					map[temp][j]='.';
					temp++;
				}
			}
		}
	}
	public static void print() {
		System.out.println();
		for(int i = 0; i<12; i++) {
			for(int j = 0; j<6; j++) {
				System.out.print(map[i][j]+"");
			}
			System.out.println();
		}
	}
}