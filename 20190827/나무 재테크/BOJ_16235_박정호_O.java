import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int[] dr = { 0, 0, 1, -1, 1, 1, -1, -1 };
	static int[] dc = { 1, -1, 0, 0, 1, -1, 1, -1 };
	static int[][] map;
	static int N, M, K;
	static ArrayList<Integer>[][] tree;
	static ArrayList<Integer>[][] dead;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		K = Integer.parseInt(s[2]);
		map = new int[N][N];
		tree = new ArrayList[N][N];
		dead = new ArrayList[N][N];
		int[][] add = new int[N][N];
		for (int i = 0; i < N; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < N; ++j) {
				add[i][j] = Integer.parseInt(s[j]);
				map[i][j] = 5;
				tree[i][j] = new ArrayList<>();
				dead[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; ++i) {
			s = br.readLine().split("\\s");
			tree[Integer.parseInt(s[0])-1][Integer.parseInt(s[1])-1].add(Integer.parseInt(s[2]));
		}

		for (int i = 0; i < K; ++i) {
			// 봄 : 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수
			// 있다.
			// 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다. 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수
			// 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
			spring();

			// 여름 : 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점
			// 아래는 버린다.
			summer();

			// 가을 : 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다. 상도의 땅을
			// 벗어나는 칸에는 나무가 생기지 않는다.
			fall();

			// 겨울 : S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
			winter(add);
		}
		// K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램을 작성하시오.
		System.out.println(cnt());

	}

	private static void spring() {
		sort();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				for (int k = 0; k < tree[i][j].size(); ++k) {
					if (map[i][j] >= tree[i][j].get(k)) {
						map[i][j] -= tree[i][j].get(k);
						tree[i][j].set(k, tree[i][j].get(k) + 1);
					} else {
						int repeat = tree[i][j].size();
						for(int l=k; l<repeat; ++l) {
							dead[i][j].add(tree[i][j].get(k) / 2);
							tree[i][j].remove(k);
						}
					}
				}
			}
		}
	}

	private static void sort() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				Collections.sort(tree[i][j]);
			}
		}
	}

	private static void summer() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				for (int k = 0; k < dead[i][j].size(); ++k) {
					map[i][j] += dead[i][j].get(k);
				}
				dead[i][j].clear();
			}
		}

	}

	private static void fall() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				for (int k = 0; k < tree[i][j].size(); ++k) {
					if (tree[i][j].get(k) % 5 == 0) {
						int tr, tc;
						for (int l = 0; l < 8; ++l) {
							tr = i + dr[l];
							tc = j + dc[l];
							if (tr < 0 || tc < 0 || tr >= N || tc >= N)
								continue;
							tree[tr][tc].add(1);
						}
					}
				}
			}
		}
	}

	private static void winter(int[][] add) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				map[i][j] += add[i][j];
			}
		}
	}

	private static int cnt() {
		int count = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				count += tree[i][j].size();
			}
		}

		return count;
	}

}