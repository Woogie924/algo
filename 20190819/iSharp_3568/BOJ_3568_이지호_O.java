package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj3568 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String start = st.nextToken();
		while (true) {
			StringBuilder sb = new StringBuilder();
			sb.append(start);
			String v = st.nextToken();
			Stack<String> s = new Stack<>();
			s.push(";");
			for (int i = 0; i < v.length(); i++) {
				if (!v.substring(i, i + 1).equals(",") && !v.substring(i, i + 1).equals(";")) {
					s.push(v.substring(i, i + 1));
				}
			}
			int count = 0;
			Stack<String> wTemp = new Stack<>();
			while (!s.isEmpty()) {
				String temp = s.pop();
				char item = temp.charAt(0);
				if (temp.equals("]")) {
					sb.append("[");
				} else if (temp.equals("[")) {
					sb.append("]");
				} else if ((item >= 'a' && item <= 'z') || (item >= 'A' && item <= 'Z')) {
					if (count == 0) {
						sb.append(" ");
					}
					wTemp.push(temp);
					count++;
				} else if (temp.equals(";")) {
					while (!wTemp.isEmpty()) {
						sb.append(wTemp.pop());
					}
					sb.append(temp);
				} else
					sb.append(temp);
			}
			System.out.println(sb.toString());
			if (v.contains(";"))
				break;
		}

	}

}
