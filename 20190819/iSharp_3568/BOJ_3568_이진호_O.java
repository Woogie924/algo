import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main
{
	static int T, N;

	public static void main(String[] args) throws IOException
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int start;
		StringTokenizer st = new StringTokenizer(br.readLine());
		String vType = st.nextToken();
		char[] temprr;
		while (st.hasMoreElements())
		{
			start = 0;
			String temp = st.nextToken();
			// �̸��κ� ã�Ƽ� �߶�� �Ѵ�.

			temprr = temp.toCharArray();// �̸��κпܿ� �ڷ��� ������ �����
			while (true)
			{
				if ((temprr[start] >= 'a' && temprr[start] <= 'z') || (temprr[start] >= 'A' && temprr[start] <= 'Z'))
				{
					start++;
				} else
				{
					break;
				}
			}
			temp = " " + temp.substring(0, start);// �̸� �����
			for (int i = start; i < temprr.length - 1; i++)
			{
				if (temprr[i] == '[')
				{
					temp = "[]" + temp;
					i++;
				} else
					temp = temprr[i] + temp;
			}
			System.out.println(vType + temp + ";");

		}
	}

}
