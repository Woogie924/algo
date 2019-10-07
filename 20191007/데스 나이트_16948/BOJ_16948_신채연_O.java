import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16948_신채연_O {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	static int[] dx = {-2,-2,0,0,2,2};
	static int[] dy = {-1,1,-2,2,-1,1};
			
	public static void main(String[] args) throws Exception{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(reader.readLine());
		
		st = new StringTokenizer(reader.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();
		boolean[][] discovered = new boolean[n][n];
		discovered[a][b]=true;
		q.add(a);
		q.add(b);
		int level=1;
		boolean flag=false;
loop:	while(!q.isEmpty()) {
			int qSize = q.size()/2;
			for(int i=0; i<qSize; ++i) {
				int x = q.poll();
				int y = q.poll();
				for(int k=0;k<6;k++) {
					int tx = x+dx[k];
					int ty = y+dy[k];
					if(tx<0||ty<0||tx>=n||ty>=n||discovered[tx][ty]) continue;
					if(tx==c && ty==d) {
						flag=true;
						break loop;
					}
					discovered[tx][ty]=true;
					q.add(tx);
					q.add(ty);
				}
			}			
			level++;
		}
		
		if(flag) writer.write(level+"");
		else writer.write("-1");
		writer.flush();
	}
}
