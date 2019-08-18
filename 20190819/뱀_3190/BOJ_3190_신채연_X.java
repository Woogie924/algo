//뱀

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_3190_신채연_X {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(reader.readLine());
		boolean[][] arr = new boolean[n][n];

		int numApple = Integer.parseInt(reader.readLine());
		boolean[][] isApple = new boolean[n][n];	//사과가있으면 true
		for(int i=0;i<numApple;i++) {
			st = new StringTokenizer(reader.readLine());
			isApple[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=true;
		}
		
		int times = Integer.parseInt(reader.readLine());
		int[] times_arr = new int[times];
		int dir=1;	//우1 하2 좌3 상0
		int a=0;	//머리의 행
		int b=0;	//머리의 열
		int c=0;	//꼬리의 행
		int d=0;	//꼬리의 열
		arr[a][b]=true;
		int count=0;	//몇 초가 지났는지

		loop1:
		for(int i=0;i<times;i++) {
			st = new StringTokenizer(reader.readLine());
			times_arr[i] = Integer.parseInt(st.nextToken());
			int num = times_arr[i];
			if(i>=1)
				num = num - times_arr[i-1];
			
			for(int j=0;j<num;j++) {
				switch(dir) {
				case 1:	//오른쪽으로 가는 경우
					//범위를 벗어나지는 않는지, 갈 칸이 자기 몸인지  체크
					if(a<0||b+1<0||a>=n||b+1>=n||arr[a][b+1]==true) {
						break loop1;
					}
					//사과가 있는지 확인하고
					if(isApple[a][b+1]) {
						//사과가 있으면 먹고, 머리만 이동
						isApple[a][b+1]=false;
						arr[a][b+1]=true;
						b++;
						count++;
						break;
					}
					else {
						//없으면 머리 이동, 꼬리도 이동
						arr[a][b+1]=true;
						arr[c][d]=false;
						b++;
						d++;
						count++;
						break;
					}
				case 2:	//아래쪽으로 가는 경우
					if(a+1<0||b<0||a+1>=n||b>=n||arr[a+1][b]==true) {
						break loop1;
					}
					if(isApple[a+1][b]) {
						isApple[a+1][b]=false;
						arr[a+1][b]=true;
						a++;
						count++;
						break;
					}
					else {
						arr[a+1][b]=true;
						arr[c][d]=false;
						a++;
						c++;
						count++;
						break;
					}
				case 3:
					if(a<0||b-1<0||a>=n||b-1>=n||arr[a][b-1]==true) {
						break loop1;
					}
					if(isApple[a][b-1]) {
						isApple[a][b-1]=false;
						arr[a][b-1]=true;
						b++;
						count++;
						break;
					}
					else {
						arr[a][b-1]=true;
						arr[c][d]=false;
						b--;
						d--;
						count++;
						break;
					}
				case 0:
					if(a-1<0||b<0||a-1>=n||b>=n||arr[a-1][b]==true) {
						break loop1;
					}
					if(isApple[a-1][b]) {
						isApple[a-1][b]=false;
						arr[a-1][b]=true;
						a--;
						count++;
						break;
					}
					else {
						arr[a-1][b]=true;
						arr[c][d]=false;
						a--;
						c--;
						count++;
						break;
					}
				}//end of switch
			}//end of for (한 줄 끝)
			//방향바꾸기
			char ch = st.nextToken().charAt(0);
			if(ch=='L') dir = (dir+3)%4;
			if(ch=='D') dir = (dir+1)%4;
		}//end of for (세 줄 끝)

		//끝나고 마지막 방향으로 갈 수 있을 때까지 가기
		switch(dir) {
		case 1:
			while(!(a<0||b+1<0||a>=n||b+1>=n||arr[a][b+1]==true)) {
				if(isApple[a][b+1]) {
					isApple[a][b+1]=false;
					arr[a][b+1]=true;
					b++;
					count++;
				}
				else {
					arr[a][b+1]=true;
					arr[c][d]=false;
					b++;
					d++;
					count++;
				}
			}
			break;
		case 2:
			while(!(a+1<0||b<0||a+1>=n||b>=n||arr[a+1][b]==true)) {
				if(isApple[a+1][b]) {
					isApple[a+1][b]=false;
					arr[a+1][b]=true;
					a++;
					count++;
				}
				else {
					arr[a+1][b]=true;
					arr[c][d]=false;
					a++;
					c++;
					count++;
				}
			}
			break;
		case 3:
			while(!(a<0||b-1<0||a>=n||b-1>=n||arr[a][b-1]==true)) {
				if(isApple[a][b-1]) {
					isApple[a][b-1]=false;
					arr[a][b-1]=true;
					b++;
					count++;
				}
				else {
					arr[a][b-1]=true;
					arr[c][d]=false;
					b--;
					d--;
					count++;
				}
			}
			break;
		case 0:
			while(!(a-1<0||b<0||a-1>=n||b>=n||arr[a-1][b]==true)) {
				if(isApple[a-1][b]) {
					isApple[a-1][b]=false;
					arr[a-1][b]=true;
					a--;
					count++;
				}
				else {
					arr[a-1][b]=true;
					arr[c][d]=false;
					a--;
					c--;
					count++;
				}
			}
			break;
		}
		
		writer.write(count+1+"");	//아무튼 1더함...
		writer.flush();
	}
}
