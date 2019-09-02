import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11559_뿌요뿌요_O {
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int arr[][] = new int[12][6];
	static boolean visit[][] = new boolean[12][6];
	static String s;
	static char c;
	static int sum,cnt,result;
	static Queue<int[]> q = new LinkedList<>();
	static Queue<Integer> fq = new LinkedList<>();
	static int[] temp;
	static int[] dx= {-1,1,0,0}; //상하좌우
	static int[] dy= {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		
		
		for(int i=0;i<12;i++) {
			s = br.readLine();
			for(int j=0;j<6;j++) {
				c = s.charAt(j);
				switch(c) {
				case '.' : arr[i][j]=0;
					break;
				case 'R' : arr[i][j]=1;
					break;
				case 'G' : arr[i][j]=2;
					break;
				case 'B' : arr[i][j]=3;
					break;
				case 'P' : arr[i][j]=4;
					break;
				case 'Y' : arr[i][j]=5;
					break;
				}
			}
		} // make map;
		
		
		cnt=-1; // while문으로 들어가기위해 초기화
		while(cnt!=0) {
		pang();		
		}
		System.out.println(result);
	}

	private static void pang() {
		cnt = 0; // 터진 횟수
		for(int i=11;i>=0;i--) {
			for(int j=0;j<6;j++) {
				if(arr[i][j]==1) gobfs(i,j,1);
				else if(arr[i][j]==2) gobfs(i,j,2);
				else if(arr[i][j]==3) gobfs(i,j,3);
				else if(arr[i][j]==4) gobfs(i,j,4);
				else if(arr[i][j]==5) gobfs(i,j,5);
			}
		}
		if(cnt>0) result++; // 터진횟수가 한번이라도 있으면 ++
		fallin(); // 떨어트림
	}

	private static void fallin() {
		visit = new boolean[12][6];
		for(int j=0;j<6;j++) {
			for(int i=11;i>=0;i--) {
				if(arr[i][j]==0) continue;
				fq.offer(arr[i][j]); // q에 0빼고 다 저장
			}
			int size = fq.size();
			for(int i=11;i>11-size;i--) {
				arr[i][j]=fq.poll(); // 앞에서부터 q에 저장된 값 빼오기
			}
			for(int i=11-size;i>=0;i--) { // 나머지 0으로 채우기
				arr[i][j]=0;
			}
		}
	}

	private static void gobfs(int i, int j, int kind) {
		int qx,qy;
		sum = 0;
		q.offer(new int[] {i,j,kind});
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int a=0;a<4;a++) {
				qx = temp[0]+dx[a];
				qy = temp[1]+dy[a];
				if(qx<0||qy<0||qx>=12||qy>=6) continue;
				if(visit[qx][qy]) continue;
				if(arr[qx][qy]==kind) {
					visit[qx][qy]=true; //중복방문방지
					q.offer(new int[] {qx,qy,kind});
					sum+=1; // kind의 개수
				}
			}
		}
		if(sum>=4) delete(i,j,kind); // 4개이상이 모이면 지움
	}

	private static void delete(int i,int j,int kind) {
		int qx,qy;
		cnt++;
		q.offer(new int[] {i,j,kind});
		while(!q.isEmpty()) {
			temp = q.poll();
			arr[temp[0]][temp[1]]=0; // 0으로 만들기
			for(int a=0;a<4;a++) {
				qx = temp[0]+dx[a];
				qy = temp[1]+dy[a];
				if(qx<0||qy<0||qx>=12||qy>=6) continue;
				if(arr[qx][qy]==kind) {
					q.offer(new int[] {qx,qy,kind});
				}
			}
		}
	}

}
