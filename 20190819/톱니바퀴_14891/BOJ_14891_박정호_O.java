package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_Gear_14891 {
	static ArrayList<Integer>[] gear = new ArrayList[5];
	static boolean[] change = new boolean[5];
	static int[] changeDir = new int[5];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 톱니바퀴 상태 입력받기
		for (int i = 1; i <= 4; ++i) {
			gear[i] = new ArrayList<Integer>();
			String[] s = br.readLine().split("");
			for (int j = 0; j < 8; ++j) {
				gear[i].add(Integer.parseInt(s[j]));
			}
		}

		int t = Integer.parseInt(br.readLine());
		int n, dir; // 톱니바퀴 번호, 방향(시계 : 1, 반시계 -1)
		for (int i = 0; i < t; ++i) {
			String[] s = br.readLine().split("\\s");
			n = Integer.parseInt(s[0]);		// n번째 톱니바퀴
			dir = Integer.parseInt(s[1]);	// 방향
			// 몇번 톱니바퀴를 회전시켜야 하는지 체크
			check(n, dir);
			for(int j=1; j<=4; ++j) {
				if(change[j]) {
					swap(j, changeDir[j]);	// n번째 톱니바퀴를 n바향으로 회전
				}
			}
		}
		
		int answer=0;
		if(gear[1].get(0)==1)
			answer+=1;
		if(gear[2].get(0)==1)
			answer+=2;
		if(gear[3].get(0)==1)
			answer+=4;
		if(gear[4].get(0)==1)
			answer+=8;
		System.out.println(answer);

	}

	private static void swap(int n, int dir) {
		
		if(dir==1) {//시계방향
			gear[n].add(0, gear[n].remove(7));
		}
		else {//반시계방향
			gear[n].add(gear[n].remove(0));
		}
	}

	private static void check(int n, int dir) {
		change = new boolean[5];
		changeDir = new int[5];
		
		change[n] = true;
		changeDir[n] = dir;
		// n의 왼쪽 톱니바퀴 체크
		// N극은 0, S극은 1
		int temp = n-1;
		while(true) {
			if(temp<1)
				break;
			if(temp==1) {
				if(gear[temp].get(2)!=gear[temp+1].get(6)) {
					change[temp] = true;
					changeDir[temp] = changeDir[temp+1]*(-1);
				}
				break;
			}
			else {
				if(gear[temp].get(2)!=gear[temp+1].get(6)) {
					change[temp] = true;
					changeDir[temp] = changeDir[temp+1]*(-1);
					temp--;
				}
				else
					break;
			}
		}
		// n의 오른쪽 톱니바퀴 체크
		temp = n+1;
		while(true) {
			if(temp>4)
				break;
			if(temp==4) {
				if(gear[temp-1].get(2)!=gear[temp].get(6)) {
					change[temp] = true;
					changeDir[temp] = changeDir[temp-1]*(-1);
				}
				break;
			}
			else {
				if(gear[temp-1].get(2)!=gear[temp].get(6)) {
					change[temp] = true;
					changeDir[temp] = changeDir[temp-1]*(-1);
					temp++;
				}
				else
					break;
			}
		}
		
	}
	
	

}
