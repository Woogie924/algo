import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 토마토
{
	static int M, N;
	static int[][] box;
	static Queue<Spoint> q;
	static int nonCount;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		nonCount = 0;
		q = new LinkedList<Spoint>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		visited = new boolean[N][M];
		int temp;
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
			{
				temp = Integer.parseInt(st.nextToken());
				if (temp == 1)
				{
					Spoint p = new Spoint(j, i);
					q.offer(p);
				}
				else if(temp == -1 ) nonCount++;
				box[i][j] = temp;
			}
		}
		System.out.println(bfs());
	}

	static int[] dx =
	{ 1, 0, -1, 0 };// 동남서북
	static int[] dy =
	{ 0, 1, 0, -1 };// 동남서북
	static boolean[][] visited;

	private static int bfs()
	{
		//if(count == N*M) return 0;
		Spoint p;
		int count = 0;
		// for문 한번더 묶어야겠다.
		int day = -2;
		while (!q.isEmpty())//q가 하나도 없을땐 0이나온다.
		{
			int qSize = q.size();
			for (int i = 0; i < qSize; i++)
			{
				p = q.poll();
				if(visited[p.y][p.x]) continue;
				visited[p.y][p.x] = true;
				count++;
				int tx = p.x;
				int ty = p.y;
				for (int j = 0; j < 4; j++,tx=p.x,ty=p.y)
				{
					tx = tx + dx[j];
					ty = ty + dy[j];
					if (tx < 0 || tx >= M || ty < 0 || ty >= N)
						continue;
					if (box[ty][tx] != 0)
						continue;
					q.add(new Spoint(tx, ty));
				}
			}
			day++;
//			System.out.printf("day : %d\n", day);
		}
//		System.out.println("count"+count);
		if (count == N * M - nonCount)
			if(day==-1) return 0;
			else return day;
		else
			return -1;
	}

	static class Spoint
	{
		int x;
		int y;

		public Spoint(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}