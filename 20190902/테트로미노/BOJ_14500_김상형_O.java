import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ14500 {
	static int max;
	//[19][3][2]
	static int[][][] tetro = {{{0,1},{0,2},{0,3}}, //-모양
					{{1,0},{2,0},{3,0}}, // ㅣ 모양
										
					{{0,1},{1,0},{1,1}}, //ㅁ모양
										
					{{1,0},{2,0},{2,1}},//L
					{{1,0},{2,0},{2,-1}}, //L반대
					{{0,1},{1,1},{2,1}},//ㄱ
					{{0,-1},{1,-1},{2,-1}}, //ㄱ반대
					{{1,0},{1,1},{1,2}}, // ㄴ 모양
					{{1,0},{1,-1},{1,-2}},//ㄴ반대
					{{0,1},{0,2},{1,2}},
					{{0,-1},{0,-2},{1,-2}},
										
					{{1,0},{1,-1},{2,-1}},
					{{1,0},{1,1},{2,1}},
					{{0,1},{1,1},{1,2}},
					{{0,-1},{1,-1},{1,-2}},
										
					{{1,0},{0,-1},{0,1}},
					{{1,1},{1,-1},{1,0}},
					{{1,0},{2,0},{1,1}},
					{{1,0},{2,0},{1,-1}}};
										
										
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");		
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		
		int[][] arr = new int[n][m];
		
		for(int y=0; y<n; ++y) {
			s = br.readLine().split(" ");
			for(int x=0; x<m; ++x) {
				arr[y][x] = Integer.parseInt(s[x]);
			}
		}
		
		for(int y=0; y<n; ++y) {
			for(int x=0; x<m; ++x) {
				for(int i=0; i<tetro.length; ++i) {
					int blockSum = arr[y][x];
					for(int j=0; j<tetro[i].length;++j) {
						int ny = y+tetro[i][j][0];
						int nx = x+tetro[i][j][1];
						if(ny<0||nx<0||ny>=n||nx>=m) continue;
						blockSum +=arr[ny][nx];
						
						max = Math.max(max, blockSum);
					}
					
				}
			}
		}
		System.out.println(max);

	}

}
