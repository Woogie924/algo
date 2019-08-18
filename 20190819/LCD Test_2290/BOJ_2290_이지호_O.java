package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Boj2290 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		String n = st.nextToken();

		int height = 2 * s + 3;
		int width = s + 2;
		int len = n.length();
		char a[][] = new char[height][width * len + len];
		int index = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = ' ';
			}
		}
		for (int cs = 0; cs < len; cs++) {

			if (n.charAt(cs) == '1') {
				for (int i = 0; i < height; i++) {
					for (int j = index; j < width + index; j++) {
						if (i != height - 1 && i != 0 && i != height / 2 && j == (width + index - 1)) {
							a[i][j] = '|';
						}
					}
				}
			} else if (n.charAt(cs) == '2') {
				for (int i = 0; i < height; i++) {
					for (int j = index; j < width + index; j++) {
						if (j > index && j < (width + index - 1) && (i == 0 || i == height - 1 || i == height / 2)) {
							a[i][j] = '-';
						}
						if( j == width + index - 1 && i >0 && i < height / 2) {
							a[i][j] = '|';
						}
						if (j == index  && i < height - 1 && i > height / 2) {
							a[i][j] = '|';
						}
					}
				}
			} else if (n.charAt(cs) == '3') {
				for (int i = 0; i < height; i++) {
					for (int j = index; j < width + index; j++) {
						if (j > index && j < (width + index - 1) && (i == 0 || i == height - 1 || i == height / 2)) {
							a[i][j] = '-';
						}
						if( j == width + index - 1 && i >0 && i < height-1&&i!=height/2) {
							a[i][j] = '|';
						}
					}
				}
			} else if (n.charAt(cs) == '4') {
				for (int i = 0; i < height; i++) {
					for (int j = index; j < width + index; j++) {
						if (j > index && j < (width + index - 1) && (i == height / 2)) {
							a[i][j] = '-';
						}
						if((j==index|| j == width + index - 1 )&& i >0 &&i < height/2) {
							a[i][j] = '|';
						}
						if((j == width + index - 1 )&& i <height-1 &&i >height/2) {
							a[i][j] = '|';
						}
					}
				}
			} else if (n.charAt(cs) == '5') {
				for (int i = 0; i < height; i++) {
					for (int j = index; j < width + index; j++) {
						if (j > index && j < (width + index - 1) && (i == 0 || i == height - 1 || i == height / 2)) {
							a[i][j] = '-';
						}
						if( j == width + index - 1 &&i < height - 1 && i > height / 2 ) {
							a[i][j] = '|';
						}
						if (j == index  && i >0 && i < height / 2) {
							a[i][j] = '|';
						}
					}
				}
			} else if (n.charAt(cs) == '6') {
				for (int i = 0; i < height; i++) {
					for (int j = index; j < width + index; j++) {
						if (j > index && j < (width + index - 1) && (i == 0 || i == height - 1 || i == height / 2)) {
							a[i][j] = '-';
						}
						if( j == width + index - 1 &&i < height - 1 && i > height / 2 ) {
							a[i][j] = '|';
						}
						if (j == index  && i >0 && i < height -1&&i!=height/2) {
							a[i][j] = '|';
						}
					}
				}
			} else if (n.charAt(cs) == '7') {
				for (int i = 0; i < height; i++) {
					for (int j = index; j < width + index; j++) {
						if (i != height - 1 && i != 0 && i != height / 2 && j == (width + index - 1)) {
							a[i][j] = '|';
						}
						if (j > index && j < (width + index - 1) && (i == 0)) {
							a[i][j] = '-';
						}
					}
				}
			} else if (n.charAt(cs) == '8') {
				for (int i = 0; i < height; i++) {
					for (int j = index; j < width + index; j++) {
						if (j > index && j < (width + index - 1) && (i == 0 || i == height - 1 || i == height / 2)) {
							a[i][j] = '-';
						}
						if ((j == index || j == width + index - 1) && i != 0 && i != height - 1 && i != height / 2) {
							a[i][j] = '|';
						}
					}
				}
			} else if (n.charAt(cs) == '9') {
				for (int i = 0; i < height; i++) {
					for (int j = index; j < width + index; j++) {
						if (j > index && j < (width + index - 1) && (i == 0 || i == height - 1 || i == height / 2)) {
							a[i][j] = '-';
						}
						if( j == width + index - 1 &&i < height - 1 && i > 0 && i!=height/2 ) {
							a[i][j] = '|';
						}
						if (j == index  && i >0 && i < height / 2) {
							a[i][j] = '|';
						}
					}
				}
			} else if (n.charAt(cs) == '0') {
				for (int i = 0; i < height; i++) {
					for (int j = index; j < width + index; j++) {
						if (j > index && j < (width + index - 1) && (i == 0 || i == height - 1)) {
							a[i][j] = '-';
						}
						if ((j == index || j == width + index - 1) && i != 0 && i != height - 1 && i != height / 2) {
							a[i][j] = '|';
						}
					}
				}
			}
			index = index + width + 1;
		}
		print(a);
	}

	private static void print(char[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]);
			}
			System.out.print(" ");
			System.out.println();
		}
	}
}