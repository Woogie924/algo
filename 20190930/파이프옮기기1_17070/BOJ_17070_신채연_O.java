import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1_O {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	static int n;
	static int[][] arr;
	static int count;	//방법의 수
	static int[] dx={0,1,0,-1};
	static int[] dy={1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(reader.readLine());
		arr = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(reader.readLine());
			for(int j=0;j<n;j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		solve(0,1,1);
		writer.write(count+"");
		writer.flush();
	}


	public static void solve(int c, int d, int dir) {
		if(c==n-1 && d==n-1) {
			count++;
			return;
		}
		
		//현재상태 dir : 가로1 세로2 대각선3
		if(dir==1) {
			//1->1
			if(d+1<n && arr[c][d+1]==0)
				solve(c,d+1,1);
			//1->3
			if(c+1<n && d+1<n && arr[c][d+1]==0 &&
					arr[c+1][d+1]==0 && arr[c+1][d]==0)
				solve(c+1,d+1,3);
		}
		
		else if(dir==2) {
			//2->2
			if(c+1<n && arr[c+1][d]==0)
				solve(c+1,d,2);
			//2->3
			if(c+1<n && d+1<n && arr[c][d+1]==0 &&
					arr[c+1][d+1]==0 && arr[c+1][d]==0)
				solve(c+1,d+1,3);
		}
		
		else if(dir==3) {
			//3->1
			if(d+1<n && arr[c][d+1]==0)
				solve(c,d+1,1);
			//3->2
			if(c+1<n && arr[c+1][d]==0)
				solve(c+1,d,2);
			//3->3
			if(c+1<n && d+1<n && arr[c][d+1]==0 &&
					arr[c+1][d+1]==0 && arr[c+1][d]==0)
				solve(c+1,d+1,3);
		}
	}
}