import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 섬의개수
{

	static int W, H;
	static boolean[][] map;
	static int landCount;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new boolean[H][W];
			if (W + H == 0)
				break;
			int tempInput = -1;
			for (int i = 0; i < H; i++)
			{
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++)
				{
					tempInput = Integer.parseInt(st.nextToken());
					if (tempInput > 0)
						map[i][j] = true;
				}
			}
			landCount = 0;
			for (int i = 0; i < H; i++)
			{
				for (int j = 0; j < W; j++)
				{
					if (!map[i][j])
						continue;
					landCount++;
					dfs(i, j);
				}
			}
			System.out.println(landCount);
		}
	}

	private static void dfs(int y, int x)
	{
		for (int i = y - 1; i <= y + 1; i++)
		{
			for (int j = x - 1; j <= x + 1; j++)
			{
				if (i < 0 || i >= H || j < 0 || j >= W)
					continue;
				if (map[i][j])
				{
					map[i][j] = false;
					dfs(i, j);
				}
			}
		}

	}

}
