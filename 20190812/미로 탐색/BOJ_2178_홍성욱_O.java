import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 

(1) 1,1 ���� ���...
	0,0���� ���..

(2) 1�� �̵� ����
	0�� �̵� �Ұ�

(3) ť�� �̿��� �̵��� �� �ִ� ���� ��ǥ�� ����
	���� ��ǥ �̵����� ���� �Ǻ�
	---:: �ܰ�����?
	---:: 1(��) ����?
	
(4) ��� ȣ��

 */
public class Main {
	static int N;
	static int M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");

		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		// N���� �ٿ� M���� ����
		visited = new boolean [N][M];
		String [][] MAP = new String[N][M];

		for(int i=0; i<N ; i++) {
			MAP[i]= br.readLine().split("");
		}
		int cnt =1;		//�����
		sol(MAP, 0, 0, cnt);
	}
	static boolean [][] visited ;
	
	static int [] dx = {0,0,-1,1};	//�����¿�,,
	static int [] dy = {-1,1,0,0};	
	static void sol(String [][] MAP, int x, int y, int cnt) {
		Queue<position> q = new LinkedList<position>();
		position point = new position(x,y, cnt);
		visited[point.x][point.y] = true;
		
		q.offer(point);
		
		//���� ��ġ (0, 0)
		//���� ��ġ (N-1, M-1)
		int tx=0;
		int ty=0;
		int count=0 ;
		while(!q.isEmpty()) {
			if(q.peek().x == MAP.length-1 && q.peek().y == MAP[1].length-1) {	//��ǥ �����̸�,, ����
				System.out.println(q.peek().cnt);
				return;
			}
			
			point = q.poll();
			
			count = point.cnt;
			
			for(int i=0; i<dx.length; i++) {
				tx = point.x + dx[i];
				ty = point.y + dy[i];
				
				if(tx <0 || ty <0 || tx>=MAP.length || ty>=MAP[1].length) {		// �ܰ��̸� �ǳʶٱ�
					continue;
				}
				
				if(!visited[tx][ty] && MAP[tx][ty].equals("1")) {			//�湮���� ����, 1�̸�,,,
					//System.out.println(tx+", "+ty);
					visited[tx][ty]= true;
					q.offer(new position(tx,ty,count+1));
					
				}
			}
		
			
		}
		
		
	}
	static class position{
		int x;
		int y;
		int cnt;
		position(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		position(){}
	}
}