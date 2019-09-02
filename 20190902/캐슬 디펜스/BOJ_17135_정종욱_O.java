import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ_17135_캐슬디펜스_O {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M,D,x,cnt,max;
	static int[][] arr,map;
	static int[][] kill;
	static Queue<int[]> q;
	static int[] temp;
	static int[] dx = {0,-1,0}; //좌 상 우 ( 뒤는필요없음 )
	static int[] dy = {-1,0,1};
	static ArrayList<Integer> list = new ArrayList<>();
	static boolean[] killcheck;
	static boolean[] visited;
	
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		arr = new int[N+1][M];
		map = new int[N+1][M];
		kill = new int[2][3];
		visited = new boolean[M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());		
			}
		} //make map;
		
		
		arch(); // 궁수 배치
		System.out.println(max);
		
		
	}

	private static void arch() {
		if(list.size()==3) {
			cnt=0;
			makeclonemap(); // 맵 복사
			for(int at=0;at<N;at++) {
				killcheck = new boolean[3]; // 킬했는지 안했는지 체크
				for(int a=0;a<3;a++) {
					q = new LinkedList<>(); // 큐 초기화
					defense(a); // 막기 시작
				}
				for(int ki=0;ki<3;ki++) {
					if(killcheck[ki]==true) {
						if(map[kill[0][ki]][kill[1][ki]]==1) {
							map[kill[0][ki]][kill[1][ki]]=0;
							cnt++;
						}
					}
				} // kill 적용
				linemove(); // 적군 전진
			}
			if(cnt>max) max = cnt;
			return;
		}
		for(int a=x;a<M;a++) {
			if(visited[a]==true)continue;
			list.add(a);
			visited[a]=true;
			arch();
			list.remove(list.size()-1);
			visited[a]=false;
			x=a+1;
		}
	}
	private static void makeclonemap() { // 맵 복사
		for(int i=0;i<N+1;i++) {
			for(int j=0;j<M;j++) {
				map[i][j]=arr[i][j];
			}
		}
	}

	private static void linemove() {
		for(int a=1;a<N;a++) {
			for(int j=0;j<M;j++) {
				map[N-a][j] = map[N-a-1][j];
			}
		}
		for(int j=0;j<M;j++) map[0][j]=0;
	}


	// q [0],[1] => 궁수 좌표 , [2] 거리
	private static void defense(int num) {
		q.offer(new int[] {N,list.get(num),1}); //q에 궁수 추가
		int qx,qy;
		loop:while(!q.isEmpty()) {
			temp = q.poll();
			if(temp[2]==D+1)break;
			for(int a=0;a<3;a++) {
				qx = temp[0]+dx[a];
				qy = temp[1]+dy[a];
				if(qx<0||qy<0||qy>=M||qx>=N) continue;
				if(map[qx][qy]==1) {
					kill[0][num]=qx;
					kill[1][num]=qy;
					killcheck[num]=true;
					break loop;
				}else {
					q.offer(new int[] {qx,qy,temp[2]+1});
				}
			}
		}
		
		
	}

}
