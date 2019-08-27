import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15683 {

	static class cctv{
		int x;
		int y;
		int type;
		public cctv(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}		
	}
	static ArrayList <cctv> list;
	static int map[][];
	static int copy[][];
	static int N, M;
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		System.arraycopy(map, 0 , copy, 0, map.length);
		list=new ArrayList<cctv>();
		for(int i =0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]>0&&map[i][j]<6) {
					list.add(new cctv(i,j,map[i][j]));
				}
			}
		}
		System.out.println();
		arr = new int[list.size()];
		solv(0);
	}
	static int arr[];
	static int tmp;
	private static void solv(int depth) {
		if(depth==3) {
			tmp=0;
			map = copy;
			//System.out.println(Arrays.toString(arr));
			for(int i = 0; i<list.size(); i++) {
				switch(arr[i]) {
				case 0: //동
					if(list.get(i).type==1) {
						east(list.get(i));
					}else if(list.get(i).type==2) {
						east(list.get(i));
						west(list.get(i));
					}else if(list.get(i).type==3) { //직각
						east(list.get(i));
						north(list.get(i));
					}else if(list.get(i).type==4) {
						east(list.get(i));
						south(list.get(i));
						north(list.get(i));
					}else if(list.get(i).type==5) {
						east(list.get(i));
						west(list.get(i));
						north(list.get(i));
						south(list.get(i));
					}
					break;
				case 1: //서
					if(list.get(i).type==1) {
						west(list.get(i));
					}else if(list.get(i).type==2) {
						east(list.get(i));
						west(list.get(i));
					}else if(list.get(i).type==3) {
						west(list.get(i));
						south(list.get(i));
					}else if(list.get(i).type==4) {
						south(list.get(i));
						west(list.get(i));
						north(list.get(i));
					}else if(list.get(i).type==5) {
						east(list.get(i));
						west(list.get(i));
						north(list.get(i));
						south(list.get(i));
					}
					break;
				case 2://남
					if(list.get(i).type==1) {
						south(list.get(i));
					}else if(list.get(i).type==2) {
						north(list.get(i));
						south(list.get(i));
					}else if(list.get(i).type==3) {
						east(list.get(i));
						south(list.get(i));
					}else if(list.get(i).type==4) {
						east(list.get(i));
						west(list.get(i));
						south(list.get(i));
					}else if(list.get(i).type==5) {
						east(list.get(i));
						west(list.get(i));
						north(list.get(i));
						south(list.get(i));
					}
					break;
				case 3: //북
					if(list.get(i).type==1) {
						north(list.get(i));
					}else if(list.get(i).type==2) {
						north(list.get(i));
						south(list.get(i));
					}else if(list.get(i).type==3) {
						west(list.get(i));
						north(list.get(i));
					}else if(list.get(i).type==4) {
						east(list.get(i));
						west(list.get(i));
						north(list.get(i));
					}else if(list.get(i).type==5) {
						east(list.get(i));
						west(list.get(i));
						north(list.get(i));
						south(list.get(i));
					}
					break;
				}
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==0) {
						tmp++;
					}
				}
			}
			if(min>tmp) {
				min=tmp;
			}
			System.out.println(min);
			return;
		}
		for(int i = 0; i<4; i++) {
			arr[depth]=i;
			solv(depth+1);
		}
	}
	private static void east(cctv cctv) {
		for(int i = cctv.y; i<M; i++) {
			if(map[cctv.x][i]==6) {
				break;
			}else if(map[cctv.x][i]==0) {
				map[cctv.x][i]=9;
			}else continue;
		}
	}
	private static void west(cctv cctv) {
		for(int i = cctv.y; i>=0; i--) {
			if(map[cctv.x][i]==6) {
				break;
			}else if(map[cctv.x][i]==0) {
				map[cctv.x][i]=9;
			}else continue;
		}
	}
	private static void north(cctv cctv) {
		for(int i = cctv.x; i>=0; i--) {
			if(map[i][cctv.y]==6) {
				break;
			}else if(map[i][cctv.y]==0) {
				map[i][cctv.y]=9;
			}else continue;
		}
	}
	private static void south(cctv cctv) {
		for(int i = cctv.y; i<N; i++) {
			if(map[i][cctv.y]==6) {
				break;
			}else if(map[i][cctv.y]==0) {
				map[i][cctv.y]=9;
			}else continue;
		}
	}
}
