import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16920_신채연_X_시간초과 {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	static int[] dx= {0,1,0,-1};
	static int[] dy= {1,0,-1,0};
	static int[][] arr;
	static int n,m,p;
	
	public static void main(String[] args) throws Exception{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());		
		arr = new int[n][m];
		
		st = new StringTokenizer(reader.readLine());
		int[] si = new int[p];
		for(int i=0;i<p;i++)
			si[i]=Integer.parseInt(st.nextToken());
		
		for(int i=0;i<n;i++) {
			String s = reader.readLine();
			for(int j=0;j<m;j++)
				arr[i][j]=s.charAt(j)-'0'-1;
		}
		//벽은 -14, 빈 곳은 -3
		
		boolean[][] brr = new boolean[n][m];
		Queue<Integer> q = new LinkedList<>();
		while(true) {
			System.out.println(checkAll()+"111");
			if(checkAll()) break;
			
			
			for(int i=0;i<p;i++) {
				for(int g=0;g<n;g++) {
					for(int h=0;h<m;h++) {
						if(arr[g][h]==i) brr[g][h]=true;
						else brr[g][h]=false;
					}
				}
				for(int g=0;g<n;g++) {
					for(int h=0;h<m;h++) {
						if(brr[g][h]) {
							boolean[][] discovered = new boolean[n][m];
							q.add(g);
							q.add(h);
							
							while(!q.isEmpty()) {
								int xx = q.poll();
								int yy = q.poll();
								for(int k=0;k<4;k++) {
									int tx=xx+dx[k];
									int ty=yy+dy[k];
									if(tx<0||ty<0||tx>=n||ty>=m) continue;
									if(arr[tx][ty]==-3 && !discovered[tx][ty] 
											&& Math.abs(g-tx)+Math.abs(h-ty)<=si[i]) {
										arr[tx][ty]=i;
										discovered[tx][ty]=true;
										q.add(tx);
										q.add(ty);
									}
								}
							}
						}
					}
				}
				if(checkAll()) break;		
			}
		}
		
		int[] count = new int[p];
		for(int g=0;g<n;g++) {
			for(int h=0;h<m;h++) {
				if(0<=arr[g][h] && arr[g][h]<p)
					count[arr[g][h]]++;
			}
		}
		
		for(int i=0;i<p;i++)
			writer.write(count[i]+" ");
		writer.flush();
	}

	public static boolean checkAll() {
		for(int g=0;g<n;g++) {
			for(int h=0;h<m;h++)
				if(arr[g][h]==-3) return false;
		}
		return true;
	}
}
