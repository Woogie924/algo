import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
	
	static class Line
	{
		int begin;
		int end;
		
		public Line() {}
		public Line(int begin, int end) 
		{
			this.begin = begin;
			this.end = end;
		}
	}
	
	static int N;
	static ArrayList<Line> lines = new ArrayList<Line>();
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(in.readLine());
		for(int i = 0 ; i < N ; i++)
		{
			String tokens[] = in.readLine().split(" ");
			int begin = Integer.parseInt(tokens[0]);
			int end = Integer.parseInt(tokens[1]);
			
			lines.add(new Line(begin, end));
		}
		
		out.write(solve() + "");
		out.flush();
	}

	static int solve() 
	{
		// begin의 오름차순으로 정렬한다.
		lines.sort(new Comparator<Line>() {
			public int compare(Line o1, Line o2) {
				return o1.begin - o2.begin;
			}
		});
		
		int result = 0;
		Line curr = new Line(Integer.MIN_VALUE, Integer.MIN_VALUE);
		for(int i = 0 ; i < lines.size() ; i++)
		{
			Line next = lines.get(i);
			
			if(curr.end < next.begin)
			{
				result += (curr.end - curr.begin);
				curr.begin = next.begin;
				curr.end = next.end;
			}
			else
				curr.end = Math.max(curr.end, next.end);
		}
		
		result += (curr.end - curr.begin);
		
		return result;
	}
}
