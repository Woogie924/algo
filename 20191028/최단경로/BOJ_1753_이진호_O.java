import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_이진호_O {
	static int V,E,K;
	static List[] lists;
	static int[] wrr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		lists = new List[V+1];
		for(int i = 1 ; i <= V ; i++)
		{
			lists[i] = new ArrayList<>();
		}
		wrr = new int[V+1];
		Arrays.fill(wrr, Integer.MAX_VALUE);
		wrr[K] = 0;		
		int u,v,w;
		for(int i = 0 ; i < E ; i++)
		{
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			lists[u].add(new EAW(v, w));
		}
		PriorityQueue<EAW> pq = new PriorityQueue<>();
		pq.add(new EAW(K,0));
		EAW eaw;
		EAW eawNext;
		while(!pq.isEmpty())
		{
			eaw = pq.poll();
			int size = lists[eaw.e].size();
			for(int i = 0 ; i < size; i++)
			{
				eawNext = (EAW) lists[eaw.e].get(i);
				if(wrr[eawNext.e]>eawNext.w+wrr[eaw.e])
				{
					wrr[eawNext.e] = eawNext.w+wrr[eaw.e];
					EAW teaw = new EAW(eawNext.e,wrr[eawNext.e]);
					pq.add(teaw);
				}
			}
		}
		show();
	}
	private static void show() {
		for(int i = 1; i<= V; i++)
		{
			if(wrr[i]==Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(wrr[i]);
		}
		
	}
	static class EAW implements Comparable<EAW>//Edge and Weight
	{
		int e;
		int w;
		public EAW(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
		@Override
		public String toString() {
			return "EAW [e=" + e + ", w=" + w + "]";
		}
		@Override
		public int compareTo(EAW o) {
			/*if(this.w < o.w) return 1;
			else if(this.w == o.w)
			{
				if(this.e< o.e) return 1;
				else return -1;
			}
			else return -1;*/
			return w-o.w;
		}
		
	}
}
