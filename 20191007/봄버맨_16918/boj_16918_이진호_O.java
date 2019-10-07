import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R,C,N;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		char ic;//input char
		String is;
		int tx,ty;
		for(int y = 0 ; y < R ; y++)
		{
			is = br.readLine();
			for(int x= 0 ; x < C ; x++)
			{
				ic = is.charAt(x);
				if(ic=='O') map[y][x] = 2;
				else map[y][x] = -1;
			}
		}
		
		for(int t = 1 ; t < N ; t++)
		{
			for(int y = 0 ; y < R ; y++)
			{
				for(int x= 0 ; x < C ; x++)
				{
					if(map[y][x]>=0)map[y][x]--;
				}
			}
//			show();
			if(t%2==1)
			{
				for(int y = 0 ; y < R ; y++)
				{
					for(int x= 0 ; x < C ; x++)
					{
						if(map[y][x]==-1) map[y][x] = 3;
					}
				}
			}
			else
			{
				for(int y = 0 ; y < R ; y++)
				{
					for(int x= 0 ; x < C ; x++)
					{
						if(map[y][x]==0)
						{
							map[y][x] = -5;
							for(int dir = 1 ; dir < 5 ; dir++)
							{
								ty = y+dy[dir];
								tx = x+dx[dir];
								if(ty<0||ty>=R||tx<0||tx>=C) continue;
								if(map[ty][tx]!=0) map[ty][tx] = -5;
							}
						}
					}
				}
				for(int y = 0 ; y < R ; y++)
				{
					for(int x= 0 ; x < C ; x++)
					{
						if(map[y][x]==-5)map[y][x]=-1;
					}
				}
			}

			
			
			
		}
		show();
	}
	private static void show() {
		for(int y = 0 ; y < R ; y++)
		{
			for(int x= 0 ; x < C ; x++)
			{
				if(map[y][x] == -1) System.out.print('.'+"");
				else System.out.print('O'+"");
			}
			System.out.println();
		}
	}
	static int[] dx= {0,1,0,-1,0};//나 동남서북
	static int[] dy = {0,0,1,0,-1};
}
