//톱니바퀴2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15662_신채연_O {
	static BufferedReader reader;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		String s="";
		int tnum = Integer.parseInt(reader.readLine());	//톱니바퀴 개수
		int[][] arr = new int[tnum][8];
		for(int i=0;i<tnum;i++) {
			s = reader.readLine();
			for(int j=0;j<8;j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
		
		int times = Integer.parseInt(reader.readLine());
		for(int i=0;i<times;i++) {	//한 회전
			st = new StringTokenizer(reader.readLine());
			int t = Integer.parseInt(st.nextToken());
			t--; //인덱싱
			int dir = Integer.parseInt(st.nextToken());	//1:시계, -1:반시계
			
			boolean[] isSame = new boolean[tnum-1];	//false 초기화
			
			for(int j=0;j<tnum-1;j++)
				if(arr[j][2]==arr[j+1][6]) isSame[j]=true;
			
			if(dir==1) {//시계
				cw(arr[t],1);	//1은 시계, -1은 반시계로 인자값 넘기기
				int flag1=dir;
				for(int k=t-1;k>=0;k--) {
					if(isSame[k]==false) {
						flag1=flag1*-1;
						cw(arr[k],flag1);
					}
					else break;
				}
				int flag2=dir;
				for(int k=t+1;k<tnum;k++) {
					if(isSame[k-1]==false) {
						flag2=flag2*-1;
						cw(arr[k],flag2);
					}
					else break;
				}
			}
			if(dir==-1) {//반시계
				cw(arr[t],-1);
				int flag1=dir;
				for(int k=t-1;k>=0;k--) {
					if(isSame[k]==false) {
						flag1=flag1*-1;
						cw(arr[k],flag1);
					}
					else break;
				}
				int flag2=dir;
				for(int k=t+1;k<tnum;k++) {
					if(isSame[k-1]==false) {
						flag2=flag2*-1;
						cw(arr[k],flag2);
					}
					else break;
				}
			}
		}//end of for (한 회전)
		
		int score=0;
		for(int i=0;i<tnum;i++)
			if(arr[i][0]==1)score++;
		System.out.println(score);
	}
	
	public static void cw(int[] arr, int a) {	//시계
		if(a==1) {	//시계 : 맨 뒤에 것을 꺼내서 맨 앞에 넣기
			int temp = arr[7];
			arr[7]=arr[6];
			arr[6]=arr[5];
			arr[5]=arr[4];
			arr[4]=arr[3];
			arr[3]=arr[2];
			arr[2]=arr[1];
			arr[1]=arr[0];
			arr[0]=temp;
		}
		if(a==-1) {	//반시계 : 맨 앞에 것을 꺼내서 맨 뒤에 넣기
			int temp = arr[0];
			arr[0]=arr[1];
			arr[1]=arr[2];
			arr[2]=arr[3];
			arr[3]=arr[4];
			arr[4]=arr[5];
			arr[5]=arr[6];
			arr[6]=arr[7];
			arr[7]=temp;
		}
	}
}
