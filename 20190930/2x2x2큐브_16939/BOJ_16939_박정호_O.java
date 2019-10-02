import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class BOJ_2x2x2큐브_16939_박정호_O {
	static int[] cube = new int[25];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		for(int i=1; i<25; ++i) {
			cube[i] = Integer.parseInt(s[i-1]);
		}
		int answer = 0;
		// 세 면만 돌려보면 됨!
		// 1번면
		// 오른쪽
		if(cube[22]==cube[20] && cube[21]==cube[19] && cube[18]==cube[8] && cube[17]==cube[7] && cube[6]==cube[16] && cube[5] == cube[15])
			answer = 1;
		else if(cube[22]==cube[16] && cube[21]==cube[15] && cube[18]==cube[24] && cube[17]==cube[23] && cube[6]==cube[20] && cube[5] == cube[19])
			answer = 1;
		// 2번면
		else if(cube[3]==cube[18] && cube[4]==cube[20] && cube[17]==cube[12] && cube[19]==cube[11] && cube[10]==cube[15] && cube[9] == cube[13])
			answer = 1;
		else if(cube[3]==cube[15] && cube[4]==cube[13] && cube[17]==cube[1] && cube[19]==cube[2] && cube[10]==cube[18] && cube[9] == cube[20])
			answer = 1;
		// 3번면
		else if(cube[4]==cube[22] && cube[2]==cube[24] && cube[21]==cube[11] && cube[23]==cube[9] && cube[12]==cube[7] && cube[10] == cube[5])
			answer = 1;
		else if(cube[4]==cube[7] && cube[2]==cube[5] && cube[21]==cube[3] && cube[23]==cube[1] && cube[12]==cube[22] && cube[10] == cube[24])
			answer = 1;
		System.out.println(answer);
	}
}
