import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	static int N;
	static int map[][];
	static boolean discovered[][];
	
	static final int dx[] = {-1, 0, 1, 0};
	static final int dy[] = {0, 1, 0, -1};
	
	static int complex; // 총 단지수
	static ArrayList<Integer> houses = new ArrayList<Integer>(); // 각 단지내의 집의 수
	
	public static void main(String[] args) throws IOException
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 지도의 크기 N 입력
		N = Integer.parseInt(reader.readLine());
		
		// 지도 생성
		map = new int[N][N];
		
		// 지도 정보 입력
		for(int i = 0 ; i < N ; i++)
		{
			String temp = reader.readLine();
			for(int j = 0 ; j < N ; j++)
				map[i][j] = temp.charAt(j) - '0';
		}
		
		// discovered 배열 생성 및 초기화
		discovered = new boolean[N][N];
		
		solve();
		
		writer.write(complex + "\n");
		for(int i = 0 ; i < houses.size() ; i++)
			writer.write(houses.get(i) + "\n");
		writer.flush();
		
	}

	public static void solve() 
	{
		for(int x = 0 ; x < N ; x++)
		{
			for(int y = 0 ; y < N ; y++)
			{
				if(map[x][y] == 1 && !discovered[x][y])
				{
					complex++;
					int house = bfs(x, y);
					houses.add(house);
				}
			}
		}
		
		// 각 단지내 집의 수를 오름차순으로 정렬한다.
		houses.sort(null);
	}
	
	// 한 단지를 이루는 집의 개수를 반환한다.
	public static int bfs(int startX, int startY)
	{
		int house = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(startX);
		q.add(startY);
		discovered[startX][startY] = true;
		
		while(!q.isEmpty())
		{
			int x = q.poll();
			int y = q.poll();
			house++;
			
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
			}
		}

		return house;
	}
}
