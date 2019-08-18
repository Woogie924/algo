package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14499 {

	static int N, M;

	public static void main(String[] args) throws Exception {//주사위 굴리기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 맵크기
		M = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][M];
		int startx = Integer.parseInt(st.nextToken());// 시작지점
		int starty = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());// 명령어 개수
		dice dice = new dice();
		dice.x = startx;
		dice.y = starty;
		for (int i = 0; i < N; i++) {// 입력부
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int commend = Integer.parseInt(st.nextToken());
			int temp = 0;
			switch (commend) {
			case 1:// 동
				dice.y++;
				if (check(dice.x, dice.y)) {
					temp = dice.up;
					dice.up = dice.left;
					dice.left = dice.down;
					dice.down = dice.right;
					dice.right = temp;
					if(map[dice.x][dice.y]==0) {
						map[dice.x][dice.y]=dice.down;
					}else {
						dice.down=map[dice.x][dice.y];
						map[dice.x][dice.y]=0;
					}
					System.out.println(dice.up);
				}else dice.y--;
				break;

			case 2:// 서
				dice.y--;
				if (check(dice.x, dice.y)) {
					temp = dice.down;
					dice.down = dice.left;
					dice.left = dice.up;
					dice.up = dice.right;
					dice.right = temp;
					if(map[dice.x][dice.y]==0) {
						map[dice.x][dice.y]=dice.down;
					}else {
						dice.down=map[dice.x][dice.y];
						map[dice.x][dice.y]=0;
					}
					System.out.println(dice.up);
				}else dice.y++;
				break;
			case 3:// 북
				dice.x--;
				if (check(dice.x, dice.y)) {
					temp = dice.down;
					dice.down = dice.behind;
					dice.behind = dice.up;
					dice.up = dice.front;
					dice.front = temp;
					if(map[dice.x][dice.y]==0) {
						map[dice.x][dice.y]=dice.down;
					}else {
						dice.down=map[dice.x][dice.y];
						map[dice.x][dice.y]=0;
					}
					System.out.println(dice.up);
				}else dice.x++;
				break;
			case 4:// 남
				dice.x++;
				if (check(dice.x, dice.y)) {
					temp = dice.up;
					dice.up = dice.behind;
					dice.behind = dice.down;
					dice.down = dice.front;
					dice.front = temp;
					if(map[dice.x][dice.y]==0) {
						map[dice.x][dice.y]=dice.down;
					}else {
						dice.down=map[dice.x][dice.y];
						map[dice.x][dice.y]=0;
					}
					System.out.println(dice.up);
				}else dice.x--;
				break;
			}
		}

	}
	static class dice {
		int x;
		int y;
		int up;
		int down;
		int left;
		int right;
		int behind;
		int front;
	}
	private static boolean check(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) {// 밖으로나가는경우
			return false;
		} else
			return true;
	}
}
