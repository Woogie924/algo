import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;

public class BOJ_5397_이진호_O {
	static int T;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			String is = br.readLine();
			LinkedList<Character> list = new LinkedList<>();
			int cursor = 0;
			int max = 0;// 다음꺼에 넣는다.
			for (int i = 0; i < is.length(); i++) {
				if (is.charAt(i) == '<')
					cursor = cursor == 0 ? 0 : cursor-1;
				else if (is.charAt(i) == '>')
					cursor = cursor == max ? max : cursor+1;
				else if (is.charAt(i) == '-') {
					if (cursor == 0)
						continue;
					cursor--;
					list.remove(cursor);
					max--;
				} else {
					list.add(cursor, is.charAt(i));
					max++;
					cursor++;
				}
			}
			Iterator<Character> it= list.iterator();
			while(it.hasNext())
			{
				bw.write(it.next());
			}
			bw.write("\n");
			bw.flush();
		}
	}

}
