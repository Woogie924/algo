import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Virus {
	int r;
	int c;

	public Virus(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class BOJ_Laboratory3_17142 {
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int[][] map;
	static boolean[] visit;
	static ArrayList<Virus> virus;
	static int n;
	static int answer = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		virus = new ArrayList<>();
		map = new int[n][n];
		for (int i = 0; i < n; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < n; ++j) {
				if (Integer.parseInt(s[j]) == 1) {
					map[i][j] = -1; // 벽
				} else if (Integer.parseInt(s[j]) == 2) {
					map[i][j] = -2; // 바이러스
					virus.add(new Virus(i, j));
				}
			}
		}

		int vNum = virus.size();
		visit = new boolean[vNum];
		// 1. 바이러스 중 m개 고른 후 조합 수만큼 반복...
		ArrayList<Integer> list = new ArrayList<>();
		permutation(0, list, m, vNum);

		
		System.out.println(answer);

	}
	
	private static void check(int[][] temp) {
		// 3. 꽉 찼는지 확인하고 꽉 안찼으면 -1
		int tAnswer = 0;
		out:for(int i=0; i<n; ++i){
			for(int j=0; j<n; ++j){
				if(temp[i][j]==0){
					return;
				}
				if(temp[i][j]>0)
					tAnswer = Math.max(tAnswer, temp[i][j]);
			}
		}
		
		if(answer == -1)
			answer = tAnswer;
		answer = Math.min(answer, tAnswer);
		
	}

	private static void bfs(ArrayList<Integer> list) {
		int[][] temp = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		for(int i=0; i<n; ++i){
			for(int j=0; j<n; ++j){
				temp[i][j] = map[i][j];
			}
		}
		
		Queue<Virus> q = new LinkedList<Virus>();
		for (int i = 0; i < list.size(); ++i) {
			q.add(virus.get(list.get(i)));
			temp[virus.get(list.get(i)).r][virus.get(list.get(i)).c] = -3;
		}
		
		
		Virus v;
		int level = 1;
		while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; ++i) {
				v = q.poll();
				int r = v.r;
				int c = v.c;
				visited[r][c] = true;
				int tr, tc;
				for (int j = 0; j < 4; ++j) {
					tr = r + dr[j];
					tc = c + dc[j];
					if (tr < 0 || tc < 0 || tr >= n || tc >= n)
						continue;
					
					if(!visited[tr][tc] && temp[tr][tc]==0){
						temp[tr][tc] = level;
						visited[tr][tc] = true;
						q.add(new Virus(tr, tc));
					}
					if(!visited[tr][tc] && temp[tr][tc]==-2){
						q.add(new Virus(tr, tc));
						visited[tr][tc] = true;
					}
				}
			}
			level++;
			
		}
		check(temp);
	}

	private static void print(int[][] temp) {
		for(int i=0; i<n; ++i){
			for(int j=0; j<n; ++j){
				System.out.print(temp[i][j]+"\t");
			}System.out.println();
		}System.out.println();
	}

	private static void permutation(int n, ArrayList<Integer> list, int m, int vNum) {
		if (list.size() == m) {
			// 2. 고른 m개 바이러스 bfs돌려 몇초만에 꽉 차는지 구하기...
			bfs(list);
			return;
		}

		for (int i = n; i < vNum; ++i) {
			if (visit[i])
				continue;
			visit[i] = true;
			list.add(i);
			permutation(i + 1, list, m, vNum);
			visit[i] = false;
			list.remove(list.size() - 1);
		}
	}

}
