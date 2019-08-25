import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pos
{
	int x;
	int y;
	
	public Pos() {}
	public Pos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}


public class Main {

	static int N, M;
	static int office[][] = new int[8][8];
	
	static ArrayList<Pos> cctvList = new ArrayList<Pos>();
	static int rotation[] = {4, 2, 4, 4, 1}; // CCTV가 몇 번 회전해야 원상태로 되는지?
	
	// CCTV가 감시하는 방향
	static int watch[][] = 
	{
	  //북, 동, 남, 서
		{0, 1, 0, 0}, // 1번 
		{0, 1, 0, 1}, // 2번
		{1, 1, 0, 0}, // 3번
		{1, 1, 0, 1}, // 4번
		{1, 1, 1, 1}  // 5번
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
	
		tokenizer = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokenizer.nextToken()); // 세로 크기
		M = Integer.parseInt(tokenizer.nextToken()); // 가로 크기
		
		// 사무실의 상태를 입력 받는다.
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++)
			{
				office[i][j] = Integer.parseInt(tokenizer.nextToken());
				
				if(office[i][j] >= 1 && office[i][j] <= 5)
					cctvList.add(new Pos(i, j));
			}
		}
		
		out.write(solve(new ArrayList<Integer>()) + "");
		out.flush();
	}

	static int solve(ArrayList<Integer> picked)
	{
		int index = picked.size();
		
		if(index == cctvList.size())
			return check(picked);

		int result = Integer.MAX_VALUE;
		int x = cctvList.get(index).x;
		int y = cctvList.get(index).y;
		int type = office[x][y] - 1;
		
		for(int rot = 0 ; rot < rotation[type] ; rot++)
		{
			picked.add(rot);
			result = Math.min(result, solve(picked));
			picked.remove(picked.size() - 1);
		}

		return result;
	}
	
	static int check(ArrayList<Integer> picked)
	{
		// 사무실 상태를 복사한다.
		int officeCopy[][] = new int[N][M];
		for(int x = 0 ; x < N ; x++)
			for(int y = 0 ; y < M ; y++)
				officeCopy[x][y] = office[x][y];
		
		int size = cctvList.size();
		for(int i = 0 ; i < size ; i++)
		{
			int x = cctvList.get(i).x; // 현재 cctv의 x위치
			int y = cctvList.get(i).y; // 현재 cctv의 y위치
			int type = office[x][y] - 1;
			
			// 감시 방향을 복사한다.
			int watchCopy[] = new int[4];
			for(int dir = 0 ; dir < 4 ; dir++)
				watchCopy[dir] = watch[type][dir];
			
			// cctv를 회전 시킨다.
			rotate(watchCopy, picked.get(i));

			// cctv가 감시하는 곳을 표시한다.
			officeCopy[x][y] = -1;
			for(int dir = 0 ; dir < 4 ; dir++)
			{
				if(watchCopy[dir] == 0)
					continue;
				
				switch(dir)
				{
					// 북
					case 0:
						for(int nx = x - 1 ; nx >= 0 && officeCopy[nx][y] != 6 ; nx--)
							officeCopy[nx][y] = -1;
						break;
					// 동
					case 1:
						for(int ny = y + 1 ; ny < M && officeCopy[x][ny] != 6 ; ny++)
							officeCopy[x][ny] = -1;
						break;
					// 남
					case 2:
						for(int nx = x + 1 ; nx < N && officeCopy[nx][y] != 6 ; nx++)
							officeCopy[nx][y] = -1;
						break;
					// 서
					case 3:
						for(int ny = y - 1 ; ny >= 0 && officeCopy[x][ny] != 6 ; ny--)
							officeCopy[x][ny] = -1;
						break;
				}
			}
		}
		
		int count = 0;
		for(int x = 0 ; x < N ; x++)
			for(int y = 0 ; y < M ; y++)
				if(officeCopy[x][y] == 0)
					count++;
		
		return count;
	}
	
	static void rotate(int watch[], int count)
	{
		for(int i = 0 ; i < count ; i++)
		{
			int temp = watch[watch.length - 1];
			for(int j = watch.length - 1 ; j > 0 ; j--)
				watch[j] = watch[j - 1];
			watch[0] = temp;
		}
	}
	
}