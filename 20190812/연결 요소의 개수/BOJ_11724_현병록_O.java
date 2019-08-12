import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11724_연결요소의개수 {
	static boolean visit[];
	static ArrayList<Integer>[] nodes;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n= Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		int n1, n2;
		nodes = new ArrayList[n+1];
		visit = new boolean[n+1];
		for(int i=0 ; i<=n; i++) nodes[i] = new ArrayList<>();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken()); n2 = Integer.parseInt(st.nextToken());
			nodes[n1].add(n2); nodes[n2].add(n1);
		}
		cnt=0;
		for(int i=1; i<=n; i++) {
			if(!(visit[i])) {
				dfs(i);
				cnt++;
			}
		}
		bw.write(cnt+""); bw.flush();
	}
	private static void dfs(int index) {
		visit[index] = true;
		for(Integer i : nodes[index]) {
			if(!(visit[i]))
				dfs(i);
		}
	}

}
