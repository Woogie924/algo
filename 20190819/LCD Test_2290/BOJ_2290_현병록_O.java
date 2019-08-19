import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2290_현병록_O {
	static int s;
	static String n;
	static String[][] garo,sero;
	static int[][] number = {
			{0,2,1,2,0},
			{1,1,1,1,1},
			{0,1,0,0,0},
			{0,1,0,1,0},
			{1,2,0,1,1},
			{0,0,0,1,0},
			{0,0,0,2,0},
			{0,1,1,1,1},
			{0,2,0,2,0},
			{0,2,0,1,0}
			
	};
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		s = Integer.parseInt(st.nextToken());
		n = st.nextToken();
		garo = new String[2][s+2]; //0번째는 가로선 그리는거 , 1은 아무것도 안하는거
		sero = new String[4][s+2]; //0은 왼쪽끝 1은 오른쪽끝 2는 양쪽끝 3은 아무것도 안하는거
		
		prepare();
		//각 줄 그리기
		drawGaro(0);
		drawSero(1);
		drawGaro(2);
		drawSero(3);
		drawGaro(4);
		bw.flush();
	}
	public static void drawGaro(int index) throws IOException {
		for(int i =0; i<n.length(); i++) {
			for(int j=0; j<s+2; j++	) {
				bw.write(garo[number[n.charAt(i)-'0'][index]][j]);
			}
			bw.write(" ");
		}
		bw.newLine();
	}
	public static void drawSero(int index) throws IOException {
		for(int sn=0; sn<s; sn++) {
			for (int i = 0; i < n.length(); i++) {
				for (int j = 0; j < s + 2; j++) { 
					bw.write(sero[number[n.charAt(i) - '0'][index]][j]);
				}
				bw.write(" ");
			}
			bw.newLine();
		}
	}
	private static void prepare() {
		//우선 전부 공백으로 
		for(int i=0; i<garo.length; i++) {
			Arrays.fill(garo[i], " ");
		}
		for(int i=0; i<sero.length; i++) {
			Arrays.fill(sero[i], " ");
		}
		
		//garo준비
		//0번째꺼는 가로선을
		for(int i =1; i<s+1; i++)
			garo[0][i]="-";
		//sero준비
		//0번째거는 왼쪽끝
		sero[0][0]="|";
		//1번째꺼는 오른쪽끝
		sero[1][s+1]="|";
		//2번째꺼는 양쪽끝
		sero[2][0]="|"; 
		sero[2][s+1]="|";
	}
}
