import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 쉬운계단_현병록 {
	static long[] nums = {0,1,1,1,1,1,1,1,1,1};
	static long temp[], result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		temp = new long[nums.length];
		while(--num>0) {
			System.arraycopy(nums, 0, temp, 0, nums.length);
			nums[0]=temp[1]%1000000000;
			for(int i=1; i<9; i++) {
				nums[i] = temp[i-1]%1000000000 + temp[i+1]%1000000000;
			}
			nums[9]=temp[8]%1000000000;
		}
		for(int i=0; i<10; i++) {
			result+=nums[i]%1000000000;
		}
		System.out.println(result%1000000000);
	}
}
