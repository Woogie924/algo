import java.io.*;
import java.util.*;

public class Solution {
	
	static class Customer
	{
		int num;					// 고객 번호
		int receptionDeskNum;		// 이용한 접수창구 번호
		int receptionDeskStartTime;	// 접수창구 이용을 시작한 시각
		int receptionDeskEndTime;	// 접수창구 이용을 끝마친 시각
		int repairDeskStartTime;	// 정비창구 이용을 시작한 시각
	}

	static int N, M, K, A, B;
	static int a[], b[];
	static PriorityQueue<Integer> t;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = null;
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int tc = 0 ; tc < T ; tc++)
		{
			tokens = new StringTokenizer(in.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			A = Integer.parseInt(tokens.nextToken());
			B = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(in.readLine());
			a = new int[N + 1];
			for(int i = 1 ; i <= N ; i++)
				a[i] = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(in.readLine());
			b = new int[M + 1];
			for(int j = 1 ; j <= M ; j++)
				b[j] = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(in.readLine());
			t = new PriorityQueue<Integer>();
			for(int k = 1 ; k <= K ; k++)
				t.offer(Integer.parseInt(tokens.nextToken()));
			
			out.write("#" + (tc + 1) + " " + solve() + "\n");
		}
		
		out.flush();
	}

	static int solve()
	{
		PriorityQueue<Customer> waitingRoom1, waitingRoom2;		// 대기실 1, 대기실 2
		Customer receptionDesk[], repairDesk[];	// 접수 창구, 정비 창구
		
		// 고객 번호가 낮은 순서대로
		waitingRoom1 = new PriorityQueue<Customer>(new Comparator<Customer>() {
			public int compare(Customer o1, Customer o2) {
				return o1.num - o2.num;
			}
		});
		// 가장 오래 기다렸던 고객 순서대로, 이용했던 접수 창구 번호가 작은 순서대로
		waitingRoom2 = new PriorityQueue<Customer>(new Comparator<Customer>() {
			public int compare(Customer o1, Customer o2) {
				if(o1.receptionDeskEndTime != o2.receptionDeskEndTime)
					return o1.receptionDeskEndTime - o2.receptionDeskEndTime;
				else
					return o1.receptionDeskNum - o2.receptionDeskNum;
			}
		});
		receptionDesk = new Customer[N + 1];
		repairDesk = new Customer[M + 1];
		
		int remain = K;		// 남은 고객 수
		int time = 0; 		// 현재 시각
		int num = 1;		// 고객 번호
		int result = 0;		// 이용한 접수 및 정비 창구의 번호가 각각 A, B인 손님들의 번호 합
		
		while(remain > 0)
		{
			// 1. 수리 창구 -> 설문 부분을 구현한다.
			for(int j = 1 ; j <= M ; j++)
			{
				Customer c = repairDesk[j];
				
				if(c == null)
					continue;
				
				if(c.repairDeskStartTime + b[j] == time)
				{
					remain--;
					
					if(c.receptionDeskNum == A && j == B)
						result += c.num;
					
					repairDesk[j] = null;
				}
			}
			
			// 2. 대기실 2 -> 수리 창구 부분을 구현한다.
			for(int j = 1 ; j <= M ; j++)
			{
				if(waitingRoom2.isEmpty())
					break;
				
				if(repairDesk[j] != null)
					continue;
				
				repairDesk[j] = waitingRoom2.poll();
				repairDesk[j].repairDeskStartTime = time;
			}
			
			// 3. 접수 창구 -> 대기실 2 부분을 구현한다.
			for(int i = 1 ; i <= N ; i++)
			{
				Customer c = receptionDesk[i];
				
				if(c == null)
					continue;
				
				if(c.receptionDeskStartTime + a[i] == time)
				{
					c.receptionDeskEndTime = time;
					waitingRoom2.offer(c);
					receptionDesk[i] = null;
				}
			}
			
			// 4. 대기실 1 -> 접수 창구 부분을 구현한다.
			for(int i = 1 ; i <= N ; i++)
			{
				if(waitingRoom1.isEmpty())
					break;
				
				if(receptionDesk[i] != null)
					continue;
				
				receptionDesk[i] = waitingRoom1.poll();
				receptionDesk[i].receptionDeskStartTime = time;
				receptionDesk[i].receptionDeskNum = i;
			}
			
			// 5. 외부 -> 대기실 1 부분을 구현한다.
			while(!t.isEmpty())
			{
				if(t.peek() != time)
					break;
				
				t.poll();
				Customer c = new Customer();
				c.num = num++;
				waitingRoom1.offer(c);
			}
			
			time++;
		}
		
		return (result != 0 ? result : -1);
	}
}