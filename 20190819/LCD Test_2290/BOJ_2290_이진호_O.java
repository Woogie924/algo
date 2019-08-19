import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LCDTEST2290
{
	static int S;
	static String N;
	static boolean[][] lcd = // 0~9까지 lcd 모양 10-7 0 1 2 3 4 5 6 7 8 9
	{
			{ true, true, true, false, true, true, true }, // 0
			{ false, false, true, false, false, true, false }, // 1
			{ true, false, true, true, true, false, true }, // 2
			{ true, false, true, true, false, true, true }, // 3
			{ false, true, true, true, false, true, false }, // 4
			{ true, true, false, true, false, true, true }, // 5
			{ true, true, false, true, true, true, true }, // 6
			{ true, false, true, false, false, true, false }, // 7
			{ true, true, true, true, true, true, true }, // 8
			{ true, true, true, true, false, true, true },// 9
	};

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		N = st.nextToken();
		char[] words = N.toCharArray();
		int[] wordsI = new int[words.length];
		for (int i = 0; i < words.length; i++)
		{
			wordsI[i] = words[i] - '0';
//			System.out.println(wordsI[i]);
		}
		for (int tc = 0; tc < words.length; tc++)
		{
			for (int j = 0; j < 7; j++)
			{

			}
		}
		getLcd(wordsI);
	}

	private static void getLcd(int[] wordsI)
	{
		getHorizon(wordsI, 0);
		getVertical(wordsI, 1);
		getHorizon(wordsI, 3);
		getVertical(wordsI, 4);
		getHorizon(wordsI, 6);

	}

	private static void getHorizon(int[] wordsI, int level)
	{
		String piece;
		for (int i = 0; i < N.length(); i++)
		{
			piece = "";
			String temp = lcd[wordsI[i]][level] ? "-" : " ";
			for (int j = 0; j < S; j++)
			{
				piece += temp;
			}
			System.out.printf(" " + piece + "  ");
		}
		System.out.println();

	}

	private static void getVertical(int[] wordsI, int level)
	{
		for (int i = 0; i < S; i++)
		{
			for (int j = 0; j < N.length(); j++)
			{
				String temp = lcd[wordsI[j]][level] ? "|" : " ";
				System.out.printf(temp);
				for (int k =0 ; k < S; k++)
				{
					System.out.print(" ");
				}
				temp = lcd[wordsI[j]][level + 1] ? "|" : " ";
				System.out.printf(temp+" ");
			}
			System.out.println();
		}
	}
}