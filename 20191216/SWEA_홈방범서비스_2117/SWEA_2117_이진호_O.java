import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2117_이진호_O {

	static int T, M, N;
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][N];

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0 ; x < N ; x++)
				{
					map[y][x] = st.nextToken().charAt(0);
				}
			}

			int maxSize = N % 2 == 0 ? N + 1 : N;
			int base;
			int result = 0;
			int sx,sy,dx,dy;
			
			for (int size = maxSize; size >= 1; size--) {
				int p = size%2==0 ? size-1: size;
				sx = sy = 0 + (p)/2;
				dx = dy = N - 1 - (p)/2;
				dx = dy = dx<sx? sx: dx;
				for (int by = sy; by <= dy; by++) {
					for (int bx = sx; bx <= dx; bx++) {
						int isy, idy, isx, idx;

						isy = by - size < 0 ? 0 : by - size;
						idy = by + size >= N ? N - 1 : by + size;

						base = size * size + (size - 1) * (size - 1);
						int sum = 0;
						for (int y = isy; y <= idy; y++) {
							int plus = size-Math.abs(y - by) - 1;
							isx = bx - plus <0 ? 0 : bx-plus;
							idx = bx + plus >=N ? N-1 : bx+plus;

							for (int x = isx; x <= idx; x++) {
								sum += map[y][x] == '1' ? 1 : 0;
							}
						}
						if ((sum * M) >= base) {
							result = sum > result ? sum : result;
						}
						// 정해진 점 기준으로 마름모 확인
					}
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}

}
