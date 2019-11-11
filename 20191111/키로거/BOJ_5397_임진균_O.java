import java.io.*;
import java.util.*;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(in.readLine());
		for(int tc = 0 ; tc < T ; tc++)
			out.write(solve(in.readLine().toCharArray()) + "\n");
			
		out.flush();
	}

	static String solve(char input[])
	{
		LinkedList<Character> pw = new LinkedList<Character>();
		ListIterator<Character> cursor = pw.listIterator();
		
		for(int i = 0 ; i < input.length ; i++)
		{
			if(input[i] == '<')
			{
				if(cursor.hasPrevious()) 
					cursor.previous();
			}
			else if(input[i] == '>')
			{
				if(cursor.hasNext()) 
					cursor.next();
			}
			else if(input[i] == '-' && cursor.hasPrevious())
			{
				cursor.previous();
				cursor.remove();
			}
			else if(Character.isAlphabetic(input[i]) || Character.isDigit(input[i]))
				cursor.add(input[i]);
		}
		
		StringBuilder result = new StringBuilder();
		Iterator<Character> it = pw.iterator();
		while(it.hasNext())
			result.append(it.next());
		
		return result.toString();
	}
}