import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16948_이진호_O
{
	static int N;
	static int sx,sy,targetX,targetY;
	static int[][] map;
	static class Loc
	{
		int y;
		int x;
		int w;
		public Loc(int y, int x, int w)
		{
			super();
			this.y = y;
			this.x = x;
			this.w = w;
		}
		@Override
		public String toString()
		{
			return "Loc [y=" + y + ", x=" + x + ", w=" + w + "]";
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		targetY = Integer.parseInt(st.nextToken());
		targetX = Integer.parseInt(st.nextToken());
		
		Queue<Loc> q = new LinkedList<boj_16948_이진호_O.Loc>();
		q.add(new Loc(sy,sx,0));
		Loc loc = null;
		int tx,ty;
		
		for(int y = 0 ; y < N ; y++)
		{
			Arrays.fill(map[y], Integer.MAX_VALUE);
		}
		map[sy][sx] = 0;
		while(!q.isEmpty())
		{
			loc = q.poll();
			if(loc.y==targetY&&loc.x==targetX)
			{
				break;
			}
			
			for(int dir = 0 ; dir < 6 ; dir++)
			{
				ty = loc.y+dy[dir];
				tx = loc.x+dx[dir];
				
				if(ty<0||ty>=N||tx<0||tx>=N) continue;
				if(map[ty][tx] > map[loc.y][loc.x]+1)
				{
					map[ty][tx] = map[loc.y][loc.x]+1;
					q.add(new Loc(ty,tx,loc.w+1));
				}
			}
		}
		if(loc.y==targetY&&loc.x==targetX)
		{
			System.out.println(loc.w);
		}
		else 
			System.out.println(-1);
	}
	static int[] dx = {1,2,1,-1,-2,-1};
	static int[] dy = {-2,0,2,2,0,-2};
	
}
