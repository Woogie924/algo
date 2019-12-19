import java.util.Scanner;
public class BOJ_11052_이진호_O {
	static int[] cards;// 입력
	static int[] max;// i개를 살때에 최고 비용
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		cards = new int[N + 1];
		max = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			cards[i] = sc.nextInt();
		}
		max[1] = cards[1];

		int median; // 중간에 나오는 temp 값
		for (int i = 2; i <= N; i++) {
			median = cards[i];
			int temp = i; // 4 일때 4 or 1,3의 최댓값 더한값 or 2,2 최대값 더한값
			while (temp >= (i - temp)) {
				temp--;
				median = Math.max(median, max[temp] + max[i - temp]);
			}
			max[i] = median;
		}
		System.out.println(max[N]);
	}
}
