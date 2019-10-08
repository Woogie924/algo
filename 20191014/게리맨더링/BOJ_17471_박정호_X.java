import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_게리맨더링_17471_박정호_X {
	static int N;
	static int[] people;
	static boolean[] visit;
	static int[][] map;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		visit = new boolean[N+1];
		map = new int[N+1][N+1];
		String[] s = br.readLine().split("\\s");
		for(int i=1; i<=N; ++i) {
			people[i] = Integer.parseInt(s[i-1]);
		}
		for(int i=1; i<=N; ++i) {
			s = br.readLine().split("\\s");
			int n = Integer.parseInt(s[0]);
			for(int j=1; j<=n; ++j) {
				map[i][Integer.parseInt(s[j])] = 1;
			}
		}
//		print();
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1; i<=N/2; ++i)
			go(1, i, list);
		if(answer==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	private static void solution(ArrayList<Integer> list1, ArrayList<Integer> list2) {
//		System.out.println(list1+ " "+list2);
		// 모두 연결되었는지 확인
		if(!isLinked(list1, list2))
			return;
		
		// 인원수 비교
		int temp = 0;
		for(int i=0; i<list1.size(); ++i)
			temp += people[list1.get(i)];
		for(int i=0; i<list2.size(); ++i)
			temp -= people[list2.get(i)];
		temp = Math.abs(temp);
		answer = Math.min(answer, temp);
//		System.out.println(list1+" "+list2+" "+temp);
	}

	private static boolean isLinked(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		boolean[] check = new boolean[N+1];
		int cnt = 0;
		for(int i=0; i<list1.size(); ++i) {
			int x = list1.get(i);
			if(check[x])
				continue;
			 Queue<Integer> q = new LinkedList<Integer>();
			 q.add(x);
			 while(!q.isEmpty()) {
				 int tx = q.poll();
				 if(check[x])
						continue;
				 for(int j=1; j<=N; ++j) {
					 if(map[tx][j]==1 && list1.contains(j)) {
						 check[j]=true;
						 q.add(j);
					 }
				 }
			 }
			 cnt++;
		}
		check = new boolean[N+1];
		for(int i=0; i<list2.size(); ++i) {
			int x = list2.get(i);
			if(check[x])
				continue;
			 Queue<Integer> q = new LinkedList<Integer>();
			 q.add(x);
			 while(!q.isEmpty()) {
				 int tx = q.poll();
				 if(check[x])
						continue;
				 for(int j=1; j<=N; ++j) {
					 if(map[tx][j]==1 && list2.contains(j)) {
						 check[j]=true;
						 q.add(j);
					 }
				 }
			 }
			 cnt++;
		}
		if(cnt==2)
			return true;
		return false;
	}

	private static void go(int n, int size, ArrayList<Integer> list) {
		if(list.size()==size) {
			ArrayList<Integer> list2 = new ArrayList<>();
			for(int i=1; i<=N; ++i) {
				if(list.contains(i))
					continue;
				list2.add(i);
			}
			solution(list, list2);
			return;
		}
		
		for(int i=n; i<=N; ++i) {
			if(visit[i])
				continue;
			visit[i] = true;
			list.add(i);
			go(i, size, list);
			visit[i] = false;
			list.remove(list.size()-1);
		}
		
	}

	private static void print() {
		System.out.println();
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}
	}

}
