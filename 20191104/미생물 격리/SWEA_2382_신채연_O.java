import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2382_신채연_O {
	static int n,m,k;	//n은 한변의 길이, m은 시간, k는 군집의 갯수
	static Micro[][][] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(reader.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(reader.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			arr = new Micro[n][n][5];

			int x,y,num,dir;
			for(int i=0;i<k;i++) {
				st = new StringTokenizer(reader.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				num = Integer.parseInt(st.nextToken());
				dir = Integer.parseInt(st.nextToken());
				arr[x][y][0] = new Micro(x,y,num,dir,false);
				//이동한 미생물은 true로 변경
			}

			for(int M=0;M<m;M++) {
				//모두 이동먼저 할거에요
				for(int i=0;i<n;i++) {
					for(int j=0;j<n;j++) {
						if(arr[i][j][0]!=null && !arr[i][j][0].check) {	//미생물이 있고, 아직 이동하지 않은 미생물이라면

							switch(arr[i][j][0].dir){
							case 1:	//상
								if(i-1==0) {					//약품을 만나면
									arr[i][j][0].num/=2;
									if(arr[i][j][0].num==0) {	//군집 크기가 0이 되면
										arr[i][j][0]=null;
										continue;
									}else {
										arr[i][j][0].dir=2;		//그게 아니면 방향전환하고 실제로 옮기기
										arr[i][j][0].check=true;

										if(arr[i-1][j][0]==null) arr[i-1][j][0]=arr[i][j][0];
										else if(arr[i-1][j][1]==null) arr[i-1][j][1]=arr[i][j][0];
										else if(arr[i-1][j][2]==null) arr[i-1][j][2]=arr[i][j][0];
										else if(arr[i-1][j][3]==null) arr[i-1][j][3]=arr[i][j][0];
										else if(arr[i-1][j][4]==null) arr[i-1][j][4]=arr[i][j][0];
										arr[i][j][0]=null;
									}
								}
								else if(arr[i-1][j][0]!=null) {	//약품을 만난건 아닌데, 가려는 자리에 누가 있으면
									arr[i][j][0].check=true;

									if(arr[i-1][j][1]==null) arr[i-1][j][1]=arr[i][j][0];
									else if(arr[i-1][j][2]==null) arr[i-1][j][2]=arr[i][j][0];
									else if(arr[i-1][j][3]==null) arr[i-1][j][3]=arr[i][j][0];
									else if(arr[i-1][j][4]==null) arr[i-1][j][4]=arr[i][j][0];
									arr[i][j][0]=null;
								}
								else {							//이동
									arr[i][j][0].check=true;
									arr[i-1][j][0]=arr[i][j][0];
									arr[i][j][0]=null;
								}
								break;

							case 2:	//하
								if(i+1==n-1) {				//약품을 만나면
									arr[i][j][0].num/=2;
									if(arr[i][j][0].num==0) {	//군집 크기가 0이 되면
										arr[i][j][0]=null;
										continue;
									}else {
										arr[i][j][0].dir=1;		//그게 아니면 방향전환하고 실제로 옮기기
										arr[i][j][0].check=true;

										if(arr[i+1][j][0]==null) arr[i+1][j][0]=arr[i][j][0];
										else if(arr[i+1][j][1]==null) arr[i+1][j][1]=arr[i][j][0];
										else if(arr[i+1][j][2]==null) arr[i+1][j][2]=arr[i][j][0];
										else if(arr[i+1][j][3]==null) arr[i+1][j][3]=arr[i][j][0];
										else if(arr[i+1][j][4]==null) arr[i+1][j][4]=arr[i][j][0];
										arr[i][j][0]=null;
									}
								}
								else if(arr[i+1][j][0]!=null) {	//약품을 만난건 아닌데, 가려는 자리에 누가 있으면
									arr[i][j][0].check=true;

									if(arr[i+1][j][1]==null) arr[i+1][j][1]=arr[i][j][0];
									else if(arr[i+1][j][2]==null) arr[i+1][j][2]=arr[i][j][0];
									else if(arr[i+1][j][3]==null) arr[i+1][j][3]=arr[i][j][0];
									else if(arr[i+1][j][4]==null) arr[i+1][j][4]=arr[i][j][0];
									arr[i][j][0]=null;
								}
								else {							//이동
									arr[i][j][0].check=true;
									arr[i+1][j][0]=arr[i][j][0];
									arr[i][j][0]=null;
								}
								break;

							case 3:	//좌
								if(j-1==0) {				//약품을 만나면
									arr[i][j][0].num/=2;
									if(arr[i][j][0].num==0) {	//군집 크기가 0이 되면
										arr[i][j][0]=null;
										continue;
									}else {
										arr[i][j][0].dir=4;		//그게 아니면 방향전환하고 실제로 옮기기
										arr[i][j][0].check=true;

										if(arr[i][j-1][0]==null) arr[i][j-1][0]=arr[i][j][0];
										else if(arr[i][j-1][1]==null) arr[i][j-1][1]=arr[i][j][0];
										else if(arr[i][j-1][2]==null) arr[i][j-1][2]=arr[i][j][0];
										else if(arr[i][j-1][3]==null) arr[i][j-1][3]=arr[i][j][0];
										else if(arr[i][j-1][4]==null) arr[i][j-1][4]=arr[i][j][0];
										arr[i][j][0]=null;
									}
								}
								else if(arr[i][j-1][0]!=null) {	//약품을 만난건 아닌데, 가려는 자리에 누가 있으면
									arr[i][j][0].check=true;

									if(arr[i][j-1][0]==null) arr[i][j-1][0]=arr[i][j][0];
									else if(arr[i][j-1][1]==null) arr[i][j-1][1]=arr[i][j][0];
									else if(arr[i][j-1][2]==null) arr[i][j-1][2]=arr[i][j][0];
									else if(arr[i][j-1][3]==null) arr[i][j-1][3]=arr[i][j][0];
									else if(arr[i][j-1][4]==null) arr[i][j-1][4]=arr[i][j][0];
									arr[i][j][0]=null;
								}
								else {							//이동
									arr[i][j][0].check=true;
									arr[i][j-1][0]=arr[i][j][0];
									arr[i][j][0]=null;
								}
								break;
							case 4:	//우
								if(j+1==n-1) {				//약품을 만나면
									arr[i][j][0].num/=2;
									if(arr[i][j][0].num==0) {	//군집 크기가 0이 되면
										arr[i][j][0]=null;
										continue;
									}else {
										arr[i][j][0].dir=3;		//그게 아니면 방향전환하고 실제로 옮기기
										arr[i][j][0].check=true;

										if(arr[i][j+1][0]==null) arr[i][j+1][0]=arr[i][j][0];
										else if(arr[i][j+1][1]==null) arr[i][j+1][1]=arr[i][j][0];
										else if(arr[i][j+1][2]==null) arr[i][j+1][2]=arr[i][j][0];
										else if(arr[i][j+1][3]==null) arr[i][j+1][3]=arr[i][j][0];
										else if(arr[i][j+1][4]==null) arr[i][j+1][4]=arr[i][j][0];
										arr[i][j][0]=null;
									}
								}
								else if(arr[i][j+1][0]!=null) {	//약품을 만난건 아닌데, 가려는 자리에 누가 있으면
									arr[i][j][0].check=true;

									if(arr[i][j+1][0]==null) arr[i][j+1][0]=arr[i][j][0];
									else if(arr[i][j+1][1]==null) arr[i][j+1][1]=arr[i][j][0];
									else if(arr[i][j+1][2]==null) arr[i][j+1][2]=arr[i][j][0];
									else if(arr[i][j+1][3]==null) arr[i][j+1][3]=arr[i][j][0];
									else if(arr[i][j+1][4]==null) arr[i][j+1][4]=arr[i][j][0];
									arr[i][j][0]=null;
								}
								else {							//이동
									arr[i][j][0].check=true;
									arr[i][j+1][0]=arr[i][j][0];
									arr[i][j][0]=null;
								}
								break;
							}//switch
						}
					}
				}

				//합치기
				for(int i=0;i<n;i++) {
					for(int j=0;j<n;j++) {
						if(isMoreThanZero(i,j)) {
							int temp_sum=0;
							int temp_max=-1;
							int temp_max_index=-1;
							for(int k=0;k<5;k++) {
								if(arr[i][j][k]==null) continue;
								if(temp_max<arr[i][j][k].num) {
									temp_max_index=k;
									temp_max = arr[i][j][k].num;
								}
								temp_sum+=arr[i][j][k].num;
							}
							arr[i][j][0]=new Micro(i,j,temp_sum,arr[i][j][temp_max_index].dir,false);
							for(int k=1;k<5;k++) arr[i][j][k]=null;
						}
					}
				}

				//군집 다 했으면 모두 check는 false로 만들기
				for(int i=0;i<n;i++) {
					for(int j=0;j<n;j++)
						if(arr[i][j][0]!=null) arr[i][j][0].check=false;
				}


				
				
			}//매 회차

			//남아있는 미생물 수 합하기
			int res=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++)
					if(arr[i][j][0]!=null) res+=arr[i][j][0].num;
			}

			writer.write("#"+t+" "+res);
			writer.newLine();
		}//testcase
		writer.flush();
	}//main

	public static boolean isMoreThanZero(int i, int j) {
		int count=0;
		for(int k=0;k<5;k++) if(arr[i][j][k]!=null) count++;
		return count>0 ? true : false;
	}

	static class Micro{
		int x;
		int y;
		int num;
		int dir;
		boolean check;
		public Micro(int x, int y, int num, int dir, boolean check) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			this.check = check;
		}
		@Override
		public String toString() {
			return "Micro [x=" + x + ", y=" + y + ", num=" + num + ", dir=" + dir + ", check=" + check + "]";
		}
	}
}
