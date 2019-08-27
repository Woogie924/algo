import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_현병록_O {
	static int map[][];
	static int size, temp, time;
	static int[] dy= {-1, 0, 0, 1}, dx= {0, -1, 1, 0};//상좌우하
	static BabyShark bs;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		bs = new BabyShark();
		time=0;
		
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<size; j++) {
				temp =Integer.parseInt(st.nextToken());
				if(temp==9) {bs.y=i; bs.x=j; continue;} 
				map[i][j] = temp;
			}
		}
		while(bfs()) {for(int[] a : map) System.out.println(Arrays.toString(a)); System.out.println();}
		System.out.println(time);
		br.readLine();
	}
	
	private static boolean bfs() {
		Queue<Integer> q = new LinkedList<>();
		ArrayList<int[]> list = new ArrayList<>();
		boolean visited[][] = new boolean[size][size];
		int y, x, ny, nx, qsize, eatTime = 0;
		boolean first = false;
		q.offer(bs.y); q.offer(bs.x);
		visited[bs.y][bs.x] = true;
		
		while(!q.isEmpty()) {
			qsize = q.size();
			eatTime++;
			for(int i =0; i<qsize/2; i++) {
				y = q.poll(); x = q.poll();
				for(int d=0; d<4; d++) {
					ny = y+dy[d]; nx=x+dx[d];
					if(ny<0||nx<0||ny>=size||nx>=size) continue;
					if(!visited[ny][nx]&&map[ny][nx]<=bs.size) {
						q.offer(ny); q.offer(nx);
						visited[ny][nx]=true;
						if(map[ny][nx]!=0 && map[ny][nx] <bs.size) {
							first=true;
							list.add(new int[] {ny, nx});
						}
					}
				}
			}
			if(first) {
				Collections.sort(list, new Comparator<int[]>() {

					@Override
					public int compare(int[] p1, int[] p2) {
						if(p1[0]==p2[0]) {
							return p1[1]-p2[1];
						}
						return p1[0] - p2[0];
					}
				});
				ny = list.get(0)[0]; nx = list.get(0)[1];
				map[bs.y][bs.x]=0;
				map[ny][nx] = 9;
				bs.eat();
				bs.y = ny;
				bs.x = nx;
				time += eatTime;
				return true;
			}
		}
		return false;
	}
	
	static class BabyShark{
		int y, x, size, eatNum;
		public BabyShark(){
			size = 2;
			eatNum=0;
		}
		public void eat() {
			eatNum++;
			if(eatNum==size) {
				eatNum=0;
				size++;
			}
			
		}
	}
}
