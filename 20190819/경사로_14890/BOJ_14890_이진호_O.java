import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 경사로14890
{

	static int L, N;
	static int[][] map;
	static int cur, count, least, result;// 현재값 , 현재값 갯수, 필요한 다음 작은값, 길갯수

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = 0;
		goHorizon();
		goVertical();
		
		System.out.println(result);
	}

	static void goVertical()
	{
		int next;
		start: for (int i = 0; i < N; i++)// 세로로 딱딱
		{
			cur = map[0][i];
			count = 1;
			least = -1;
			for (int j = 1; j < N; j++)
			{
				next = map[j][i];
				if(least == 0) 
				{
					count =0;
					least --;
				}
				if (Math.abs(cur - next) > 1)
					continue start;
				else//같거나 한칸차이나거나
				{
					if (cur == next)
					{
						count++;
						least--;
					}
					else
					{
						if(least > 0 )
						{
							continue start;
						}
						else if(cur < next)
						{
							if(count<L)//다음 칸에 경사로를 못넣는경우
							{
								continue start;
							}
							else
							{
								cur = next;
								count = 1;
							}
						}
						else if (cur > next)
						{
							cur = next;
							least = L-1;//
						}
					}
				}
				
			}
			if(least>0) continue start;
			result++;// 성공한경우
		}
	}

	static void goHorizon()
	{
		int next;
		start: for (int i = 0; i < N; i++)// 세로로 딱딱
		{
			cur = map[i][0];
			count = 1;
			least = -1;
			for (int j = 1; j < N; j++)
			{
				next = map[i][j];
				if(least == 0) 
				{
					count =0;
					least --;
				}
				if (Math.abs(cur - next) > 1)
					continue start;
				else//같거나 한칸차이나거나
				{
					if (cur == next)
					{
						count++;
						least--;
					}
					else
					{
						if(least > 0 )
						{
							continue start;
						}
						else if(cur < next)
						{
							if(count<L)//다음 칸에 경사로를 못넣는경우
							{
								continue start;
							}
							else
							{
								cur = next;
								count = 1;
							}
						}
						else if (cur > next)
						{
							cur = next;
							least = L-1;//
						}
					}
				}
				
			}
			if(least>0) continue start;
			result++;// 성공한경우
		}
	}
}
