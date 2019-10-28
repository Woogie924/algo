import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1922_홍성욱_X {

	static int [][] cost;
	static int N;
	static int M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		cost = new int[N][N];
		boolean [] visited = new boolean[N];
		
		String [] input;
		int a;
		int b;
		for(int i=0; i<M;i++) {
			input = br.readLine().split(" ");
			a= Integer.parseInt(input[0])-1;
			b= Integer.parseInt(input[1])-1;
			cost[a][b] = Integer.parseInt(input[2]);
//			cost[b][a] = Integer.parseInt(input[2]);
		}
		
		for(int i=0;i<N;i++) {
			int cnt=123457790;
			for(int j=0;j<N;j++) {
				if(cost[i][j] !=0 && cost[i][j]<cnt) {
					cnt = cost[i][j];
				}
			}
//			System.out.println(cnt);
			if(cnt!=123457790) {
				res+=cnt;
			}
		}
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		for(int i=0;i<N;i++) {
//			connect(i,0,visited,0, list);
//		}
		System.out.println(res);
	}
	static int res = 0;
	static void connect(int next, int depth, boolean [] visited, int count, ArrayList<Integer> data) {
		if(depth==N) {
			
			for(int i=0; i<data.size(); i++) {
				System.out.print(data.get(i)+" ");
			}
			System.out.println();
			if(res>count ) {
				System.out.println(" :: " +count);
				res = count;
			}
			return;
		}else{
			for(int i=0;i<N;i++) {
				if(next==i) {
					continue;
				}
				if(!visited[i] && cost[next][i] >0) {
					visited[i] = true;
					data.add(i);
					connect( i, depth+1, visited, count+cost[next][i], data);
					data.remove(data.size()-1);
					visited[i] = false;
				}
			}
		}
	}

}
