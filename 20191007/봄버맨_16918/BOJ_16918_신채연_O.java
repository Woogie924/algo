import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16918_신채연_O {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int sec = Integer.parseInt(st.nextToken());	//몇초후
		
		int[][] arr = new int[n][m];
		for(int i=0;i<n;i++) {
			String s = reader.readLine();
			for(int j=0;j<m;j++) {
				if(s.charAt(j)=='O') arr[i][j]=2;
				else arr[i][j]=0;
			}
		}
		sec--;
		
		boolean[][] temp = new boolean[n][m];
		while(true) {
			if(sec==0) break;
			
			//비어있는 곳에 폭탄 설치
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++)
					if(arr[i][j]==0) arr[i][j]=1;
			}
			sec--;
			if(sec==0) break;
			
			for(int i=0;i<n;i++) {	//2였던 곳이 1로 바뀌면 안되서...
				for(int j=0;j<m;j++)
					if(arr[i][j]==2) temp[i][j]=true;
					else temp[i][j]=false;
			}
			
			//폭탄터짐
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(temp[i][j]==true) {		
						for(int k=0;k<4;k++) {
							int tx = i+dx[k];
							int ty = j+dy[k];
							if(tx<0||ty<0||tx>=n||ty>=m) continue;
							arr[tx][ty]=0;
							arr[i][j]=0;
						}
					}
				}
			}
			sec--;
			if(sec==0) break;
			
			//폭탄 시간 차 조정
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++)
					if(arr[i][j]==1) arr[i][j]=2;
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++)
				if(arr[i][j]==2 || arr[i][j]==1) writer.write("O");
				else writer.write(".");
			writer.newLine();
		}
		writer.flush();
	}
}
