import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_선긋기_2170_박정호_O {

	static class Line {
		int start;
		int end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] s;
		ArrayList<Line> list = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			s = br.readLine().split("\\s");
			list.add(new Line(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
		}
		// start, end순으로 정렬
		Collections.sort(list, new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				if (o1.start == o2.start)
					return o1.end - o2.end;
				return o1.start - o2.start;
			}
		});

		long answer = 0;
		int start = list.get(0).start;
		int end = list.get(0).end;
		for (int i = 1; i < N; ++i) {
			if(end>=list.get(i).start) {
				end = Math.max(end, list.get(i).end);
			}
			else {
				answer += (end-start);
				start = list.get(i).start;
				end = list.get(i).end;
			}
		}
		answer += (end-start);
		System.out.println(answer);
	}

}
