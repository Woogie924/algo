import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14503_현병록_O {
	static char[][] map;
	static int sero, garo, rtv=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sero = Integer.parseInt(st.nextToken()); garo = Integer.parseInt(st.nextToken());
		map = new char[sero][garo];
		
		//현재 청소기 위치와 방향 dir- 0-북 1-동 2-남 3-서
		st = new StringTokenizer(br.readLine());
		
		//현재 위치와 방향을 가진 청소기 객체생성
		cleaner cleaner = new cleaner(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		for(int i =0; i<sero; i++){ 
				Arrays.fill(map[i], '1');
		}
		//맵입력 false-벽 true-빈칸
		for(int i=0; i<sero; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<garo; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		cleaner.move();
		System.out.println(rtv);
		
	}
	static class cleaner{
		int[] dy= {-1,0,1,0}, dx= {0,1,0,-1}; //북동남서
		int y,x,dir,cnt=0; //청소기위치
		
		public cleaner(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
		
		public void move() {
			pos1: while (true) {
				// 1.현재 위치를 청소한다.
				if (map[y][x] == '0') {
					rtv++;
					map[y][x] = '2';
				}
				cnt = 0;
				// 2.현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대록 탐색을 진행한다.
				pos2: while (true) {
					// 2-a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
					if (leftCheck()) {
						continue pos1;
					}

					// 2-b.왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
					else {
						// leftRotate();
						if (cnt == 4)
							cnt = 0;// 네 방향 모두 청소가 이미되어있거나 벽인 경우
						else
							continue pos2;
					}
					// 2-c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
					// 2-d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
					y -= dy[dir];
					x -= dx[dir]; // 후진 방향
					if (y < 0 || x < 0 || y >= sero || x >= garo)
						break pos1;
					if (map[y][x] == '1')// 후진할 곳이 벽이라면 동작을 멈추고
						break pos1;
					// 아니면 후진하고 방향유지하고 2번으로 돌아간다.
					else if (map[y][x] == '2')
						continue pos2;
				}
			}

		}
		
		private boolean leftCheck() {
			//왼쪽으로 돈 뒤에 그 앞에꺼를 검사한다. 빈 칸이면 좌표 변경해주고 true반환,  아니면 false 반환
			leftRotate();
			int ny = y+dy[dir], nx = x + dx[dir];
			if(ny<0||nx<0||ny>=sero||nx>=garo) return false;
			if(map[ny][nx]=='0') {
				y+=dy[dir]; x+=dx[dir];
				return true;
			}
			return false;
		}
		
		private void leftRotate() {
			cnt++;
			if(--dir<0) dir=3;
		}
		
	}
}
