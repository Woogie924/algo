import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 톱니바퀴_2_15662 {
	static int N,T;
	static int[][] arr;//각톱니바퀴 톱니 값 저장
	static int[] gears;//각톱니바퀴의 12시 방향이 뭔지 저장
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1][8+1];
		gears = new int[N+1];
		for(int tc = 1 ; tc < N+1 ; tc++)
		{
			String input = br.readLine();
			for(int i = 1 ; i < 9 ; i++)
			{
				arr[tc][i] = input.charAt(i-1)-'0';
			}
			gears[tc] = 1;
		}
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int gearNum;
		int ro_dir;
		for(int tc = 0 ; tc < T ; tc++)
		{
			st = new StringTokenizer(br.readLine());
			gearNum = Integer.parseInt(st.nextToken());
			ro_dir = Integer.parseInt(st.nextToken());
			go(0,gearNum,ro_dir);
			go(1,gearNum,ro_dir);
			gears[gearNum] = rotate(ro_dir,gears[gearNum]);
		}
		int count = N;
		for(int i = 1 ; i < N+1 ; i++)
		{
			if(arr[i][gears[i]]==0) count--;
		}
		System.out.println(count);
	}
	private static void go(int dir,int index,int roDir)//dir == 왼쪽 0 오른쪽 1
	{
		if(dir == 0)//왼쪽으로가기
		{
			if(index == 1) return ; // 맨왼쪽
			else if(arr[index][(gears[index]+5)%8+1] != arr[index-1][(gears[index-1]+1)%8+1])
			{
				go(dir,index-1,-roDir);
				gears[index-1] = rotate(-roDir,gears[index-1]);
			}
		}
		if(dir == 1)//오른쪽으로 가기
		{
			if(index == N) return; // 맨오른쪽
			else if(arr[index][(gears[index]+1)%8+1] != arr[index+1][(gears[index+1]+5)%8+1])
			{
				go(dir,index+1,-roDir);
				gears[index+1] = rotate(-roDir,gears[index+1]);
			}
		}
	}
	static int rotate(int dir,int cur)//회전하여 12시의 값을 반환
	{
		if(dir == 1) return (cur-2+8)%8+1;//시계방향
		else if(dir == -1) return (cur+8)%8+1;//반시계방향
		return 1;
	}
}