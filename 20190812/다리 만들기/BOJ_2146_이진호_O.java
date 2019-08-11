import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 다리만들기2146
{

	static int N;
	static int[][] map;
	static int[] dx = { 1, 0, -1, 0 };// 동남서북
	static int[] dy = { 0, 1, 0, -1 };// 동남서북
	static int islandCount;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		islandCount = 2;
		
		for (int i = 0; i < N; i++)// 입력받기
		{
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) // 섬 인덱스 달아주기
		{
			for (int j = 0; j < N; j++)
			{
				if(map[i][j] ==1) bfs(i,j,islandCount++);
			}
		}
		

		min = Integer.MAX_VALUE;//최솟값 초기화

		bfs(); //다리 길이구하는 함수
		
		System.out.println(min);
	}

	static boolean flag = false;
	static int min;

	private static void bfs()
	{
		int ty, tx;
		Queue<location> q = new LinkedList<>();
		
		for (int k = 2; k < islandCount; k++)//섬의 육지를 인덱스 순으로 큐에 저장
		{
			for (int i = 0; i < N; i++)
			{
				for (int j = 0; j < N; j++)
				{
					if (map[i][j] == k)	q.add(new location(j, i));
				}

			}
		}
		int level = -1;
		location tempL;

		while (!q.isEmpty())
		{
			level++;
			int size = q.size();
			for (int i = 0; i < size; i++)
			{
				tempL = q.poll();
				for (int k = 0; k < 4; k++)// 섬이라면
				{
					ty = tempL.y + dy[k];
					tx = tempL.x + dx[k];
					if (ty < 0 || ty >= N || tx < 0 || tx >= N)
						continue;
					if (map[ty][tx] == 0)// 이웃한 육지가있으면 묶는다.
					{
						map[ty][tx] = map[tempL.y][tempL.x];
						q.offer(new location(tx, ty));
						/*
						 * System.out.println("======================"); for (int w = 0; w < N; w++) {
						 * System.out.println(Arrays.toString(map[w])); }
						 * System.out.println("======================");
						 */
						continue;
					}
					if (map[ty][tx] != map[tempL.y][tempL.x])
					{
						int temp = 0;
						if (map[ty][tx] > map[tempL.y][tempL.x])
							temp = level * 2;
						else
							temp = level * 2 + 1;
						if (temp < min)
							min = temp;
					}
				}
			}
			
		}
	}
	private static void bfs(int ty, int tx, int type)
	{
		Queue<location> q = new LinkedList<>();

		location tempL;
		q.add(new location(tx, ty));
		map[ty][tx] = type;
		while (!q.isEmpty())
		{
			tempL = q.poll();
			for (int k = 0; k < 4; k++)// 섬이라면
			{
				ty = tempL.y + dy[k];
				tx = tempL.x + dx[k];
				if (ty < 0 || ty >= N || tx < 0 || tx >= N)
					continue;
				if (map[ty][tx] == 1)
				{
					map[ty][tx] = type;
					q.add(new location(tx,ty));
				}
			}
		}
	}
	static class location
	{
		int y;
		int x;

		public location(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
