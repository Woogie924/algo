import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14889 {
	static int N;
	static int team[][];
	static boolean visit[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		team = new int[N][N];
		visit = new boolean[N];
		for(int i =0 ; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0 ; j<N; j++) {
				team[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(0,0);
		System.out.println(ans);
	}
	static int ans=Integer.MAX_VALUE;
	private static void DFS(int select, int depth) {
		if(select == N/2) {
			int teamA=0;
			int teamB=0;
			//System.out.println(Arrays.toString(visit));
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(visit[i]&&visit[j]) {
						teamA+=team[i][j];
					}else if(!visit[i]&&!visit[j]) {
						teamB+=team[i][j];
					}
				}
			}
			ans=Math.min(ans, Math.abs(teamA-teamB));
			
		}
		if(depth>=N) return;
		visit[depth]=true;
		DFS(select+1,depth+1);
		visit[depth]=false;
		DFS(select,depth+1);
	}

}
