import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16236_이옥주_O {
	static Queue<position> q = new LinkedList<>();
	static ArrayList<position> list = new ArrayList<>();
	static int N, size, count, sharkX, sharkY;
	static int result = 0;
	static boolean[][] visited;
	static int[][] arr;
	static int sharkCount = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
				if(arr[i][j] == 9) {
					sharkX = i;
					sharkY = j;
				}
			}
		}   //입력 받기
		
		size = 2;
		
		aa:while(true) {
			list.clear();
			visited = new boolean[N][N];
			q.clear();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j] < size && arr[i][j]!=0 && arr[i][j]!=9) {
						list.add(new position(i,j,0));
					}
				}
			}
			// 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
			if(list.size()==0) {
				break;
			}
			//먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
			else if(list.size()==1) {
				q.offer(new position(sharkX, sharkY,0));
				bfs(sharkX, sharkY, 0);
				if(list.get(0).count ==0) {
					break;
				}
			}
			//먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장	가까운 물고기를 먹으러 간다.
			else {
				for(int r=0; r<list.size(); r++) {
					visited = new boolean[N][N];
					q.clear();
					q.offer(new position(sharkX, sharkY, 0));
					bfs(sharkX, sharkY, r);

					if(list.get(r).count==0) {
						r--;
						list.remove(r+1);
					}
				
				}  
				
				if(list.size()==0) {
					break aa;
				}
				Collections.sort(list,new Comparator<position>() {

					@Override
					public int compare(position o1, position o2) {
						if(o1.count == o2.count) {  //거리가 같고, 같은 줄에 있다면
							if(o1.x == o2.x) {
								return o1.y - o2.y;   //왼쪽에 있는 순으로 정렬
							}
							return o1.x - o2.x;  //거리만 같다면 윗줄부터 정렬
						}
						return o1.count-o2.count;  //아니면 거리로 정렬
					}
				});
			}
			
			result+=list.get(0).count;
			arr[sharkX][sharkY] = 0;
			sharkX = list.get(0).x;
			sharkY = list.get(0).y;
			arr[sharkX][sharkY] = 9;
			sharkCount++;
			if(sharkCount == size) {
				size++;
				sharkCount=0;
			}
		}
		System.out.println(result);
	}
	
	static position p = new position(0,0,0);
	static int[] x = {0,0,-1,1};
	static int[] y = {-1,1,0,0};
	static int tempX, tempY, tempCnt;
	
	static void bfs(int sharkX, int sharkY, int idx) {
		visited[sharkX][sharkY] = true;
		
		while(!q.isEmpty()) {
			p = q.poll();
			
			for(int r=0; r<4; r++) {
				tempX = p.x+ x[r];
				tempY = p.y + y[r];
				
				if(tempX<0 || tempX>=N || tempY<0 || tempY>=N || arr[tempX][tempY]>size || visited[tempX][tempY]==true) continue;
				if(tempX==list.get(idx).x && tempY==list.get(idx).y) {
					list.get(idx).count = p.count+1;
					return;
				}
				visited[tempX][tempY] = true;
				q.offer(new position(tempX, tempY, p.count+1));
			}
		}
	}

	static class position{
		int x;
		int y;
		int count;
		
		public position(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
}
