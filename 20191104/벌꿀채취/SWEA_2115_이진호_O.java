import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2115_이진호_O {
	static int T, N, M, C;
	static int[][] map, eMap, cMap, mMap, fMap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			eMap = new int[N][N];
			cMap = new int[N][N];
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					eMap[y][x] = map[y][x] * map[y][x];
				}
			}

			for (int y = 0; y < N; y++)// 조합으로 구할까 :: 여기기준 M개를 기준으로 최댓값
			{
				for (int x = 0; x <= N - M; x++) {
					int temp = Integer.MIN_VALUE;
					for (int i = 1; i < (1 << M); i++) {
						int tempSum = 0;
						int tempExp = 0;
						for (int j = 0; j < M; j++) {
							if ((i & (1 << j)) == (1 << j)) {
								tempSum += map[y][x + j];
								tempExp += eMap[y][x + j];
							}
						}
						if (tempSum > C)
							continue;
						else {
							temp = temp < tempExp ? tempExp : temp;
						}
					}
					cMap[y][x] = temp;
				}
			}

			int temp;
			int max = Integer.MIN_VALUE;
			for (int y = 0; y < N; y++)// 조합으로 구할까 :: 여기기준 M개를 기준으로 최댓값
			{
				for (int x = 0; x <= N - M; x++) {
					temp = cMap[y][x];
					for (int ny = y; ny < N; ny++)// 조합으로 구할까 :: 여기기준 M개를 기준으로 최댓값
					{
						int nx;
						if(ny==y) nx = x+M;
						else nx = 0;
						for (; nx <= N - M; nx++) {
							max = temp + cMap[ny][nx] > max ? temp + cMap[ny][nx] : max;
						}
					}
				}
			}
			System.out.println("#" + tc+" "+max);
		}
	}

}
