import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st; 
	static int N, max,qsize;
	static int[][] arr;
	static int[][] clone;
	static int[] com = new int[5];
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		clone = new int[N][N];
		arr = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				clone[i][j]=Integer.parseInt(st.nextToken());
			}
		} // make map
		
		max = Integer.MIN_VALUE;
		makeperm(0);
		System.out.println(max);
		
		
	}

	private static void makeperm(int cnt) {
		if(cnt==5) {
			reset();
			for(int a=0;a<5;a++) {
//				if(com[0]==2)
//					System.out.println();
				move(com[a]);
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(arr[i][j]<max)continue;
					else max=arr[i][j];
				}
			}
			return;
		}
		
		for(int a=0;a<4;a++) {
			com[cnt]=a;
			makeperm(cnt+1);
		}
	}


	private static void move(int dir) {
		switch(dir) {
		// �� - finish
		case 0:
			for(int b=0;b<N;b++) {
				pushu(b);
				for(int a=0;a<N-1;a++) {
					if(arr[a+1][b]==0)break;
					if(arr[a][b]==arr[a+1][b]) {
						arr[a][b]=arr[a][b]*2;
						for(int c=a+2;c<N;c++) {
							arr[c-1][b]=arr[c][b];
							if(arr[c][b]==0)break;
						} // ������ �ױ�
						arr[N-1][b]=0;
					}					
				}// ��
			}// �� ��� üũ
			
			break; 
			
		// ��
		case 1:
			for(int b=0;b<N;b++) {
				pushd(b);
				for(int a=N-1;a>0;a--) { //if 3= 2,1
					if(arr[a-1][b]==0)break;
					if(arr[a][b]==arr[a-1][b]) {
						arr[a][b]=arr[a][b]*2;
						for(int c=a-2;c>=0;c--) {
							arr[c+1][b]=arr[c][b];
							if(arr[c][b]==0)break;
						} // ������ �ױ�
						arr[0][b]=0;
					}
				}// ��
			}// �� ��� üũ
			break;
			
		// �� - finish
		case 2:
			for(int b=0;b<N;b++) {
				pushl(b);
				for(int a=0;a<N-1;a++) {
					if(arr[b][a+1]==0)break;
					if(arr[b][a]==arr[b][a+1]) {
						arr[b][a]=arr[b][a]*2;
						for(int c=a+2;c<N;c++) {
							arr[b][c-1]=arr[b][c];
							if(arr[b][c]==0)break;
						} // ������ �ױ�
						arr[b][N-1]=0;
					}
				}// ��
			}// �� ��� üũ
			break;
			
		// ��
		case 3:
			for(int b=0;b<N;b++) {
				pushr(b);
				for(int a=N-1;a>0;a--) { //if 3= 2,1
					if(arr[b][a-1]==0)break;
					if(arr[b][a]==arr[b][a-1]) {
						arr[b][a]=arr[b][a]*2;
						for(int c=a-2;c>=0;c--) {
							arr[b][c+1]=arr[b][c];
							if(arr[b][c]==0)break;
						} // ������ �ױ�
						arr[b][0]=0;
					}
					
				}// ��
			}// �� ��� üũ
			break;
		}
		
		
	}

	private static void pushr(int i) { // �����ʹб�
		for(int j=0;j<N;j++) {
			if(arr[i][j]!=0) {
			q.offer(arr[i][j]);
			arr[i][j]=0;
			}
		}
		qsize=q.size();
		for(int j=N-qsize;j<N;j++) {
			arr[i][j]=q.poll();
		}
	}

	private static void pushd(int j) { // �Ʒ��б�
		for(int i=0;i<N;i++) {
			if(arr[i][j]!=0) {
			q.offer(arr[i][j]);
			arr[i][j]=0;
			}
		}
		qsize=q.size();
		for(int i=N-qsize;i<N;i++) {
			arr[i][j]=q.poll();
		}
	}

	private static void pushu(int j) { // ���ιб�
		for(int i=0;i<N;i++) {
			if(arr[i][j]!=0) {
			q.offer(arr[i][j]);
			arr[i][j]=0;
			}
		}
		qsize=q.size();
		for(int i=0;i<qsize;i++) {
			arr[i][j]=q.poll();
		}
	}

	private static void pushl(int i) { // ���ʹб�
		for(int j=0;j<N;j++) {
			if(arr[i][j]!=0) {
			q.offer(arr[i][j]);
			arr[i][j]=0;
			}
		}
		qsize=q.size();
		for(int j=0;j<qsize;j++) {
			arr[i][j]=q.poll();
		}
	}
	

	private static void reset() { // �迭�ʱ�ȭ
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				arr[i][j]=clone[i][j];
			}
		}
	}
	

}
