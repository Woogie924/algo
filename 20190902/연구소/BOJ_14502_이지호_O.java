import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {
	static class space{
		int x;
		int y;
		public space(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N,M;
	static int[][] map,copy ;
	static boolean[][] visit;
	static Queue<space> q;
	static ArrayList<space> s;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map= new int[N][M];
		copy = new int[N][M];
		q = new LinkedList<>();
		s = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				copy[i][j]=map[i][j];
				if(map[i][j]==2) {
					q.add(new space(i,j));
				}else if(map[i][j]==0) {
					s.add(new space(i,j));
				}
			}
		}
		for(int i = 0; i<s.size(); i++) {
			for(int j = i+1 ; j<s.size(); j++) {
				for(int k = j+1; k<s.size(); k++) {
					Init();
					space tmp1=s.get(i);
					space tmp2=s.get(j);
					space tmp3=s.get(k);
					map[tmp1.x][tmp1.y]=1;
					map[tmp2.x][tmp2.y]=1;
					map[tmp3.x][tmp3.y]=1;
					BFS();
					//print();
					findAns();
				}
			}
		}
		System.out.println(ans);
		

	}
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	static int ans=Integer.MIN_VALUE;
	private static void BFS() {
		while(!q.isEmpty()) {
			space temp = q.poll();
			for(int i =0 ; i<4; i++) {
				int tx = temp.x+dx[i];
				int ty = temp.y+dy[i];
				if(tx>=N||tx<0||ty>=M||ty<0||map[tx][ty]==2||map[tx][ty]==1) continue;
				map[tx][ty]=2;
				//visit[tx][ty]=true;
				q.add(new space(tx,ty));
			}
		}
	}
	static boolean flag=false;
	public static void findAns() {
		int cnt=0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j]==0) {
					cnt++;
				}
			}
		}
		if(cnt>ans) {
			ans=cnt;
		}
	}
	public static void Init() {
		q=new LinkedList<>();
		for(int i = 0; i<N; i++) {
			for (int j = 0; j<M; j++) {
				map[i][j]=copy[i][j];
				if(map[i][j]==2) {
					q.add(new space(i,j));
				}
			}
		}
	}
	public static void print() {
		System.out.println();
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
