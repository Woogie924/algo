package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

public class RepeatPermutation_2331 {
	static ArrayList<Integer> num = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int p = sc.nextInt();

		go(a, p);
	}

	private static void go(int a, int p) {
		if(num.contains(a)) {
			int idx = 0;
			for(int i=0; i<num.size(); ++i) {
				if(num.get(i)==a)
					break;
				idx++;
			}
			System.out.println(idx);
			return;
		}
		num.add(a);
		int sum = 0;
		while (a != 0) {
			sum += Math.pow(a % 10, p);
			a /= 10;
		}
		go(sum, p);
	}

}
