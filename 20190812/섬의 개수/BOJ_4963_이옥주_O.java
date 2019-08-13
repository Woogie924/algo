import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_4963_¿Ãø¡¡÷_O {
	static int[][] arr;
	static int[] x= {0,-1,-1,-1,0,1,1,1};
	static int[] y= {-1,-1,0,1,1,1,0,-1};
	static Queue<position> q = new LinkedList<>();
	static int count = 0;
	static int w,h;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str;
		
		while(true) {
			count = 0;
			str = br.readLine().split(" ");
			w = Integer.parseInt(str[1]);
			h = Integer.parseInt(str[0]);
			arr = new int[w][h];
			
			if(w==0 & h==0) break;
			
			for(int i=0; i<w; i++) {
				str = br.readLine().split(" ");
				
				for(int j=0; j<h; j++) {
					arr[i][j] = Integer.parseInt(str[j]);
				}
			}  //0¿Ã∏È πŸ¥Ÿ, 1¿Ã∏È ∂•
			
			for(int i=0; i<w; i++) {
				for(int j=0; j<h; j++) {
					if(arr[i][j] == 0) continue;
					arr[i][j] = 0;
					count++;
					q.offer(new position(i,j));
					bfs();
				}
			}
			
			System.out.println(count);	
		}
	}
	static position p = new position(0, 0);
	static int tempX = 0;
	static int tempY = 0;
	static void bfs() {
		while(!q.isEmpty()) {
			p = q.poll();
			tempX = p.x;
			tempY = p.y;
			
			for(int k=0; k<8; k++) {
				if(tempX+x[k]<0 || tempX+x[k]>=w || tempY+y[k]<0 || tempY+y[k]>=h || arr[tempX+x[k]][tempY+y[k]]==0) continue;
				arr[tempX+x[k]][tempY+y[k]] = 0;
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
