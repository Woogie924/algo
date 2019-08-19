package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_정종욱_O {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,K,L,cnt,Lcnt;
	static int[][] arr;
	static int X;
	static String C;
	static int dx[] = {-1,0,1,0}; // 상 우 하 좌
	static int dy[] = {0,1,0,-1};
	static int dir;
	static int temp[];
	static Queue<int[]> q;
	
	public static void main(String[] args) throws Exception {
		q = new LinkedList<int[]>();
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
	
		arr = new int[N][N];	//MAP
		
		for(int a=0;a<K;a++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]=1;
		} // 사과위치 입력
		
		L = Integer.parseInt(br.readLine()); // 명령 갯수
		
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		C = st.nextToken();
		Lcnt = L-1; // 남은 명령 갯수
		
		int x=0;
		int y=0;
		arr[x][y]=-1; //지렁이 위치 -1로 표기
		dir=1; // 방향 우측
		q.offer(new int[] {x,y});
		go(x,y);
		System.out.println(cnt);
	}
	
	
	private static void go(int x, int y) throws IOException {
		int tx = x+dx[dir];
		int ty = y+dy[dir];
		if(cnt==X) {
			if(C.equals("D")) {
				dir=(dir+1)%4; // 오른쪽전환
				tx=x+dx[dir];
				ty=y+dy[dir];
			}else if(C.equals("L")) {
				dir=(dir+3)%4; // 왼쪽 전환
				tx=x+dx[dir];
				ty=y+dy[dir];
			}
				if(Lcnt!=0) {
				st=new StringTokenizer(br.readLine());
				X = Integer.parseInt(st.nextToken());
				C = st.nextToken();
				Lcnt--; // 남은 명령어
				}
			} // 명령어 실행후 명령어를 다시 받음
		if(tx>=N || tx<0 || ty>=N || ty<0 || arr[tx][ty]==-1) { 
			cnt++;
			return; //자기 자신과 부딫히거나 장외로 게임오버
		}
			
			if(arr[tx][ty]==1) { 
				arr[tx][ty]=-1; // 사과가 있으면 앞으로 한칸
				q.offer(new int[] {tx,ty});
				cnt++;
				go(tx,ty);
			}
			else {
				arr[tx][ty]=-1; // 사과가 없으면 앞으로 한칸 후
				q.offer(new int[] {tx,ty});
				temp=q.poll();
				arr[temp[0]][temp[1]]=0; //후미 지움
				cnt++;
				go(tx,ty);
			}
		}	
	

}
