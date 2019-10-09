import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_봄버맨_16918_박정호_O {
	static int R, C, N;
	static Bomb[][] map;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static Queue<Pair> q;
	static int time = 3;

	static class Bomb {
		int state; // 0이면 빈칸, 1이면 폭탄
		int time;
		public Bomb(int state, int time) {
			this.state = state;
			this.time = time;
		}
	}

	static class Pair {
		int r;
		int c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		N = Integer.parseInt(s[2]);
		map = new Bomb[R][C];
		for(int i=0; i<R; ++i){
			s = br.readLine().split("");
			for(int j=0; j<C; ++j){
				if(s[j].equals("."))
					map[i][j] = new Bomb(0, 0);
				else
					map[i][j] = new Bomb(1, 1);
			}
		}
		if(N==1)		//1초면 바로 출력
			print();
		else{
			putBomb();	//2초 상태
			if(N%2==0)
				print();
			else{
				while(true){
					explosion();	//3초 이후부터 여기서 ㄱㄱ
					if(time==N)
						break;
					putBomb();
					time+=2;
				}
				print();
			}
		}
		
	}

	private static void explosion() {
		// 폭탄들 1초씩 증가시키기
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j].state==1){
					map[i][j].time++;
				}
			}
		}
		// 3초인 폭탄들 큐에 좌표 넣기
		q = new LinkedList<>();
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j].state==1 && map[i][j].time==3){
					q.add(new Pair(i, j));
				}
			}
		}
		// 큐에 넣은 3초인 폭탄들 제거, 상하좌우도 제거
		int r, c;
		Pair p;
		while(!q.isEmpty()){
			p = q.poll();
			r = p.r;
			c = p.c;
			map[r][c].state = 0;
			map[r][c].time = 0;
			int tr, tc;
			for(int i=0; i<4; ++i){
				tr = r+dr[i];
				tc = c+dc[i];
				if(tr<0 || tc<0 || tr>=R || tc>=C)
					continue;
				map[tr][tc].state = 0;
				map[tr][tc].time = 0;
			}
		}
		//
	}

	private static void putBomb() {
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j].state==0){
					map[i][j].state = 1;
				}else{
					map[i][j].time++;
				}
			}
		}
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j].state == 0) {
					sb.append(".");
				} else {
					sb.append("O");
				}
//				sb.append(map[i][j].time);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
