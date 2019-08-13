package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1707_현병록_O {
	static ArrayList<Integer>[] nodes;
	static int[] visit;
	static int size, line, n1, n2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testcase = Integer.parseInt(br.readLine());
		here : for(int tc=1; tc<=testcase; tc++) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken()); line = Integer.parseInt(st.nextToken());
			nodes = new ArrayList[size+1];
			visit = new int[size+1]; //방문안한거 0, 1번그룹방문 1, 2번그룹 방문 -1
			for(int i=1; i<=size; i++) nodes[i] = new ArrayList<>();
			for(int i=0; i<line; i++) {
				st = new StringTokenizer(br.readLine());
				n1 = Integer.parseInt(st.nextToken()); n2 = Integer.parseInt(st.nextToken());
				nodes[n1].add(n2); nodes[n2].add(n1);
			}
			for(int i = 1; i<=size; i++) {
				if(visit[i]==0) {
					if(!bfs(i)) {
						System.out.println("NO");
						continue here;
					}
				}
			}
			System.out.println("YES");
		}
	}
	private static boolean bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		int nn;
		q.offer(start);
		visit[start]=1;
		while(!q.isEmpty()) {
			nn = q.poll();
			for(int i=0; i<nodes[nn].size(); i++) {
				if(visit[nodes[nn].get(i)]==0) {//다음 노드가 방문안했을 때
					//그 전 노드와 다른색으로 칠해주면 된다.
					visit[nodes[nn].get(i)]=visit[nn]*-1;
					q.offer(nodes[nn].get(i));
				}
				else if(visit[nn]==visit[nodes[nn].get(i)]) {//자신의 노드와 다음 방문할 노드의 색이 같을때
					return false;
				}
			}
		}
		return true;
	}
}
