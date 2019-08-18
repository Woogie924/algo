//톱니바퀴

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14891_신채연_O {
	static BufferedReader reader;
	static StringTokenizer st;
	static ArrayList<Integer> t1;
	static ArrayList<Integer> t2;
	static ArrayList<Integer> t3;
	static ArrayList<Integer> t4;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		String s="";
		
		t1 = new ArrayList<Integer>();
		s = reader.readLine();
		for(int j=0;j<8;j++)
			t1.add(s.charAt(j)-'0');
		t2 = new ArrayList<Integer>();
		s = reader.readLine();
		for(int j=0;j<8;j++)
			t2.add(s.charAt(j)-'0');
		t3 = new ArrayList<Integer>();
		s = reader.readLine();
		for(int j=0;j<8;j++)
			t3.add(s.charAt(j)-'0');
		t4 = new ArrayList<Integer>();
		s = reader.readLine();
		for(int j=0;j<8;j++)
			t4.add(s.charAt(j)-'0');
		
		int times = Integer.parseInt(reader.readLine());
		for(int i=0;i<times;i++) {	//한 회전
			st = new StringTokenizer(reader.readLine());
			int t = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());	//1:시계, -1:반시계
			
			//** same12_4는 4번째 톱니에서 1번톱니와 2번톱니가 맞닿은 부분이 같은지여부!!
			switch(t) {
			case 1:
				boolean same12_1 = false;
				if(t1.get(2)==t2.get(6)) same12_1=true;
				boolean same23_1 = false;
				if(t2.get(2)==t3.get(6)) same23_1=true;
				boolean same34_1 = false;
				if(t3.get(2)==t4.get(6)) same34_1=true;
				
				if(dir==1) {//시계방향
					cw(t1);
					if(same12_1==false) ccw(t2);
					else break;
					if(same23_1==false) cw(t3);
					else break;
					if(same34_1==false) ccw(t4);
					else break;
				}
				
				if(dir==-1) {//반시계방향
					ccw(t1);
					if(same12_1==false) cw(t2);
					else break;
					if(same23_1==false) ccw(t3);
					else break;
					if(same34_1==false) cw(t4);
					else break;
				}
				break;
			case 2:
				boolean same12_2 = false;
				if(t1.get(2)==t2.get(6)) same12_2=true;
				boolean same23_2 = false;
				if(t2.get(2)==t3.get(6)) same23_2=true;
				boolean same34_2 = false;
				if(t3.get(2)==t4.get(6)) same34_2=true;
				
				if(dir==1) {//시계방향
					cw(t2);
					if(same12_2==false) ccw(t1);
					if(same23_2==false) ccw(t3);
					else break;
					if(same34_2==false) cw(t4);
					else break;
				}
				
				if(dir==-1) {//반시계방향
					ccw(t2);
  					if(same12_2==false) cw(t1);
					if(same23_2==false) cw(t3);
					else break;
					if(same34_2==false) ccw(t4);
					else break;
				}
				break;
			case 3:
				boolean same12_3 = false;
				if(t1.get(2)==t2.get(6)) same12_3=true;
				boolean same23_3 = false;
				if(t2.get(2)==t3.get(6)) same23_3=true;
				boolean same34_3 = false;
				if(t3.get(2)==t4.get(6)) same34_3=true;
				
				if(dir==1) {//시계방향
					cw(t3);
					if(same34_3==false) ccw(t4);
					if(same23_3==false) ccw(t2);
					else break;
					if(same12_3==false) cw(t1);
					else break;
				}
				
				if(dir==-1) {//반계방향
					ccw(t3);
					if(same34_3==false) cw(t4);
					if(same23_3==false) cw(t2);
					else break;
					if(same12_3==false) ccw(t1);
					else break;
				}
				break;
			case 4:
				boolean same12_4=false;
				if(t1.get(2)==t2.get(6)) same12_4=true;
				boolean same23_4=false;
				if(t2.get(2)==t3.get(6)) same23_4=true;
				boolean same34_4=false;
				if(t3.get(2)==t4.get(6)) same34_4=true;
				
				if(dir==1) {//시계방향
					cw(t4);
					if(same34_4==false) ccw(t3);
					else break;
					if(same23_4==false) cw(t2);
					else break;
					if(same12_4==false) ccw(t1);
					else break;
				}
				
				if(dir==-1) {//반시계방향
					ccw(t4);
					if(same34_4==false) cw(t3);
					else break;
					if(same23_4==false) ccw(t2);
					else break;
					if(same12_4==false) cw(t1);
					else break;
				}
				break;
			}//end of switch
		}//end of for (한 회전)
		
		int score=0;
		if(t1.get(0)==1) score+=1;
		if(t2.get(0)==1) score+=2;
		if(t3.get(0)==1) score+=4;
		if(t4.get(0)==1) score+=8;
		
		System.out.println(score);
	}
	
	public static void cw(ArrayList<Integer> list) {	//시계
		//맨 뒤에 것을 꺼내서 맨 앞에 넣기
		int a = list.get(list.size()-1);
		list.remove(list.size()-1);
		list.add(0, a);
	}
	
	public static void ccw(ArrayList<Integer> list) {	//반시계
		//맨 앞에 것을 꺼내서 맨 뒤에 넣기
		int a = list.get(0);
		list.remove(0);
		list.add(a);
	}
}
