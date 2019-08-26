import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 처음 아기상어 크기 : 2,
 * 
 */
public class Main {
	static int N=0;
	static int sharkSize =2;
	static int sharkCount = 0;
	static position sharkPosition;
	static int [][] distance ;
	static int [][] MAP;
	static int time =0;
	static boolean exitFlag = false;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];

		MAP = new int[N][N];
		distance = new int [N][N];
		fishList = new ArrayList<position>();
		for(int i=0; i<N ; i++) {
			String [] input = br.readLine().split(" ");
			for(int j=0; j<N ; j++) {
				MAP[i][j] = Integer.parseInt(input[j]);
				if(MAP[i][j] == 9) {//아기상어 위치
					sharkPosition = new position(i,j,0);
				}
			}
		}

		while(true) {
			if(exitFlag == true) {
				System.out.println(time);
				break;
			}
			getDistance(MAP,distance);
			
		}
		

	//	for(int i=0; i<N; i++)
	//	{
	//		for(int j=0; j<N ; j++) {
	//			System.out.print(distance[i][j]+" ");
	//		}
	//		System.out.println();
	//	}


	}
	static void sortedList() {
		fishList.sort(new Comparator<position>() {

			@Override
			public int compare(position o1, position o2) {
				/*
				 * 1. 거리가 가까운 물고기가 많다면, 가장 위쪽 
				 * 2. 그러한 물고기가 여러마리라면 가장 왼쪽
				 * 3. 상어 현재 위치 이동 +  (sharkCount++); 
				 */
				if(o1.phase == o2.phase) {
					if(o1.x ==o2.x) {
						return o1.y- o2.y;
					}
//					System.out.println("1");
					return o1.x-o2.x;
				}
				
				return o1.phase - o2.phase;
			}
		});
		//for(position p : fishList) {
		//	System.out.println(p.x + ", "+p.y+" :: "+p.phase);
		//}
	}
	static void moveShark() {
		/*
		 * 1. 거리가 가까운 물고기가 많다면, 가장 위쪽 
		 * 2. 그러한 물고기가 여러마리라면 가장 왼쪽
		 * 3. 상어 현재 위치 이동 +  (sharkCount++); 
		 */
		position nextShark;
		if(fishList.isEmpty()) {
			exitFlag = true;
			return;
			//System.out.println("소요시간:" +time);
		}
		else {
			nextShark = fishList.get(0);		//1~2 조건 충족하는 물고기 먹기!
		}
		
		
		MAP[sharkPosition.x][sharkPosition.y] = 0;
		sharkPosition = nextShark;
		time += sharkPosition.phase;
		sharkPosition.phase=0;
		sharkCount++;
		
		if(sharkCount==sharkSize) {
			sharkSize++;
			sharkCount=0;
		}
		
		//이동
		MAP[sharkPosition.x][sharkPosition.y] = 9;
		//System.out.println("상어 위치 : "+ sharkPosition.x+", "+sharkPosition.y);
		for(int i=0; i<N; i++) {
			Arrays.fill(distance[i], 0);
			Arrays.fill(visited[i], false);
		}
		fishList.clear();
		
	}
	static int [] dx = {0,0,-1,1};
	static int [] dy = {-1,1,0,0};
	static ArrayList<position> fishList;
	static boolean [][] visited;
	static void getDistance(int [][] MAP, int [][] distance) {
		int dist=0;						//현재 상어 위치를 기준으로 거리를 선정해야함~!
		search(MAP, distance);
		for(int i=0; i<N;i++) {
			for(int j=0; j<N;j++) {
				if(0<MAP[i][j] && MAP[i][j]<sharkSize && distance[i][j] !=0 && distance[i][j]!=-1) {
					fishList.add(new position(i,j,distance[i][j]));
				}
			}
		}
		sortedList();
		moveShark();
	}
	/*
	 * 현재 상어위치 기준 ,,, 물고기 거리값 산출
	 */
	static void search(int [][] MAP, int [][] distance) {
		Queue<position> q = new LinkedList<position>();
		int x =0;			//현재 상어 위치
		int y =0;
		q.offer(sharkPosition);
		//visited[sharkPosition.x][sharkPosition.y] = true;
		//visited[x][y] = true;
		//visited[i][j] = true;
		//q.offer(new position(i,j));
		int cnt=0;
		while(!q.isEmpty()) {
			x = q.peek().x;
			y = q.peek().y;
			cnt = q.peek().phase;
			q.poll();
			visited[x][y] = true;
		
			for(int dir =0; dir<4 ; dir++) {
				int tx = x +dx[dir];
				int ty = y +dy[dir];
				
				if(0>tx || 0>ty || tx>=N || ty>=N) {	//외곽일때	
					continue;
				}
				if(MAP[tx][ty] > sharkSize) {
					distance[tx][ty] = -1;		
					continue;
				}
				if(MAP[tx][ty] == 0) {
					distance[tx][ty] = 0;
					//continue;
				}
				if(MAP[tx][ty] <= sharkSize && !visited[tx][ty] ) {
					
		//			System.out.println(tx+", "+ ty);
					visited[tx][ty] = true;
					distance[tx][ty] = cnt+1;
					q.offer(new position(tx,ty,cnt+1));
				}
				
			}
		}
	}
	/*
	 * 1. 먹을 수 있는 물고기가 많다면, 거리가 가장 가까운 물고기를 먹으러간다.
	 * 		ㅁ거리는 아기 상어가 있는 칸에서 물고기가 있는 칸까지의 최솟값
	 * 		ㅁ가까운 물고기가 많다면,,,
	 * 				가장 위
	 * 			여러마리라면 가장 왼쪽에 있는 물고기
	 * 
	 */
	static class position{
		int x;
		int y;
		int phase;
		position() {}
		position(int x, int y, int phase){
			this.x = x;
			this.y = y;
			this.phase = phase;
		}
	}
}