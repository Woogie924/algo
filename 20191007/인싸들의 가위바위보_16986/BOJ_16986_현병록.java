import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16986_인싸들의가위바위보 {
	static int n, k, count[], wincnt[], result;
	static int[][] map, enemy;
	static boolean visited[], flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		count = new int[3];
		wincnt = new int[3];
		enemy = new int[3][20];
		for(int i=1; i<=2; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<20; j++) {
				enemy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[n+1];
		flag = false;
		result=0;
		permutation(0, 1);
		System.out.println(result);
		
	}
	private static void permutation(int left, int right) {
		int win;
		//종료조건
		if(flag) return; 
		if(wincnt[0]==k) {
			flag = true;
			result=1;
			return;
		}else if(wincnt[1]==k || wincnt[2]==k) {
			//flag = true;
			return;
		}
		if(left==0) {
			for(int i=1; i<=n; i++) {
				if(visited[i]) continue;
				visited[i]= true;
				win = fight(i, enemy[right][count[right]], left, right);
				count[right]++;
				wincnt[win]++;
				permutation(win, 3-left-right);
				count[right]--;
				wincnt[win]--;
				visited[i]= false;
			}
		}else if(right==0) {
			for(int i=1; i<=n; i++) {
				if(visited[i]) continue;
				visited[i]= true;
				win = fight(enemy[left][count[left]], i, left, right);
				count[left]++;
				wincnt[win]++;
				permutation(win, 3-left-right);
				count[left]--;
				wincnt[win]--;
				visited[i]= false;
			}
		}else {
			win = fight(enemy[left][count[left]],enemy[right][count[right]], left, right);
			count[right]++;
			count[left]++;
			wincnt[win]++;
			permutation(win, 3-left-right);
			count[right]--;
			count[left]--;
			wincnt[win]--;

		}
	}
	private static int fight(int i, int j, int left, int right) {
		if(map[i][j]==2) {//i승리
			return left;
		}else if(map[i][j]==1) {
			//무승부
			return Math.max(left, right);
		}else {
			//i패배
			return right;
		}
	}
}
