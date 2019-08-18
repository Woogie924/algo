package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15662 {
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
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		gear = new Gear[n+1];
		for (int i = 1; i <= n; i++) {
			int[] input = new int[8];
			String[] inputs = br.readLine().split("");
			for (int j = 0; j < 8; j++) {
				input[j] = Integer.parseInt(inputs[j]);
			}
			gear[i] = new Gear(input[0], input[1], input[2], input[3], input[4], input[5], input[6], input[7]);
		}
		int k = Integer.parseInt(br.readLine());
		for (int commend = 0; commend < k; commend++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			check = new boolean[n+1];
			check[gearNum] = true;
			leftCheck(gearNum);
			rightCheck(gearNum);
			int swit;
			switch (gearNum%2) {
			case 0://톱니바퀴 번호가 짝수
				if (dir == 1) {
					swit=-1;
					for(int i =1; i<=n; i++ ) {
						Rotate(gear[i],check[i],swit);//swit 가 1일때 시계방향 -1일때 반시계
						swit*=-1;
					}
				} else {
					swit=1;
					for(int i =1; i<=n; i++ ) {
						Rotate(gear[i],check[i],swit);//swit 가 1일때 시계방향 -1일때 반시계
						swit*=-1;
					}
				}
				break;
			case 1://톱니바퀴 번호가 홀수
				if (dir == 1) {//톱니바퀴 시계방향
					swit=1;
					for(int i =1; i<=n; i++ ) {
						Rotate(gear[i],check[i],swit);//swit 가 1일때 시계방향 -1일때 반시계
						swit*=-1;
					}
				} else {
					swit=-1;
					for(int i =1; i<=n; i++ ) {
						Rotate(gear[i],check[i],swit);//swit 가 1일때 시계방향 -1일때 반시계
						swit*=-1;
					}
				}
				break;
			}
		}
		int count=0;
		for(int i = 1; i<=n; i++) {
			if(gear[i].twelve==1) {
				count++;
			}
		}
		System.out.println(count);
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
		if (gearNum == n) {
			return;
		}
		if (gear[gearNum].three != gear[gearNum + 1].nine) {
			check[gearNum + 1] = true;
		} else {
			return;
		}
		rightCheck(gearNum + 1);
	}

	private static void Rotate(Gear gear,boolean check, int swit) {
		if(!check) return;
		int temp = 0;
		if(swit==-1) {//반시계
			temp = gear.twelve;
			gear.twelve = gear.one;
			gear.one = gear.three;
			gear.three = gear.five;
			gear.five = gear.six;
			gear.six = gear.seven;
			gear.seven = gear.nine;
			gear.nine = gear.eleven;
			gear.eleven = temp;
		}else if (swit==1) {//시계
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
}
