import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader in;
	static BufferedWriter out;

	static char statement[]; // 선언문
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 선언문을 입력받는다.
		statement = in.readLine().toCharArray();
		
		out.write(solve());
		out.flush();
	}
	
	static String solve()
	{
		String type = ""; // 공통된 변수형
		ArrayList<String> names = new ArrayList<String>(); // 변수명 리스트
		ArrayList<String> additionalTypes = new ArrayList<String>(); // 추가적인 변수형
		
		int index = 0; // statement에 대한 인덱스

		// 공통된 변수형을 파싱한다.
		while(statement[index] != ' ')
			type += statement[index++];
		
		// 공백은 건너 뛴다.
		index++;
		
		// 변수명과 추가적인 변수형을 파싱한다.
		String name = "", additionalType = "";
		while(index < statement.length)
		{
			if(statement[index] == ';')
			{
				names.add(name);
				additionalTypes.add(additionalType);
				break;
			}
				
			if(statement[index] == ',' && statement[index + 1] == ' ')
			{
				names.add(name);
				additionalTypes.add(additionalType);
				index += 2;
				name = additionalType = "";
				continue;
			}
			
			// 변수 이름
			if(Character.isAlphabetic(statement[index]))
				name += statement[index++];
			else
				additionalType += statement[index++];
		}
		
		// 결과를 생성한다.
		String ret = "";
		int nameSize = names.size();
		for(int i = 0 ; i < nameSize ; i++)
		{
			// 공통된 변수형을 추가한다.
			ret += type;
			
			// 추가적인 변수형을 뒤집어서 추가한다.
			int additionalTypeSize = additionalTypes.get(i).length();
			for(int j = additionalTypeSize - 1 ; j >= 0 ; j--)
			{
				if(additionalTypes.get(i).charAt(j) == ']')
					ret += "[";
				else if(additionalTypes.get(i).charAt(j) == '[')
					ret += "]";
				else
					ret += additionalTypes.get(i).charAt(j);
			}
			
			// 공백을 하나 추가한다.
			ret += " ";
			
			// 변수명을 추가한다.
			ret += names.get(i);
			
			// 세미콜론을 추가한다.
			ret += ";\n";
		}
		
		return ret;
	}
}