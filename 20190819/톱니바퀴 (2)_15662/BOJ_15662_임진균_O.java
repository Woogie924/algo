import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader in;
	static BufferedWriter out;

	static int T;
	
	// 12시방향부터 시계방향 순서대로 저장한다.
	static int gears[][]; // 톱니바퀴

	public static void main(String[] args) throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 톱니바퀴의 개수
		T = Integer.parseInt(in.readLine());
		
		gears = new int[T + 1][8];
		
		// 톱니바퀴의 상태를 입력 받는다.
		for(int i = 1 ; i <= T ; i++)
		{
			String temp = in.readLine();
			for(int j = 0 ; j < 8 ; j++)
				gears[i][j] = temp.charAt(j) - '0';
		}
		
		// 회전 횟수 K만큼 톱니바퀴를 회전 시킨다.
		int K = Integer.parseInt(in.readLine());
		boolean visited[] = new boolean[T + 1];
		for(int i = 0 ; i < K ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(tokenizer.nextToken());
			int dir = Integer.parseInt(tokenizer.nextToken());

			visited[num] = true;
			rotate(num, dir, visited);
			visited[num] = false;
		}
	
		int count = 0;
		for(int i = 1 ; i <= T ; i++)
			if(gears[i][0] == 1)
				count++;
		
		out.write(count + "");
		out.flush();
	}
	
	// num번째 톱니바퀴를 dir 방향으로 회전시킨다.
	static void rotate(int num, int dir, boolean visited[])
	{
		// 왼쪽으로 전이
		if(num - 1 > 0 && gears[num][6] != gears[num - 1][2] && !visited[num - 1])
		{
			visited[num - 1] = true;
			rotate(num - 1, -1 * dir, visited);
			visited[num - 1] = false;
		}
		
		// 오른쪽으로 전이
		if(num + 1 <= T && gears[num][2] != gears[num + 1][6] && !visited[num + 1])
		{
			visited[num + 1] = true;
			rotate(num + 1, -1 * dir, visited);
			visited[num + 1] = false;
		}
		
		// 더 이상 전이할 수 없다면 현재 톱니바퀴를 회전시킨다.
		
		// 시계 방향
		if(dir == 1)
		{
			int temp = gears[num][7];
			for(int i = 7 ; i >= 1 ; i--)
				gears[num][i] = gears[num][(i + 7) % 8];
			gears[num][0] = temp;
		}
		// 반시계 방향
		else
		{
			int temp = gears[num][0];
			for(int i = 0 ; i <= 6 ; i++)
				gears[num][i] = gears[num][(i + 1) % 8];
			gears[num][7] = temp;
		}
	}
	
}

