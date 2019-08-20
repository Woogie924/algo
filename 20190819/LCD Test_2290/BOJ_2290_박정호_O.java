package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_LCDTest_2290 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;

		String[] input = br.readLine().split("\\s");

		int n = Integer.parseInt(input[0]);
		String[] s = input[1].split("");
		for (int i = 1; i <= 5; ++i) {
			sb = new StringBuilder();
			switch (i) {
			case 1:
				for (int j = 0; j < s.length; ++j) {
					sb.append(" ");
					if (s[j].equals("1") || s[j].equals("4")) {
						for (int k = 0; k < n; ++k) {
							sb.append(" ");
						}
					} else {
						for (int k = 0; k < n; ++k) {
							sb.append("-");
						}
					}
					sb.append("  ");
				}
				break;
			case 2:
				for (int l = 0; l < n; ++l) {
					for (int j = 0; j < s.length; ++j) {
						if (s[j].equals("1") || s[j].equals("2") || s[j].equals("3") || s[j].equals("7")) {
							sb.append(" ");
							for (int k = 0; k < n; ++k) {
								sb.append(" ");
							}
							sb.append("| ");
						} else if (s[j].equals("5") || s[j].equals("6")) {
							sb.append("|");
							for (int k = 0; k < n; ++k) {
								sb.append(" ");
							}
							sb.append("  ");
						} else {
							sb.append("|");
							for (int k = 0; k < n; ++k) {
								sb.append(" ");
							}
							sb.append("| ");
						}
					}
					sb.append("\n");
				}
				sb.deleteCharAt(sb.length()-1);
				break;
			case 3:
				for (int j = 0; j < s.length; ++j) {
					sb.append(" ");
					if (s[j].equals("1") || s[j].equals("7") || s[j].equals("0")) {
						for (int k = 0; k < n; ++k) {
							sb.append(" ");
						}
					} else {
						for (int k = 0; k < n; ++k) {
							sb.append("-");
						}
					}
					sb.append("  ");
				}
				break;
			case 4:
				for (int l = 0; l < n; ++l) {
					for (int j = 0; j < s.length; ++j) {
						if (s[j].equals("6") || s[j].equals("8") || s[j].equals("0")) {
							sb.append("|");
							for (int k = 0; k < n; ++k) {
								sb.append(" ");
							}
							sb.append("| ");
						} else if (s[j].equals("2")) {
							sb.append("|");
							for (int k = 0; k < n; ++k) {
								sb.append(" ");
							}
							sb.append("  ");
						} else {
							sb.append(" ");
							for (int k = 0; k < n; ++k) {
								sb.append(" ");
							}
							sb.append("| ");
						}
					}
					sb.append("\n");
				}
				sb.deleteCharAt(sb.length()-1);
				break;
			case 5:
				for (int j = 0; j < s.length; ++j) {
					sb.append(" ");
					if (s[j].equals("1") || s[j].equals("4") || s[j].equals("7")) {
						for (int k = 0; k < n; ++k) {
							sb.append(" ");
						}
					} else {
						for (int k = 0; k < n; ++k) {
							sb.append("-");
						}
					}
					sb.append("  ");
				}
				break;
			}
			System.out.println(sb);
		}

	}

}
