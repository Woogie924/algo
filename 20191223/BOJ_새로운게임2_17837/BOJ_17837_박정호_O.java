import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_새로운게임2_17837_박정호_O {
	static int N, K;
	static int[][] map;
	static int answer;
	static ArrayList<Horse>[][] board;
	static ArrayList<Horse> horse = new ArrayList<>();
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};

	static class Horse {
		int num;
		int r;
		int c;
		int dir;

		public Horse(int num, int r, int c, int dir) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		K = Integer.parseInt(s[1]);
		map = new int[N][N];
		board = new ArrayList[N][N];

		// map 입력
		for (int i = 0; i < N; ++i) {
			s = br.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		// board 객체 생성
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				board[i][j] = new ArrayList<>();
			}
		}
		// 말 상태 입력받음 (오, 왼, 위, 아래) 순서
		for (int i = 1; i <= K; ++i) {
			s = br.readLine().split(" ");
			int r = Integer.parseInt(s[0])-1;
			int c = Integer.parseInt(s[1])-1;
			int dir = Integer.parseInt(s[2])-1;
					
			Horse h = new Horse(i, r, c, dir);
			board[r][c].add(h);
			horse.add(h);
		}
		
		Horse h = null;
		here:while (answer <= 1000) {
			answer++;
			for(int k=0; k<K; ++k) {
				int horseNum = k+1;
				h = horse.get(k);
				int r = h.r;
				int c = h.c;
				int dir = h.dir;
				//다음 이동할 칸이 범위를 벗어나거나 벽이라면 방향을 바꿔준다. 
				int tr = r+dr[dir];
				int tc = c+dc[dir];
				if(tr<0 || tc<0 || tr>=N || tc>=N || map[tr][tc]==2) {
					if(dir==0)
						dir = 1;
					else if(dir==1)
						dir = 0;
					else if(dir==2)
						dir = 3;
					else
						dir = 2;
					
					h.dir = dir;
					tr = r+dr[dir];
					tc = c+dc[dir];
				}
				
				//한칸 이동한다. 만약 한칸 이동할 자리가 흰색 칸이라면 본인 위에 있는 말들과 함께 이동한다.
				if(tr<0 || tc<0 || tr>=N || tc>=N || map[tr][tc]==2) {
					tr = r;
					tc = c;
				}
				else if(map[tr][tc]==0) {
					// 본인의 index찾기
					int idx = 0;
					for(int i=0; i<board[r][c].size(); ++i) {
						if(horseNum==board[r][c].get(i).num) {
							idx = i;
							break;
						}
					}
					// 본인부터 본인 위 모든 말들까지 이동시킴
					for(int i=idx; i<board[r][c].size(); ++i) {
						Horse temp = board[r][c].get(i);
						board[tr][tc].add(temp);
						temp.r = tr;
						temp.c = tc;
					}
					// 원래칸 지워줌
					int erase = board[r][c].size()-idx;
					for(int i=0; i<erase; ++i)
						board[r][c].remove(board[r][c].size()-1);
				}
				// 역순으로 한칸 옮김
				else if(map[tr][tc]==1) {
					// 본인의 index찾기
					int idx = 0;
					for(int i=0; i<board[r][c].size(); ++i) {
						if(horseNum==board[r][c].get(i).num) {
							idx = i;
							break;
						}
					}
					// 본인부터 본인 위 모든 말들까지 이동시킴, 역순으로
					for(int i=board[r][c].size()-1; i>=idx; --i) {
						Horse temp = board[r][c].get(i);
						board[tr][tc].add(temp);
						temp.r = tr;
						temp.c = tc;
					}
					// 원래칸 지워줌
					int erase = board[r][c].size()-idx;
					for(int i=0; i<erase; ++i)
						board[r][c].remove(board[r][c].size()-1);
				}
				if(board[tr][tc].size()>=4)
					break here;
			}//for_K
		}//while
		if (answer > 1000)
			answer = -1;
		System.out.println(answer);

	}

}
