import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	static int[]map;
	static int[] dp;
	static class Loc implements Comparable<Loc>
	{
		int p;//place;
		int w;//weight
		public Loc(int p, int w) {
			super();
			this.p = p;
			this.w = w;
		}
		@Override
		public String toString() {
			return "Loc [p=" + p + ", w=" + w + "]";
		}
		@Override
		public int compareTo(Loc loc) {
			if(this.w > loc.w) return 1;
			else return -1;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[101];//1~100까지
		dp = new int[101];
		int s,d;
		for(int i = 0 ; i < N+M;i++)
		{
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			map[s] = d;
		}//입력완료
		
		PriorityQueue<Loc> pq = new PriorityQueue<>();
		pq.add(new Loc(1,0));
		Arrays.fill(dp, Integer.MAX_VALUE);
		Loc loc;

//		int result = 0;
		while(!pq.isEmpty())
		{
			loc = pq.poll();
			if(loc.p==100) break;
			for(int i = 1 ; i <= 6 ;i++)
			{
				if(loc.p+i>100) break;
				if(map[loc.p+i]!=0)//사다리나 뱀이 존재하면
				{
					//범위를 넘어가거나 dp값이 더크면 저장안한다.
					if(dp[map[loc.p+i]]>loc.w+1)
					{
						dp[map[loc.p+i]] = loc.w+1;
						pq.add(new Loc(map[loc.p+i],loc.w+1));
					}
				}
				else
				{
					if(dp[loc.p+i]>loc.w+1)
					{
						dp[loc.p+i] = loc.w+1;
						pq.add(new Loc(loc.p+i,loc.w+1));
					}
				}
//			System.out.println("loc.p :"+loc.p);
//			System.out.println(Arrays.toString(dp));
			}
		}
		System.out.println(dp[100]);
	}

}
