import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2933_현병록_O {
	static int garo, sero;
	static char[][] cave;
	static int[] dy= {1,-1,0,0}, dx= {0,0,1,-1};
	static ArrayList<int[]> needMove;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sero  = Integer.parseInt(st.nextToken()); garo = Integer.parseInt(st.nextToken());
		cave = new char[sero][garo];
		for(int i=0; i<sero; i++) {
			cave[i] = br.readLine().toCharArray();
		}
		
		int insNum = Integer.parseInt(br.readLine());
		int index,realindex;
		boolean left = true;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<insNum; i++) {
			index = Integer.parseInt(st.nextToken());
			//블럭을 부순다 / 첫번째는 왼쪽에서 오른쪽 그다음은 번갈아가며
			realindex = sero-index;
			if(left) {//왼쪽을 부실차례
				for(int j=0; j<garo; j++) {
					if(cave[realindex][j]=='x') {
						solve(realindex, j);
						break;
						
					}
				}
				left=false;
			}
			else {
				for(int j=garo-1; j>=0; j--) {
					if(cave[realindex][j]=='x') {
						solve(realindex, j);
						break;
						
					}
				}
				left=true;
			}
		}
		//출력부분
		for(int i=0; i<sero; i++) {
			for(int j=0; j<garo; j++)
				bw.write(cave[i][j]);
			bw.newLine();
		}
		bw.flush();
	}
	private static void solve(int y, int x) {
		int ny, nx;
		ArrayList<int[]> list;
		//일단 부셔주기
		cave[y][x]='.';
		//4방향검사해줘서 눈덩이 있는곳은 클러스터를 bfs로 탐색해준다.탐색하면서 아랫방향을 list에 넣어주면서 아랫방향블럭의 인덱스가 sero-1인곳이 없으면 list를 반환 아니면 null반환
		for(int i=0; i<4; i++) {
			ny = y+dy[i]; nx = x+dx[i];
			if(ny<0||nx<0||ny>=sero||nx>=garo) continue;
			if(cave[ny][nx]=='x') {//x인 부분 발견되면 bfs로 탐색한다.
				needMove = new ArrayList<>();
				list = bfs(ny, nx);
				if(list!=null) {//공중에 떨어져있는 클러스터이다
					//그럼 이때 list를 돌면서 한칸씩 아래를 검사해줘서 막히면 그 수만큼 모든 블럭을 줄여준다.
					int cnt=0;
					here : while(true) {
						cnt++;
						for(int[] xy : list) {
							if(xy[0]+cnt ==sero ||cave[xy[0]+cnt][xy[1]]=='x') {
								break here;
							}
							
						}
						for (int[] np : needMove) {
							cave[np[0]+cnt-1][np[1]] = '.';
						}
						for (int[] np : needMove) {
							cave[np[0] + cnt][np[1]] = 'x';
						}

					}
					//cnt - 움직일 수 있는 칸 수 만큼 움직여준다.
										return;
				}
			}
		}
	}
	
	private static ArrayList<int[]> bfs(int sy, int sx) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[][] visited = new boolean[sero][garo];
		ArrayList<int[]> list = new ArrayList<>(); 
		int y,x,ny,nx;
		q.offer(sy); q.offer(sx);
		visited[sy][sx]=true;
		while(!q.isEmpty()) {
			y=q.poll(); x=q.poll();
			needMove.add(new int[] {y,x});
			if(y==sero-1) return null; //바닥에 붙어있는 경우 아무것도 할 필요가 없다.
			else if(y+1<sero && cave[y+1][x]=='.') {//공중에 떠있는 클러스트의 바닥들을 모을거다
				list.add(new int[] {y,x});
			}
			for(int i=0; i<4; i++) {
				ny = y+dy[i]; nx = x+dx[i];
				
				if(ny<0||nx<0||ny>=sero||nx>=garo) continue;
				if(cave[ny][nx]=='x'&&!visited[ny][nx]) {
					visited[ny][nx]=true;
					q.offer(ny); q.offer(nx);
				}
			}
		}
		return list;
	}
}
