//iSharp

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_3568_신채연_O {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(reader.readLine());
		
		String basic = st.nextToken();	//기본형
		ArrayList<String> list = new ArrayList<String>();
		while(st.hasMoreTokens())
			list.add(st.nextToken());
		
		for(String s : list) {
			int size = s.length();
			String var = "";
			for(int i=0;i<size;i++) {
				if(s.charAt(i)!='[' && s.charAt(i)!=']' &&
						s.charAt(i)!='*' && s.charAt(i)!='&' &&
						s.charAt(i)!=';' && s.charAt(i)!=',')
					var+=s.charAt(i);
			}
			int varsize = var.length();
			
			writer.write(basic);
			for(int i=size-2;i>=1;i--) {
				if(i==varsize-1) break;
				if(s.charAt(i)=='[')
					writer.write("]");
				else if(s.charAt(i)==']')
					writer.write("[");
				else
					writer.write(s.charAt(i));
			}
			writer.write(" "+var+";\n");
		}
		writer.flush();
	}
}
