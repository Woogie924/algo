import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15686_현병록_O {
	static int size, maxStore;
	static class Home{
		int y,x;
		public Home(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	static ArrayList<Home> homes;
	static ArrayList<int[]> stores;
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		maxStore = Integer.parseInt(st.nextToken());
		homes = new ArrayList<>();
		stores = new ArrayList<>();
		min = Integer.MAX_VALUE;
		int temp;
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				temp = Integer.parseInt(st.nextToken());
				if(temp==1) {
					homes.add(new Home(i, j));
				}
				else if(temp==2) {
					stores.add(new int[] {i,j});
				}
			}
		}
		combinations();
		System.out.println(min);
	}
	private static void combinations() {
		int lsize = stores.size(), a;
		int smallest[] = new int[homes.size()];
		
		for(int i=0; i<(1<<lsize); i++){
			
			if(Integer.toBinaryString(i).replace("0", "").length() == maxStore)
			{
				Arrays.fill(smallest, Integer.MAX_VALUE);
				for(int j=0; j<lsize; j++) {
					if((i&(1<<j))!=0) {
						//그 치킨집을 선택하는 경우
						for(int h=0; h<homes.size(); h++) {
							a = Math.abs(homes.get(h).y- stores.get(j)[0]) + Math.abs(homes.get(h).x - stores.get(j)[1]);
							if(a<smallest[h]) {
								smallest[h] = a;
							}
						}
					}
				}
				for(int s =1; s<smallest.length; s++) {
					smallest[0]+=smallest[s];
				}
				if(smallest[0]<min) min=smallest[0];
			}
		}
		
	}
}
