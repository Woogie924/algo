import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Shark
{
	int r, c; // 위치
	int s; // 속력
	int d; // 방향
	int z; // 크기
	char n; // 이름
	
	public Shark() {}
	public Shark(int r, int c, int s, int d, int z, char n)
	{
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
		this.n = n;
	}
}

class Main {
	static BufferedReader in;
	static BufferedWriter out;

	static int R, C, M;

	static ArrayList<Shark> list = new ArrayList<Shark>();
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 가로 세로 크기 및 상어의 수 입력
		tokenizer = new StringTokenizer(in.readLine());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		
		// 상어의 정보 입력
		for(int i = 0 ; i < M ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
		
			int r, c, s, d, z;
			r = Integer.parseInt(tokenizer.nextToken());
			c = Integer.parseInt(tokenizer.nextToken());
			s = Integer.parseInt(tokenizer.nextToken()); // 속력
			d = Integer.parseInt(tokenizer.nextToken()); // 방향
			z = Integer.parseInt(tokenizer.nextToken()); // 크기
	
			list.add(new Shark(r, c, s, d, z, (char)('A' + i)));
		}
	
		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		int sum = 0; // 잡은 상어 크기의 합
		
		for(int man = 1 ; man <= C; man++)
		{
			// 땅과 제일 가까운 상어를 잡는다.
			sum += fish(man);
			
			// 상어가 이동한다.
			moveAllSharks();
		}

		return sum;
	}
	
	static int fish(int man)
	{
		int idx = -1;
		int minR = 100 + 1;
		
		int size = list.size();
		for(int i = 0 ; i < size ; i++)
		{
			Shark shark = list.get(i);
	
			if(shark.c != man)
				continue;
			
			if(shark.r < minR)
			{
				minR = shark.r;
				idx = i;
			}
		}
		
		return (idx != -1 ? list.remove(idx).z : 0); 
	}
	
	static void moveAllSharks()
	{
		// 모든 상어를 이동시킨다.
		int size = list.size();
		for(int i = 0 ; i < size ; i++)
		{
			Shark shark = list.get(i);
			int r = shark.r;
			int c = shark.c;
			int s = shark.s;
			int d = shark.d;
			
			switch(d)
			{
				// 위쪽
				case 1:
				{
					s = s % (2 * R - 2);
					
					if(s >= 0 && s < r)
						r = r - s;
					else if(s >= r && s < r + R - 1)
					{
						r = s - r + 2;
						d = 2;
					}
					else
						r = 2 * R - s + r - 2;
						
					shark.d = d;
					shark.r = r;
					break;
				}
				// 아래쪽
				case 2:
				{
					s = s % (2 * R - 2);
					
					if(s >= 0 && s < R - r + 1)
						r = r + s;
					else if(s >= R - r + 1 && s < 2 * R - r)
					{
						r = 2 * R - s - r;
						d = 1;
					}
					else
						r = s - 2 * R + r + 2;
						
					shark.d = d;
					shark.r = r;
					break;
				}
				// 오른쪽
				case 3:
				{
					s = s % (2 * C - 2);
					
					if(s >= 0 && s < C - c + 1)
						c = c + s;
					else if(s >= C - c + 1 && s < 2 * C - c)
					{
						c = 2 * C - s - c;
						d = 4;
					}
					else
						c = s - 2 * C + c + 2;
						
					shark.d = d;
					shark.c = c;
					break;
				}
				// 왼쪽
				case 4:
				{
					s = s % (2 * C - 2);
					
					if(s >= 0 && s < c)
						c = c - s;
					else if(s >= c && s < c + C - 1)
					{
						c = s - c + 2;
						d = 3;
					}
					else
						c = 2 * C - s + c - 2;
						
					shark.d = d;
					shark.c = c;
					break;
				}	
			}
		}
		
		Shark map[][] = new Shark[100 + 1][100 + 1];
		
		// 각 칸에는 크기가 가장 큰 상어만 존재할 수 있다.
		for(int i = 0 ; i < size ; i++)
		{
			Shark shark = list.get(i);
			
			// 해당 칸이 비었거나 현재 상어가 해당 칸에 있는 상어보다 더 크면 새로 갱신한다.
			if(map[shark.r][shark.c] == null || 
					map[shark.r][shark.c].z < shark.z)
				map[shark.r][shark.c] = shark;
		}
				
		// 리스트를 갱신한다.
		list.clear();
		for(int x = 1 ; x <= R ; x++)
			for(int y = 1 ; y <= C ; y++)
				if(map[x][y] != null)
					list.add(map[x][y]);
	}

}