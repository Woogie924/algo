import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main
{

	static int TargetX, TargetY, TargetV;
	static int R, C;
	static int[][] map;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		TargetY = Integer.parseInt(st.nextToken());
		TargetX = Integer.parseInt(st.nextToken());
		TargetV = Integer.parseInt(st.nextToken());

		R = 3;
		C = 3;
		int minTime = -1;
		map = new int[200 + 2][200 + 2];// rc가 최대 100이면 전부하나씩 있어도 이백개

		for (int i = 0; i < R; i++)
		{
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int tc = 0; tc <= 100; tc++)// 최소시간은 100을 넘어가지 않는다
		{
//			rowFunc();
			if(TargetY-1 < R && TargetX-1 < C) {
			if(map[TargetY-1][TargetX-1]==TargetV) 
			{
				minTime = tc;
				break;
			}}
			if (C <= R)
				rowFunc();
			else
				colomnFunc();
			
			/*System.out.println("============================");
			for (int a = 0; a < R; a++)
			{
				for (int b = 0; b < C; b++)
				{
					System.out.print(map[a][b] + "\t");
				}
				System.out.println();
			}
			System.out.println("============================");*/
		}
		System.out.println(minTime);
	}

	private static void rowFunc()// 바꿀때 0으로 청소필요
	{
		List<VC> list = new ArrayList<>();
		int next = 0;
		int maxSize = C;
		for (int i = 0; i < R; i++)
		{
			list.clear();
			c: for (int j = 0; j < C; j++)
			{
				next = map[i][j];
				if (next == 0)
					continue;
				for (VC vc : list)
				{
					if (next == vc.value)
					{
						vc.count++;
						continue c;
					}
				}
				list.add(new VC(next));
			}
			list.sort(new Comparator<VC>()
			{
				public int compare(VC o1, VC o2)
				{
					if (o1.count > o2.count)
						return 1;
					else if (o1.count == o2.count)
					{
						if (o1.value > o2.value)
							return 1;
					}
					return -1;

				}
			});
			for (int j = 0; j < C; j++)// 배열초기화
			{
				map[i][j] = 0;
			}
			for (int j = 0; j < list.size(); j++)
			{
				map[i][2 * j] = list.get(j).value;
				map[i][2 * j + 1] = list.get(j).count;
			}
			if (maxSize < (list.size() * 2))
				maxSize = (list.size() * 2);
		}
		C = maxSize;
	}

	private static void colomnFunc()
	{
		List<VC> list = new ArrayList<>();
		int next = 0;
		int maxSize = R;
		for (int i = 0; i < C; i++)
		{
			list.clear();
			c: for (int j = 0; j < R; j++)
			{
				next = map[j][i];
				if (next == 0)
					continue;
				for (VC vc : list)
				{
					if (next == vc.value)
					{
						vc.count++;
						continue c;
					}
				}
				list.add(new VC(next));
			}
			list.sort(new Comparator<VC>()
			{
				public int compare(VC o1, VC o2)
				{
					if (o1.count > o2.count)
						return 1;
					else if (o1.count == o2.count)
					{
						if (o1.value > o2.value)
							return 1;
					}
					return -1;

				}
			});

			for (int j = 0; j < R; j++)
			{
				map[j][i] = 0;
			}
			for (int j = 0; j < list.size(); j++)
			{
				map[2 * j][i] = list.get(j).value;
				map[2 * j + 1][i] = list.get(j).count;
			}
			if (maxSize < (list.size() * 2))
				maxSize = (list.size() * 2);
			
		}
		
		R = maxSize;
	}

	static class VC// value count
	{
		int value;
		int count;

		public VC(int value)
		{
			this.value = value;
			count = 1;
		}
	}

}
