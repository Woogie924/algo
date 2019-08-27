import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_정종욱_X {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int si,sj;
	static int ate,ateLv,cnt,qcnt;
	static int arr[][];
	static boolean visit[][];
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int[] qtemp;
	static int[] tmp;
	static Queue<int[]> q = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visit = new boolean[N][N];
		ateLv=2;
		

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 9) {
					si=i;
					sj=j;
				}
			}
		} // make map
		q.offer(new int[] {si,sj,0});
		checkaround(0);
		System.out.println(cnt);
		
	}

	private static void checkaround(int count) {
		int qx=0;
		int qy=0;
		
		while(!q.isEmpty()) {
			qtemp = q.poll();
			
			for(int a=0;a<4;a++) {
				qx = qtemp[0]+dx[a];
				qy = qtemp[1]+dy[a];
				if(qx>=N||qx<0||qy>=N||qy<0)continue;
				if(arr[qx][qy]>ateLv)continue;
				if(arr[qx][qy]==ateLv || arr[qx][qy]==0) {
					if(visit[qx][qy]==true)continue;
					visit[qx][qy]=true;
					q.offer(new int[] {qx,qy,count+1});
					continue;
				}
				if(arr[qx][qy]<ateLv) {
					visit[qx][qy]=true;
					ate++; LvUp();
					samevalue(qx,qy,qtemp[2]);
					break;
					}
			}
		}
	}
	private static void samevalue(int i,int j, int count) {
		int tx,ty;
		while(true) {
			tmp=q.poll();
			if(q.isEmpty() ||count!=tmp[2]) break;
			
			for(int a=0;a<4;a++) {
			tx = tmp[0]+dx[a];
			ty = tmp[1]+dy[a];
			if(tx>=N||tx<0||ty>=N||ty<0)continue;
			if(arr[tx][ty]<ateLv) {
				if(i>tmp[0]) { i=tmp[0]; j=tmp[1]; }
				if(i==tmp[0]) {
					if(j>tmp[1]) { i=tmp[0]; j=tmp[1]; }
					}
				}
			}
		} //while
		cnt+=count;
		count=0;
		q = new LinkedList<int[]>();// 큐 비우기
		arr[si][sj]=0;
		arr[i][j]=9;
		q.offer(new int[] {i,j,0});
		checkaround(0);
	}

	

	private static  void LvUp() {
		switch(ate) {
		case 2: ateLv=3; break;
		case 5: ateLv=4; break;
		case 9: ateLv=5; break;
		case 14: ateLv=6; break;
		case 20: ateLv=7; break;
		}
	}

}
