import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17136_신채연_X {
	static BufferedReader reader;
	static StringTokenizer st;
	static int[][] arr;
	static int p5,p4,p3,p2,p1;
	static ArrayList<Integer> list1; 	//순열을 위한 임시배열
	static ArrayList<Integer> minimum;	//색종이의 최소갯수를 구하기 위한 배열
	static boolean[] visited;
	static boolean clear;

	public static void main(String[] args) throws Exception{
		reader = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[10][10];
		visited = new boolean[6];
		for(int i=0;i<10;i++) {
			st = new StringTokenizer(reader.readLine());
			for(int j=0;j<10;j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		list1 = new ArrayList<Integer>();
		minimum = new ArrayList<Integer>();
		combi();

		if(clear) {
			int min=Integer.MAX_VALUE;
			for(int i=0;i<minimum.size();i++)
				if(min>minimum.get(i)) min=minimum.get(i);
			System.out.println(min);
		}
		else System.out.println("-1");
	}

	public static void combi() {
		if(list1.size()==5) {
			paste();
			return;
		}
		
		for(int i=1;i<=5;i++) {
			if(visited[i]) continue;
			visited[i]=true;
			list1.add(i);
			combi();
			list1.remove(list1.size()-1);
			visited[i]=false;
		}
		
	}

	public static void paste() {
		System.out.println(list1);
		p5=p4=p3=p2=p1=5;

		int[][] arr_clone = new int[10][10];
		for(int i=0;i<=9;i++) {
			for(int j=0;j<=9;j++) {
				arr_clone[i][j]=arr[i][j];
			}
		}
		
		for(int i=0;i<5;i++) {
			switch(list1.get(i)) {
			case 1:	do1(arr_clone); break;
			case 2: do2(arr_clone); break;
			case 3: do3(arr_clone); break;
			case 4: do4(arr_clone); break;
			case 5: do5(arr_clone); break;
			}
		}
		
		//모두 가려졌나 확인
		boolean flag = false;
   loop:for(int i=0;i<=9;i++) {
			for(int j=0;j<=9;j++) {
				if(arr_clone[i][j]==1) {
					flag=true;
					break loop;
				}
			}
		}
		
		if(flag==false) {
			minimum.add(25-p5-p4-p3-p2-p1);
			clear=true;
		}	
	}

	public static void do5(int[][] arr_clone) { //5x5
		for(int i=0;i<=5;i++) {
			for(int j=0;j<=5;j++) {
				if(arr_clone[i][j]==1) {
					int count=0;
					for(int k=0;k<5;k++) {
						for(int l=0;l<5;l++) {
							if(arr_clone[i+k][j+l]==1) count++;
						}
					}
					if(count==25 && p5>0) {
						p5--;
						for(int k=0;k<5;k++) {
							for(int l=0;l<5;l++) {
								if(arr_clone[i+k][j+l]==1)
									arr_clone[i+k][j+l]=0;
							}
						}
					}
				}
			}
		}
	}

	public static void do4(int[][] arr_clone) { //4x4
		for(int i=0;i<=6;i++) {
			for(int j=0;j<=6;j++) {
				if(arr_clone[i][j]==1) {
					int count=0;
					for(int k=0;k<4;k++) {
						for(int l=0;l<4;l++)
							if(arr_clone[i+k][j+l]==1) count++;
					}
					if(count==16 && p4>0) {
						p4--;
						for(int k=0;k<4;k++) {
							for(int l=0;l<4;l++) {
								if(arr_clone[i+k][j+l]==1)
									arr_clone[i+k][j+l]=0;
							}
						}
					}
				}
			}
		}
	}

	public static void do3(int[][] arr_clone) { //3x3
		for(int i=0;i<=7;i++) {
			for(int j=0;j<=7;j++) {
				if(arr_clone[i][j]==1) {
					int count=0;
					for(int k=0;k<3;k++) {
						for(int l=0;l<3;l++)
							if(arr_clone[i+k][j+l]==1) count++;
					}
					if(count==9 && p3>0) {
						p3--;
						for(int k=0;k<3;k++) {
							for(int l=0;l<3;l++) {
								if(arr_clone[i+k][j+l]==1)
									arr_clone[i+k][j+l]=0;
							}
						}
					}
				}
			}
		}
	}

	public static void do2(int[][] arr_clone) { //2x2
		for(int i=0;i<=8;i++) {
			for(int j=0;j<=8;j++) {
				if(arr_clone[i][j]==1) {
					int count=0;
					for(int k=0;k<2;k++) {
						for(int l=0;l<2;l++)
							if(arr_clone[i+k][j+l]==1) count++;
					}
					if(count==4 && p2>0) {
						p2--;
						for(int k=0;k<2;k++) {
							for(int l=0;l<2;l++) {
								if(arr_clone[i+k][j+l]==1)
									arr_clone[i+k][j+l]=0;
							}
						}
					}
				}
			}
		}
	}

	public static void do1(int[][] arr_clone) { //1x1
		for(int i=0;i<=9;i++) {
			for(int j=0;j<=9;j++) {
				if(arr_clone[i][j]==1 && p1>0) {
					p1--;
					arr_clone[i][j]=0;
				}
			}
		}
	}
}
