import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class BOJ5397_키로거 {
	static char[] c;
	static List<Character> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testcase; tc++) {
			c = br.readLine().toCharArray();
			list = new LinkedList<>();
			ListIterator<Character> it = list.listIterator();
			for(int i=0; i<c.length; i++) {
				if(c[i]=='<') {
					if(it.hasPrevious()) {
						it.previous();
					}
				}else if(c[i]=='>') {
					if(it.hasNext())
						it.next();
				}else if(c[i]=='-') {
					if(it.hasPrevious()) {
						it.previous();
						it.remove();
					}
				}else {
					it.add(c[i]);
				}
			}
			for(Character ch : list)
				sb.append(ch);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
