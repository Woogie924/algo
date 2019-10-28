import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1194_이진호_X_메모리초과
{
	static int N,M;
	static char[][] map;
	static boolean[][] visited;
	static List<Loc> list;
	static List<Pair> pairs;
	static List<boolean[]> keyList;
	static class Pair
	{
		int y;
		int x;
		char c;
		public Pair(int y, int x, char c)
		{
			super();
			this.y = y;
			this.x = x;
			this.c = c;
		}
		
	}
	static class Loc
	{
		int y;
		int x;
		int dist;
		public Loc(int y, int x,int dist)
		{
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
		@Override
		public String toString()
		{
			return "Loc [y=" + y + ", x=" + x + ", dist=" + dist + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		pairs = new ArrayList<>();//a A 좌표 저장용
		list = new ArrayList<boj_1194_이진호_X_메모리초과.Loc>();
		String is;
		char ic;
		for(int y = 0 ; y < N ; y++)
		{
			is = br.readLine();
			for(int x = 0 ; x < M ; x++)
			{
				ic = is.charAt(x);
				if(ic=='0')
				{
					list.add(new Loc(y,x,0));
					continue;
				}
				else if(ic>='a'&&ic<='f')
				{
					pairs.add(new Pair(y,x,ic));
				}
				else if(ic>='A'&&ic<='F')
				{
					pairs.add(new Pair(y,x,ic));
				}
				map[y][x] = is.charAt(x);
			}
		}
		Queue<Loc> q = new LinkedList<>();
		keyList = new ArrayList<>();
		keyList.add(new boolean[6]);
		Loc loc;
		int tx,ty;
		char tc;
		boolean[] keyset;
		while(!list.isEmpty())
		{
			q.add(list.get(list.size()-1));
			list.remove(list.size()-1);
			visited = new boolean[N][M];
			loc = q.peek();
			keyset = keyList.get(keyList.size()-1);
			keyList.remove(keyList.size()-1);
			
			changeMap(keyset);
			visited[loc.y][loc.x] = true;
			while(!q.isEmpty())
			{
				loc = q.poll();
				for(int dir= 0; dir< 4 ; dir++)
				{
					ty = loc.y+dy[dir];
					tx = loc.x+dx[dir];
					if(ty<0||ty>=N||tx<0||tx>=M) continue;
					if(visited[ty][tx]) continue;
					visited[ty][tx] = true;
					tc = map[ty][tx];
					
					if(tc=='#') continue;//벽
					if(tc>='A'&&tc<='F') continue;//문만남
					
					Loc tl = new Loc(ty,tx,loc.dist+1);
					if(tc>='a'&&tc<='f')//열쇠만남
					{
						boolean[] tbarr = new boolean[6];
						for(int i = 0 ; i < 6 ; i++)
						{
							tbarr[i] = keyset[i];
						}
						tbarr[tc-'a']= true;
						list.add(tl);
						keyList.add(tbarr);
						continue;
					}
					else if(tc=='1')//통과
					{
						min = min > loc.dist+1 ? loc.dist+1: min;
						continue;
					}
					q.add(tl);
					//가는 경우 . 열쇠 있을때
					// 못가는 경우 # 열쇠 없을댸
				}
			}
		}
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
	
	private static void changeMap(boolean[] key)
	{
		Iterator<Pair> it = pairs.iterator();
		Pair pair;
		int idx;
		while(it.hasNext())
		{
			pair = it.next();
			
			idx = pair.c<(int)'G'? pair.c-'A':pair.c-'a';
			if(key[idx])
			{
				map[pair.y][pair.x] = '.';
			}
			else
			{
				map[pair.y][pair.x]= pair.c; 
			}
		}
	}

	static int min = Integer.MAX_VALUE;
	static int[] dx= {1,0,-1,0};
	static int[] dy= {0,1,0,-1};
	

}
