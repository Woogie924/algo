import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 리모컨_현병록{
	static String input;
	static int goal, size, min, sSize;
	static boolean[] buttons;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		sSize = input.length();
		goal = Integer.parseInt(input);
		size= Integer.parseInt(br.readLine());
		buttons = new boolean[10];
		StringTokenizer st = new StringTokenizer("");
		if(size!=0)
			st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			buttons[Integer.parseInt(st.nextToken())]=true;
		}
		min = Math.abs(goal-100);
		permu(0, 0, true);
		System.out.println(min);
	}
	private static void permu(int num, int depth, boolean isFirst) {
		int cal;
		if(!isFirst) {
			if(depth>=sSize-1) {
				min = Math.min(min, Math.abs(num-goal)+depth);
				if(depth==sSize+1) return;
			}
		}
		
		isFirst = false;
		for(int i=0; i<10; i++) {
			if(buttons[i]) continue;
			cal=i*(int)Math.pow(10, depth);
			num += cal;
//			if(num==0) continue;
			permu(num, depth+1, isFirst);
			num-=cal;
		}
	}
}
