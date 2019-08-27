package samsungexam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15686 {
	static class posit{
		int x;
		int y;
		public posit(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N;
	static int maxChicken;
	static ArrayList<posit> chicken;
	static ArrayList<posit> house;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		maxChicken = Integer.parseInt(st.nextToken());
		chicken = new ArrayList<posit>();
		house = new ArrayList<posit>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				int p = Integer.parseInt(st.nextToken());
				if(p==1) {
					house.add(new posit(i,j));
				}else if(p==2) {
					chicken.add(new posit(i,j));
				}
			}
		}
		visit = new boolean[chicken.size()];
		solv(0,0);
		System.out.println(ans);
	}
	static int chickenStreet=Integer.MAX_VALUE;
	static int ans=Integer.MAX_VALUE;
	private static void solv(int depth,int select) {
		if(select==maxChicken) {
			int temp=0;
			for(int i = 0; i<house.size(); i++) {
				chickenStreet = Integer.MAX_VALUE;
				for(int j = 0; j<visit.length; j++) {
					if(visit[j]) {
						int dis = Math.abs(house.get(i).x-chicken.get(j).x)+Math.abs(house.get(i).y-chicken.get(j).y);
						if(chickenStreet>dis) {
							chickenStreet=dis;
						}
					}
				}
				temp+=chickenStreet;
			}
			if(ans>temp) {
				ans = temp;
			}
			return;
		}
		if(depth==chicken.size()) return;
		
		visit[depth]=true;
		solv(depth+1, select+1);
		visit[depth]=false;
		solv(depth+1,select);
	}
	
}
