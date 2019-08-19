import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14499_현병록_O {
	static int n, m, x, y, k, command;
	static int map[][], ny, nx;
	static BufferedWriter bw;
	static int[] dy= {0, 0, 0, -1, 1}, dx= {0, 1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());  m = Integer.parseInt(st.nextToken());
		y =  Integer.parseInt(st.nextToken()); x = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		Dice dice = new Dice();
		st = new StringTokenizer(br.readLine());
		for(int i =0; i<k; i++) {
			//명령어 입력 부분 여기서 작업이 돌아간다.
			dice.move(Integer.parseInt(st.nextToken()));
		}
		bw.flush();
	}
	static class Dice {
		int top, bottom, north, south, east, west, temp;
		
		public Dice(){
			top=bottom=north=south=east=west=0;
		}

		public void move(int direc) throws IOException {
			// 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4로 주어진다.
			ny=y+dy[direc]; nx=x+dx[direc];
			if(ny<0 || ny>=n || nx<0 || nx>=m) return; //범위벗어나면 아무일도 안한다.
			y=ny; x=nx;
			switch (direc) {
			case 1:
				temp = east;
				east = top;
				top = west;
				west = bottom;
				bottom = temp;
				break;
			case 2:
				temp = west;
				west = top;
				top = east;
				east = bottom;
				bottom = temp;
				break;

			case 3:
				temp = south;
				south = bottom;
				bottom = north;
				north = top;
				top = temp;
				break;

			case 4:
				temp = bottom;
				bottom = south;
				south = top;
				top = north;
				north = temp;
				break;

			default:
				break;
			}
			if(map[ny][nx]==0) {//이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다
				map[ny][nx]=bottom;
			}else {//0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
				bottom = map[ny][nx];
				map[ny][nx]=0;
			}
			bw.write(top+"\n");
		}
	}
	
}
