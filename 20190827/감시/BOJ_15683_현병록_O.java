package 기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683_현병록_O {
	static int sero, garo, count, min;
	static char[][] map;
	static int[] dy= {0,1,0,-1}, dx= {1,0,-1,0};
	static ArrayList<Cctv> cctvs;
	static boolean[][] visited;
	static class Cctv{
		int y,x;
		char type;
		public Cctv(int y, int x, char type) {
			super();
			this.y = y;
			this.x = x;
			this.type = type;
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sero = Integer.parseInt(st.nextToken()); garo = Integer.parseInt(st.nextToken());
		map = new char[sero][garo];
		visited = new boolean[sero][garo];
		char c;
		cctvs = new ArrayList<>();
		min = Integer.MAX_VALUE;
		count=0;
		for(int i=0; i<sero; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<garo; j++) {
				c = st.nextToken().charAt(0);
				map[i][j] = c;
				if(c=='0') count++;
				else if(c!='6') {
					cctvs.add(new Cctv(i, j, c));
				}
			}
		}
		for(int i=0; i<cctvs.size(); i++) {
			if(cctvs.get(i).type=='5') {
				for(int d=0; d<4; d++) {
					zzing(cctvs.get(i).y, cctvs.get(i).x, d);
				}
				cctvs.remove(i);
				i--;
			}
		}
		gamsi(0);

		
		System.out.println(min);
	}

	private static void gamsi(int index) {
		if(index==cctvs.size()) {
			if(min>count) {min = count;
			
			}
			return;
		}

		char c = cctvs.get(index).type;
		char[][] temp = new char[sero][garo];
		int tempCount = count;
		for(int i =0 ; i<sero; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, garo);
		}
		switch (c) {
		case '1':
			for(int d =0; d<4; d++) {
				zzing(cctvs.get(index).y, cctvs.get(index).x, d);
				gamsi(index+1);
				for(int i =0 ; i<sero; i++) {
					System.arraycopy(temp[i], 0, map[i], 0, garo);
				}
				count = tempCount;
			}
			break;
		case '2':
			for(int d =0; d<2; d++) {
				zzing(cctvs.get(index).y, cctvs.get(index).x, d);
				zzing(cctvs.get(index).y, cctvs.get(index).x, d+2);
				gamsi(index+1);
				for(int i =0 ; i<sero; i++) {
					System.arraycopy(temp[i], 0, map[i], 0, garo);
				}
				count = tempCount;

			}
			break;
		case '3':
			for(int d =0; d<4; d++) {
				zzing(cctvs.get(index).y, cctvs.get(index).x, d);
				zzing(cctvs.get(index).y, cctvs.get(index).x, (d+1)%4);
				gamsi(index+1);
				for(int i =0 ; i<sero; i++) {
					System.arraycopy(temp[i], 0, map[i], 0, garo);
				}
				count = tempCount;

			}
			break;
		case '4':
			for(int d =0; d<4; d++) {
				zzing(cctvs.get(index).y, cctvs.get(index).x, d);
				zzing(cctvs.get(index).y, cctvs.get(index).x, (d+1)%4);
				zzing(cctvs.get(index).y, cctvs.get(index).x, (d+2)%4);
				gamsi(index+1);
				for(int i =0 ; i<sero; i++) {
					System.arraycopy(temp[i], 0, map[i], 0, garo);
				}
				count = tempCount;

			}
			break;
		default:
			break;
		}
	}


	private static void zzing(int y, int x, int dir) {
		int depth=0;
		int ny, nx;
		while(true) {
			depth++;
			ny= y+dy[dir]*depth; nx = x+dx[dir]*depth;
			if(ny<0||nx<0||ny>=sero||nx>=garo||map[ny][nx]=='6') break;
			if(map[ny][nx]!='0') continue;
			map[ny][nx]='#';
			count--;
		}
	}
	
}
