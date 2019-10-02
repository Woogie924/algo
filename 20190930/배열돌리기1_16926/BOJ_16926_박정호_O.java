import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class BOJ_배열돌리기_16926_박정호_O {
	static int[][] map;
	static int N, M, R;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		R = Integer.parseInt(s[2]);
		map = new int[N][M];
		for (int i = 0; i < N; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		// 기준row, 기준col
		solution();
		print();
	}

	private static void solution() {
		int r = 0;
		int c = 0;
		int h = N;
		int w = M;
		while(true) {
			if(h<=1 || w<=1)
				break;
			int cycle = (h+w)*2-4;	// 한 사이클 도는데 걸리는 바퀴 수
			int rotationNum = R%cycle;	// 회전 횟수
			
			for(int j=0; j<rotationNum; ++j) {
				int temp = map[r][c];
				for(int i=c+1; i<c+w; ++i) {
					map[r][i-1] = map[r][i];
				}
				for(int i=r+1; i<r+h; ++i) {
					map[i-1][c+w-1] = map[i][c+w-1];
				}
				for(int i=c+w-2; i>=c; --i) {
					map[r+h-1][i+1] = map[r+h-1][i];
				}
				for(int i=r+h-2; i>r; --i) {
					map[i+1][c] = map[i][c];
				}
				map[r+1][c] = temp;
			}
			r++;
			c++;
			h-=2;
			w-=2;
		}
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
