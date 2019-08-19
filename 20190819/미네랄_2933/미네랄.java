import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	static String[][] arr;
	static boolean[][] visited;
	static int R, C;
	static ArrayList<position> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]); // 세로
		C = Integer.parseInt(str[1]); // 가로
		arr = new String[R + 1][C + 1];

		for (int i = 1; i <= R; i++) {
			str = br.readLine().split("");

			for (int j = 1; j <= C; j++) {
				arr[i][j] = str[j - 1];
			}
		} // 배열에 입력받기

		int N = Integer.parseInt(br.readLine()); // 막대를 던진 횟수

		str = br.readLine().split(" "); // 막대 높이
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(str[i]);
			visited = new boolean[R + 1][C + 1];

			if (i % 2 == 0) { // 창영
				for (int j = 1; j <= C; j++) {
					if (arr[R - num + 1][j].equals("x")) {
						arr[R - num + 1][j] = ".";
						// System.out.println("창영 x:" + (R - num + 1) + ", y:" + j);
						break;
					}
				}
			}

			else { // 상근
				for (int j = C; j >= 1; j--) {
					if (arr[R - num + 1][j].equals("x")) {
						arr[R - num + 1][j] = ".";
						// System.out.println("상근 x:" + (R - num + 1) + ", y:" + j);
						break;
					}
				}
			}

			checkInAir: for (int j = 1; j <= R; j++) {
				for (int k = 1; k <= C; k++) {
					if (visited[j][k] == true || arr[j][k].equals("."))
						continue; // 이미 방문했거나 .이면 넘어가기
					list.clear();
					isClusterInAir = false; // 초기화
					list.add(new position(j, k)); // dfs시작
					// System.out.println("dd"+j+" , "+k);
					visited[j][k] = true;
					checkClusterInAir(j, k);

					if (isClusterInAir == false) { // dfs끝나면 검사하기(땅에 닿여있는지)
						for (int p = 0; p < list.size(); p++) {
							arr[list.get(p).x][list.get(p).y] = ".";
						}
						break checkInAir;
					}
				}
			}

			if (isClusterInAir == false) // 땅에서 떨어져 있다면
			{
				Collections.sort(list, new Comparator<position>() { // 그 부분의 좌표들 정렬하기
					@Override
					public int compare(position arg0, position arg1) {
						if (arg0.y == arg1.y)
							return arg1.x - arg0.x;
						else
							return arg1.y - arg0.y;
					}
				});

//				for (int p = 0; p < list.size(); p++) {
//					System.out.println(list.get(p).x + ", " + list.get(p).y);
//				}

				int temp = -1;
				ArrayList<position> clustersOnFloor = new ArrayList();

				for (int p = 0; p < list.size(); p++) { // 좌표 중 제일 밑의 좌표들 가져오기
					if (list.get(p).y != temp) {
						clustersOnFloor.add(new position(list.get(p).x, list.get(p).y));
						temp = list.get(p).y;
						//System.out.println("좌표 : " + list.get(p).x + " , " + list.get(p).y);
					}
				}

				int count = 1;
				boolean availableDown = true;

				a:while (availableDown ==true) {
					for (position p : clustersOnFloor) {
						if (p.x + count == R) {
							availableDown = false;
							break a;
						}
						if (!arr[p.x + count+1][p.y].equals(".")) {
							availableDown = false;
							break a;
						}
					}

					if (availableDown == true) {
						count++;
					}
				}	

				if (availableDown == false) {
					for (position p : list) {
						arr[p.x + count][p.y] = "x";
					}
				}
			}
		}

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}

	static int[] nextX = { 0, 0, -1, 1 };
	static int[] nextY = { -1, 1, 0, 0 };
	static boolean isClusterInAir = false;

	static void checkClusterInAir(int x, int y) {
		if (x == R) {
			isClusterInAir = true;
		}
		int tempX;
		int tempY;

		for (int i = 0; i < 4; i++) {
			tempX = x + nextX[i];
			tempY = y + nextY[i];

			if (tempX < 1 || tempX > R || tempY < 1 || tempY > C || arr[tempX][tempY].equals(".")
					|| visited[tempX][tempY] == true)
				continue;
			visited[tempX][tempY] = true;
			list.add(new position(tempX, tempY));
			checkClusterInAir(tempX, tempY);
		}
	}

	static class position {
		int x;
		int y;

		public position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
