//치킨배달

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686_신채연_O {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;
	static int n;
	static int m;
	static int ch;
	static int house;
	static int x;
	static boolean[] visited;
	static int[][] minLen;
	static int min=Integer.MAX_VALUE;
	static ArrayList<Integer> list;
	static ArrayList<Integer> list1;
	static ArrayList<Integer> chickenList;
	static ArrayList<Integer> houseList;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		
		ch=0;		//치킨집의 개수
		house=0;	//집의 개수
		list = new ArrayList<Integer>();		//어느 치킨집을 고르느냐에 따라 달라지는 최솟값을 저장할 리스트 (예)10,15..
		list1 = new ArrayList<Integer>();		//permutation으로 고른 치킨집의 index (예)1,3
		chickenList = new ArrayList<Integer>();	//배열에서 치킨집의 i,j값을 저장할 리스트
		houseList = new ArrayList<Integer>();	//배열에서 가정집의 i,j값을 저장할 리스트
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(reader.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==1) {
					house++;
					houseList.add(i);
					houseList.add(j);
				}
				if(arr[i][j]==2) {
					ch++;
					chickenList.add(i);
					chickenList.add(j);
				}
			}
		}
		minLen = new int[m][house];	//최대치킨집의 갯수 * 집의 갯수 크기의 배열로, 각 치킨집과 집의 거리를 저장
		visited = new boolean[ch];	//permutation에서 필요
		
		solve(0, list1);
		list.sort(null);	//최솟값을 찾기 위해 sort
		writer.write(list.get(0)+"");
		writer.flush();
	}
	
	public static void solve(int toPick, ArrayList<Integer> list1) {
		if(toPick==m) {
			getMin(list1);
			return;
		}
		
		for(int next=x;next<ch;next++) {
			if(!visited[next]) {
				visited[next]=true;
				list1.add(next);
				solve(toPick+1, list1);
				list1.remove(list1.size()-1);
				visited[next]=false;
				x=next+1;
			}
		}
	}
	
	public static void getMin(ArrayList<Integer> list1) {
		for(int i=0;i<m;i++)
			for(int j=0;j<house;j++)
				minLen[i][j]=Math.abs(chickenList.get(list1.get(i)*2)-houseList.get(j*2))+
						Math.abs(chickenList.get(list1.get(i)*2+1)-houseList.get(j*2+1));
		
		int sum=0;
		for(int j=0;j<house;j++) {
			int min = minLen[0][j];
			for(int i=0;i<m;i++)
				if(min>minLen[i][j]) min=minLen[i][j];
			sum+=min;
		}
		list.add(sum);
	}
}
