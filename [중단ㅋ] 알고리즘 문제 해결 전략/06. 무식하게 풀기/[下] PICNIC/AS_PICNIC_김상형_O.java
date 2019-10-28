package six.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PICNIC {
	static int C;
	static int N;
	static int M;
	static boolean[][] isFriend = new boolean[10][10];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		C = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < C; ++ t) {
			String[] s = br.readLine().split("\\s");
			
			N = Integer.parseInt(s[0]);
			M = Integer.parseInt(s[1]);
			
			s = br.readLine().split("\\s");
			int idx = 0;
			for(int i=0; i<M; ++i) {
				int a = Integer.parseInt(s[idx++]);
				int b = Integer.parseInt(s[idx++]);
				isFriend[a][b] = isFriend[b][a] = true;
 			}
			System.out.println(countPairings(new boolean[N]));
		}
	}
	
	static int countPairings(boolean taken[]) {
		int firstFree = -1;
		for(int i=0; i<N; ++i) {
			if(!taken[i]) {
				firstFree = i;
				break;
			}
		}
		
		//base case
		if(firstFree == -1) return 1;
		
		int ret = 0;
		
		for(int pair = firstFree+1; pair<N; ++pair) {
			if(!taken[pair] && isFriend[firstFree][pair]) {
				taken[firstFree] = true;
				taken[pair] = true;
				
				ret += countPairings(taken);
				
				taken[firstFree] = false;
				taken[pair]=false;
			}
		}
		return ret;
	}
	

}
