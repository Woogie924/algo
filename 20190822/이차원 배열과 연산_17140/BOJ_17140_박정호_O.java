import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2Chaone_17140 {
	static int[][] map = new int[100][100];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		int r = Integer.parseInt(s[0]);
		int c = Integer.parseInt(s[1]);
		int k = Integer.parseInt(s[2]);

		for (int i = 0; i < 3; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < 3; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		int nowr = 3;
		int nowc = 3;
		int answer = 0;
		while (map[r-1][c-1] != k) {
			if (answer>100) {
				answer = -1;
				break;
			}
			answer++;
			if (nowr >= nowc) {
				calcR(nowr);
			} else {
				calcC(nowc);
			}
			
			// 가장 긴 행, 열의 크기를 찾아 다음 연산을 결정
			boolean flag = false;
			for (int i = 0; i < 100; ++i) {
				nowr = i;
				flag = false;
				for (int j = 0; j < 100; ++j) {
					if (map[i][j] == 0) {
						continue;
					}
					flag = true;
					break;
				}
				if (!flag)
					break;
			}
			// 가장 긴 열 크기 찾기
			flag = false;
			for (int i = 0; i < 100; ++i) {
				nowc = i;
				flag = false;
				for (int j = 0; j < 100; ++j) {
					if (map[j][i] == 0) {
						continue;
					}
					flag = true;
					break;
				}
				if (!flag)
					break;
			}
		}
		System.out.println(answer);
	}

	private static void calcR(int nowr) {
		int[] arr;
		int[][] temp = new int[100][100];
		for (int i = 0; i < nowr; ++i) {
			arr = new int[101];
			for (int j = 0; j < 100; ++j) {
				if (map[i][j] == 0)
					continue;
				arr[map[i][j]]++;
			}
			// System.out.println(Arrays.toString(arr));
			int max = 0;
			for (int j = 1; j <= 100; ++j) {
				max = Math.max(max, arr[j]);
			}
			int idx = 0;
			for (int j = 1; j <= max; ++j) {
				for (int k = 1; k <= 100; ++k) {
					if (arr[k] == j) {
						temp[i][idx++] = k;
						temp[i][idx++] = j;
					}
				}
			}
		}
		map = temp;
	}

	private static void calcC(int nowc) {
		int[] arr;
		int[][] temp = new int[100][100];
		for (int i = 0; i < nowc; ++i) {
			arr = new int[101];
			for (int j = 0; j < 100; ++j) {
				if (map[j][i] == 0)
					continue;
				arr[map[j][i]]++;
			}
			// System.out.println(Arrays.toString(arr));
			int max = 0;
			for (int j = 1; j <= 100; ++j) {
				max = Math.max(max, arr[j]);
			}
			int idx = 0;
			for (int j = 1; j <= max; ++j) {
				for (int k = 1; k <= 100; ++k) {
					if (arr[k] == j) {
						temp[idx++][i] = k;
						temp[idx++][i] = j;
					}
				}
			}
		}
		map = temp;
	}

	private static void print() {
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

}
