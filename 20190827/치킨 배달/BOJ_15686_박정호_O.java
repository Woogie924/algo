import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class ChickenLoc{
	int r;
	int c;
	public ChickenLoc(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
}

public class Main {
	static int[][] map;
	static ArrayList<int[]> chicken = new ArrayList<>();
	static ArrayList<int[]> house = new ArrayList<>();
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		map = new int[N][N];
		for (int i = 0; i < N; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
				if (map[i][j] == 1) {
					int[] temp = { i, j };
					house.add(temp);
				} else if (map[i][j] == 2) {
					int[] temp = { i, j };
					chicken.add(temp);
				}
			}
		}

		ArrayList<Integer> list = new ArrayList<>();
		go(N, M, 0, list);
		System.out.println(answer);
	}

	private static void bfs(ArrayList<Integer> list, int n, int m) {
		int[][] temp = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (map[i][j] == 2)
					continue;
				temp[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < m; ++i) {
			temp[chicken.get(list.get(i))[0]][chicken.get(list.get(i))[1]] = 2;
		}
		
		int cnt = 0;
		for (int i = 0; i < house.size(); ++i) {
			Queue<ChickenLoc> q = new LinkedList<ChickenLoc>();
			boolean[][] visit = new boolean[n][n];
			q.add(new ChickenLoc(house.get(i)[0], house.get(i)[1]));
			int level = 1;			
			ChickenLoc cl;
			out:while (!q.isEmpty()) {
				int qSize = q.size();
				for (int j = 0; j < qSize; ++j) {
					cl = q.poll();
					int r = cl.r;
					int c = cl.c;
					visit[r][c] = true;
					int tr, tc;
					for (int k = 0; k < 4; ++k) {
						tr = r + dr[k];
						tc = c + dc[k];
						if (tr < 0 || tc < 0 || tr >= n || tc >= n || visit[tr][tc])
							continue;
						if(temp[tr][tc]==2) {
//							System.out.println(level);
							cnt+=level;
							break out;
						}
						q.add(new ChickenLoc(tr, tc));
						visit[tr][tc] = true;
					}
				}
				level++;
			}
		}
		answer = Math.min(answer, cnt);

//		print(temp, n);

	}

	private static void print(int[][] temp, int n) {
		System.out.println();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void go(int n, int m, int index, ArrayList<Integer> list) {
		if (list.size() == m) {
//			System.out.println(list);
			bfs(list, n, m);
			return;
		}
		for (int i = index; i < chicken.size(); ++i) {
			list.add(i);
			go(n, m, i + 1, list);
			list.remove(list.size() - 1);
		}
	}

}
