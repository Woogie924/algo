//LCDTest
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2290_신채연_O {
	static BufferedReader reader;
	static BufferedWriter writer;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(reader.readLine());
		int s = Integer.parseInt(st.nextToken());
		String str_n = st.nextToken();
		int n = Integer.parseInt(str_n);
		int len = str_n.length();

		StringBuffer sb = new StringBuffer();

		//#1
		for(int i=0;i<len;i++) {
			char ch = str_n.charAt(i);
			switch(ch) {
			case '1':
			case '4':
				for(int j=0;j<s+2;j++)
					sb.append(" ");
				sb.append(" ");
				break;
			default:
				sb.append(" ");
				for(int j=0;j<s;j++)
					sb.append("-");
				sb.append(" ");
				sb.append(" ");
			}
		}
		writer.write(sb.toString());
		writer.newLine();
		sb.delete(0, sb.length());

		//#2
		for(int i=0;i<len;i++) {
			char ch = str_n.charAt(i);
			switch(ch) {
			case '1':
			case '2':
			case '3':
			case '7':
				for(int j=0;j<2+s-1;j++)
					sb.append(" ");
				sb.append("|");
				sb.append(" ");
				break;
			case '5':
			case '6':
				sb.append("|");
				for(int j=0;j<2+s-1;j++)
					sb.append(" ");
				sb.append(" ");
				break;
			default:
				sb.append("|");
				for(int j=0;j<s;j++)
					sb.append(" ");
				sb.append("|");
				sb.append(" ");
			}
		}
		for(int i=0;i<s;i++) {
			writer.write(sb.toString());
			writer.newLine();
		}
		sb.delete(0, sb.length());

		//#3
		for(int i=0;i<len;i++) {
			char ch = str_n.charAt(i);
			switch(ch) {
			case '1':
			case '7':
			case '0':
				for(int j=0;j<2+s;j++)
					sb.append(" ");
				sb.append(" ");
				break;
			default:
				sb.append(" ");
				for(int j=0;j<s;j++)
					sb.append("-");
				sb.append(" ");
				sb.append(" ");
			}
		}
		writer.write(sb.toString());
		writer.newLine();
		sb.delete(0, sb.length());

		//#4
		for(int i=0;i<len;i++) {
			char ch = str_n.charAt(i);
			switch(ch) {
			case '2':
				sb.append("|");
				for(int j=0;j<2+s-1;j++)
					sb.append(" ");
				sb.append(" ");
				break;
			case '6':
			case '8':
			case '0':
				sb.append("|");
				for(int j=0;j<s;j++)
					sb.append(" ");
				sb.append("|");
				sb.append(" ");
				break;
			default:
				for(int j=0;j<2+s-1;j++)
					sb.append(" ");
				sb.append("|");
				sb.append(" ");
				break;
			}
		}
		for(int i=0;i<s;i++) {
			writer.write(sb.toString());
			writer.newLine();
		}
		sb.delete(0, sb.length());

		//#5
		for(int i=0;i<len;i++) {
			char ch = str_n.charAt(i);
			switch(ch) {
			case '1':
			case '4':
			case '7':
				for(int j=0;j<s+2;j++)
					sb.append(" ");
				sb.append(" ");
				break;
			default:
				sb.append(" ");
				for(int j=0;j<s;j++)
					sb.append("-");
				sb.append(" ");
				sb.append(" ");
			}
		}
		writer.write(sb.toString());
		writer.newLine();		
		sb.delete(0, sb.length());

		writer.flush();
	}
}