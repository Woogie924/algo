import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144_현병록_O {
	static int[][] map, addDust;
	static int aircleaner,r,c,t;
	static int[] dy= {-1,0,1,0},dx= {0,1,0,-1};//상우하좌
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//r행 c열 t시간
		r=Integer.parseInt(st.nextToken()); 
		c =Integer.parseInt(st.nextToken()); 
		t =Integer.parseInt(st.nextToken());
		
		aircleaner=0; // 공기청정 아래칸의 행의 수, 열은 0으로 고정 행-1칸까지 2칸이 총 공기청정기
		map = new int[r][c];
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) aircleaner=i;
			}
		}
		
		for(int i=0; i<t; i++) {//t초동안 일어나는 일들
			//1.미세먼지의 확산
			spread();
			//2.공기청정기 동작
			cleaner();
		}
		//총 먼지 수 계산
		int sum=0;
		for(int i=0; i<r; i++) {
			for(int j=0;j<c; j++) {
				sum+=map[i][j];
			}
		}
		System.out.println(sum+2);
		
	}
	private static void cleaner() {
		int y,x,ny,nx;
		//공기청정기 위쪽에선 y방향으로 -1로 시작해서 범위만날때 마다 오른쪽회전하고 공기청정기 높이가되면 오른쪽회전해서 공기청정기 만날때까지 계속 값을 미루어준다
		int start = aircleaner-1, dir=0;//공기청정기 위쪽 좌표와 위쪽방향으로 시작
		y=start-1; x=0;	
		while(y>=0) {
			ny=y+dy[dir]; nx=x+dx[dir]; 
			if(ny<0||nx<0||ny>=r||nx>=c || ny==aircleaner){
				dir=(dir+1)%4; //벽만나면 방향 돌려서 다시 진행
				continue;
			}
			else if(map[ny][nx]==-1) {//청소기를 만나면 나온바람처리해주고 종료
				map[y][x]=0;
				break;
			}
			else {//아니면 한칸씩 당긴다.
				map[y][x]=map[ny][nx];
				y=ny; x=nx;
			}
		}
		//아래쪽은 반대로해준다
		start = aircleaner; dir=2;//공기청정기 아래쪽 좌표와 아래쪽방향으로 시작
		y=start+1; x=0;
		while(y<r) {
			ny=y+dy[dir]; nx=x+dx[dir]; 
			if(ny<0||nx<0||ny>=r||nx>=c || ny==aircleaner-1){
				dir=(dir+3)%4; //벽만나면 방향 돌려서 다시 진행
				continue;
			}
			else if(map[ny][nx]==-1) {//청소기를 만나면 나온바람처리해주고 종료
				map[y][x]=0;
				break;
			}
			else {//아니면 한칸씩 당긴다.
				map[y][x]=map[ny][nx];
				y=ny; x=nx;
			}
		}
	}
	private static void spread() {
		addDust = new int[r][c];
		int ny,nx;
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				//먼지가 있으면 확산될 양들을 각 위치에 저장해놓자
				if(map[i][j]>0) {
					for(int d=0; d<4; d++) {
						ny=i+dy[d]; nx=j+dx[d];
						if(ny<0||nx<0||ny>=r||nx>=c||map[ny][nx]==-1) continue;
						addDust[i][j]-=map[i][j]/5;
						addDust[ny][nx]+=map[i][j]/5;
					}
				}
			}
		}
		//저장해놓은 번지는 먼지양을 한번에 적용
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				map[i][j] += addDust[i][j];
			}
		}
	}
}