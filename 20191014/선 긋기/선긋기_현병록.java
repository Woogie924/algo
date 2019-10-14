import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static class Site implements Comparable<Site>{
		int y, x;

		public Site(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(Site o) {
			return this.y - o.y;
		}
		
		
	}
	static ArrayList<Site> list;
	static int start, end;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		list = new ArrayList<>();
		int size = Integer.parseInt(br.readLine());
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Site(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		int tStart=list.get(0).y, tEnd= list.get(0).x, result=0;
		for(int i=1; i<size; i++) {
			start = list.get(i).y;
			end = list.get(i).x;
			if(tEnd < start) {
				result += tEnd - tStart;
				tStart = start;
				tEnd = end;
			}
			else {
				tEnd = Math.max(end, tEnd);
			}
		}
		result += tEnd - tStart;
		System.out.println(result);
	}
}
