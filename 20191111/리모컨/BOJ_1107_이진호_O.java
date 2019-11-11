import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1107_이진호_O {
	static int N, M;
	static int buttons;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		buttons = 0;
		
		int button;
		if (M != 0) {
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < M; i++) {
				button = Integer.parseInt(st.nextToken());
				buttons = buttons | (1 << button);
			}
		}
		
		int min = Math.abs(100-N);

		int i = N;
		st: for (; i >= 0; i--) {//해당숫자포함 밑으론 내려가는 반복문
			int temp = i;
			int count = 0;
			while (temp > 0)// 리모컨으로 가능한지 확인
			{
				if ((buttons & (1 << temp % 10)) == 1 << temp % 10)//버튼 확인
					continue st;
				count++;
				temp /= 10;
			}
			if (temp == 0 && count == 0) {//숫자 0에 대한 처리
				if ((buttons & (1 << temp % 10)) == 1 << temp % 10)
					continue st;
				count++;
				temp /= 10;
			}
			min = min > N - i + count ? N - i + count : min;
			break st;
		}
		i = N + 1;
		st: for (; i <= 1000000 + 10; i++) {//해당값 초과 로 검사하는 반복문 최대 백만으로 잡음//입력값이 오십만이기때문에 정확히는 499900개면 될듯
			if((i-N)>min) break;
			int temp = i;
			int count = 0;
			while (temp > 0)// 리모컨으로 가능한지 확인
			{
				if ((buttons & (1 << temp % 10)) == 1 << temp % 10)
					continue st;
				count++;
				temp /= 10;
			}

			min = min > i - N + count ? i - N + count : min;
			break st;
		}
		System.out.println(min);
	}

}
