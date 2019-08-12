import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문제를 읽어보고 노드를 저장할 index와 value를 비교하여
 * index==value이면 자기자신만 팀이니 그 전까지 타고 올라온 노드들은 visit체크만 해주고 cnt에 추가할려고 하였고
 * 아니면 value를 타고 올라가다가 index가 같은애가 나오면 팀을 만드는 것으로 생각하였다
 * visit이 true인 곳을 만나면 바로 true처리를 해주면서 진행하면 될 것 같다.
 */
public class BJ9466_텀프로젝트 {
	static int node[], rtv;
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int testcase = Integer.parseInt(br.readLine()), n;
		for(int tc=1; tc<=testcase; tc++) {
			n = Integer.parseInt(br.readLine());
			node = new int[n+1]; visited = new boolean[n+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				node[i] = Integer.parseInt(st.nextToken());
			}
			rtv=0;
			for(int i=1; i<=n; i++) {
				if(!visited[i]) {
					bfs(i);
				}
			}
			bw.write(rtv+"\n"); bw.flush();
		}
	}
	private static void bfs(int index) {
		Queue<Integer> q = new LinkedList<Integer>();
		ArrayList<Integer> list = new ArrayList<>();
		int i, cnt=0;
		q.offer(index);
		visited[index]=true;
		while(!q.isEmpty()) {
			i = q.poll();
			list.add(i);
			if(i==node[i]) {//자기가 자기자신을 가르치는 경우라서 자신빼고 앞에 쌓인 애들은 팀을 못만든다 쌓인 cnt를 결과에 더해준다.
				rtv+=cnt;
				return;
			}else if (index == node[i]) {// 처음 들어간 친구와 현재 value값이 일치할 때, 즉 한싸이클이 완성될 때... 그냥 아무런 작업안하고 다음 작업노드를 찾아주면 된다
				return;
			}else if (visited[node[i]]) {// 자신의 다음노드가 이미 방문되어있으면 싸이클을 못 만든다, 즉 그 전까지 쌓인애들은 다 팀을 못만든다. 자기자신까지 count해주고 더해준다.
				if(list.contains(node[i])) {//중간에 방문한적이 있으면  그 방문한 인덱스 제외하고 더해준다
					rtv += list.indexOf(node[i]);
					return;
				}
				rtv += ++cnt;
				return;
			} 
			cnt++;
			q.offer(node[i]);
			visited[node[i]] = true;
		}
		rtv+=cnt;
		
	}
}
