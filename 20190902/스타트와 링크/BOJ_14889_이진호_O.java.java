import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스타트와링크
{
	static int N ;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for(int y = 0 ; y < N ; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x= 0 ; x < N ; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}//입력완료
		home = new int[N/2];
		away = new int[N/2];
		min = Integer.MAX_VALUE;
		combination(0,0,new boolean[N]);
		System.out.println(min);
	}
	static int min;
	private static void combination( int start, int depth, boolean[]  visited)
	{
		int temp;
		if(depth == N/2)
		{
			temp = getStatsDiff(visited);
			if(min > temp) min = temp;
			return;
		}
		for(int i = start ; i < N ; i++)
		{
			if(!visited[i])
			{
				visited[i] = true;
				combination(i, depth+1, visited);
				visited[i] = false;
			}
		}
	}
	static int[] away;
	static int[] home;
	private static int getStatsDiff(boolean[]  visited)
	{
		int aidx = 0;
		int hidx = 0;
		for(int i = 0 ; i < N ; i++)
		{
			if(visited[i]) home[hidx++] = i;
			else away[aidx++] = i;
		}
		int astats = 0;
		int hstats = 0;
		
		for(int sp = 0 ; sp < N/2 ; sp++)//start player
		{
			for(int dp= 0 ; dp < N/2 ; dp++)// destination player
			{
				astats += map[away[sp]][away[dp]];
				hstats += map[home[sp]][home[dp]];
			}
		}
		return Math.abs(astats-hstats);
	}
	
	
}
