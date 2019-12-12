/**
 * @author nakru03
 * 
 * SWEA 2117 홈 방범 서비스
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M; 
	static int[][] board;
	
	static final int[] dy = {0,1,0,-1};
	static final int[] dx = {1,0,-1,0};
	
	static int answer = Integer.MIN_VALUE; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0; t<tc; ++t) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());			
			M = Integer.parseInt(st.nextToken());
			
			board = new int[N][N];
			
			for(int y=0; y<N; ++y) {
				
				st = new StringTokenizer(br.readLine());
				
				for(int x=0; x<N; ++x) {
					
					board[y][x] = Integer.parseInt(st.nextToken());
					
				}
			}
			//입력 end
			
			for(int y=0; y<N; ++y) {
				
				for(int x=0; x<N; ++x) {
					
					checker(y, x);
					
				}
			}
			//모든 점에 대해서 brute force
			
			System.out.println("#"+(t+1)+" "+answer);
			answer = Integer.MIN_VALUE;
			
		}
	}

	static Queue<Pair> q = new LinkedList<>();
	
	private static void checker(int sy, int sx) {
		int house = 0;
		int level = 1;
		int coverCost = 0;
		int profit = 0;
		
		q.clear();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new Pair(sy, sx));
		visited[sy][sx] = true;
		
		if(board[sy][sx]==1) {
			profit = M - 1;
			house++;
			if(profit>=0) answer = Math.max(answer, 1);
		}
		
		while(!q.isEmpty()) {
			int qs = q.size();
			for(int i=0; i<qs; ++i) {
				Pair curr = q.poll();
				
				for(int dir=0; dir<4; ++dir) {
					int ny = curr.y + dy[dir];
					int nx = curr.x + dx[dir];
					
					if(ny < 0 || nx <0 || ny>=N || nx>=N) continue;
					if(visited[ny][nx]) continue;
					
					if(board[ny][nx] == 1) {
						house++;
					}
					q.offer(new Pair(ny,nx));
					visited[ny][nx] = true;
				}
				
			}
			//레벨이 끝나면 계산
			level++;
			coverCost = level * level + (level-1)*(level-1);
			profit = M * house - coverCost;
			if(profit>=0) {
				answer = Math.max(house, answer);
				/*System.out.println("레벨 : "+ level);
				System.out.println("시작좌표 : " + sy + sx);
				System.out.println("집의 개수 : " + house);
				System.out.println("이윤 : " + profit);*/
				
			}
		}
		
		
	}
	
	static class Pair{
		int y;
		int x;
		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}
