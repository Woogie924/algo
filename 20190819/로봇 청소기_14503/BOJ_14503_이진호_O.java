import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 로봇청소기_14503
{
	static int M, N, R, C, D;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st= new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		
		
		for(int i = 0 ; i < N ; i++)
		{
			st= new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int rotateCount = 0;
		int cleanCount = 0;
		while (true)
		{
			if (rotateCount == 4)//이변수가 4라는건 네번돌면서 아무데도 못갓다는뜻
			{
				if (isFlag(R, C, (D + 2) % 4,1))// 뒤에 벽이 있으면
				{
					break;
				} else
				{
					go(R,C,(D + 2) % 4);//후진
				}
				rotateCount = 0;
			}
			if (map[R][C] == 0)
			{
				map[R][C] = cleanCount+2; // 청소
				cleanCount++;
				/*System.out.println("+======================+");
				for(int i = 0 ; i < N ; i++)
				{
					for(int j = 0 ; j < M ; j++)
					{
						System.out.print(map[i][j]+"\t");
					}
					System.out.println();
				}
				System.out.println("+======================+");*/
			}
			
			rotateCount++;
			D = (D + 3) % 4;// 왼쪽 방향
			
			if (isFlag(R, C, D,0))// 왼쪽이 빈경우
			{
				go(R,C,D);
				rotateCount = 0;
			} else // 안빈경우
			{
			}

		}
		System.out.println(cleanCount);
	}

	static int[] dy =
	{ -1, 0, 1, 0 };
	static int[] dx =
	{ 0, 1, 0, -1 };

	static boolean isFlag(int y, int x, int d,int flag)// 왼쪽이 비었느지안비엇는지 확인
	{
//		d = (d + 3) % 4;
		int tx = x + dx[d];
		int ty = y + dy[d];
		if (tx < 0 || tx >= M || ty < 0 || ty >= N)
			return false;
		if (map[ty][tx] == flag)
			return true;
		else
			return false;
	}

	static boolean go(int y, int x, int d)
	{
		int tx = x + dx[d];
		int ty = y + dy[d];
		if (tx < 0 || tx >= M || ty < 0 || ty >= N)
			return false;
		else
		{
			R = ty;
			C = tx;
			return true;
		}
	}
}