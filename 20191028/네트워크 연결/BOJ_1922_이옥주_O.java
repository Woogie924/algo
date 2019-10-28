import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1922_이옥주_O {
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int size = Integer.parseInt(br.readLine());
	    int num = Integer.parseInt(br.readLine());
	    parent = new int[size+1];
	    String[] str;
	    int[][] arr = new int[num][3];
	    int count=0;
	    int result = 0;
	    
	    //입력 받기
	    for(int i=0; i<num; i++) {
	    	str = br.readLine().split(" ");
	    	
	    	for(int j=0; j<3; j++) {
	    		arr[i][j] = Integer.parseInt(str[j]);
	    	}	
	    }
	   
	    Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
	    
	    makeSet();
	    
	    for(int i=0; i<arr.length; i++) {
	    	if(count == size-1) break;
	    	
	    	if(findSet(arr[i][0]) != findSet(arr[i][1])) {
	    		unionSet(findSet(arr[i][0]), findSet(arr[i][1]));
	    		count++;
	    		result+= arr[i][2];
	    	}
	    }
	    
	    System.out.println(result);
	}

	private static void unionSet(int num1, int num2) {
		parent[num2] = parent[num1];
	}

	private static int findSet(int num) {
		if(num == parent[num]) return num;
		
		return parent[num] = findSet(parent[num]);
	}

	private static void makeSet() {
		for(int i=1; i<parent.length; i++) {
			parent[i] = i;
		}
	}
}