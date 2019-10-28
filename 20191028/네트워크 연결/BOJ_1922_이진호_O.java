import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1922_이진호_O
{
	static int N,M;
	static int[] p;
	static class Loc implements Comparable<Loc>
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
		@Override
		public int compareTo(Loc o)
		{
			return this.w-o.w;
		}
		
	}
	static PriorityQueue<Loc> pq;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		int y,x,w;
		for(int i = 0 ; i < M ; i++)
		{
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			pq.add(new Loc(y,x,w));
		}
		int result = 0;
		Loc loc;
		makeSet();
		while(!pq.isEmpty())
		{
			loc = pq.poll();
			if(findSet(loc.y)==findSet(loc.x)) continue;
			unionSet(loc.y, loc.x);
			//System.out.println(loc.y +" : "+loc.x);
			result+= loc.w;
		}
		System.out.println(result);
	}
	private static void unionSet(int y, int x)
	{
		y = findSet(y);
		x = findSet(x);
		if(y==x) return ;
		p[x] = y;
	}
	private static int findSet(int x)
	{
		if(p[x]==x) return x;
		
		int parent = findSet(p[x]);
		p[x] = parent;
		return parent;
	}
	private static void makeSet()
	{
		p = new int[N+1];
		for(int i = 1 ; i <= N ; i++)
		{
			p[i] = i;
		}
	}
	
}
