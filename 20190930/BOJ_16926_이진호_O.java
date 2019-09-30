import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N,M,R;
	static List<range> list = new ArrayList<>();
	static class range
	{
		int sx;
		int dx;
		int sy;
		int dy;
		public range(int sx, int dx, int sy, int dy) {
			super();
			this.sx = sx;
			this.dx = dx;
			this.sy = sy;
			this.dy = dy;
		}
		@Override
		public String toString() {
			return "range [sx=" + sx + ", dx=" + dx + ", sy=" + sy + ", dy=" + dy + "]";
		}
		
	}
	static int[] dx = {1,0,-1,0};
	static int[] dy=  {0,1,0,-1};
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		int x = 1;
		int y = 1;
		while(true)
		{
			list.add(new range(x,M+1-x,y,N+1-y));
			x++;
			y++;
			if(x>(M/2+M%2)||y>(N/2+N%2)) break;
		}
		map = new int[N+1][M+1];
		for(y = 1; y <= N ; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(x= 1; x<= M ; x++)
			{
				map[y][x] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		List<Integer>[] values = new ArrayList[list.size()];
		int v = 0;
		for(int i = 0 ; i < list.size(); i++)
		{
			
			range r = list.get(i);
			values[v] = new ArrayList<>();
			x = r.sx;
			y = r.sy; 
			int dir = 0;
			while(true)
			{
				values[v].add(map[y][x]);
				x += dx[dir];
				y += dy[dir];
				if(x<r.sx||x>r.dx||y<r.sy||y>r.dy)
				{
					x -=dx[dir];
					y -= dy[dir];
					dir++;
   					x +=dx[dir];
					y +=dy[dir];
				}
				if(x==r.sx&&y==r.sy) break;
			}
			v++;
		}
		v = 0;
		int iter;
		for(int i = 0 ; i < list.size(); i++)
		{
			iter = R%values[v].size();
			range r = list.get(i);
			x = r.sx;
			y = r.sy; 
			int dir = 0;
			while(true)
			{
				values[v].add(map[y][x]);
				map[y][x] = values[v].get(iter);
				x += dx[dir];
				y += dy[dir];
				if(x<r.sx||x>r.dx||y<r.sy||y>r.dy)
				{
					x -=dx[dir];
					y -= dy[dir];
					dir++;
   					x +=dx[dir];
					y +=dy[dir];
				}
				iter = (iter+1)%values[v].size();
				if(x==r.sx&&y==r.sy) break;
			}
			v++;
		}
		for(y = 1; y <= N ; y++)
		{
			for(x= 1; x<= M ; x++)
			{
				System.out.print(map[y][x]+" ");
			}
			System.out.println();
		}
	}

}
