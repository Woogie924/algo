import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P08_Island {

	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	static int n,m;
	static int count;
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));

		while(true) {
			st = new StringTokenizer(reader.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if(n==0 && m==0) break;
			count=0;
			solve();
		}
	}


	public static void solve() throws IOException {
		String[][] arr = new String[m][n];
		boolean[][] discovered = new boolean[m][n];

		for(int i=0;i<m;i++) {
			st = new StringTokenizer(reader.readLine());
			for(int j=0;j<n;j++)
				arr[i][j] = st.nextToken();
		}

		Queue<int[]> q = new LinkedList<int[]>();
		int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
		int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
		int tx,ty;

		//현재지점,검사할지점
		int[] xy;

		//시작점 선정
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				//첫 번째 원소가 바다이면 넣기
				if(arr[i][j].equals("1") && !discovered[i][j]) {
					q.offer(new int[] {i,j}); 
					count++;
				}
				else continue;
				discovered[i][j]=true;
				while(!q.isEmpty()) {
					xy = q.poll();
					//우하좌상 대각선포함
					for(int k=0;k<8;k++) {
						ty = xy[0] + dy[k];
						tx = xy[1] + dx[k];
						//경계 검사
						if(tx<0||tx>=n||ty<0||ty>=m)
							continue;
						//땅이면 큐에 넣기
						if(arr[ty][tx].equals("1") && !discovered[ty][tx]) {
							q.offer(new int[] {ty,tx});
						}
						discovered[ty][tx]=true;
					}
				}
			}
		}
		System.out.println(count);
	}
}

