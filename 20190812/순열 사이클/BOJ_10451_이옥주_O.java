import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10451_¿Ãø¡¡÷_O {
	static boolean[] visited;
	static int[] arr;
	static int check;
	static int start;
	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int caseNum = Integer.parseInt(br.readLine());
		
		for(int i=0; i<caseNum; i++) {
			count = 0;
			check = 0;
			int size = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");
			arr = new int[size+1];
			visited = new boolean[size+1];
			
			for(int j=0; j<size; j++) {
				arr[j+1] = Integer.parseInt(str[j]);
			}
			
			for(int j=1; j<=size; j++) {
				if(visited[j]) continue;
				start = j;
				search(j);
			}
			
			System.out.println(count);
		}
	}

	static void search(int i) {
		if(arr[i] == start) {
			count++;
			return;
		}
		
		visited[arr[i]] = true;
		
		search(arr[i]);
	}
}
