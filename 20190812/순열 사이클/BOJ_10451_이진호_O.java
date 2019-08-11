import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

//순열사이클
public class 순열사이클10451
{
	static int T, N;
	static int[] des;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine()); 
		for (int tc = 0; tc < T; tc++)
		{
			N = Integer.parseInt(br.readLine());
			N += 1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			des = new int[N];
			for (int j = 1; j < N; j++)
			{
				des[j] = Integer.parseInt(st.nextToken());
			}

			visited = new boolean[N];

			count = 0;
			start = -1;
			getCycle(0);
			System.out.println(count);
		}

	}

	static int count;
	static int start;
	private static void getCycle(int index)
	{
		if (index == start)
		{
			count++;
			start = -1;
			return;
		} else if (start != -1)
		{
			visited[index] = true;
			getCycle(des[index]);
		} else
		{
			for (int i = 1; i < N; i++)
			{
				if (!visited[i])
				{
					if (start == -1)
						start = i;
					visited[i] = true;
					getCycle(des[i]);
				}
			}
		}
	}

}