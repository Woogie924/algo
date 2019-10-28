import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Main {
	static int N;
	static int M;
	static int start;
	static int dst;
	static int[] dist;
	static int[][] board;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		
		dist = new int[N];
		board = new int[N][N];
		
		for(int i=0; i<N; ++i) {
			dist[i] = INF;
		}
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				board[i][j] = INF;
			}
		}
		for(int i=0; i<M; ++i) {
			String[] s = br.readLine().split("\\s");
			
			int y = Integer.parseInt(s[0])-1;
			int x = Integer.parseInt(s[1])-1;
			int cost = Integer.parseInt(s[2]);
			if(board[y][x]>cost) {
				board[y][x] = cost;
			}
		}
		
		String[] s = br.readLine().split("\\s");
		start = Integer.parseInt(s[0])-1;
		dst = Integer.parseInt(s[1])-1;
		dijkstra();
		System.out.println(dist[dst]);
		
		
	}

		private static void dijkstra() {
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			pq.offer(start);
			board[start][start] = 0;
			dist[start] = 0;
			
			while(!pq.isEmpty()) {
				int curr = pq.poll();
				for(int i=0; i<N; ++i) {
					if(dist[i]>board[curr][i]+dist[curr]) {
						dist[i] = board[curr][i]+dist[curr];
						pq.offer(i);
					}
				}
			}
		}
		
	
}
