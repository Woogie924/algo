import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 단지번호붙이기
{

	static int N;
	static boolean[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		//StringTokenizer st;
		String temp;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine();
			for (int j = 0; j < N; j++)
			{
				
				if (temp.charAt(j)-'0' > 0)
					map[i][j] = true;
			}
		}

		List<Integer> partition = new ArrayList<>();
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if(!map[i][j]) continue;
				partition.add(dfs(i, j,1));

			}
		}
		
		partition.sort(new Comparator<Integer>()
		{

			@Override
			public int compare(Integer o1, Integer o2)
			{
				// TODO Auto-generated method stub
				return o1-o2;
			}
		});
		
		System.out.println(partition.size());
		for(int i : partition)
		{
			System.out.println(i);
		}
	}

	static int[] dx =
	{ 1, 0, -1, 0 };// 동남서북
	static int[] dy =
	{ 0, 1, 0, -1 };// 동남서북

	private static int dfs(int y, int x,int depth)
	{
		int sum = 0;
		int tx,ty;
		map[y][x] = false;
		for (int i = 0; i < 4; i++)
		{
			ty = y+dy[i];
			tx = x+dx[i];
			if(ty<0||ty>=N||tx<0||tx>=N) continue;
			if(map[ty][tx])
			{
				map[ty][tx] = false;
				sum += dfs(ty, tx,depth+1);
			}
			
		}
		return sum+1;
	}

}
