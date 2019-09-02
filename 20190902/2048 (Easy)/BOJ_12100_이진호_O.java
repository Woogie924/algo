import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 이공사팔
{
	static int N;
	static int[][] map;
	static int[][] newMap;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		newMap = new int[N][N];
		for (int y = 0; y < N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N];
		permutation(new int[5], 0);
		System.out.println(max);
	}

	private static void permutation(int[] result, int depth)
	{// 다섯가지 방향의 중복가능순열을 고름
		if (depth == 5)
		{
//			System.out.println(Arrays.toString(result));
			move(result);
			return;
		}

		for (int dir = 0; dir < 4; dir++)
		{
			result[depth] = dir;
			permutation(result, depth + 1);
		}
	}
	static int max = Integer.MIN_VALUE;
	private static void move(int[] result)
	{
		// map을 바꿔주면 안되지
		copyMap();
		int[] temp =
		{ 0, 1, 2, 3, 0 };
		for (int i = 0; i < 5; i++)
		{
			moveOnce(result[i]);
//			moveOnce(temp[i]);
			findMax();

		}
//		show();
	}

	private static void findMax()
	{
	
		for (int y = 0; y < N; y++)
		{
			for (int x = 0; x < N; x++)
			{
				if(max<newMap[y][x]) max = newMap[y][x];
			}
		}
	}

	private static void show()
	{
		System.out.println("============================");
		for(int y = 0 ; y < N ; y++)
		{
			System.out.println(Arrays.toString(newMap[y]));
		}
		System.out.println("============================");
	}

	private static void copyMap()
	{
		for (int y = 0; y < N; y++)
		{
			for (int x = 0; x < N; x++)
			{
				newMap[y][x] = map[y][x];
			}
		}
	}
	static int[] dx =
	{ -1, 0, 1, 0 };// 동남서북
	static int[] dy =
	{ 0, -1, 0, 1 };
	static boolean[] visited;

	private static void moveOnce(int dir)
	{
		int st = -1;
		int des = -1;

		switch (dir)
		{
		case 0:// 동쪽
			st = N - 1;
			des = 0;
			break;
		case 1:// 남쪽
			st = N - 1;
			des = 0;
			break;// 서쪽
		case 2:
			st = 0;
			des = N - 1;
			break;
		case 3:
			st = 0;
			des = N - 1;
			break;
		}
		int preIndex;
		if (dir == 0 || dir == 2)
		{
			
			for (int y = 0; y < N; y++)
			{
				int x = st;
				preIndex = st;
				Arrays.fill(visited, false);
				while (true) // x만 바꿔주면된다.
				{
					if (x >= N || x < 0 )
						break;
					if (newMap[y][x] != 0)
					{
						if(x==st) 
						{
							x += dx[dir];
							continue;
						}
						if (x != preIndex)
						{
							if (newMap[y][x] == newMap[y][preIndex])// 옮길위치와 값이 같다면
							{
								if (!visited[preIndex])
								{
									newMap[y][x] = 0;
									newMap[y][preIndex] *= 2;
									visited[preIndex] = true;
								} else
								{
									preIndex += dx[dir];
									newMap[y][preIndex] = newMap[y][x];
									newMap[y][x] = 0;
								}
							} else if(newMap[y][preIndex] == 0)
							{
								newMap[y][preIndex] = newMap[y][x];
								newMap[y][x] = 0;
							}
							else if(x == preIndex + dx[dir])
							{
								preIndex += dx[dir];
							}
							else
							{
								preIndex += dx[dir];
								newMap[y][preIndex] = newMap[y][x];
								newMap[y][x] = 0;
							}
						}
						else
						{
							preIndex += dx[dir];
						}
					}
					x += dx[dir];
				}
			}
		}
		else if (dir == 1 || dir == 3)
		{
			for (int x = 0; x < N; x++)
			{
				int y = st;
				preIndex = st;
				Arrays.fill(visited, false);
				while (true) // x만 바꿔주면된다.
				{
					if (y >= N || y < 0 )
						break;
					if (newMap[y][x] != 0)
					{
						if(y==st) 
						{
							y += dy[dir];
							continue;
						}
						if (y != preIndex)
						{
							if (newMap[y][x] == newMap[preIndex][x])// 옮길위치와 값이 같다면
							{
								if (!visited[preIndex])
								{
									newMap[y][x] = 0;
									newMap[preIndex][x] *= 2;
									visited[preIndex] = true;
								} else
								{
									preIndex += dy[dir];
									newMap[preIndex][x] = newMap[y][x];
									newMap[y][x] = 0;
								}
							} else if(newMap[preIndex][x] == 0)
							{
								newMap[preIndex][x] = newMap[y][x];
								newMap[y][x] = 0;
							}
							else if(y == preIndex + dy[dir])
							{
								preIndex += dy[dir];
							}
							else 
							{
								preIndex += dy[dir];
								newMap[preIndex][x] = newMap[y][x];
								newMap[y][x] = 0;
							}
						}
						else
						{
							preIndex += dy[dir];
						}
					}
					y += dy[dir];
				}
			}
		}
	}
}
