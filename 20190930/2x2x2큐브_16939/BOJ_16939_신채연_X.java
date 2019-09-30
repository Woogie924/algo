import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16939_2x2x2큐브_3_X {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(reader.readLine());
		arr = new int[24];
		for(int i=0;i<24;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		//1.좌로회전
		boolean check1 = false;
		if(check(0,1,2,3) && check(4,5,14,15) && 
			check(16,17,6,7) && check(20,21,18,19) &&
			check(12,13,22,23) && check(8,9,10,11))
			check1=true;
		
		//2.좌로회전해서 안되면 우로회전
		boolean check2 = false;
		if(!check1) {
		if(check(0,1,2,3) && check(12,13,6,7) &&
			check(4,5,18,19) && check(16,17,22,23) &&
			check(20,21,14,15) && check(8,9,10,11))
			check2=true;
		}
		
		//3.좌우 둘다 안되면 하로 회전
		boolean check3 = false;
		if(!check1 && !check2) {
		if(check(12,13,14,15) && check(16,17,18,19) &&
			check(0,2,5,7) && check(4,6,9,11) &&
			check(8,10,22,20) && check(23,21,1,3))
			check3=true;
		}
		
		//4.다 안되면 상으로회전
		boolean check4 = false;
		if(!check1 && !check2 && !check3) {
		if(check(12,13,14,15) && check(16,17,18,19) &&
			check(0,2,22,20) && check(4,6,1,3) &&
			check(8,10,5,7) && check(23,21,9,11))
			check4=true;	
		}
		
		int res=-1;
		//하나라도 true면 1 출력
		if(check1 || check2 || check3 || check4)
			//writer.write("1");
			res=1;
		else res=0;//writer.write("0");
		//writer.flush();
		System.out.println(res);
	}

	private static boolean check(int i, int j, int k, int l) {
		if(arr[i]==arr[j] && arr[j]==arr[k] && arr[k]==arr[l]) return true;
		else return false;
	}
}
