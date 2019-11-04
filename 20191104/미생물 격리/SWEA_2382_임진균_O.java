import java.io.*;
import java.util.*;

public class Solution {
	
	static class Group
	{
		int count;
		int dir;
		
		public Group() {}
		public Group(int count, int dir) 
		{
			this.count = count;
			this.dir = dir;
		}
	}
	
	static int N, M, K;
	static ArrayList<Group> map[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int tc = 0 ; tc < T ; tc++)
		{
			tokens = new StringTokenizer(in.readLine());
			N = Integer.parseInt(tokens.nextToken()); // 셀의 개수
			M = Integer.parseInt(tokens.nextToken()); // 격리 시간
			K = Integer.parseInt(tokens.nextToken()); // 미생물 군집의 개수
			
			map = new ArrayList[N][N];
			for(int i = 0 ; i < N ; i++)
				for(int j = 0 ; j < N ; j++)
					map[i][j] = new ArrayList<Group>();
			
			for(int i = 0 ; i < K ; i++)
			{
				tokens = new StringTokenizer(in.readLine()); 
				int x = Integer.parseInt(tokens.nextToken());
				int y = Integer.parseInt(tokens.nextToken());
				int count = Integer.parseInt(tokens.nextToken());
				int dir = Integer.parseInt(tokens.nextToken());
				
				if(dir == 1)
					dir = 0;
				else if(dir == 4)
					dir = 1;
				
				map[x][y].add(new Group(count, dir));
			}
			
			out.write("#" + (tc + 1) + " " + solve() + "\n");
		}
		
		out.flush();
	}

	static int solve()
	{
		ArrayList<Group> temp[][] = new ArrayList[N][N];
		for(int i = 0 ; i < N ; i++)
			for(int j = 0 ; j < N ; j++)
				temp[i][j] = new ArrayList<Group>();
		
		for(int t = 0 ; t < M ; t++)
		{
			// 1. 각 군집들은 이동 방향으로 1칸 움직인다.
			for(int x = 0 ; x < N ; x++)
			{
				for(int y = 0 ; y < N ; y++)
				{
					if(map[x][y].isEmpty())
						continue;
					
					Group g = map[x][y].remove(0);
					int nx = x + dx[g.dir];
					int ny = y + dy[g.dir];
					
					temp[nx][ny].add(g);
				}
			}
			
			// 2. 약품이 칠해진 셀에 도착한 미생물 그룹을 적절히 처리한다.
			for(int x = 0 ; x < N ; x++)
			{
				for(int y = 0 ; y < N ; y++)
				{
					if((x > 0 && x < N - 1 && y > 0 && y < N - 1) || temp[x][y].isEmpty())
						continue;
					
					Group g = temp[x][y].get(0);
					g.count /= 2;
					g.dir = (g.dir + 2) % 4;
					
					if(g.count == 0)
						temp[x][y].remove(0);
				}
			}
			
			// 3. 두 개 이상의 군집이 모여있는 경우를 처리한다.
			for(int x = 0 ; x < N ; x++)
			{
				for(int y = 0 ; y < N ; y++)
				{
					if(temp[x][y].isEmpty())
						continue;
					
					Group biggest = new Group(-1, -1); // 가장 큰 미생물 그룹
					Group merge = new Group(); // 합쳐진 그룹
					for(int i = 0 ; i < temp[x][y].size() ; i++)
					{
						Group g = temp[x][y].get(i);
						
						if(g.count > biggest.count)
						{
							biggest.count = g.count;
							biggest.dir = g.dir;
						}
						
						merge.count += g.count;
					}
					
					merge.dir = biggest.dir;
					temp[x][y].clear();
					map[x][y].add(merge);
				}
			}
		}
		
		// 남아 있는 미생물의 수를 계산한다.
		int result = 0;
		for(int x = 0 ; x < N ; x++)
			for(int y = 0 ; y < N ; y++)
				if(!map[x][y].isEmpty())
					result += (map[x][y].get(0).count);
		
		return result;
	}
}