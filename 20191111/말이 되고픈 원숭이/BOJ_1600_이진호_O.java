import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_����ȣ_O {
	static int K, W, H;
	static int[][] map;

	static class Monkey {//y,x��ǥ ������ , ��ó�� �ڼ�
		int y;
		int x;
		int w;
		int k;

		public Monkey(int y, int x, int w, int k) {
			super();
			this.y = y;
			this.x = x;
			this.w = w;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Monkey [y=" + y + ", x=" + x + ", w=" + w + ", k=" + k + "]";
		}

	}

	static int[] dy = { -1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2 };//�װ������� �������� ������
	static int[] dx = { 0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[][][] dk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		dk = new int[H][W][31];//k�� 30������ ���� :: ������ �����迭
		map = new int[H][W];
		for (int y = 0; y < H; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < W; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<Monkey> q = new LinkedList<>();
		q.add(new Monkey(0, 0, 0, 0));
		
		for (int y = 0; y < H; y++) {//���δ� max_value�� �ʱ�ȭ
			for (int x = 0; x < W; x++) {
				Arrays.fill(dk[y][x], Integer.MAX_VALUE);
			}
		}
		Arrays.fill(dk[0][0], 0);
		Monkey m = null;
		int tx, ty;
		while (!q.isEmpty()) {

			m = q.poll();
			//show(m);
			if (m.y == H - 1 && m.x == W - 1)//�����ϸ� �극��ũ
				break;
			for (int dir = 0; dir < 4; dir++) {//�׹������������
				tx = m.x + dx[dir];
				ty = m.y + dy[dir];
				if (ty < 0 || ty >= H || tx < 0 || tx >= W)
					continue;
				if (map[ty][tx] == 1)
					continue;
				if (dk[ty][tx][m.k] <= m.w)
					continue;
				dk[ty][tx][m.k] = m.w;
				q.add(new Monkey(ty, tx, m.w + 1, m.k));
			}

			if (m.k < K) {//���� �� �������� K���� ������ ������ �غ���
				for (int dir = 4; dir < 12; dir++) {
					tx = m.x + dx[dir];
					ty = m.y + dy[dir];
					if (ty < 0 || ty >= H || tx < 0 || tx >= W)
						continue;
					if (map[ty][tx] == 1)
						continue;
					if (dk[ty][tx][m.k+1] <= m.w)
						continue;
					dk[ty][tx][m.k+1] = m.w;//dk[ty][tx][m.k+1]���� �� �������ڸ��� �������� �����ź��� ������ �־��ֱ�
					q.add(new Monkey(ty, tx, m.w + 1, m.k + 1));
				}
			}
		}
		if (m.y == H - 1 && m.x == W - 1)
			System.out.println(m.w);
		else
			System.out.println(-1);//ť�������µ� ������������
	}

	private static void show(Monkey m) {//��¹� ����3���� ǥ��
		System.out.println("=========================");
		for (int y = 0; y < H; y++) {

			for (int x = 0; x < W; x++) {
				if (m.y == y && m.x == x)
					System.out.print(3 + " ");
				else
					System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println("=========================");
	}
}
