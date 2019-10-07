import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16920_현병록 {
	static class Site{
		int y,x;
		public Site(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static int sero, garo, playerN, playerMove[], count[];
	static Queue<Site> q[];
	static boolean[][] visited;
	static char c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String input;
		
		sero = Integer.parseInt(st.nextToken());
		garo = Integer.parseInt(st.nextToken());
		playerN = Integer.parseInt(st.nextToken());
		
		visited = new boolean[sero][garo];
		playerMove = new int[playerN];
		count = new int[playerN];
		q = new Queue[playerN];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<playerN; i++) {
			playerMove[i] = Integer.parseInt(st.nextToken());
			q[i] = new LinkedList<>();
		}
		
		
		for(int i=0; i<sero; i++) {
			input = br.readLine();
			for(int j=0; j<garo; j++) {
				c=input.charAt(j);
				if(c!='.') {
					if(c!='#')q[c-'1'].add(new Site(i, j));
					visited[i][j]=true;
				}
			}
		}
		//여기까지 입력
		
		bfs();
		//for(char[] c: visited)System.out.println(Arrays.toString(c));
		for(int i : count) bw.write(i + " ");
		bw.flush();
		
	}
	static int[] dy= {1,-1, 0, 0}, dx= {0, 0, 1, -1};
	private static void bfs() {
		Site temp;
		int qsize, ny, nx;
		while(checkQs()) {
			for(int i=0; i<playerN; i++) {
				for(int j=0; j<playerMove[i]; j++) {
					if(q[i].isEmpty()) break;
					//각 플레이어의 이동거리만큼 반복된다
					qsize = q[i].size();
					count[i]+=qsize;
					for(int k=0; k<qsize; k++) {
						temp = q[i].poll();
						for(int d=0; d<4; d++) {
							ny = temp.y + dy[d];
							nx = temp.x + dx[d];
							if(ny<0||nx<0||ny>=sero||nx>=garo||visited[ny][nx]) continue;
							q[i].offer(new Site(ny, nx));
							visited[ny][nx] = true;
						}
					}
				}
			}
		}
	}
	private static boolean checkQs() {
		boolean flag = false;
		for(int i=0; i<playerN; i++)
			if(!q[i].isEmpty())
				flag = true;
		return flag;
	}
}
