import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
	static LinkedList<Character> answer = new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0; t<tc; ++t) {
			answer.clear();
			String s = br.readLine().trim();
			
			int idx = 0;
			ListIterator it = answer.listIterator(answer.size());
			for(int i=0; i<s.length(); ++i) {
				
				if(s.charAt(i)=='<') {
					if(it.hasPrevious()) {
						it.previous();
					}
				}else if(s.charAt(i)=='>') {
					if(it.hasNext()) {
						it.next();
					}
						
				}else if(s.charAt(i)=='-') {
					if(it.hasPrevious()) {
						it.previous();
						it.remove();
					}
					
				}else {
					it.add(s.charAt(i));
				}
			}
			StringBuilder sb = new StringBuilder();
			
			it = answer.listIterator();
            while(it.hasNext()){
                sb.append(it.next());
            }
			
			System.out.println(sb);
		}
	}

}
