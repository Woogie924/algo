package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_ISharp_3568 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		String[] s = br.readLine().split("\\s");
		for (int i = 1; i < s.length; ++i) {
			sb = new StringBuilder();
			sb.append(s[0]);
			String[] temp = s[i].split("");
			for (int j = temp.length - 2; j >= 0; --j) {
				if (temp[j].equals("*") || temp[j].equals("&")) {
					sb.append(temp[j]);
				} else if (temp[j].equals("]")) {
					sb.append(temp[j - 1]);
					sb.append(temp[j]);
					--j;
				} else {
					sb.append(" ");
					for(int k=0; k<=j; ++k) {
						sb.append(temp[k]);
					}
					break;
				}
			}
			sb.append(";");
			System.out.println(sb);
		}

	}

}
