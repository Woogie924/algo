import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16918_현병록 {

	static int sero, garo, times, ny, nx;
	static int[][] map;
	static int[] dy= {1,-1,0,0}, dx= {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		String input;
		sero = Integer.parseInt(st.nextToken());
		garo = Integer.parseInt(st.nextToken());
		times = Integer.parseInt(st.nextToken());
		
		map = new int[sero][garo];
		for(int i=0; i<sero; i++) {
			input = br.readLine();
			for(int j=0; j<garo;j++) {
				if(input.charAt(j)=='O')
					map[i][j]=2;
				else
					map[i][j]=-1;
			}
		}
		if(times%2==0) {
			for(int i=0; i<sero; i++) {
				for(int j=0; j<garo; j++) {
					map[i][j]=1;
				}
			}
		}
		else if(times!=1) {
			while(--times!=0) {
				for(int i=0; i<sero; i++) {
					for(int j=0; j<garo; j++) {
						if(map[i][j]==-1)
							map[i][j]=3;
						else
							map[i][j]--;
					}
				}
				
				if(--times==0) break;
				
				for(int i=0; i<sero; i++) {
					for(int j=0; j<garo; j++) {
						if(map[i][j]>1)
							map[i][j]--;
						else if(map[i][j]==1){
							map[i][j]=-1;
							for(int d=0; d<4; d++) {
								ny=i+dy[d];
								nx=j+dx[d];
								if(ny<0||nx<0||ny>=sero||nx>=garo||map[ny][nx]==1) continue;
								map[ny][nx]=-1;
							}
						}
					}
				}
				
			}
		}

		
		for(int i=0; i<sero; i++) {
			for(int j=0; j<garo; j++) {
				if(map[i][j]<0)
					sb.append('.');
				else
					sb.append('O');
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
