import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOj_15662_현병록_O {
	static char wheel[][];
	static int wNum, insNum, wStart[], size;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		size = Integer.parseInt(br.readLine());
		wheel = new char[size+1][8]; // 톱니바퀴 0-n, 1-s (바퀴 4개 톱니 8개씩)
		wStart = new int[size+1];
		int sum=0;
		//바퀴값을 입력받는다 index 0부터 12시방향으로 시계방향
		for(int i =1; i<=size; i++) {
			wheel[i] = br.readLine().toCharArray();
		}
		int ins = Integer.parseInt(br.readLine());

		///명령어의 갯수만큼 명령을 실행
		for(int i=0; i<ins; i++) {
			st = new StringTokenizer(br.readLine());
			wNum = Integer.parseInt(st.nextToken()); insNum = Integer.parseInt(st.nextToken());
			doIns();
		}
		//점수 계산
		for(int i=1; i<=size; i++) {
			if(wheel[i][checkIndex(0, i)]=='1') sum++;
		}

		System.out.println(sum);
	}
	private static void doIns() {
		int rtv[] = new int[size+1];
		rtv[wNum] = insNum;
		for(int i =wNum+1; i<wheel.length; i++) {//자기 오른쪽에 있는애들을 회전해준다.
			if(wheel[i-1][checkIndex(2, i-1)]!=wheel[i][checkIndex(6, i)]) {//같지않을때 역방향으로 회전시켜준다.
				rtv[i] = rtv[i-1]*-1;
			}else break;//옆이 같으면 그대로 끝
		}
		for(int i=wNum-1; i>0; i--) {//자기 왼쪽에 있는 애들을 회전해준다.
			if(wheel[i+1][checkIndex(6, i+1)]!=wheel[i][checkIndex(2, i)]) {//같지않을때 역방향으로 회전시켜준다.
				rtv[i] = rtv[i+1]*-1;
			}else break;//옆이 같으면 그대로 끝
		}
		for(int i =1; i<wStart.length; i++) {
			wStart[i] += rtv[i];
		}
	}
	private static int checkIndex(int index,int w) {
		int num = index+((-1)*wStart[w]);
		num%=8;
		if(num<0) return 8+num;
		else return num;
	}
}
