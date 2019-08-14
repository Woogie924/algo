import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 주사위 굴리기
 */
public class Main {
	static int N;
	static int M;
	static int y;
	static int x;
	static int K;
	static int [] dice = new int [6];
	static int [][] MAP ;
	public static void main(String[] args) throws Exception {
		/*
		 * N : 지도의 세로 크기
		 * M : 지도의 가로크기 
		 * y,x : 주사위 놓은 곳 좌표
		 * K : 명령의 개수
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		x = Integer.parseInt(input[2]);
		y = Integer.parseInt(input[3]);
		K = Integer.parseInt(input[4]);
		
		//주사위 초기상태
		/*
		 * 주사위
		 * 			idx :   0  1  2  3  4  5
		 * 			면  :  윗 북 동 서 남 아랫
		 */
		//dice[0] = 0; dice[1]=0; dice[2] =3; dice[3] = 4; dice[4] = 5; dice[5] = 6;
		
		
		MAP = new int [N][M];
		String [] inputMap;
//		int startRow =0;
//		int startColumn =0;
		
		//맵 초기화,, 시작 좌표 설정
		for(int i=0; i<N; i++) {
			inputMap =br.readLine().split(" ");
			for(int j=0; j<M;j++) {
				MAP[i][j] = Integer.parseInt(inputMap[j]);
				
				
//				if(MAP[i][j]==0) {
//					startRow = i;
//					startColumn = j;
//				}
			}
		}
		String [] inputCommand = br.readLine().split(" ");
		int [] Command = new int [inputCommand.length];
		// 명령어 셋팅
		for(int i=0; i<Command.length; i++) {
			Command[i] = Integer.parseInt(inputCommand[i]);
		}
		
		int tRow = x;
		int tColumn = y;
		//명령 반복
		for(int i=0; i<Command.length; i++) {
			
			switch(Command[i]){
			case 1:	//동쪽
				tColumn++;
				if(tRow<0 || tColumn<0 || tRow>=N || tColumn>=M) {
					tColumn--;
					//move(Command[i]);
					break;
				}
				if(MAP[tRow][tColumn] ==0) {
					move(Command[i]);
					MAP[tRow][tColumn] = dice[5];
				}
				else {
					move(Command[i]);
					dice[5] = MAP[tRow][tColumn];
					MAP[tRow][tColumn]=0;
				}
				break;
			case 2:	//서쪽
				tColumn--;
				if(tRow<0 || tColumn<0 || tRow>=N || tColumn>=M) {
					tColumn++;
					//move(Command[i]);
					break;
				}
				if(MAP[tRow][tColumn] ==0) {
					move(Command[i]);
					MAP[tRow][tColumn] = dice[5];
				}
				else {
					move(Command[i]);
					dice[5] = MAP[tRow][tColumn];
					MAP[tRow][tColumn]=0;
				}
				break;
			case 3:	//북쪽
				tRow--;
				if(tRow<0 || tColumn<0 || tRow>=N || tColumn>=M) {
					tRow++;
					//move(Command[i]);
					break;
				}
				if(MAP[tRow][tColumn] ==0) {
					move(Command[i]);
					MAP[tRow][tColumn] = dice[5];
				}
				else {
					move(Command[i]);
					dice[5] = MAP[tRow][tColumn];
					MAP[tRow][tColumn]=0;
				}
				break;
			case 4://남쪽
				tRow++;
				if(tRow<0 || tColumn<0 || tRow>=N || tColumn>=M) {
					tRow--;
					//move(Command[i]);
					break;
				}
				if(MAP[tRow][tColumn] ==0) {
					move(Command[i]);
					MAP[tRow][tColumn] = dice[5];
				}
				else {
					move(Command[i]);
					dice[5] = MAP[tRow][tColumn];
					MAP[tRow][tColumn]=0;
				}
				break;
			}
//			System.out.println(move(Command[i]));
		}
		
	}
	
	static void move(int dir) {
		/*
		 * 동쪽은 1
		 * 서쪽은 2
		 * 북쪽은 3
		 * 남쪽은 4
		 */
		int a,b,c,d,e,f;
		int res=0;
		switch(dir) {
		case 1: // 동쪽
			a= dice[3]; b= dice[1]; c= dice[0]; d=dice[5]; e=dice[4]; f= dice[2];
			dice[0] = a; dice[1] = b; dice[2] = c; dice[3] = d; dice[4] = e; dice[5] = f;
			res= dice[0];
			break;
		case 2: // 서쪽
			a = dice[2]; b= dice[1]; c= dice[5]; d= dice[0]; e= dice[4]; f= dice[3];
			dice[0] = a; dice[1] = b; dice[2] = c; dice[3] = d; dice[4] = e; dice[5] = f;
			res= dice[0];
			break;
		case 3: // 북쪽
			a = dice[1]; b=dice[5]; c= dice[2]; d= dice[3]; e= dice[0]; f= dice[4];
			dice[0] = a; dice[1] = b; dice[2] = c; dice[3] = d; dice[4] = e; dice[5] = f;
			res= dice[0];
			break;
		case 4: // 남쪽
			a = dice[4]; b= dice[0]; c= dice[2]; d= dice[3]; e= dice[5]; f= dice[1];
			dice[0] = a; dice[1] = b; dice[2] = c; dice[3] = d; dice[4] = e; dice[5] = f;
			res= dice[0];
			break;
		case 0: //제자리
			res = dice[0];
			break;
		}
		System.out.println(res);
		//return res;
		
	}
}
