import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14889_신채연_O {
	static BufferedReader reader;
	static StringTokenizer st;
	static int[][] arr;
	static int n;
	static ArrayList<Integer> list1;
	static ArrayList<Integer> list2;
	static ArrayList<Integer> res;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(reader.readLine());
		arr = new int[n][n];
		for(int i=0;i<n;i++) {
			st  = new StringTokenizer(reader.readLine());
			for(int j=0;j<n;j++)
				arr[i][j]=Integer.parseInt(st.nextToken());
		}
		
		list1 = new ArrayList<Integer>();
		list2 = new ArrayList<Integer>();
		res = new ArrayList<Integer>();
		
		makeTeam(list1);
		
		int min=Integer.MAX_VALUE;
		for(int i=0;i<res.size();i++)
			if(min>res.get(i)) min=res.get(i);
		System.out.println(min);
	}

	public static void makeTeam(ArrayList<Integer> list1) {
		if(list1.size()==n/2) {
			list2.clear();
			for(int i=0;i<n;i++)
				if(!list1.contains(i))
					list2.add(i);
			getSum();
			return;
		}
		
		int smallest = list1.isEmpty()?0:list1.get(list1.size()-1)+1;
		for(int next=smallest;next<n;next++) {
			list1.add(next);
			makeTeam(list1);
			list1.remove(list1.size()-1);
		}
	}

	public static void getSum() {
		int startSum=0;
		int linkSum=0;
		
		for(int i=0;i<n/2-1;i++) {
			for(int j=i+1;j<n/2;j++) {
				int x = list1.get(i);
				int y = list1.get(j);
				startSum+=arr[x][y];
				startSum+=arr[y][x];
			}
		}
		
		for(int i=0;i<n/2-1;i++) {
			for(int j=i+1;j<n/2;j++) {
				int x = list2.get(i);
				int y = list2.get(j);
				linkSum+=arr[x][y];
				linkSum+=arr[y][x];
			}
		}
		res.add(Math.abs(startSum-linkSum));
	}
}
