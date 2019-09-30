import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_16928_뱀과사다리게임_X {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	static int n,m;
	static ArrayList<Integer> ladderList;
	
	public static void main(String[] args) throws Exception{
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ladderList = new ArrayList<>();
		
		int[][] ladder = new int[n][3];	//시작, 끝, 크기
		int[] board = new int[101];	//1부터 100까지
		board[0]=1;	//사용안함
		board[1]=1;	//시작위치
		
		//사다리 입력받기
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(reader.readLine());
			ladder[i][0] = Integer.parseInt(st.nextToken());
			ladder[i][1] = Integer.parseInt(st.nextToken());
			ladder[i][2] = ladder[i][1]-ladder[i][0];
		}
		
		//사다리 크기 순서대로 정렬
		Arrays.sort(ladder, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[2]-o1[2];
			}
		});
		
		//뱀이 있으면 보드에 -1로
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(reader.readLine());
			board[Integer.parseInt(st.nextToken())]=-1;
		}
		
		//사다리 타기
		for(int i=0;i<n;i++) {
			int start = ladder[i][0];
			int end = ladder[i][1];
			boolean isAllZero = false;
			if(end-start>=7) {	//7이상의 크기를 갖는 사다리만 타보자...
				for(int j=start;j<=end;j++) {	//사다리를 겹치지 않게 타기
					if(board[j]==1) {			//중간에 뱀이어도 가능
						isAllZero=true;
						break;
					}
				}
				if(!isAllZero) {	//사다리끼리 안겹치면 사다리 타기
					for(int j=start;j<=end;j++)
						board[j]=1;
					ladderList.add(ladder[i][0]);	//사다리 시작점 저장
					board[ladder[i][0]]=0;	//편의를 위해 사다리 시작점은 다시 0으로 해둠
				}
			}
		}
		
		int now=1;
		int count=0;
		for(int i=0;i<ladderList.size();i++) {	//각 사다리에 대해서
			int ladderStart=ladderList.get(i);
			
			while(now<=ladderStart) {
				if(now==ladderStart) break;
				else if(now+6<=ladderStart && board[now+6]==0) {
					for(int j=1;j<=6;j++) board[now+j]=1;	//안해도될 것같긴 한데 일단 추가
					now+=6;
				}
				else if(now+5<=ladderStart && board[now+5]==0) {
					for(int j=1;j<=5;j++) board[now+j]=1;
					now+=5;
				}
				else if(now+4<=ladderStart && board[now+4]==0) {
					for(int j=1;j<=4;j++) board[now+j]=1;
					now+=4;
				}
				else if(now+3<=ladderStart && board[now+3]==0) {
					for(int j=1;j<=3;j++) board[now+j]=1;
					now+=3;
				}
				else if(now+2<=ladderStart && board[now+2]==0) {
					for(int j=1;j<=2;j++) board[now+j]=1;
					now+=2;
				}
				else if(now+1<=ladderStart && board[now+1]==0) {
					board[now+1]=1;
					now+=1;
				}
				count++;
			}
			
			int ladderEnd=-2;
			for(int j=0;j<n;j++) {
				if(ladderStart==ladder[j][0])
					ladderEnd=ladder[j][1];
			}
			now=ladderEnd;
		}
		
		//마지막 사다리 끝난 시점부터 100번까지 주사위 굴리기
		while(now<=100) {
			if(now==100) break;
			else if(now+6<=100 && board[now+6]==0) {
				for(int j=1;j<=6;j++) board[now+j]=1;
				now+=6;
			}
			else if(now+5<=100 && board[now+5]==0) {
				for(int j=1;j<=5;j++) board[now+j]=1;
				now+=5;
			}
			else if(now+4<=100 && board[now+4]==0) {
				for(int j=1;j<=4;j++) board[now+j]=1;
				now+=4;
			}
			else if(now+3<=100 && board[now+3]==0) {
				for(int j=1;j<=3;j++) board[now+j]=1;
				now+=3;
			}
			else if(now+2<=100 && board[now+2]==0) {
				for(int j=1;j<=2;j++) board[now+j]=1;
				now+=2;
			}
			else if(now+1<=100 && board[now+1]==0) {
				board[now+1]=1;
				now+=1;
			}
			count++;
		}
		System.out.println("count="+count);
	}
}