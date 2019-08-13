import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.LinkedList;

import java.util.Queue;



public class Main {

	static int N; //정점의 개수
	static int M; //간선의 갯수
	static int V; //시작 정점 번호
	static boolean [][] MAP;
	static boolean [] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");

		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		V = Integer.parseInt(input[2]);

		MAP = new boolean[N+1][N+1];
		visited = new boolean [N+1];
		for(int i=1; i<=M ;i++) {	//adjoint matrix
			String [] line = br.readLine().split(" ");
			MAP[Integer.parseInt(line[0])][Integer.parseInt(line[1])]=true;
			MAP[Integer.parseInt(line[1])][Integer.parseInt(line[0])]=true;
		}
		dfs(V, 0);
		visited = new boolean [N+1];
		System.out.println();
		bfs(V);
		
	}
	static void dfs(int v, int depth) {
		//if(depth == M) {
			//System.out.print(v+" ");
		//	return;
		//}
		visited[v] = true;
		System.out.print(v+ " ");
		for(int i=0; i<MAP.length;i++) {
			if(MAP[v][i] == true && !visited[i]) {
				visited[i] = true;
				dfs(i,depth+1);
			
				
			}
		}
	}
	static void bfs(int v) {//v는 시작 정점
		Queue<Integer> q= new LinkedList<>();
		q.offer(v);
		visited[v] = true;

		while(!q.isEmpty()) {
			int pivot = q.peek();
			System.out.print(pivot+" ");
			q.poll();
			for(int i=0; i<MAP.length ; i++) {	
				if(MAP[pivot][i] == true && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}	
		}
	}
}