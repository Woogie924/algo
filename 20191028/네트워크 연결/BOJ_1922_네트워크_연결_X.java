import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1922_네트워크_연결_X {
	static int n,m;
	static ArrayList<Line> list;
	static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(reader.readLine());
		m = Integer.parseInt(reader.readLine());
		list = new ArrayList<>();
		parent = new int[n+1];
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(reader.readLine());
			int com1 = Integer.parseInt(st.nextToken());
			int com2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list.add(new Line(com1,com2,w));
		}
		
		list.sort(new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				return o1.w-o2.w;
			}
		});
		
		for(Line l : list) {
			System.out.println(l.toString());
		}
		System.out.println("========");
		
		//unionSet의 결과, sort된 list의 첫번째 Line의 첫 번째 컴퓨터가 모두의 부모가 되면 끝
		int cost=0;
		makeSet();
		for(Line l : list) {
			if(isFinished()) break;
/*			unionSet(l.com1,l.com2);
			cost+=l.w;*/
			if(parent[l.com1]!=parent[l.com2]) {
				unionSet(l.com1,l.com2);
				cost+=l.w;
			}
			System.out.println(cost);
			System.out.println(Arrays.toString(parent));
		}
		System.out.println("final"+cost);
	}

	
	private static void unionSet(int com1, int com2) {
		com1 = findSet(com1);
		com2 = findSet(com2);
		if(com1!=com2) parent[com2]=com1;
	}

	private static int findSet(int x) {
		if(parent[x]==x) return x;
		//return parent[x] = findSet(parent[x]);
		parent[x] = findSet(parent[x]);
		return parent[x];
	}

	private static void makeSet() {
		for(int i=1;i<n+1;i++) parent[i]=i;
	}
	
	private static boolean isFinished() {
		int temp = parent[1];
		for(int i=2;i<=n;i++) if(temp!=findSet(i)) return false;
		return true;
	}

	static class Line{
		int com1;
		int com2;
		int w;

		public Line(int com1, int com2, int w) {
			super();
			this.com1 = com1;
			this.com2 = com2;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Line [com1=" + com1 + ", com2=" + com2 + ", w=" + w + "]";
		}
	}
}
