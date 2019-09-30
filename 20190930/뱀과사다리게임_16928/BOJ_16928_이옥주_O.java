import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16928_이옥주_O {
	static Queue<info> q = new LinkedList<>();
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int ladder = Integer.parseInt(str[0]);
		int snake = Integer.parseInt(str[1]);
		arr = new int[101];
		visited = new boolean[101];

		for (int i = 0; i < ladder; i++) {
			str = br.readLine().split(" ");
			arr[Integer.parseInt(str[0])] = Integer.parseInt(str[1]);
		} // 사다리 정보 받기

		for (int i = 0; i < snake; i++) {
			str = br.readLine().split(" ");
			arr[Integer.parseInt(str[0])] = Integer.parseInt(str[1]);
		} // 뱀 정보 받기

		bfs();

		System.out.println(result);
	}

	static info in = new info(0, 0);
	static int result;

	private static void bfs() {
		q.offer(new info(1, 0));
		visited[1] = true;

		while (!q.isEmpty()) {
			in = q.poll();

			if (in.num == 100) {
				result = in.count;
				return;
			}

			for (int i = 1; i <= 6; i++) {
				if(in.num+i > 100 || visited[in.num + i] == true) continue;
				
				if(arr[in.num+i] != 0) {
					q.offer(new info(arr[in.num+i],in.count+1));
					visited[arr[in.num+i]] = true;
					visited[in.num+i] = true;
				}
				else {
					q.offer(new info(in.num + i, in.count + 1));
					visited[in.num + i] = true;
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