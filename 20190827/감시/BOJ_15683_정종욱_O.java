import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683_정종욱_O {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R,C;
	static int arr[][] ;
	static int carr[][];
	static int max,dir,sum,result,zerosum;
	static int dx[]= {-1,0,1,0};  // 상우하좌
	static int dy[]= {0,1,0,-1};
	static int tx,ty;
	static ArrayList<int[]> list = new ArrayList<>();
	static ArrayList<Integer> templist = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		max= Integer.MIN_VALUE;
		
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==1||arr[i][j]==2||arr[i][j]==3||arr[i][j]==4||arr[i][j]==5) {
					if(arr[i][j]==2) dir=2;
					else if(arr[i][j]==5) dir=1;
					else dir=4;
					list.add(new int[] {arr[i][j],dir,i,j});
				}
				if(arr[i][j]==0)zerosum++; 
			}
		} // make map
		
		go(0);
		result=zerosum-max;
		System.out.println(result);
}

	private static void go(int cnt) {
		if(templist.size()==list.size()) {
			for(int a=0;a<templist.size();a++) {
				laser(list.get(a)[0],a,templist.get(a)); // 감시카메라
			}
			for(int a=0;a<templist.size();a++) {
				removelaser(list.get(a)[0],a,templist.get(a));
			}
			if(sum>=max)max=sum;
			sum=0;
			return;
		}
		int turn=list.get(cnt)[1];
		for(int a=0;a<turn;a++) {
		templist.add(a);
		cnt+=1;
		go(cnt);
		templist.remove(templist.size()-1);
		cnt--;
		}		
	}

	private static void removelaser(int kind, int num, int d) {
		switch(kind) {
		case 1: 
				remove8(list.get(num)[2]+dx[d],list.get(num)[3]+dy[d],d);
			break;
		
		case 2: 
				remove8(list.get(num)[2]+dx[d],list.get(num)[3]+dy[d],d);
				remove8(list.get(num)[2]+dx[(d+2)%4],list.get(num)[3]+dy[(d+2)%4],(d+2)%4);
			break;
			
		case 3:	remove8(list.get(num)[2]+dx[d],list.get(num)[3]+dy[d],d);
				remove8(list.get(num)[2]+dx[(d+1)%4],list.get(num)[3]+dy[(d+1)%4],(d+1)%4);
			break;
			
		case 4: remove8(list.get(num)[2]+dx[d],list.get(num)[3]+dy[d],d);
				remove8(list.get(num)[2]+dx[(d+1)%4],list.get(num)[3]+dy[(d+1)%4],(d+1)%4);
				remove8(list.get(num)[2]+dx[(d+2)%4],list.get(num)[3]+dy[(d+2)%4],(d+2)%4);
			break;
			
		case 5: 
				remove8(list.get(num)[2]+dx[0],list.get(num)[3]+dy[0],0);
				remove8(list.get(num)[2]+dx[1],list.get(num)[3]+dy[1],1);
				remove8(list.get(num)[2]+dx[2],list.get(num)[3]+dy[2],2);
				remove8(list.get(num)[2]+dx[3],list.get(num)[3]+dy[3],3);
			break;
		}
	}



	private static void laser(int kind,int num,int d) {
		switch(kind) {
		case 1: 
				move(list.get(num)[2]+dx[d],list.get(num)[3]+dy[d],d);
			break;
		
		case 2: 
				move(list.get(num)[2]+dx[d],list.get(num)[3]+dy[d],d);
				move(list.get(num)[2]+dx[(d+2)%4],list.get(num)[3]+dy[(d+2)%4],(d+2)%4);
			break;
			
		case 3:	move(list.get(num)[2]+dx[d],list.get(num)[3]+dy[d],d);
				move(list.get(num)[2]+dx[(d+1)%4],list.get(num)[3]+dy[(d+1)%4],(d+1)%4);
			break;
			
		case 4: move(list.get(num)[2]+dx[d],list.get(num)[3]+dy[d],d);
				move(list.get(num)[2]+dx[(d+1)%4],list.get(num)[3]+dy[(d+1)%4],(d+1)%4);
				move(list.get(num)[2]+dx[(d+2)%4],list.get(num)[3]+dy[(d+2)%4],(d+2)%4);
			break;
			
		case 5: 
				move(list.get(num)[2]+dx[0],list.get(num)[3]+dy[0],0);
				move(list.get(num)[2]+dx[1],list.get(num)[3]+dy[1],1);
				move(list.get(num)[2]+dx[2],list.get(num)[3]+dy[2],2);
				move(list.get(num)[2]+dx[3],list.get(num)[3]+dy[3],3);
			break;
		}
	}

	private static void move(int i, int j,int num) {
		if(i<0||j<0||i>=R||j>=C||arr[i][j]==6) return;
		int tx = i+dx[num];
		int ty = j+dy[num];
		if(arr[i][j]==0) { arr[i][j]=8; sum++; move(tx,ty,num); }
		else { move(tx,ty,num); }
	}
	
	private static void remove8(int i, int j, int num) {
		if(i<0||j<0||i>=R||j>=C||arr[i][j]==6) return;
		int tx = i+dx[num];
		int ty = j+dy[num];
		if(arr[i][j]==8) { arr[i][j]=0; remove8(tx,ty,num); }
		else { remove8(tx,ty,num); }
	}
	
}
