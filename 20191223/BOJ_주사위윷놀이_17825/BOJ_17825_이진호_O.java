import java.util.Arrays;
import java.util.Scanner;

public class BOJ_17825_이진호_O {
	static int[] order = new int[10];
	static int max;
	static int[] map = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 13, 16, 19, 22,
			24, 28, 27, 26, 25, 30, 35 };// 20,21~23,24~25,26~28,29~31;
	static int[][] way = { { 1, 1 }, // 0에서 시작지점
			{ 2, 2 }, { 3, 3 }, { 4, 4 }, { 5, 5 }, { 21, 6 }, { 7, 7 }, { 8, 8 }, { 9, 9 }, { 10, 10 }, { 24, 11 },
			{ 12, 12 }, { 13, 13 }, { 14, 14 }, { 15, 15 }, { 26, 16 }, { 17, 17 }, { 18, 18 }, { 19, 19 }, { 20, 20 },
			{ -777, -777 }, { 22, 22 }, { 23, 23 }, { 29, 29 }, { 25, 25 }, { 29, 29 }, { 27, 27 }, { 28, 28 },
			{ 29, 29 }, { 30, 30 }, { 31, 31 }, { 20, 20 } };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {
			order[i] = sc.nextInt();
		}

		int[] num = new int[10];
		Arrays.fill(num, 1);
		num[9] = 0; // 24,25라인은 1111~111부터 시작하기위한 초기화
		max = Integer.MIN_VALUE;
		while (num[1] <= 2) {// 순열 만드는 반복문
			num[9]++;
			for (int i = 9; i >= 0; i--) {
				if (num[i] == 5) {
					num[i] = 1;
					num[i - 1]++;
				}
			}

			/*
			 * if (Arrays.toString(num).equals("[1, 2, 2, 2, 1, 2, 1, 1, 2, 1]")) {
			 * System.out.println("Ss"); }///체크용 코드
			 */
			go(num);
		}
		System.out.println(max);
	}

	private static void go(int[] num) {
		boolean[] visited = new boolean[32]; // 방문 체크용
		int[] loc = new int[5]; // 현재 각 말의 위치 정보
		int horse;
		int score = 0;
		st: for (int i = 0; i < 10; i++) {
			horse = num[i];

			int next = loc[horse];
			if (next == -777)//이미 종료지점에 가있으면 무시한다
				continue;
			visited[next] = false;
			for (int step = 0; step < order[i]; step++) {
				//한칸씩 가면서 그 칸에서 이동방향으로 이동
				if (step == 0) {
					next = way[next][0];
				} else {
					next = way[next][1];
				}
				if (next == -777) {
					continue st;
				}
			}
			if (visited[next])//중복시 바로 리턴
				return;
			loc[horse] = next;
			visited[next] = true;
			score += map[next];
		}
//		System.out.println(score);
		max = max < score ? score : max;
	}

}