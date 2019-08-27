package samsungexam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16234 {// 인구이동
	static boolean visit[][];
	static int map[][];
	static int N,L,R;
	static boolean result;
	static class posit {
		int x;
		int y;

		public posit(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			result=false;
			for(int i =0; i<N; i++) {
				for(int j =0; j<N; j++) {
					if(!visit[i][j]) {
						BFS(i, j);
						sum=0;
					}
				}
			}
			cnt++;
			visit = new boolean[N][N];
			if(!result) break;
		}
		System.out.println(cnt-1);
	}
	static int cnt;
	static int sum;
	static int dx[]= {0,0,1,-1};
	static int dy[]= {1,-1,0,0};
	static Queue <posit> union;
	private static void BFS(int si, int sj) {
		Queue <posit> q = new LinkedList<>();
		union = new LinkedList<>();
		q.add(new posit(si,sj));
		sum=sum+map[si][sj];
		visit[si][sj]=true;
		while(!q.isEmpty()) {
			posit temp = q.poll();
			for(int i =0; i<4; i++) {
				int tx = temp.x+dx[i];
				int ty = temp.y+dy[i];
				if(tx<0||tx>=N||ty<0||ty>=N||visit[tx][ty])continue;
				int person = Math.abs(map[temp.x][temp.y]-map[tx][ty]);
				if(person>=L&&person<=R) {
					q.add(new posit(tx,ty));
					union.add(new posit(tx,ty));
					sum=sum+map[tx][ty];
					visit[tx][ty]=true;
				}
			}
		}
		if(union.isEmpty()) return;
		union.add(new posit(si,sj));
		int move = sum/union.size();
		while(!union.isEmpty()) {
			posit temp = union.poll();
			map[temp.x][temp.y]=move;
		}
		
		result=true;
	}

}
