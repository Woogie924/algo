package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_2117_홈방범서비스 {
	static int N, M;
	static int[][] home;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 도시의 크기
			M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용
			max = 0; // 서비스를 제공받는 집의 최대 개수

			home = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					home[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}
			System.out.println("#" + t + " " + max);
		}
		
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static void bfs(int x, int y) {
		// 보안회사의 이익 = 서비스 제공받는 집들을 통해 얻는 수익(M * 집의 개수) - 운영 비용
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int profit = 0; // 보안회사의 이익
		int homeCnt = 0; // 서비스를 제공받는 집들의 개수
		int tempK = 1; // 서비스 영역의 크기
		
		q.add(new Point(x, y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int i = 0; i < qSize; i++) {
				Point temp = q.poll();
				if(home[temp.x][temp.y] == 1) homeCnt++;
				profit = (M * homeCnt) - getCost(tempK);
				if(profit >= 0) max = Math.max(max, homeCnt);
				
				for(int j = 0; j < 4; j++) {
					int tx = temp.x + dx[j];
					int ty = temp.y + dy[j];
					
					if(tx < 0 || ty < 0 || tx >= N || ty >= N || visited[tx][ty]) continue;
					visited[tx][ty] = true;
					q.add(new Point(tx, ty));
				}
			}
			tempK++;
		}
	}

	// 운영비용
	static int getCost(int k) {
		return k * k + (k - 1) * (k - 1);
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
