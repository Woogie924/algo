import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2667_이옥주_O {
	static int[][] arr;
	static int count = 0;
	static int cnt = 0;
	static int size;
	static Queue<position> q = new LinkedList<position>();
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		String[] str;
		
		arr = new int[size][size];
		
		for(int i=0; i<size; i++) {
			str = br.readLine().split("");
			
			for(int j=0; j<size; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}  // 입력받기
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(arr[i][j]==0) continue;
				cnt=1;
				arr[i][j] = 0;
				q.offer(new position(i,j));
				count++;
				bfs();
				list.add(cnt);
			}
		}
		
		System.out.println(count);
		Collections.sort(list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}

	}
	static int[] x = {0,0,-1,1};
	static int[] y = {-1,1,0,0};
	static position po = new position(0, 0);
	static int tempX;
	static int tempY;
	
	static void bfs() {
		while(!q.isEmpty()) {
			po = q.poll();
			tempX = po.x;
			tempY = po.y;
			
			for(int k=0; k<4; k++) {
				if(tempX+x[k]<0 ||tempX+x[k]>=size || tempY+y[k]<0 || tempY+y[k]>=size || arr[tempX+x[k]][tempY+y[k]]==0) continue;
				arr[tempX+x[k]][tempY+y[k]] = 0;
				cnt++;
				q.offer(new position(tempX+x[k],tempY+y[k]));
			}
		}
	}

	static class position{
		int x;
		int y;
		
		public position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
