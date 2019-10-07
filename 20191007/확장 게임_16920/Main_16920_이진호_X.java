import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_16920_이진호_X {
	static int[][] map;
	static int N,M,P;
	static int[] S;
	static int[] result;
	static int count;
	static Queue<Loc> q;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		count = N*M;
		
		map = new int[N][M];
		S = new int[P+1];
		result = new int[P+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= P ; i++)
		{
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		q = new LinkedList<>();
		String is;
		char ic;
		for(int y = 0 ; y < N ; y++)
		{
			is = br.readLine();
			for(int x= 0; x < M ; x++)
			{
				ic = is.charAt(x);
				if(ic=='.') map[y][x] =0;
				else if(ic=='#')
				{
					map[y][x] = -1;
					count--;
				}
				else
				{
					map[y][x] = (int)ic-'0';
					q.add(new Loc(y,x,map[y][x]));
					result[map[y][x]]++;
					count--;
				}
			}
		}
		Loc loc;
		int sx,sy,dx,dy;
		
		while(!q.isEmpty())
		{
			if(count<=0) break;
			loc = q.poll();
			sx = loc.x-S[loc.p] <0 ? 0 : loc.x-S[loc.p];
			dx = loc.x+S[loc.p] >=M ? M-1 : loc.x+S[loc.p];
			sy = loc.y-S[loc.p] < 0 ? 0 : loc.y-S[loc.p];
			dy = loc.y+S[loc.p] >=N ? N-1 : loc.y+S[loc.p];
			
			go(loc,sy,dy,sx,dx);
		}
//		System.out.println(Arrays.toString(result));
//		show();
		for(int i = 1 ; i <= P; i++)
		{
			System.out.print(result[i] + " ");
	
		}
	}
	
	private static void go(Loc loc,int sy, int dy, int sx, int dx)
	{
		int y;
		int x = sx;
		int w = 0;
		int wx = dx;
		for(y= loc.y; y>=sy; y--)
		{
			x= sx+w;
			wx = dx-w;
			if(x>=loc.x) 
			{
				x= loc.x;
			}
			if(wx<=loc.x)
			{
				wx = loc.x;
			}
			w++;
			for(; x<=wx; x++)
			{
				if(map[y][x]==0)
				{
					result[loc.p] ++;
					map[y][x] = loc.p;
					count--;
					if((Math.abs(y-loc.y)+Math.abs(x-loc.x))==S[loc.p])
					{
						q.add(new Loc(y,x,loc.p));
					}
				}
			}
			
		}
		w = 0;
		for(y= loc.y; y<=dy; y++)
		{
			x = sx + w;
			wx = dx - w;
			if (x >= loc.x)
			{
				x = loc.x;
			}
			if (wx <= loc.x)
			{
				wx = loc.x;
			}
			w++;
			for(; x<=wx; x++)
			{
				if(map[y][x]==0)
				{
					result[loc.p] ++;
					map[y][x] = loc.p;
					count--;
					if((Math.abs(y-loc.y)+Math.abs(x-loc.x))==S[loc.p])
					{
						q.add(new Loc(y,x,loc.p));
					}
				}
			}
		}
	}

	private static void show() {
		for(int y = 0 ; y < N ; y++)
		{
			for(int x= 0; x < M ; x++)
			{
				System.out.print(map[y][x]+" ");
			}
			System.out.println();
		}
		
	}
	static class Loc
	{
		int y;
		int x;
		int p;
		public Loc(int y, int x, int p) {
			super();
			this.y = y;
			this.x = x;
			this.p = p;
		}
		@Override
		public String toString() {
			return "Loc [y=" + y + ", x=" + x + ", p=" + p + "]";
		}
	}
	static int[] dx = {1,0,-1,0};//동남서북
	static int[] dy = {0,1,0,-1};
	
}
