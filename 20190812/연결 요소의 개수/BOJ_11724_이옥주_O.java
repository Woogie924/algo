import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11724_이옥주_O {
	static int count = 0;
	static int[][] arr;
	static boolean flag;
	static boolean[] visited;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);  //정점의 개수
		int M = Integer.parseInt(str[1]);  //간선의 개수
		int a,b;
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			a = Integer.parseInt(str[0]); 
			b = Integer.parseInt(str[1]); 
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		for(int i=1; i<=N; i++) {
			if(visited[i]) continue;
			flag = false;
			visited[i] = true;
			search(i);
		}
		System.out.println(count);
	}
	
	static void search(int i) {
		for(int j=1; j<=N; j++) {
			if(visited[j]==true || arr[i][j] ==0) continue;
			visited[j] = true;
			search(j);
		}
		
		if(flag==false) {
			count++;
			flag = true;
			return;
		}
		
	}

}
