import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_Gear2_15662 {

	static ArrayList<Integer>[] gear;
	static boolean[] change;
	static int[] changeDir;
	static int gearNum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 톱니바퀴 개수
		gearNum = Integer.parseInt(br.readLine());
		gear = new ArrayList[gearNum+1]; // 기어의 상태를 저장하는 리스트
		change = new boolean[gearNum+1]; // 회전 시키는지 저장하는 배열
		changeDir = new int[gearNum+1]; // 회전 방향을 저장하년 배열

		// 톱니바퀴 상태 입력받기
		for (int i = 1; i <= gearNum; ++i) {
			gear[i] = new ArrayList<Integer>();
			String[] s = br.readLine().split("");
			for (int j = 0; j < 8; ++j) {
				gear[i].add(Integer.parseInt(s[j]));
			}
		}

		int cmdNum = Integer.parseInt(br.readLine()); // 명령어 수
		int n, dir; // 톱니바퀴 번호, 방향(시계 : 1, 반시계 -1)
		for (int i = 0; i < cmdNum; ++i) {
			String[] s = br.readLine().split("\\s");
			n = Integer.parseInt(s[0]); // n번째 톱니바퀴
			dir = Integer.parseInt(s[1]); // 방향
			// 몇번 톱니바퀴를 회전시켜야 하는지 체크
			check(n, dir);
			for (int j = 1; j <= gearNum; ++j) {
				if (change[j]) {
					swap(j, changeDir[j]); // n번째 톱니바퀴를 n바향으로 회전
				}
			}
		}

		int answer = 0;
		for (int i = 1; i <= gearNum; ++i) {
			if(gear[i].get(0)==1)
				answer++;
		}
		System.out.println(answer);

	}

	private static void swap(int n, int dir) {

		if (dir == 1) {// 시계방향
			gear[n].add(0, gear[n].remove(7));
		} else {// 반시계방향
			gear[n].add(gear[n].remove(0));
		}
	}

	private static void check(int n, int dir) {
		change = new boolean[gearNum+1];
		changeDir = new int[gearNum+1];

		// 명령어로 들어온 톱니바퀴는 회전시켜야 하므로 true, 방향은 dir
		change[n] = true;
		changeDir[n] = dir;

		// n번째 톱니의 왼쪽 톱니바퀴 체크
		// N극은 0, S극은 1
		int temp = n - 1;
		while (true) {
			// 왼쪽 톱니바퀴가 1보다 작은건 없으므로 break
			if (temp < 1)
				break;
			// 왼쪽 톱니바퀴가 1일 때 마지막으로 수행
			if (temp == 1) { // 왼쪽 톱니의 2번과, 오른쪽 톱니의 6번 톱니 비교해서 다르면 true(회전), 방향은
								// 반대로(곱하기 -1)
				if (gear[temp].get(2) != gear[temp + 1].get(6)) {
					change[temp] = true;
					changeDir[temp] = changeDir[temp + 1] * (-1);
				}
				break;
			} else {
				if (gear[temp].get(2) != gear[temp + 1].get(6)) {
					change[temp] = true;
					changeDir[temp] = changeDir[temp + 1] * (-1);
					temp--;
				} else // 왼쪽톱니바퀴가 회전하지 않을경우 나머지 왼쪽도 회전하지 않으므로 break
					break;
			}
		}
		// n의 오른쪽 톱니바퀴 체크
		temp = n + 1;
		while (true) {
			if (temp > gearNum)
				break;
			if (temp == gearNum) {
				if (gear[temp - 1].get(2) != gear[temp].get(6)) {
					change[temp] = true;
					changeDir[temp] = changeDir[temp - 1] * (-1);
				}
				break;
			} else {
				if (gear[temp - 1].get(2) != gear[temp].get(6)) {
					change[temp] = true;
					changeDir[temp] = changeDir[temp - 1] * (-1);
					temp++;
				} else
					break;
			}
		}

	}

}
