package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15685_현병록_O {
	static int[] dy = {0,-1,0,1}, dx= {1,0,-1,0}; //방향을 저장할건데 우상좌하 순서대로저장 b/c 시계방향 회전을 적용하기 위해서
	static int y, x, dir, g, lsize;
	static ArrayList<Integer> moved;
	static boolean[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new boolean[101][101];
		int size = Integer.parseInt(br.readLine()), cnt=0;
		//각 명령어에 따른 드래곤커브정보를 저장할 리스트를 작성한 후, 그 리스트를돌면서 맵에 드래곤이 지나간 자리 표시
		for(int i =0; i<size; i++) {
			moved = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			curve(); //드래곤커브의 모든세대의 방향변화를 저장한 리스트를 생성
			draw();//map에 리스트를 돌면서 드래곤이 지나가는 점을 표시하여준다.
			//맵을 돌면서 사각형의 갯수를 세어준다.
			
		}
		for(int ty=0; ty<100; ty++) {//맨윗칸부터 아래에서 한칸 위까지 - 사각형을 만들기 위해서
			for(int tx=1; tx<101; tx++) {//사각형을 만들기위해서 좌측에 여유를 두었다.
				if(check(ty, tx)) cnt++;
			}
		}
		System.out.println(cnt);
	}
	private static boolean check(int ty, int tx) {
		for(int i=0; i<=1; i++) {
			for(int j=-1; j<=0; j++) {
				if(!map[ty+i][tx+j]) return false; 
			}
		}
		return true;
	}
	private static void draw() {
		map[y][x] = true; //시작점 색칠
		for(Integer i : moved) {
			y+=dy[i]; x+=dx[i];
			map[y][x]=true;
		}
	}
	private static void curve() {
		//0세대
		moved.add(dir);
		for(int i =0; i<g; i++) {//세대만큼 반복한다.
			//리스트에 있는거를 방향적용해서 리스트에 다시 저장한다.
			for(int j=moved.size()-1; j>=0; j--) {//지금까지 저장된거부터 위에서 아래로 방향 반시계로 돌려서 다시 넣어주기
				moved.add((moved.get(j)+1)%4);
			}
		}
	}
	
}
