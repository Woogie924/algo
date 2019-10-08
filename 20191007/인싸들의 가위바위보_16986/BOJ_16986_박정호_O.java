import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_인싸들의가위바위보_16986_박정호_X {
	static int N, K;
	static int[][] map;
	static int[] kh = new int[20];
	static int[] mh = new int[20];
	static boolean answer;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		N = Integer.parseInt(s[0]);
		K = Integer.parseInt(s[1]);
		map = new int[N][N];
		visit = new boolean[N + 1];
		for (int i = 0; i < N; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		s = br.readLine().split("\\s");
		for (int i = 0; i < 20; ++i)
			kh[i] = Integer.parseInt(s[i]);
		s = br.readLine().split("\\s");
		for (int i = 0; i < 20; ++i)
			mh[i] = Integer.parseInt(s[i]);

		// N보다 K가 크면 지우가 승수만큼 이길 수 없음
		if (K > N)
			answer = false;
		else {
			// 1부터 N까지 순열 만듦
			ArrayList<Integer> list = new ArrayList<>();
			go(list);
		}

		if (answer)
			System.out.println(1);
		else
			System.out.println(0);
	}

	static int first, second, third;
	static int[] idx;
	static int[] win;

	private static void solution(ArrayList<Integer> list) {
		// 지우 : 0, 경희 : 1, 민호 : 2
		first = 0;
		second = 1;
		third = 2;
		idx = new int[3];
		win = new int[3];
		while (true) {
			if (idx[0] == N || idx[1] == 20 || idx[2] == 20)
				return;
			int result = 0;
			// 지우선
			if (first == 0 && second == 1) {
				result = map[list.get(idx[0]++)-1][kh[idx[1]++]-1];
			} else if (first == 0 && second == 2) {
				result = map[list.get(idx[0]++)-1][mh[idx[2]++]-1];
			}
			// 경희선
			else if (first == 1 && second == 0) {
				result = map[kh[idx[1]++]-1][list.get(idx[0]++)-1];
			} else if (first == 1 && second == 2) {
				result = map[kh[idx[1]++]-1][mh[idx[2]++]-1];
			}
			// 민호선
			else if (first == 2 && second == 0) {
				result = map[mh[idx[2]++]-1][list.get(idx[0]++)-1];
			} else if (first == 2 && second == 1) {
				result = map[mh[idx[2]++]-1][kh[idx[1]++]-1];
			}
			check(result);
			if (win[0] == K) {
				answer = true;
				return;
			} else if (win[1] == K || win[2] == K)
				return;
		}
	}

	private static void check(int result) {
		int temp = second;
		if (result == 2) {
			win[first]++;
			second = third;
			third = temp;
		} else if (result == 0) {
			win[second]++;
			second = third;
			third = first;
			first = temp;
		}else{
			int loser = Math.min(first, second);
			int winner = Math.max(first, second);
			win[winner]++;
			first = winner;
			second = third;
			third = loser;
		}
	}

	private static void go(ArrayList<Integer> list) {
		if (answer)
			return;
		if (list.size() == N) {
			solution(list);
			return;
		}

		for (int i = 1; i <= N; ++i) {
			if (visit[i])
				continue;
			visit[i] = true;
			list.add(i);
			go(list);
			if (answer)
				return;
			visit[i] = false;
			list.remove(list.size() - 1);
		}

	}

}
