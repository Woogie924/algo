
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14500_신채연_O {
	static BufferedReader reader;
	static StringTokenizer st;
	static int n,m;
	static int[][] arr;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws Exception{
		reader = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		list = new ArrayList<Integer>();
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(reader.readLine());
			for(int j=0;j<m;j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				do1(i, j);
				do2(i, j);
				do3(i, j);
				do4(i, j);
				do5(i, j);
				do6(i, j);
				do7(i, j);
				do8(i, j);
				do9(i, j);
				do10(i, j);
				do11(i, j);				
			}
		}
		
		int max=-1;
		for(int i=0;i<list.size();i++)
			if(max<list.get(i)) max=list.get(i);
		System.out.println(max);
	}
	
	public static void do11(int a1, int a2) {
		int b1=a1+1;
		int b2=a2;
		int c1=a1+2;
		int c2=a2;
		int d1=a1+1;
		int d2=a2+1;
		int e1=a1+1;
		int e2=a2-1;
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
		if(b1>=0&&c1>=0&&e1>=0&&b1<n&&c1<n&&e1<n&&
				b2>=0&&c2>=0&&e2>=0&&b2<m&&c2<m&&e2<m)
			getSum(a1,a2,b1,b2,c1,c2,e1,e2);
	}
	
	public static void do10(int a1, int a2) {
		int b1=a1;
		int b2=a2+1;
		int c1=a1;
		int c2=a2+2;
		int d1=a1-1;
		int d2=a2+1;
		int e1=a1+1;
		int e2=a2+1;
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
		if(b1>=0&&c1>=0&&e1>=0&&b1<n&&c1<n&&e1<n&&
				b2>=0&&c2>=0&&e2>=0&&b2<m&&c2<m&&e2<m)
			getSum(a1,a2,b1,b2,c1,c2,e1,e2);
	}
	
	public static void do9(int a1, int a2) {
		int b1=a1;
		int b2=a2+1;
		int c1=a1+1;
		int c2=a2;
		int d1=a1-1;
		int d2=a2+1;
		int e1=a1+1;
		int e2=a2-1;
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
		if(b1>=0&&c1>=0&&e1>=0&&b1<n&&c1<n&&e1<n&&
				b2>=0&&c2>=0&&e2>=0&&b2<m&&c2<m&&e2<m)
			getSum(a1,a2,b1,b2,c1,c2,e1,e2);
	}
	
	public static void do8(int a1, int a2) {
		int b1=a1;
		int b2=a2+1;
		int c1=a1+1;
		int c2=a2+1;
		int d1=a1+1;
		int d2=a2+2;
		int e1=a1-1;
		int e2=a2;
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
		if(b1>=0&&c1>=0&&e1>=0&&b1<n&&c1<n&&e1<n&&
				b2>=0&&c2>=0&&e2>=0&&b2<m&&c2<m&&e2<m)
			getSum(a1,a2,b1,b2,c1,c2,e1,e2);
	}
	
	public static void do7(int a1, int a2) {
		int b1=a1+1;
		int b2=a2;
		int c1=a1+2;
		int c2=a2;
		int d1=a1+2;
		int d2=a2+1;
		int e1=a1+2;
		int e2=a2-1;
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
		if(b1>=0&&c1>=0&&e1>=0&&b1<n&&c1<n&&e1<n&&
				b2>=0&&c2>=0&&e2>=0&&b2<m&&c2<m&&e2<m)
			getSum(a1,a2,b1,b2,c1,c2,e1,e2);
	}
	
	public static void do6(int a1, int a2) {
		int b1=a1;
		int b2=a2+1;
		int c1=a1;
		int c2=a2+2;
		int d1=a1+1;
		int d2=a2+2;
		int e1=a1+1;
		int e2=a2;
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
		if(b1>=0&&c1>=0&&e1>=0&&b1<n&&c1<n&&e1<n&&
				b2>=0&&c2>=0&&e2>=0&&b2<m&&c2<m&&e2<m)
			getSum(a1,a2,b1,b2,c1,c2,e1,e2);
	}
	
	public static void do5(int a1, int a2) {
		int b1=a1+1;
		int b2=a2;
		int c1=a1+2;
		int c2=a2;
		int d1=a1;
		int d2=a2+1;
		int e1=a1;
		int e2=a2-1;
		
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
		if(b1>=0&&c1>=0&&e1>=0&&b1<n&&c1<n&&e1<n&&
				b2>=0&&c2>=0&&e2>=0&&b2<m&&c2<m&&e2<m)
			getSum(a1,a2,b1,b2,c1,c2,e1,e2);
	}

	public static void do4(int a1, int a2) {
		int b1=a1;
		int b2=a2+1;
		int c1=a1;
		int c2=a2+2;
		int d1=a1-1;
		int d2=a2;
		int e1=a1-1;
		int e2=a2+2;
		
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
		if(b1>=0&&c1>=0&&e1>=0&&b1<n&&c1<n&&e1<n&&
				b2>=0&&c2>=0&&e2>=0&&b2<m&&c2<m&&e2<m)
			getSum(a1,a2,b1,b2,c1,c2,e1,e2);
	}

	public static void do3(int a1, int a2) {
		int b1=a1;
		int b2=a2+1;
		int c1=a1+1;
		int c2=a2;
		int d1=a1+1;
		int d2=a2+1;
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
	}

	public static void do2(int a1, int a2) {
		int b1=a1;
		int b2=a2+1;
		int c1=a1;
		int c2=a2+2;
		int d1=a1;
		int d2=a2+3;
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
	}

	public static void do1(int a1, int a2) {
		int b1=a1+1;
		int b2=a2;
		int c1=a1+2;
		int c2=a2;
		int d1=a1+3;
		int d2=a2;
		if(b1>=0&&c1>=0&&d1>=0&&b1<n&&c1<n&&d1<n&&
				b2>=0&&c2>=0&&d2>=0&&b2<m&&c2<m&&d2<m)
			getSum(a1,a2,b1,b2,c1,c2,d1,d2);
	}

	public static void getSum(int a1, int a2, int b1, int b2, int c1, int c2, int d1, int d2) {
		int sum=arr[a1][a2]+arr[b1][b2]+arr[c1][c2]+arr[d1][d2];
		list.add(sum);
	}
}
