import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Pro_외벽점검 {
	static ArrayList<Integer> weakList = new ArrayList<>();
	static ArrayList<Integer> distList = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		int[] weak = new int[str.length];
		boolean[] visited = new boolean[str.length];
		
		for(int i=0; i<str.length; i++) {
			weak[i] = Integer.parseInt(str[i]);
		}
		str = br.readLine().split(" ");
		int[] dist = new int[str.length];
		for(int i=0; i<str.length; i++) {
			dist[i] = Integer.parseInt(str[i]);
		}
		
		go(weak, weak.length, visited, dist, len);
		System.out.println(min==Integer.MAX_VALUE?-1:min);
	}

	private static void go(int[] weak, int size, boolean[] visited, int[] dist, int len) {
		if(weakList.size() == size) {
			//distList 초기화 해주기
			distList.clear();
			for(int j=0; j<dist.length; j++) {
				distList.add(dist[j]);
			}
			
			int result = 0;
			boolean isDist = false;
			int sum = 0;
			int temp = 0;
			
			for(int j=0; j<size-1; j++) {
				isDist = false;
				temp = Math.min(Math.abs(weakList.get(j+1) - weakList.get(j)), len-Math.abs(weakList.get(j+1) - weakList.get(j)));
				
				for(int k=0; k<distList.size(); k++) {
					if(sum+temp <= distList.get(k)) {
						isDist = true;
						sum += temp;
						break;
					}
				}
				
				if(sum==0 && isDist==false) {
					return;
				}
				
				if(!isDist) {
					for(int k=0; k<distList.size(); k++) {
						if((sum<=distList.get(k)) && (sum+temp > distList.get(k))) {
							distList.remove(k);
							break;
						}
					}
					sum = 0;
					result++;
				}
			}
			
			if(sum!=0) {
				result++;
			}
			
			if(result!=0 && result < min) {
				min = result;
			}
			
			return;
		}
		
		for(int i=0; i<size; i++) {
			if(visited[i]) continue;
			weakList.add(weak[i]);
			visited[i] = true;
			go(weak, size, visited, dist, len);
			visited[i] = false;
			weakList.remove(weakList.size()-1);
		}	
	}
}