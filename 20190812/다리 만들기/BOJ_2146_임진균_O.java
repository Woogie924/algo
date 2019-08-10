import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	static int N; // 지도의 크기
	static int map[][]; // 지도

	static final int dx[] = {-1, 0, 1, 0};
	static final int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// N 입력
		N = Integer.parseInt(reader.readLine());
		
		// 지도 생성
		map = new int[N][N];
		
		// 지도 정보 입력
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0 ; j < N ; j++)
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
		}

		writer.write(solve() + "");
		writer.flush();
	}

	public static int solve()
	{		
		// 1. 각 섬마다 고유 번호를 할당한다.
		int count = indexAllIslands();
		
		ArrayList<ArrayList<Integer> > coastlines= new ArrayList<ArrayList<Integer>>();
		for(int i = 0 ; i <= count ; i++)
			coastlines.add(new ArrayList<Integer>());
		
		// 2. 각 섬마다 해안가의 위치를 찾는다.
		findCoastlines(coastlines);
		
		// 3. 섬 island의 각 해안가에서 다른 섬으로 향하는 다리를 직접 만들어본다.
		int minLen = Integer.MAX_VALUE;
		for(int island = 1 ; island <= count ; island++)
			minLen = Math.min(minLen, makeBridges(island, coastlines.get(island)));

		return minLen;
	}

	// 각 섬마다 고유 번호를 할당한다.
	// 찾아낸 섬의 개수를 반환한다.
	public static int indexAllIslands()
	{
		boolean discovered[][] = new boolean[N][N];
		int island = 1;
		
		for(int x = 0 ; x < N ; x++)
			for(int y = 0 ; y < N ; y++)
				if(map[x][y] != 0 && !discovered[x][y])
					indexIsland(x, y, island++, discovered);
		
		return (island - 1);
	}
	
	// (startX, startY)를 포함하는 섬 전체에 고유번호 island를 할당한다.
	public static void indexIsland(int startX, int startY, int island, boolean discovered[][])
	{
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(startX);
		q.add(startY);
		discovered[startX][startY] = true;
		map[startX][startY] = island;
		
		while(!q.isEmpty())
		{
			int x = q.poll();
			int y = q.poll();
			
			for(int dir = 0 ; dir < 4 ; dir++)
			{
				int nextX = x + dx[dir];
				int nextY = y + dy[dir];
				
				if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || 
						map[nextX][nextY] == 0 || discovered[nextX][nextY])
					continue;
				
				q.add(nextX);
				q.add(nextY);
				discovered[nextX][nextY] = true;
				map[nextX][nextY] = island;
			}
		}
	}
	
	// 각 섬마다 해안가의 위치를 찾는다.
	public static void findCoastlines(ArrayList<ArrayList<Integer> > coastlines)
	{
		for(int x = 0 ; x < N ; x++)
		{
			for(int y = 0 ; y < N ; y++)
			{
				if(map[x][y] == 0)
					continue;
				
				boolean isCoastline = false;
				
				for(int dir = 0 ; dir < 4 ; dir++)
				{
					int nextX = x + dx[dir];
					int nextY = y + dy[dir];
					
					if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N)
						continue;
					
					if(map[nextX][nextY] == 0)
					{
						isCoastline = true;
						break;
					}
				}
				
				if(!isCoastline)
					continue;
				
				int island = map[x][y];
				coastlines.get(island).add(x);
				coastlines.get(island).add(y);
			}
		}
	}
	
	// island 섬에서 다른 섬을 잇는 다리 중에서 가장 작은 짧은 다리 길이를 반환한다.
	public static int makeBridges(int island, ArrayList<Integer> coastline) 
	{
		int minLen = 0;
		
		boolean discovered[][] = new boolean[N][N];
		Queue<Integer> q = new LinkedList<Integer>();
		
		// 초기 상태 지정
		// island 섬의 모든 해안가가 출발점이 된다.
		int size = coastline.size();
		for (int i = 0; i < size; i += 2)
		{
			int x = coastline.get(i);
			int y = coastline.get(i + 1);
			
			q.add(x);
			q.add(y);
			q.add(0);
			discovered[x][y] = true;
		}
		
		while(!q.isEmpty())
		{
			int x = q.poll();
			int y = q.poll();
			int len = q.poll();
			
			if(map[x][y] != island && map[x][y] > 0)
			{
				minLen = len;
				break;
			}
			
			for(int dir = 0 ; dir < 4 ; dir++)
			{
				int nextX = x + dx[dir];
				int nextY = y + dy[dir];
				
				if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || 
						discovered[nextX][nextY])
					continue;
				
				q.add(nextX);
				q.add(nextY);
				q.add(len + 1);
				discovered[nextX][nextY] = true;
			}
		}
		
		return (minLen - 1);
	}
}
