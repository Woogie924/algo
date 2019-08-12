import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ10451_순열사이클 {
	static boolean visit[];
	static ArrayList<Integer>[] nodes;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int testcase = Integer.parseInt(br.readLine());
		int n, cnt;
		for(int tc=1; tc<=testcase; tc++) {
			n= Integer.parseInt(br.readLine());
			nodes = new ArrayList[n+1];
			visit = new boolean[n+1];
			for(int i=0 ; i<=n; i++) nodes[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) 
				nodes[i].add(Integer.parseInt(st.nextToken()));
			//노드탐색 dfs방식
			cnt = 0;
			
			for(int i=1; i<=n; i++) {
				if(!visit[i]) {
					cnt++;
					solve(i);
				}
			}
			bw.append(cnt+"\n");
		}
		bw.flush();
	}
	private static void solve(int index) {
		visit[index] = true;
		for(Integer integer : nodes[index])	{
			if(!visit[integer])
				solve(integer);
		}
	}

}
