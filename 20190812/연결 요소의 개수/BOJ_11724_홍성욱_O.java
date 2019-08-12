import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int M;
	static int [][] MAT;
	static boolean [] visited;
	static int cnt = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		MAT = new int [N+1][N+1];
		visited = new boolean[N+1];

		int v;
		int nextV;
		for(int i=0; i<M ; i++) {
			String [] input = br.readLine().split(" ");
			v = Integer.parseInt(input[0]);
			nextV = Integer.parseInt(input[1]);

			MAT[v][nextV]=1;
			MAT[nextV][v]=1;
		}
		
		// input[0] -> input[1]
		for(int i=1; i<=N; i++) {
			/*
			 * if(visited[i] = false) { visited[i] = true; sol(i); }
			 */
			if(visited[i]) continue;
			flag = false;
			visited[i] = true;
			sol(i);
		}
		System.out.println(cnt);


	}
	static boolean flag;
	static void sol(int idx) {
		for(int i=1; i<=N ; i++) {
			if(visited[i] == true || MAT[idx][i] ==0) {		//방문했거나, 0이면 연결X
				continue;
			}
			visited[i] = true;
			sol(i);
		}
		if(flag==false) {
			cnt++;
			flag=true;
			return;
		}
	}
	/*
1->2 2->5 5->1  		
처음부터 끝까지 정점수만큼 boolean 탐색, false 하나라도 있으면 cnt++;
3->4 4->6	1개

1 2 
2 5 
5 1 
3 4 
4 6
	 */
}
