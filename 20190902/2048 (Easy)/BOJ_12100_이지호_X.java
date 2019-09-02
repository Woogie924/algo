import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12100 {
	static int map[][];
	static int N;
	static int max=Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(0);
		System.out.println(max);
	}

	private static void DFS(int depth) {
		if (depth == 5) {
			for(int i =0 ; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(max<map[i][j]) {
						max=map[i][j];
					}
				}
			}
			return;
		}
		int copy[][] = new int[N][N];
		copy(copy, map);
		for (int i = 0; i < 4; i++) {
			merge(i);
			DFS(depth + 1);
			copy(map,copy);
		}

	}

	private static void merge(int dir) {
		Queue<Integer> q = new LinkedList<>();

		if (dir == 0) {// 상
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[j][i] != 0)
						q.add(map[j][i]);
					map[j][i] = 0;
				}
				int cnt = 0;
				int temp;
				while (!q.isEmpty()) {
					temp = q.poll();
					if (map[cnt][i] == 0) {
						map[cnt][i] = temp;
					} else if (map[cnt][i] == temp) {
						map[cnt][i] = map[cnt][i] * 2;
						cnt++;
					} else {
						cnt++;
						map[cnt][i] = temp;
					}
				}
			}
		} else if (dir == 1) {// 하
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (map[j][i] != 0)
						q.add(map[j][i]);
					map[j][i] = 0;
				}
				int cnt = N-1;
				int temp;
				while (!q.isEmpty()) {
					temp = q.poll();
					if (map[cnt][i] == 0) {
						map[cnt][i] = temp;
					} else if (map[cnt][i] == temp) {
						map[cnt][i] = map[cnt][i] * 2;
						cnt--;
					} else {
						cnt--;
						map[cnt][i] = temp;
					}
				}
			}
		} else if (dir == 2) {// 좌
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0)
						q.add(map[i][j]);
					map[i][j] = 0;
				}
				int cnt = 0;
				int temp;
				while (!q.isEmpty()) {
					temp = q.poll();
					if (map[i][cnt] == 0) {
						map[i][cnt] = temp;
					} else if (map[cnt][i] == temp) {
						map[i][cnt] = map[i][cnt] * 2;
						cnt++;
					} else {
						cnt++;
						map[i][cnt] = temp;
					}
				}
			}
		} else if (dir == 3) {// 우
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] != 0)
						q.add(map[i][j]);
					map[i][j] = 0;
				}
				int cnt = N-1;
				int temp;
				while (!q.isEmpty()) {
					temp = q.poll();
					if (map[i][cnt] == 0) {
						map[i][cnt] = temp;
					} else if (map[i][cnt] == temp) {
						map[i][cnt] = map[i][cnt] * 2;
						cnt--;
					} else {
						cnt--;
						map[i][cnt] = temp;
					}
				}
			}
		}

	}

	private static void copy(int[][] arr1, int[][] arr2) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
		return;
	}

}
