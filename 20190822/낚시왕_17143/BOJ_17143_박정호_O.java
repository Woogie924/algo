import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Shark {
	int sr;
	int sc;
	int speed;
	int dir;
	int size;
	int num;

	public Shark(int sr, int sc, int speed, int dir, int size, int num) {
		this.sr = sr;
		this.sc = sc;
		this.speed = speed;
		this.dir = dir;
		this.size = size;
		this.num = num;
	}
}

public class BOJ_FishingKing_17143 {
	static int[][] map;
	static int r, c, m;
	// 방향은 북동남서
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		r = Integer.parseInt(s[0]);
		c = Integer.parseInt(s[1]);
		m = Integer.parseInt(s[2]);
		map = new int[r][c];

		ArrayList<Shark> shark = new ArrayList<Shark>();
		for (int i = 0; i < m; ++i) {
			s = br.readLine().split("\\s");
			shark.add(new Shark(Integer.parseInt(s[0]) - 1, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2]),
					Integer.parseInt(s[3]), Integer.parseInt(s[4]), i + 1));
			map[Integer.parseInt(s[0]) - 1][Integer.parseInt(s[1]) - 1] = i + 1;
		}

		// 1. 낚시왕 오른쪽으로 한 칸씩 이동 :: 모든 for문으로 column돌면됨
		for (int t = 0; t < c; ++t) {
			// 2. 낚시왕이 있는 열(t)에 있는 상어 중 가장 가까운 상어를 잡음. 상어를 잡으면 상어는 사라짐
			for (int i = 0; i < r; ++i) {
				if (map[i][t] > 0) {
					answer += shark.get(map[i][t] - 1).size;
					map[i][t] = 0;
					break;
				}
			}
			// 3. 상어가 이동함. 상어가 칸을 벗어나면 방향을 반대로 바꿈
			int sr, sc, dir, speed;
			int[][] temp = new int[r][c];
			for (int k = 0; k < shark.size(); ++k) {
				Shark sh = shark.get(k);
				sr = sh.sr;
				sc = sh.sc;
				if (map[sr][sc] != sh.num)
					continue;
				dir = sh.dir;
				speed = sh.speed;
				for (int i = 0; i < speed; ++i) {
					if (dir == 1 || dir == 2) {
						if (sr + dr[dir] < 0 || sr + dr[dir] >= r) {
							dir = 3 - dir;
						}
						sr += dr[dir];
					} else {
						if (sc + dc[dir] < 0 || sc + dc[dir] >= c) {
							dir = 7 - dir;
						}
						sc += dc[dir];
					}
				}
				sh.sr = sr;
				sh.sc = sc;
				sh.dir = dir;
				// 새로운 상어 위치 등록
				// 빈자리거나 상어가 있다면 그 상어보다 클 때
				if (temp[sr][sc] == 0 || shark.get(temp[sr][sc] - 1).size < sh.size)
					temp[sr][sc] = sh.num;
			}
			map = temp;

		}
		System.out.println(answer);

	}

	private static void print() {
		System.out.println();
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
