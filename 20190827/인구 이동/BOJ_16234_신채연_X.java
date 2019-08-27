import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234_신채연_X {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	static boolean[][] visited;
	static boolean[][] discovered;
	static int[][] arr;
	static boolean[][] check;
	static ArrayList<Integer> list;
	static int n;

	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		arr = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(reader.readLine());
			for(int j=0;j<n;j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		int count=0;
		boolean flag = false;
		list = new ArrayList<Integer>();


		while(true){
			//전부다 돌면서 false 처리를 할지, 새로 생성하는게 나을지....
			check = new boolean[n][n];
			discovered = new boolean[n][n];

			for(int i=0;i<n;i++) {
				for(int j=0;j<n-1;j++) {
					if(Math.abs(arr[j][i]-arr[j+1][i])>=l && Math.abs(arr[j][i]-arr[j+1][i])<=r) {
						if(!check[j][i]) {
							check[j][i]=true;
						}
						if(!check[j+1][i]) {
							check[j+1][i]=true;
						}
						flag=true;
					}
				}
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<n-1;j++) {
					if(Math.abs(arr[i][j]-arr[i][j+1])>=l && Math.abs(arr[i][j]-arr[i][j+1])<=r) {
						if(!check[i][j]) {
							check[i][j]=true;
						}
						if(!check[i][j+1]) {
							check[i][j+1]=true;
						}
						flag=true;
					}
				}
			}
			if(flag==false) break;

			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(check[i][j]) {	//인구이동 대상인 국가가 한 곳 발견되기 시작
						sum=0;
						count_countries=0;
						list1.clear();
						doBfs(i, j);
					}
				}
			}

			count++;
			flag=false;

			//출력테스트
			System.out.println("=====");
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++)
					System.out.print(arr[i][j]+" ");
				System.out.println();
			}
			System.out.println("=====");
			//
		}
		System.out.println(count);
	}

	
	static ArrayList<Integer> list1 = new ArrayList<Integer>();
	static int sum=0;
	static int count_countries=0;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};

	public static void doBfs(int i, int j) {

		Queue<Integer> q = new LinkedList<>();
		
		q.offer(i);
		q.offer(j);
		sum+=arr[i][j];
		discovered[i][j]=true;
		count_countries++;
		list1.add(i);
		list1.add(j);
		
		while(!q.isEmpty()) {
			for(int k=0;k<4;k++) {
				int tx = i + dx[k];
				int ty = j + dy[k];
				if(tx>=0 && ty>=0 && tx<n && ty<n) {
					if(check[tx][ty] && !discovered[tx][ty]) {
						q.offer(tx);
						q.offer(ty);
						sum+=arr[tx][ty];
						discovered[tx][ty]=true;
						count_countries++;
						list1.add(tx);
						list1.add(ty);
					}
				}
			}
			i = q.poll();
			j = q.poll();
		}
		int avg = sum/count_countries;
		int index=0;
		for(int k=0;k<list1.size()/2;k++)
			arr[list1.get(index++)][list1.get(index++)]=avg;
		return;
	}
}

/*
출력테스트 

for(int i=0;i<n;i++) {
for(int j=0;j<n;j++)
	System.out.print(arr[i][j]+" ");
System.out.println();
}

 */