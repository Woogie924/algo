import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16928_이옥주_O {
	static Queue<info> q = new LinkedList<>();
	static int[] ladderArr, snakeArr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int ladder = Integer.parseInt(str[0]);
		int snake = Integer.parseInt(str[1]);
		ladderArr = new int[101];
		snakeArr = new int[101];
		visited = new boolean[101];

		for (int i = 0; i < ladder; i++) {
			str = br.readLine().split(" ");
			ladderArr[Integer.parseInt(str[0])] = Integer.parseInt(str[1]);
		} // 사다리 정보 받기

		for (int i = 0; i < snake; i++) {
			str = br.readLine().split(" ");
			snakeArr[Integer.parseInt(str[0])] = Integer.parseInt(str[1]);
		} // 뱀 정보 받기

		bfs();

		System.out.println(result);
	}

	static info in = new info(0, 0);
	static int result;

	private static void bfs() {
		q.offer(new info(1, 0));

		while (!q.isEmpty()) {
			in = q.poll();

			if (in.num == 100) {
				result = in.count;
				return;
			}

			for (int i = 1; i <= 6; i++) {
				if (ladderArr[in.num] != 0) {
					q.offer(new info(ladderArr[in.num], in.count));
					visited[ladderArr[in.num]] = true;
				} else if (snakeArr[in.num] != 0) {
					q.offer(new info(snakeArr[in.num], in.count));
					visited[ladderArr[in.num]] = true;
				} else {
					if ((in.num + i <= 100) && (visited[in.num + i] == false)) {
						q.offer(new info(in.num + i, in.count + 1));
						visited[ladderArr[in.num + i]] = true;
					}
				}
			}
		}
	}

	static class info {
		int num;
		int count;

		public info(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}
	}
}