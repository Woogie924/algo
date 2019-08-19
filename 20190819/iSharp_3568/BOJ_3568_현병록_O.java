import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_3568_현병록_O {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//입력받고 첫 토큰은 기존 변수형일테니 start에 저장해둔다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		String start = st.nextToken();
		
		String token;
		int i;
		while(st.hasMoreTokens()) {
			token = st.nextToken();
			//뒤에서부터 읽어서 붙일거라서 배열을 반대로 변경하여준다.
			token = token.replace("[]", "][");
			bw.write(start);
			for(i =token.length()-2; i>=0; i--) {
				char c = token.charAt(i);
				if(c=='['||c==']'||c=='&'||c=='*')
					bw.append(token.charAt(i));
				else break;
			}
			bw.append(" " + token.substring(0, i+1) + ";");
			bw.newLine();
		}

		bw.flush();
	}
}
