import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_16235_현병록_O {
	static ArrayList<Integer>[][] treeMap;
	static int size, treeNum, year;
	static int[][] S2D2, map, deadTree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		size = Integer.parseInt(st.nextToken());
		treeNum = Integer.parseInt(st.nextToken());
		year = Integer.parseInt(st.nextToken());
		
		deadTree = new int[size+1][size+1];
		S2D2 = new int[size+1][size+1];
		treeMap = new ArrayList[size+1][size+1];
		map = new int[size+1][size+1];
		for(int i=1; i<=size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=size; j++) {
				S2D2[i][j] = Integer.parseInt(st.nextToken());
				treeMap[i][j] = new ArrayList<>();
				map[i][j]=5;
			}
		}
		
		for(int i=0; i<treeNum; i++) {
			st = new StringTokenizer(br.readLine());
			treeMap[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<year; i++) {
			yearCycle();
		}

		System.out.println(countTree()+"");
	}
	private static int countTree() {
		int rtv=0;
		for(int i=1; i<=size; i++) {
			for(int j=1; j<=size; j++) {
				rtv += treeMap[i][j].size();
			}
		}
		return rtv;
	}
	private static void yearCycle() {
		//봄
		spring();
		//여름
		summer();
		//가을
		fall();
		//겨울
		winter();
	}
	private static void winter() {
		//S2D2가 돌아다니며 양분을 추가한다.
		for(int i=1; i<=size; i++) {
			for(int j=1; j<=size; j++) {
				map[i][j]+=S2D2[i][j];
			}
		}
	}
	private static void fall() {
		int ny, nx;
		//나이가 5의 배수인 나무가 번식한다
		for(int i=1; i<=size; i++) {
			for(int j=1; j<=size; j++) {
				for(int t=0; t<treeMap[i][j].size(); t++) {
					if(treeMap[i][j].get(t)%5==0) {
						for(int y=-1; y<=1; y++) {
							for(int x=-1; x<=1; x++) {
								if(y==0 && x==0) continue;
								ny = i+y; nx = j+x;
								if(ny<1|| nx<1 || ny>size|| nx>size) continue;
								treeMap[ny][nx].add(1);
							}
						}
					}
				}
			}
		}
	}
	private static void summer() {
		//봄에 죽은 나무가 양분이 된다.
		for(int i=1; i<=size; i++) {
			for(int j=1; j<=size; j++) {
				map[i][j]+=deadTree[i][j];
			}
		}
	}
	private static void spring() {
		for(int i=1; i<=size; i++) {
			Arrays.fill(deadTree[i], 0);
		}
		
		//어린 나무 부터 자신의 나이만큼 양분을 먹고 부족하면 죽는다. 
		for(int i=1; i<=size; i++) {
			for(int j=1; j<=size; j++) {
				
				Collections.sort(treeMap[i][j]);
				for(int t=0; t<treeMap[i][j].size(); t++) {
					if(treeMap[i][j].get(t)<=map[i][j]) {
						map[i][j]-=treeMap[i][j].get(t);
						treeMap[i][j].set(t, treeMap[i][j].get(t)+1);
					}
					else {
						deadTree[i][j] += treeMap[i][j].get(t)/2;
						treeMap[i][j].remove(t);
						t--;
					}
				}
					
			}
		}
	}
}
