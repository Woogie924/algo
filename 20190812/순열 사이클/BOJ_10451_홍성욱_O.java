import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int Test_Case = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=Test_Case; tc++) {
			int N = Integer.parseInt(br.readLine());
			String [] input = br.readLine().split(" ");
			data = new int[N+1];
			visited = new boolean [N+1];
			
			for(int i=1; i<=N ; i++) {
				data[i] = Integer.parseInt(input[i-1]);
			}
//			for(int i=1; i<=N ; i++) {
//				System.out.print(data[i] +" ");
//			}
			int cnt=0;
			
			for(int i=1; i<=N; i++) {
				if(visited[i] == false) {
					startIdx = i;
					visited[i] = true;
					sol(i);
					cnt++;
				}
			}
			System.out.println(cnt);
			//System.out.println("cnt : "+cnt);
		}
	}
	static int startIdx=0;
	static int [] data;
	static boolean [] visited;
	
	static void sol(int idx) {
		int nextIdx= data[idx];
		if(nextIdx == startIdx) {
			//System.out.print(data[nextIdx]);
			return;
		}
		
		visited[nextIdx]= true;
		//System.out.print(data[nextIdx]);
		sol(nextIdx);
	}
/*
1 2 3 4 5 6 7 8
3 2 7 8 1 4 5 6

1->3 3->7 7->5 5->1		1개
2->2 	1개
4->8 8->6 6->4		1개

 */
}
