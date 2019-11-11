import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1107_����ȣ_O {
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
		st: for (; i >= 0; i--) {//�ش�������� ������ �������� �ݺ���
			int temp = i;
			int count = 0;
			while (temp > 0)// ���������� �������� Ȯ��
			{
				if ((buttons & (1 << temp % 10)) == 1 << temp % 10)//��ư Ȯ��
					continue st;
				count++;
				temp /= 10;
			}
			if (temp == 0 && count == 0) {//���� 0�� ���� ó��
				if ((buttons & (1 << temp % 10)) == 1 << temp % 10)
					continue st;
				count++;
				temp /= 10;
			}
			min = min > N - i + count ? N - i + count : min;
			break st;
		}
		i = N + 1;
		st: for (; i <= 1000000 + 10; i++) {//�ش簪 �ʰ� �� �˻��ϴ� �ݺ��� �ִ� �鸸���� ����//�Է°��� ���ʸ��̱⶧���� ��Ȯ���� 499900���� �ɵ�
			if((i-N)>min) break;
			int temp = i;
			int count = 0;
			while (temp > 0)// ���������� �������� Ȯ��
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
