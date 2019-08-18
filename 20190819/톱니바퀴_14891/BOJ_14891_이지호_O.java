package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14891 {
	public static class Gear {
		int twelve;
		int one;
		int three;
		int five;
		int six;
		int seven;
		int nine;
		int eleven;
		public Gear(int twelve, int one, int three, int five, int six, int seven, int nine, int eleven) {
			this.twelve = twelve;
			this.one = one;
			this.three = three;
			this.five = five;
			this.six = six;
			this.seven = seven;
			this.nine = nine;
			this.eleven = eleven;
		}
	}

	static Gear[] gear;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new Gear[5];
		for (int i = 1; i <= 4; i++) {
			int[] input = new int[8];
			String[] inputs = br.readLine().split("");
			for (int j = 0; j < 8; j++) {
				input[j] = Integer.parseInt(inputs[j]);
			}
			gear[i] = new Gear(input[0], input[1], input[2], input[3], input[4], input[5], input[6], input[7]);
		}
		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			check = new boolean[5];
			check[gearNum] = true;
			leftCheck(gearNum);
			rightCheck(gearNum);
			switch (gearNum) {
			case 1:
				if (dir == 1) {
					Forward(gear[1],check[1]);
					Behind(gear[2],check[2]);
					Forward(gear[3],check[3]);
					Behind(gear[4],check[4]);
				} else {
					Behind(gear[1],check[1]);
					Forward(gear[2],check[2]);
					Behind(gear[3],check[3]);
					Forward(gear[4],check[4]);
				}
				break;
			case 2:
				if (dir == 1) {
					Forward(gear[2],check[2]);
					Behind(gear[1],check[1]);
					Behind(gear[3],check[3]);
					Forward(gear[4],check[4]);
				} else {
					Behind(gear[2],check[2]);
					Forward(gear[1],check[1]);
					Forward(gear[3],check[3]);
					Behind(gear[4],check[4]);
				}
				break;
			case 3:
				if (dir == 1) {
					Forward(gear[3],check[3]);
					Behind(gear[2],check[2]);
					Behind(gear[4],check[4]);
					Forward(gear[1],check[1]);
				} else {
					Behind(gear[3],check[3]);
					Forward(gear[2],check[2]);
					Forward(gear[4],check[4]);
					Behind(gear[1],check[1]);
				}
				break;
			case 4:
				if (dir == 1) {
					Forward(gear[4],check[4]);
					Behind(gear[3],check[3]);
					Forward(gear[2],check[2]);
					Behind(gear[1],check[1]);
				} else {
					Behind(gear[4],check[4]);
					Forward(gear[3],check[3]);
					Behind(gear[2],check[2]);
					Forward(gear[1],check[1]);
				}
				break;
			}
		}
		int sum = 0; int score=1;
		for(int i = 1; i<=4; i++) {
			if(gear[i].twelve==1) {
				sum=sum+score;
			}
			score*=2;
		}
		System.out.println(sum);
	}

	static boolean[] check;

	private static void leftCheck(int gearNum) {
		if (gearNum ==1) {
			return;
		}
		if (gear[gearNum].nine != gear[gearNum - 1].three) {
			check[gearNum - 1] = true;
		} else {
			return;
		}
		leftCheck(gearNum - 1);
	}

	private static void rightCheck(int gearNum) {
		if (gearNum == 4) {
			return;
		}
		if (gear[gearNum].three != gear[gearNum + 1].nine) {
			check[gearNum + 1] = true;
		} else {
			return;
		}

		rightCheck(gearNum + 1);
	}

	private static void Behind(Gear gear,boolean check) {
		if(!check) return;
		int temp = 0;
		temp = gear.twelve;
		gear.twelve = gear.one;
		gear.one = gear.three;
		gear.three = gear.five;
		gear.five = gear.six;
		gear.six = gear.seven;
		gear.seven = gear.nine;
		gear.nine = gear.eleven;
		gear.eleven = temp;
	}

	private static void Forward(Gear gear, boolean check) {
		if(!check) return;
		int temp = 0;
		temp = gear.twelve;
		gear.twelve = gear.eleven;
		gear.eleven = gear.nine;
		gear.nine = gear.seven;
		gear.seven = gear.six;
		gear.six = gear.five;
		gear.five = gear.three;
		gear.three = gear.one;
		gear.one = temp;
	}
}
