import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Position {
	int r;
	int c;

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
}

public class Main {
	static int R, C;
	static int[][] map;
	static ArrayList<Position> wall = new ArrayList<>();
	static ArrayList<Position> virus = new ArrayList<>();
	static int size;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		map = new int[R][C];

		for (int i = 0; i < R; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < C; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
				if (map[i][j] == 0)
					wall.add(new Position(i, j));
				else if (map[i][j] == 2)
					virus.add(new Position(i, j));
			}
		}

		size = wall.size();
		ArrayList<Integer> list = new ArrayList<>();
		go(0, list);
		System.out.println(answer);

	}

	private static void go(int n, ArrayList<Integer> list) {
		if (list.size() == 3) {
			bfs(list);
			return;
		}

		for (int i = n; i < size; ++i) {
			list.add(i);
			go(i + 1, list);
			list.remove(list.size() - 1);
		}

	}

	private static void bfs(ArrayList<Integer> list) {
		int[][] temp = new int[R][C];
		for (int i = 0; i < R; ++i)
			for (int j = 0; j < C; ++j)
				temp[i][j] = map[i][j];

		int tr, tc;
		for (int i = 0; i < 3; ++i) {
			tr = wall.get(list.get(i)).r;
			tc = wall.get(list.get(i)).c;
			temp[tr][tc] = 1;
		}
		Queue<Position> q = new LinkedList<Position>();
		for (int i = 0; i < virus.size(); ++i) {
			q.offer(new Position(virus.get(i).r, virus.get(i).c));
		}
		Position p;
		boolean[][] visit = new boolean[R][C];
		while (!q.isEmpty()) {
			p = q.poll();
			int r = p.r;
			int c = p.c;
			visit[r][c] = true;
			for (int i = 0; i < 4; ++i) {
				tr = r + dr[i];
				tc = c + dc[i];
				if (tr < 0 || tc < 0 || tr >= R || tc >= C || temp[tr][tc] == 1 || temp[tr][tc] == 2 || visit[tr][tc])
					continue;
				visit[tr][tc] = true;
				temp[tr][tc] = 2;
				q.offer(new Position(tr, tc));
			}
			
		}
		
		int cnt = 0;
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				if(temp[i][j]==0)
					cnt++;
			}
		}
		answer = Math.max(answer, cnt);
	}


}
