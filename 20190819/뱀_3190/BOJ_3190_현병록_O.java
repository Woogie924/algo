package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_현병록_O {
	static int[][] map;
	static int size;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//size받아서 map생성
		size = Integer.parseInt(br.readLine());
		map = new int[size+1][size+1];
		//사과갯수입력받아서 사과 위치마다 1로변경
		int appleSize = Integer.parseInt(br.readLine());
		for(int i=0; i<appleSize; i++) {
			st=new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		Snake snake = new Snake();
		int moveSize = Integer.parseInt(br.readLine());
		for(int i=0; i<moveSize; i++) {
			st = new StringTokenizer(br.readLine());
			if(!snake.move(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0))) break;
		}
		//다움직인뒤에도 계속 움직여준다.
		snake.move(Integer.MAX_VALUE, 'D');
		System.out.println(snake.getTime()+1);
	}
	static class Snake{
		Queue<Integer> q;
		int y,x,time,dir;
		int[] dy= {0,1,0,-1},dx= {1,0,-1,0};
		public Snake() {
			y=1;
			x=1;
			time = 0;
			dir=0;
			q = new LinkedList<Integer>();
			q.offer(y); q.offer(x);//첫점을 넣어준다.
			map[y][x]=2;
		}
		private int getTime() {
			return time;
		}
		public boolean move(int times, char d) {
			int ny, nx;
			for(int i =time; i<times; i++) {
				ny = y+dy[dir]; nx= x+dx[dir];//다음 방향
				if(ny<1||nx<1||ny>size||nx>size||map[ny][nx]==2) { return false;}//범위 벗어나거나 뱀 몸통 만났을때
				else if(map[ny][nx]==1) {//사과일때 몸통을 늘린다. 사과를 없애고 꼬리는 움직이지 않는다.
					map[ny][nx]=2;
					q.offer(ny); q.offer(nx);
				}
				else {//범위안의 빈칸 일때 꼬리없애고 몸통 늘린다. 사라진 칸은 0으로 해준다.
					map[q.poll()][q.poll()] = 0;
					q.offer(ny); q.offer(nx);
					map[ny][nx] =2;
				}
				y=ny; x=nx;
				time++;
			}
			//다 움직인 뒤에 방향을 전환해준다.
			rotate(d);
			return true;
		}
		public void rotate(char d) {
			if(d=='D') {//오른쪽 회전
				dir+=1;
			}else if(d=='L') {//왼쪽 회전
				dir-=1;
			}
			if(dir<0) dir+=4;
			else if(dir>3) dir%=4;
		}
	}
}
