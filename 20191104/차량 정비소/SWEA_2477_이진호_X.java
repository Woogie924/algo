import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class SWEA_2477_이진호_X {
	static int T, N, M, K, A, B;
	static int[] fc;// firstCutomer
	static int[] a;
	static int[] curA;
	static int[] b;
	static int[] curB;
	static Queue<Customer> q;

	static class Customer {
		int n;
		int t;
		int at;
		int bt;

		public Customer(int n,int t, int at, int bt) {
			super();
			this.n = n;
			this.t = t;
			this.at = at;
			this.bt = bt;
		}

		@Override
		public String toString() {
			return "Customer [n=" + n + ", t=" + t + ", at=" + at + ", bt=" + bt + "]";
		}


	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			fc = new int[K];
			a = new int[N];
			curA = new int[N];
			b = new int[M];
			curB = new int[M];
			q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)// 접수창구
			{
				a[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());// 수리창구
			for (int i = 0; i < M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());// 손님
			for (int i = 0; i < K; i++) {
				fc[i] = Integer.parseInt(st.nextToken());
			}
			int next;
			PriorityQueue<Customer> set = new PriorityQueue<>(new Comparator<Customer>() {

				@Override
				public int compare(Customer o1, Customer o2) {
					if(o1.t==o2.t)
					{
						return o1.n-o2.n;
					}
					else return o1.t-o2.t;
				}
			});
			st: for (int i = 0; i < K; i++) {
				next = fc[i];
				for (int ai = 0; ai < N; ai++) {
					if (curA[ai] <= next) {
						curA[ai] = next + a[ai];
						set.add(new Customer(i+1,curA[ai], ai+1, -1));
						continue st;
					}
					if (ai == N - 1) {
						ai = -1;
						next++;
					}
				}
				// 이부분 더줄일려면 각 창구의 최솟값을 정해주면됨
			}
			Customer cust;
			st: while(!set.isEmpty()) {
				cust = set.poll();
				for (int bi = 0; bi < M; bi++) {
					if (curB[bi] <= cust.t) {
						curB[bi] = cust.t + b[bi];
						cust.bt = bi+1;
						q.add(cust);
						continue st;
					}
					if (bi == M - 1) {
						bi = -1;
						cust.t++;
					}
				}
			}
			int result = 0;
			while(!q.isEmpty())
			{
				cust = q.poll();
				if(cust.at == A && cust.bt == B)
				{
					result+=cust.n;
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}

}
