import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소_O{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, zsum,fvsum,vsum, size, cnt, x, qx, qy;
	static int sum, min;
	static int dx[] = { -1, 1, 0, 0 }; // 상하좌우
	static int dy[] = { 0, 0, -1, 1 };
	static int temp[] = new int[2];
	static int num[] = new int[3];
	static int map[][];
	static boolean checknum[];
	static int arr[][];
	static Queue<int[]> q = new LinkedList<>();
	static ArrayList<int[]> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0) {
					list.add(new int[] { i, j });
					zsum++;
				}
			} // make map;
		}
		min = Integer.MAX_VALUE;
		size = list.size();
		checknum = new boolean[size];
		makepillar();
		System.out.println(zsum-min-3);
		
	}
	private static void makepillar() {
		if (cnt == 3) {
			resetmap();
			for (int a = 0; a < 3; a++) {
				map[list.get(num[a])[0]][list.get(num[a])[1]] = 1;
			}
			govirus();
			return;
		}
		for (int a = x; a < size; a++) {
			if (checknum[a] == true)
				continue;
			num[cnt] = a;
			checknum[a] = true;
			cnt = cnt + 1;
			makepillar();
			checknum[a] = false;
			cnt = cnt - 1;
			x = a + 1;
		}

	}

	private static void govirus() { //바이러스 확산 후 비교
		vsum=0;
		while (!q.isEmpty()) {
			temp = q.poll();
			for (int a = 0; a < 4; a++) {
				qx = temp[0] + dx[a];
				qy = temp[1] + dy[a];
				if (qx < 0 || qy < 0 || qx >= N || qy >= M)
					continue;
				if (map[qx][qy] == 2 || map[qx][qy] == 1)
					continue;
				vsum++;
                if(vsum>min)break;
				map[qx][qy] = 2;
				q.offer(new int[] { qx, qy });
			}
		}
        while(!q.isEmpty())q.poll();
		if(vsum<min) min=vsum;
	}

	private static void resetmap() { // 맵리셋, q에 바이러스 등록
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = arr[i][j];
				if (arr[i][j] == 2)
					q.offer(new int[] { i, j });
			}
		}
	}

}
