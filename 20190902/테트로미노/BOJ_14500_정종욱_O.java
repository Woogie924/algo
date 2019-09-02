import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노_O {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,max;
	static int arr[][];
	static boolean visit[][];
	static int dx[]= {-1,1,0,0}; // 상하좌우
	static int dy[]= {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // make map;
		
		max = Integer.MIN_VALUE;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				around(i,j,arr[i][j]);
				visit[i][j]=true;
				start(i,j,arr[i][j],0);
				visit[i][j]=false;
			}
		}
		System.out.println(max);
		
	}

	private static void around(int i, int j, int sum) {
		int tx;
		int ty;
		int temp=0;
		int cnt=0;
		for(int a=0;a<4;a++) {
			tx=i+dx[a];
			ty=j+dy[a];
			if(tx>=N||ty>=M||tx<0||ty<0)continue;
			sum+=arr[tx][ty];
			cnt++;
		}
		if(cnt>=3) {
			for(int a=0;a<4;a++) {
				tx=i+dx[a];
				ty=j+dy[a];
					if(tx>=N||ty>=M||tx<0||ty<0) {
					if(sum>max) max=sum;
					continue;
				}
				temp = sum-arr[tx][ty];
				if(temp>max) max=temp;		
			}
		}
	}

	private static void start(int i, int j, int sum, int cnt) {
		if(cnt==3) {
			if(sum>max) max=sum;
			return;
		}
		for(int a=0;a<4;a++) {
			int tx = i+dx[a];
			int ty = j+dy[a];
			if(tx>=N||ty>=M||ty<0||tx<0) continue;
			if(visit[tx][ty]==true) continue;
			visit[tx][ty]=true;
			sum+=arr[tx][ty];
			cnt+=1;
			start(tx,ty,sum,cnt);
			visit[tx][ty]=false;
			sum-=arr[tx][ty];
			cnt-=1;
		}
	}
}
