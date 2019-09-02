import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트링크_O {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,x,sum1,sum2,min,n;
	static boolean[] team;
	static int[][] arr;
	
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		team = new boolean[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		} // make map;
		n=N/2;
		min = Integer.MAX_VALUE;
		maketeam(0);
		System.out.println(min/2);
	}


	private static void maketeam(int cnt) {
		if (cnt == N/2) {
			sum1=0;
			sum2=0;
			for(int a=0;a<N;a++) {
				for(int b=0;b<N;b++) {
					if(team[a]==team[b]) {
						if(team[a]==true) {
							sum1+=arr[a][b];
							sum1+=arr[b][a];
						} else if(team[a]==false) {
							sum2+=arr[a][b];
							sum2+=arr[b][a];
						}
					}
				}
			}
			if(Math.abs(sum1-sum2)<min) min = Math.abs(sum1-sum2);
			return;
		}
		for (int a = x; a < N; a++) {
			if(team[a]==true)continue;
			team[a]=true;
			cnt+=1;
			maketeam(cnt);
			team[a]=false;
			cnt-=1;
			x=a+1;
		}

		
	}




}
