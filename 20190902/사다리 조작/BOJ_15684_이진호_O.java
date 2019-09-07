import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
	static int N, M, H;
	static int[][] map;// 가로선 집합

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N];// 한 시작점에 대하여 -2가 왼쪽 -1이 오른쪽 가로선을 나타냄
		// 둘다 1부터 시작해서 저런 인덱스가나옴
		int y, x;
		for (int input = 0; input < M; input++)
		{
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			map[y-1][x-1] = 1;
		}
		isok = false;
		int result = -1;
		for (int i = 0; i <= 3; i++)//사다리 놓기 조합 함수
		{
			combination(new int[i+1], 0, 0, i, new boolean[(N * H)+10]);
			if(isok) 
			{
				result = i;
				break;
			}
		}
		System.out.println(result);
	}
	static boolean isok;
	private static void combination(int[] result, int start, int depth, int level, boolean[] visited)
	{
		if (depth == level)
		{
			drawLine(result, level);
			if(check())//N번만큼 진행 i -> i가 되는지확인
			{
				isok = true;
			}
			removeLine(result, level);
			return;
		}
		int y, x;
		for (int i = start; i <= ((N - 1) * (H))-1; i++)
		{
			if (!visited[i])
			{
				y = i / (N - 1);
				x = i % (N - 1);
				if (map[y][x] == 1)
					continue;
				if (x>=1&&map[y][x-1] ==1)
				{
					continue;
				}
				if (x<N-2&&map[y][x+1] ==1)
				{
					continue;
				}
				result[depth] = i;
				visited[i] = true;
				combination(result, i, depth + 1, level, visited);
				if(isok) return;
				visited[i] = false;
			}
		}
	}

	private static boolean check()
	{
		int curX;
		for(int x = 1 ; x <= N ; x++)
		{
			curX = x;
			for(int y = 0 ; y < H ; y++)
			{
				if(curX>=2&&map[y][curX-2]==1)// 왼쪽 길 존재
				{
					curX -=1;
				}
				else if(curX<=N-1&&map[y][curX-1]==1)// 오른쪽 길 존재
				{
					curX +=1;
				}
			}
			if(curX != x) return false;
		}
		return true;
	}

	private static void removeLine(int[] result, int level)
	{
		int y, x;

		for (int i = 0; i < level; i++)
		{
			y = result[i] / (N - 1);
			x = result[i] % (N - 1);
			map[y][x] = 0;
		}
	}

	private static void drawLine(int[] result, int level)
	{
		int y, x;

		for (int i = 0; i < level; i++)
		{
			y = result[i] / (N - 1);
			x = result[i] % (N - 1);
			map[y][x] = 1;
		}
	}

}
