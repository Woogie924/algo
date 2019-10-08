import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_다리만들기2_17472_박정호_O {
	static int R, C;
	static int[][] map;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static int[] parent;
	static int idx = 2;
	static int bridgeNum;
	static int answer;
	static ArrayList<Bridge> list = new ArrayList<>();
	
	static class Pair{
		int r;
		int c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
	}
	
	static class Bridge{
		Pair start;
		Pair end;
		int leng;
		public Bridge(Pair start, Pair end, int leng) {
			this.start = start;
			this.end = end;
			this.leng = leng;
		}
		@Override
		public String toString() {
			return "Bridge [start=" + start + ", end=" + end + ", leng=" + leng + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		map = new int[R][C];
		for(int i=0; i<R; ++i) {
			s = br.readLine().split("\\s");
			for(int j=0; j<C; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		// 섬 마다 번호붙이기 2부터 시작
		paint();
//		print();
		// 놓을 수 있는 다리 탐색
		search();
		// 다리 길이로 정렬
		Collections.sort(list, new Comparator<Bridge>() {
			@Override
			public int compare(Bridge o1, Bridge o2) {
				return o1.leng-o2.leng;
			}
		});
//		for(Bridge b : list)
//			System.out.println(b);
		put();
		int cnt = 0;
		for(int i=2; i<=idx; ++i) {
			if(parent[i]==i)
				cnt++;
		}
		if(cnt==1)
			System.out.println(answer);
		else
			System.out.println(-1);
		
	}

	private static void put() {
		makeset();
		int size = list.size();
		for(int i=0; i<size; ++i) {
			int num1 = map[list.get(i).start.r][list.get(i).start.c];
			int num2 = map[list.get(i).end.r][list.get(i).end.c];
			union(num1, num2, list.get(i).leng);
//			System.out.println(Arrays.toString(parent));
			if(bridgeNum==idx-2)
				break;
			
		}
	}

	private static void union(int num1, int num2, int leng) {
		int fn1 = find(num1);
		int fn2 = find(num2);
		if(fn1==fn2)
			return;
		
		parent[fn2] = fn1;
		bridgeNum++;
		answer += leng;
//		System.out.println(num1+" "+num2+" 연결, bridgeNum : "+bridgeNum);
	}

	private static int find(int num) {
		if(parent[num]==num)
			return num;
		return parent[num] = find(parent[num]);
	}

	private static void makeset() {
		for(int i=2; i<=idx; ++i) {
			parent[i] = i;
		}
	}

	private static void search() {
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				//섬 아니면 pass~
				if(map[i][j]==0)	
					continue;
				
				// 다른 섬 탐색
				int r, c;
				for(int k=0; k<4; ++k) {
					r = i+dr[k];
					c = j+dc[k];
					// 해변과 인접한 것만 체크
					if(r<0 || c<0 || r>=R || c>=C || map[r][c]!=0)
						continue;
					go(i, j, k);
				}
			}
		}
	}

	private static void go(int r, int c, int dir) {
		int sr = r, sc = c;
		int cnt = 0;
		while(true) {
			r += dr[dir];
			c += dc[dir];
			if(r<0 || c<0 || r>=R || c>=C)
				return;
			if(map[r][c]==0) {
				cnt++;
				continue;
			}
			else {
				if(cnt==1)
					return;
				list.add(new Bridge(new Pair(sr, sc), new Pair(r, c), cnt));
				return;
			}
		}
	}

	private static void print() {
		System.out.println();
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				System.out.print(map[i][j]+" ");
			}System.out.println();
		}
		
	}

	private static void paint() {
		
		idx = 2;	//섬 index
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				if(map[i][j]==1) {
					Queue<Pair> q = new LinkedList<>();
					q.add(new Pair(i, j));
					Pair p;
					while(!q.isEmpty()) {
						p = q.poll();
						map[p.r][p.c] = idx;
						int r, c;
						for(int k=0; k<4; ++k) {
							r = p.r+dr[k];
							c = p.c+dc[k];
							if(r<0 || c<0 || r>=R || c>=C || map[r][c]!=1)
								continue;
							map[r][c] = idx;
							q.add(new Pair(r, c));
						}
					}
					idx++;
				}
			}
		}
		//섬 개수만큼 배열 생성
		parent = new int[idx--];
		
	}

}
