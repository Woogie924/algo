import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader in;
	static BufferedWriter out;

	// 7 세그먼트를 정의한다.
	static int sevenSegment[][] = 
	{
	 //  A, B, C, D, E, F, G
		{1, 1, 1, 1, 1, 1, 0}, // '0'
		{1, 1, 0, 0, 0, 0, 0}, // '1'
		{1, 0, 1, 1, 0, 1, 1}, // '2'
		{1, 1, 1, 0, 0, 1, 1}, // '3'
		{1, 1, 0, 0, 1, 0, 1}, // '4'
		{0, 1, 1, 0, 1, 1, 1}, // '5'
		{0, 1, 1, 1, 1, 1, 1}, // '6'
		{1, 1, 0, 0, 0, 1, 0}, // '7'
		{1, 1, 1, 1, 1, 1, 1}, // '8'
		{1, 1, 1, 0, 1, 1, 1}  // '9'	
	};
	
	static int S; // 길이
	static String N; // LCD 모니터에 나타내야 할 수
	
	static int H, W; // 버퍼의 가로, 세로 크기
	static int buffer[][]; // 디스플레이에 표시할 내용
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer tokenizer = new StringTokenizer(in.readLine());
		S = Integer.parseInt(tokenizer.nextToken());
		N = tokenizer.nextToken();
	
		H = 2 * S + 3;
		W = (S + 2) * N.length() + N.length();
		buffer = new int[H][W];
		
		out.write(solve());
		out.flush();
	}
	
	static String solve()
	{
		int size = N.length();
		for(int i = 0 ; i < size ; i++)
		{
			int startX = 0;
			int startY = ((S + 2) + 1) * i;
			int digit = N.charAt(i) - '0';
			
			mark(startX, startY, digit);
		}
		
		// buffer의 내용을 정답 형태로 변경한다.
		String ret = "";
		for(int i = 0 ; i < H ; i++)
		{
			for(int j = 0 ; j < W ; j++)
			{
				if(buffer[i][j] == 0)
					ret += " ";
				else if(buffer[i][j] == 1)
					ret += "-";
				else
					ret += "|";
			}
			ret += "\n";
		}
	
		return ret;
	}
	
	// 기준 좌표 : (startX, startY)
	// buffer에 표시할 숫자 : digit
	static void mark(int startX, int startY, int digit)
	{
		for(int segment = 'A' ; segment <= 'G' ; segment++)
		{
			// 표시할 필요가 없는 세그먼트인 경우
			if(sevenSegment[digit][segment - 'A'] == 0)
				continue;
			
			// 가로 방향 세그먼트인 경우
			if(segment == 'F' || segment == 'G' || segment == 'C')
			{
				int x = -1, y = -1;
				
				if(segment == 'F')
				{
					x = startX;
					y = startY;
				}
				else if(segment == 'G')
				{
					x = startX + (S + 1);
					y = startY;
				}
				else
				{
					x = startX + (2 * S + 2);
					y = startY;
				}
				
				// buffer에 실제로 표시한다.
				for(int j = y + 1 ; j <= y + S ; j++)
					buffer[x][j] = 1;
			}
			// 세로 방향 세그먼트인 경우
			else
			{
				int x = -1, y = -1;
				
				if(segment == 'A')
				{
					x = startX;
					y = startY + (S + 1);
				}
				else if(segment == 'B')
				{
					x = startX + (S + 1);
					y = startY + (S + 1);
				}
				else if(segment == 'E')
				{
					x = startX;
					y = startY;
				}
				else
				{
					x = startX + (S + 1);
					y = startY;
				}
				
				// buffer에 실제로 표시한다.
				for(int i = x + 1 ; i <= x + S ; i++)
					buffer[i][y] = 2;
			}
		}
	}
}