import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_현병록_O {
	static int map[][], size, diff;
	static boolean combi[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		combi = new boolean[size];
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		diff = Integer.MAX_VALUE;
		combination(0,0);
		System.out.println(diff);
	}
	private static void combination(int depth, int index) {
		if(depth==size/2) {
			ability();
			return;
		}
		for(int i=index; i<size; i++) {
			combi[i]=true;
			combination(depth+1, i+1);
			combi[i]=false;
		}
	}
	private static void ability() {
		int pros=0, cons=0;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if (combi[i]&&combi[j]) {
					pros += map[i][j];
				} else if(!combi[i]&&!combi[j]) {
					cons+=map[i][j];
				}
			}
		}
		if(diff>Math.abs(pros-cons))
			diff = Math.abs(pros-cons);
	}
}