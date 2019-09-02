import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_PuyoPuyo_11559_박정호_O {
	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[][] map = new String[12][6];
		String[] s;
		for (int i = 0; i < 12; ++i) {
			s = br.readLine().split("");
			for (int j = 0; j < 6; ++j) {
				map[i][j] = s[j];
			}
		}

		int answer = 0;
		boolean flag = true;
		while (flag) {
			// 뿌요를 찾아 내 4개 이상인지 확인!
			boolean[][] visit = new boolean[12][6];
			ArrayList<Pair> breakList = new ArrayList<>();
			for (int i = 0; i < 12; ++i) {
				for (int j = 0; j < 6; ++j) {
					if (map[i][j].equals(".") || visit[i][j])
						continue;
					String puyo = map[i][j];
					// 연결된 뿌요를 저장하는 리스트
					ArrayList<Pair> check = new ArrayList<>();
					Queue<Pair> q = new LinkedList<>();
					q.add(new Pair(i, j));
					Pair p;
					while (!q.isEmpty()) {
						p = q.poll();
						check.add(p);
						int r = p.r;
						int c = p.c;
						visit[r][c] = true;
						int tr, tc;
						for (int k = 0; k < 4; ++k) {
							tr = r + dr[k];
							tc = c + dc[k];
							if (tr < 0 || tc < 0 || tr >= 12 || tc >= 6 || visit[tr][tc] || !map[tr][tc].equals(puyo))
								continue;
							visit[tr][tc] = true;
							q.add(new Pair(tr, tc));
						}
					}
					if (check.size() >= 4) {
						for (Pair pair : check)
							breakList.add(pair);
					}
				}
			}
			if (breakList.isEmpty()) {
				flag = false;
			} else {
				answer++;
				for(Pair pair : breakList){
					map[pair.r][pair.c] = ".";
				}
				// 뿌요를 밑으로 내리기
				fall(map);
			}
		}
		
		System.out.println(answer);

	}

	private static void fall(String[][] map) {
		for(int j=0; j<6; ++j){
			ArrayList<String> temp = new ArrayList<>();
			for(int i=11; i>=0; --i){
				if(map[i][j].equals("."))
					continue;
				temp.add(map[i][j]);
				map[i][j] = ".";
			}
			int idx = 11;
			for(int i=0; i<temp.size(); ++i){
				map[idx--][j] = temp.get(i);
			}
		}
		
	}

}
