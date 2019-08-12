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
 * ������ �о�� ��带 ������ index�� value�� ���Ͽ�
 * index==value�̸� �ڱ��ڽŸ� ���̴� �� ������ Ÿ�� �ö�� ������ visitüũ�� ���ְ� cnt�� �߰��ҷ��� �Ͽ���
 * �ƴϸ� value�� Ÿ�� �ö󰡴ٰ� index�� �����ְ� ������ ���� ����� ������ �����Ͽ���
 * visit�� true�� ���� ������ �ٷ� trueó���� ���ָ鼭 �����ϸ� �� �� ����.
 */
public class BJ9466_��������Ʈ {
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
			if(i==node[i]) {//�ڱⰡ �ڱ��ڽ��� ����ġ�� ���� �ڽŻ��� �տ� ���� �ֵ��� ���� ������� ���� cnt�� ����� �����ش�.
				rtv+=cnt;
				return;
			}else if (index == node[i]) {// ó�� �� ģ���� ���� value���� ��ġ�� ��, �� �ѽ���Ŭ�� �ϼ��� ��... �׳� �ƹ��� �۾����ϰ� ���� �۾���带 ã���ָ� �ȴ�
				return;
			}else if (visited[node[i]]) {// �ڽ��� ������尡 �̹� �湮�Ǿ������� ����Ŭ�� �� �����, �� �� ������ ���ξֵ��� �� ���� �������. �ڱ��ڽű��� count���ְ� �����ش�.
				if(list.contains(node[i])) {//�߰��� �湮������ ������  �� �湮�� �ε��� �����ϰ� �����ش�
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
