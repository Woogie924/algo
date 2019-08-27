import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15686_정종욱_O {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N,M;
	static int cknum,upto,sumfar,homes,sum,result;		//치킨집 개수, 중복방지,가까운거리
	static boolean[] openck;//오픈한 치킨집
	static int minfar[];
	static int arr[][];
	static ArrayList<int[]> list= new ArrayList<>();
	static ArrayList<int[]> ck = new ArrayList<>();
	static ArrayList<Integer> templist = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr= new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				if(arr[i][j]==0) continue;
				if(arr[i][j]==1) list.add(new int[] {i,j});
				if(arr[i][j]==2) { ck.add(new int[] {i,j}); cknum++; }
			}
		} // make map;
		
		openck = new boolean[cknum];
		homes = list.size();
		minfar = new int[homes];
		result = Integer.MAX_VALUE;
		openlist();
		System.out.println(result);
		
		
	}

	private static void openlist() { // 폐업하지않은 치킨집 뽑아내기
		if(templist.size()==M) {
			sum=0;
			Arrays.fill(minfar, Integer.MAX_VALUE);
			for(int a=0;a<templist.size();a++) { 
				far(templist.get(a));
			}
			for(int a=0;a<homes;a++) { // 다 더하기
				sum+=minfar[a];
			}
			if(sum<=result) result =sum;
			return;
		}
		for(int a=upto;a<cknum;a++) {
			if(openck[a]==true)continue;
			openck[a]=true;
			templist.add(a);
			openlist();
			templist.remove(templist.size()-1);
			openck[a]=false;
			upto=a+1;
			}
		}

	private static void far(int num) { // 치킨집과의 거리
		int i=ck.get(num)[0];
		int j=ck.get(num)[1];
		int temp;
		for(int a=0;a<homes;a++){
			temp=Math.abs(i-list.get(a)[0])+Math.abs(j-list.get(a)[1]);
			if(temp<=minfar[a]) minfar[a] = temp;
		}
		
	}

}
