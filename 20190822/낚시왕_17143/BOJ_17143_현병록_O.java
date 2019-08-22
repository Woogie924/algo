import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17143_현병록_O {
	static class Shark{
		//int y,x;
		int speed, dir, size;

		@Override
		public String toString() {
			return "Shark [speed=" + speed + ", dir=" + dir + ", size=" + size + "]";
		}
		
	}
	static int r,c,sNum, fishingKing,rtv;
	static Shark[][] map;
	static int[] dy= {0,-1,1,0,0},dx= {0,0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken());
		sNum=Integer.parseInt(st.nextToken());
		map = new Shark[r+1][c+1];
		int y,x;
		Shark newsh;
		for(int i=0; i<sNum; i++) {
			st = new StringTokenizer(br.readLine());
			newsh = new Shark();
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			newsh.speed = Integer.parseInt(st.nextToken());
			newsh.dir = Integer.parseInt(st.nextToken());
			newsh.size = Integer.parseInt(st.nextToken());
			map[y][x] = newsh;
		}
		
		fishingKing = 0; //1번위치에서 시작
		rtv=0;//결과값 저장할 변수
		while(fishingKing<c) { //fishingKing<c
			//1. 낚시왕이 오른쪽으로 한 칸 이동한다.
			fishingKing ++;
			//2.낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
			for(int i =1; i<=r; i++) {
				if(map[i][fishingKing]!=null) {
					rtv+=map[i][fishingKing].size;
					map[i][fishingKing] = null;
					break;
				}
			}
			//3.상어가 이동한다.
			move();
			//for(Shark[] s : map) System.out.println(Arrays.toString(s));System.out.println(rtv);System.out.println();
		}
		System.out.println(rtv);
	}
	private static void move() {
		//모든 상어가 움직인다. 겹치면 먹히기때문에 다른 배열을 두고 계산한다.
		Shark[][] sizeMap = new Shark[r+1][c+1];//움직인 후의 그 위치에 있는 상어의 사이즈를 저장할
		for(int i=1; i<=r; i++) {
			for(int j=1; j<=c; j++) {
				if(map[i][j]!=null) {
					int[] after = movedIndex(i, j, map[i][j]);
					if(sizeMap[after[0]][after[1]]!=null) {
						if(sizeMap[after[0]][after[1]].size < map[i][j].size) {
							sizeMap[after[0]][after[1]] = map[i][j];
						}
					}else {
						sizeMap[after[0]][after[1]] = map[i][j];
					}
				}
			}
		}
		for(int i =1; i<=r; i++)
			System.arraycopy(sizeMap[i], 0, map[i], 0, c+1);
	}
	private static int[] movedIndex(int y, int x, Shark shark) {
		//dir size spped - shark
		int ny, nx,dir=shark.dir,s = 0;
		if(dy[dir]==0) {//행이동하는중이다
			s = shark.speed%(2*c-2);
		}else if(dx[dir]==0) {
			s = shark.speed%(2*r-2);
		}
		for(int i=0; i<s; i++) {//여기 칸수 만큼만 움직인다.
			ny = y + dy[dir];
			nx = x + dx[dir];
			if(ny<1|| nx<1||ny>r||nx>c) {
				if(dir%2==0) {//짝수면 -1
					dir-=1;
				}else {
					dir+=1;
				}
			}
			y+=dy[dir];
			x+=dx[dir];
		}
		shark.dir = dir;
		return new int[] {y,x};
	}
}
