import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int time = Integer.parseInt(br.readLine());
		int red=0, green = 0, blue=0, tempR, tempG, tempB, inputR, inputG, inputB;
		for(int i=0; i<time; i++) {
			st = new StringTokenizer(br.readLine());
			inputR = Integer.parseInt(st.nextToken());
			inputG = Integer.parseInt(st.nextToken());
			inputB = Integer.parseInt(st.nextToken());
			tempR = Math.min(green, blue) + inputR;
			tempG = Math.min(red, blue) + inputG;
			tempB = Math.min(red, green) + inputB;
			red = tempR;
			green = tempG;
			blue = tempB;
		}
		System.out.println(Math.min(Math.min(red, blue), green));
	}
}
