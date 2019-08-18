import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_Runway_14890 {
	static int n;
	static int l;
	static int answer = 0;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		n = Integer.parseInt(s[0]);
		l = Integer.parseInt(s[1]);
		map = new int[n][n];
		for(int i=0; i<n; ++i){
			s = br.readLine().split("\\s");
			for(int j=0; j<n; ++j){
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		// 모든 row 검사
		check();

		// 모든 column 검사
		rotation();	// map을 왼쪽 90도 회전
		check();
		System.out.println(answer);
	}

	private static void rotation() {
		int[][] temp = new int[n][n];
		for(int i=0; i<n; ++i){
			for(int j=0; j<n; ++j){
				temp[n-1-j][i] = map[i][j];
			}
		}
		map = temp;
	}

	private static void check() {
		// 모든 row 검사
		boolean flag = true; // 지나갈 수 있는 길인지 확인하는 flag
		int cnt = 0; // 연속된 길 길이
		for (int i = 0; i < n; ++i) {
			cnt = 1;
			flag = true;
			for (int j = 0; j < n - 1; ++j) {
				// 높이 차이가 2이상인경우 불가능!!
				if (Math.abs(map[i][j] - map[i][j + 1]) >= 2) {
					flag = false;
					break;
				}

				// 높이가 같다면 pass!
				if (map[i][j] == map[i][j + 1]) {
					cnt++; // 높이가 같다면 연속된 길 길이 추가
					continue;
				}

				// 길이 낮아지는 경우
				if (map[i][j] > map[i][j + 1]) {
					cnt = 1; // 낮아지는 경우는 cnt가 필요없음
					// 뒤에 경사로를 놓을 수 있는지 확인, 못놓는 경우 flag = false, break
					// 경사로를 놓을 길이 부족할 경우
					if (j + l >= n) {
						flag = false;
						break;
					}
					// 연속된 길인지 확인
					for (int k = 1; k < l; ++k) {
						if (map[i][j + k] != map[i][j + k + 1]) {
							flag = false;
							break;
						}
					}
					if (!flag)
						break;
					// 경사로만큼 뛰어넘음
					j += l;
					//경사로가 끝난 직후 높이가 다르면 못지나감
					if(j<n-1 && map[i][j]<map[i][j+1]){
						flag = false;
						break;
					}
				}
				// 길이 높아지는 경우
				else {
					// 연속된 길의 길이가 l 보다 작으면 못지나가므로 break
					if (cnt < l) {
						flag = false;
						break;
					}
					// 지나갈 수 있다면 cnt를 다시 1로 변경
					cnt = 1;
				}
			}
			if (flag){
				//System.out.println("통과~~!!");
				answer++;
			} /*else{
				System.out.println("불통!!");
			}*/
		}
	}

}
