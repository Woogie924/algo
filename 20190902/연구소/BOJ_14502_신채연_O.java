import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ_14502_신채연_O

public class BOJ_14502_신채연_O {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	static int[][] arr;
	static int empty;	//n과 m에서 n=empty, m=3
	static ArrayList<Integer> emptyList;
	static ArrayList<Integer> safe;
	static int n,m;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		
		empty=0;	//비어있는 공간의 갯수
		emptyList = new ArrayList<Integer>();
		safe = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(reader.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==0) {
					empty++;
					emptyList.add(i);
					emptyList.add(j);
				}
			}
		}
		
		solve(new ArrayList<Integer>());
		int max=Integer.MIN_VALUE;
		for(int i : safe)
			if(max<i) max=i;
		System.out.println(max);
	}
	
	public static void solve(ArrayList<Integer> picked) {
		if(picked.size()==3) {
			buildWall(picked);
			return;
		}
		
		int smallest = picked.isEmpty()? 0 : (picked.get(picked.size()-1))+1;
		for(int next=smallest;next<empty;next++) {
			picked.add(next);
			solve(picked);
			picked.remove(picked.size()-1);
		}
	}
	
	public static void buildWall(ArrayList<Integer> picked) {
		int index=0;
		for(int i=0;i<3;i++) {
			int x = emptyList.get(2*picked.get(index));
			int y = emptyList.get(2*picked.get(index)+1);
			arr[x][y]=1;
			index++;
		}
		
		//세 개의 벽을 세웠으면
		virus();
		
		//세운 벽을 원래대로 복구
		index=0;
		for(int i=0;i<3;i++) {
			int x = emptyList.get(2*picked.get(index));
			int y = emptyList.get(2*picked.get(index)+1);
			arr[x][y]=0;
			index++;
		}
	}

	public static void virus() {
		int[][] arr2 = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++)
				arr2[i][j]=arr[i][j];
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		boolean discovered[][] = new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(arr2[i][j]==2) {
					q.add(i);
					q.add(j);
					discovered[i][j]=true;
					while(!q.isEmpty()) {
						int x = q.poll();
						int y = q.poll();
						for(int k=0;k<4;k++) {
							int tx=x+dx[k];
							int ty=y+dy[k];
							if(tx>=0&&ty>=0&&tx<n&&ty<m&&arr2[tx][ty]==0) {
								arr2[tx][ty]=2;
								q.add(tx);
								q.add(ty);
							}
						}
					}
				}
			}
		}
		
		int count=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(arr2[i][j]==0) count++;
			}
		}
		safe.add(count);
	}
}
