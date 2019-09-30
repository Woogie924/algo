import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class BOJ_뱀과사다리게임_16928_박정호_O {

	static int[] map = new int[101];
	static boolean[] visit = new boolean[101];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split("\\s");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		
		for(int i=0; i<N+M; ++i) {
			s = br.readLine().split("\\s");
			map[Integer.parseInt(s[0])] = Integer.parseInt(s[1]);
		}		
		System.out.println(bfs());		
	}

	private static int bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visit[1] = true;
		int level = 1;
		out:while(!q.isEmpty()) {
			int qSize = q.size();
			for(int k=0; k<qSize; ++k) {
				int now = q.poll();
				for(int i=1; i<=6; ++i) {
					if(now+i>100 || visit[now+i])
						continue;
					if(now+i==100)
						break out;
					
					if(map[now+i]==0) {
						q.add(now+i);
						visit[now+i] = true;
					} else { //뱀이나 사다리가 있는 칸인 경우
						q.add(map[now+i]);
						visit[now+i] = true;
						visit[map[now+i]] = true;
					}					
				}
			}
			level++;
		}
		return level;
	}

}
