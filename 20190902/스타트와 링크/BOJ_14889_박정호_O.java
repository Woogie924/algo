import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_StartAndLink_14889_박정호_O {
	static int answer = Integer.MAX_VALUE;
	static int[][] map;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String[] s;
		for (int i = 0; i < N; ++i) {
			s = br.readLine().split("\\s");
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		// N과 M
		ArrayList<Integer> list = new ArrayList<>();
		go(0, list);
		System.out.println(answer);
	}

	private static void go(int n, ArrayList<Integer> list) {
		if (list.size() == N / 2) {
			check(list);
			return;
		}

		for (int i = n; i < N; ++i) {
			list.add(i);
			go(i + 1, list);
			list.remove(list.size() - 1);
		}

	}

	private static void check(ArrayList<Integer> list) {
		int size = N/2;
		ArrayList<Integer> opposition = new ArrayList<>();
		for(int i=0; i<N; ++i){
			if(list.contains(i))
				continue;
			opposition.add(i);			
		}
		int cnt = 0;
		for(int i=0; i<size-1; ++i){
			for(int j=i+1; j<size; ++j){
				cnt += map[list.get(i)][list.get(j)];
				cnt += map[list.get(j)][list.get(i)];
				cnt -= map[opposition.get(i)][opposition.get(j)];
				cnt -= map[opposition.get(j)][opposition.get(i)];
			}
		}
		answer = Math.min(answer, Math.abs(cnt));
	}

}
