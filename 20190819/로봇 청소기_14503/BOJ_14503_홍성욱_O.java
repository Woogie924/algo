import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int M;
	static int r;
	static int c;
	static int d;
	static int curRow;
	static int curColumn;
	static int [][] MAP;
	static boolean [][] visited;
	static int res=0;
	public static void main(String[] args) throws Exception {
		/*
		 * N : 세로 크기
		 * M : 가로 크기
		 * r : 행 좌표
		 * c : 열 좌표
		 * d : 바라보는 방향
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		input=br.readLine().split(" ");
		r = Integer.parseInt(input[0]);
		c = Integer.parseInt(input[1]);
		d = Integer.parseInt(input[2]);

		MAP = new int[N][M];
		visited = new boolean[N][M];

		for(int i=0; i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<input.length ; j++) {
				MAP[i][j] = Integer.parseInt(input[j]);
			}
		}
		curRow = r;
		curColumn = c;
		checkState(curRow, curColumn);
		System.out.println(res);
	}
	static boolean isBack() {
		int tx=curRow, ty=curColumn;
		boolean flag = false;
		if(d==0) {
			tx++;
		}
		else if(d==1) {
			ty--;
		}
		else if(d==2) {
			tx--;
		}
		else if(d==3) {
			ty++;
		}
		if(tx<0|| ty<0|| tx>=N || ty>= M) {
			//이동할 칸이 외곽이면
			flag = false;

		}else if( MAP[tx][ty] == 1){
			flag = false;
		}else {
			flag = true;
		}
		return flag;
	}

	static void checkState(int xx, int yy) {
		// 1, 외곽은 벽
		/* 네방향 다 탐색 ,,,
		 *  청소가 되있거나 벽인 경우,
		 *  		1) 후진 할 수 있음	move(-1) 호출
		 *  		2) 후진 불가 : 종료
		 *  
		 */	
		boolean chk=false;
		int chkCnt=0;		//후진 카운트 : 네방향 탐색중 4가 되면 후진

		while(true) {
			chk= false;
			chkCnt=0;
			if(MAP[curRow][curColumn] == 0 && !visited[curRow][curColumn]) {
				//현재 위치 청소
				visited[curRow][curColumn] = true;
				//System.out.println(curRow + " "+ curColumn + " : 방향 ::"+d +"         // d ::   0-북쪽,   1-동쪽,   2-남쪽,   3-서쪽");
				res++;
			}

			for(int i=0; i<4 ; i++) {
				// chkLeft true: 아직 청소하지 않은 공간 존재
				// chkLeft false: 왼쪽방향에 청소할 공간 없음
				// : 네 방향 다 청소 or 벽

				if(chkCnt ==4) {
					if(isBack()) {		//네방향 다 청소or 벽이고, 후진 가능
						move(-1);		//한칸 후진;
						chkCnt=0;
						i=-1;
						continue;
					}
					else {				//네방향 다 청소or 벽이고, 후진 불가능이면
						return;
					}
				}
				chk= chkLeft(curRow,curColumn);	
				if(chk) {	//왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면
					rotate();
					move(1);
					break;
				}
				else {		//청소할 공간이 없다면 그 방향으로 회전
					rotate();
					chkCnt++;
					i=-1;
				}
			}


		}
	}
	static boolean chkLeft(int xx,int yy) {
		// d ::   0-북쪽,   1-동쪽,   2-남쪽,   3-서쪽
		boolean flag = false; // true면, 현재 방향의 왼쪽방향에 청소하지 않은 공간 존재
		int tx, ty;		// 들여다볼 좌표
		switch(d) {
		case 0:
			tx = xx;
			ty = yy-1;
			if(MAP[tx][ty]==0 && !visited[tx][ty]) {
				flag =true;
			}
			break;
		case 1:
			tx = xx-1;
			ty = yy;
			if(MAP[tx][ty]==0 && !visited[tx][ty]) {
				flag =true;
			}
			break;
		case 2:
			tx = xx;
			ty = yy+1;
			if(MAP[tx][ty]==0 && !visited[tx][ty]) {
				flag =true;
			}
			break;
		case 3:
			tx = xx+1;
			ty = yy;
			if(MAP[tx][ty]==0 && !visited[tx][ty]) {
				flag =true;
			}
			break;


		}
		return flag; // true면, 현재 방향의 왼쪽방향에 청소하지 않은 공간 존재
	}
	// 바라보는 방향(d)를 향해 직진, 후진
	static void move(int moveCnt) {
		//moveCnt :: 전진(1), 후진(-1)
		//바라보는 방향 필요함~
		// d ::   0-북쪽,   1-동쪽,   2-남쪽,   3-서쪽
		int x = curRow;
		int y = curColumn;

		switch(moveCnt) {
		case 1:
			if(d==0) {
				x--;
			}
			else if(d==1) {
				y++;
			}
			else if(d==2) {
				x++;
			}
			else if(d==3) {
				y--;
			}
			if(x<0|| y<0|| x>=N || y>= M) {
				//이동할 칸이 외곽이면
				return;

			}
			curRow = x;
			curColumn = y;

			break;
		case -1:
			if(d==0) {
				x++;
			}
			else if(d==1) {
				y--;
			}
			else if(d==2) {
				x--;
			}
			else if(d==3) {
				y++;
			}
			if(x<0|| y<0|| x>=N || y>= M) {
				//이동할 칸이 외곽이면
				return;
			}
			curRow = x;
			curColumn = y;

			break;
		}
		//		if(moveCnt == 1) {
		//			System.out.print( "전진");
		//		}
		//		else {
		//			System.out.print("후진");
		//		}
	}
	// 바라보는 방향(d)를 회전!
	static void rotate() {
		// d ::   0-북쪽,   1-동쪽,   2-남쪽,   3-서쪽
		// 동쪽-> 북쪽
		// 북쪽-> 서쪽
		// 서쪽-> 남쪽
		// 남쪽-> 동쪽
		if(d == 1) {
			d= 0;
		}
		else if(d==0) {
			d= 3;
		}
		else if(d==3) {
			d= 2;
		}
		else if(d==2) {
			d= 1;
		}
	}

}
