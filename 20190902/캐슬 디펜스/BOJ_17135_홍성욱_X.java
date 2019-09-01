import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N=0;
	static int M=0;
	static int D=0;
	static int [][] MAP;
	static int [] Managedarchor;		//뽑힌 궁수 관리

	static int res=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String [] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		D = Integer.parseInt(input[2]);

		MAP = new int[N][M];
		Managedarchor = new int[M];
		ArrayList<Integer> archor = new ArrayList<>();		//아처 뽑기용

		for(int i=0; i< N ; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<M ; j++) {
				MAP[i][j] = Integer.parseInt(input[j]);
			}
		}

		selectArchor(M, 3, 0, 0, archor);		//1. 아처 선택! 2. 선택된 아처로 사정거리에 있는 적 잡기!
		System.out.println(res);
	}

	static boolean enemyFlag=false;
	static void moveEnemy() {
		//성 바로 앞 남은 적 세기! 있으면 함락!
		//한줄씩 전진
		for(int i=N-1 ; i>=1; i--) {	
			for(int j=0; j<M ; j++) {
				MAP[i][j] = MAP[i-1][j];
			}
		}
		for(int i=0; i<M; i++) {
			MAP[0][i] = 0;
		}
	}
	static int catchCount=0;
	static void attackEnemy(ArrayList<Integer> data) {
		/*
		 * (r1,c1) (r2,c2)의 거리는 |r1-r2| + |c1-c2|
		 * 4,1 5,1
		 */
		int [][] tempMAP = new int[N][M];
		for(int i=0; i< N;i++) {
			for(int j=0;j<M;j++) {
				tempMAP[i][j] = MAP[i][j];
			}
		}
		a:for(int archorNum =0; archorNum<data.size() ; archorNum++) {
			for(int i= N-1; i>=N-D ; i--) {
				for(int j=0; j<M ; j++) {
					if(tempMAP[i][j] == 1 && (Math.abs(i-N) + Math.abs(j - data.get(archorNum))<=D)){
						tempMAP[i][j] = 0;	//적 제거
						//	System.out.println(i+","+j+ "제거!");
						//	System.out.println("====================");
						catchCount++;
						continue a;
					}
				}
			}
		}
		for(int i=0; i< N;i++) {
			for(int j=0;j<M;j++) {
				MAP[i][j] = tempMAP[i][j];
			}
		}

	}

	static void selectArchor(int n, int r, int pivot, int depth, ArrayList<Integer> data) {
		if(r==0) {
			int [][] aaa = new int[N][M];
			for(int i=0; i< N;i++) {
				for(int j=0;j<M;j++) {
					aaa[i][j] = MAP[i][j];
				}
			}
			for(int phase = 0; phase<N; phase++) {
				//System.out.println((phase+1) +"phase");
				attackEnemy(data);
				moveEnemy();

			}
			if(catchCount>res) {
				//System.out.println(catchCount);
				res= catchCount;
			}
			catchCount =0;
			for(int i=0; i< N;i++) {
				for(int j=0;j<M;j++) {
					MAP[i][j] = aaa[i][j];
				}
			}
			return;
		}
		else if(depth == n) {
			return;
		}else {
			//선택했을 경우
			data.add(pivot);
			selectArchor(n, r-1, pivot+1, depth+1, data);

			data.remove(data.size()-1);
			selectArchor(n, r, pivot+1, depth+1, data);
			//선택 안했을 경우
		}
	}


}
