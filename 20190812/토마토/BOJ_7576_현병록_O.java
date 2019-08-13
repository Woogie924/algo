package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576_현병록_O {
	static int n,m, tomato[][];
	static int[] dy = { 1, -1, 0, 0 }, dx = { 0, 0, 1, -1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//m-가로, n-세로
		m = Integer.parseInt(st.nextToken()); n = Integer.parseInt(st.nextToken());
		tomato = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//
		System.out.println(bfs());
	}
	private static int bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		int day=0, size, y, x, ny, nx;
		//첫째날 토마토들을 찾아서 넣어준다.
		for(int i =0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(tomato[i][j]==1) {
					q.offer(i); q.offer(j);
				}
			}
		}
		//날짜들을 늘려주면서 록키록키로깈ㅋㅋㅋ
		
		while(!q.isEmpty()) {
			size = q.size()/2;
			for(int i =0; i<size; i++) {
				y=q.poll(); x=q.poll();
				for(int j=0; j<4; j++) {
					ny = y+dy[j]; nx = x+dx[j];
					if(ny<0||nx<0||ny>=n||nx>=m) continue;
					if(tomato[ny][nx]==0) {
						tomato[ny][nx] = 1;
						q.offer(ny);
						q.offer(nx);
					}
				}
			}
			if(!q.isEmpty())
				day++;
		}
		//다 돌았는데 토마토가 있으면 -1인경우이니 확인
		for(int i =0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(tomato[i][j]==0) {
					return -1;
				}
			}
		}
		return day; 
	}
}
