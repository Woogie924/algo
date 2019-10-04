import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
  
public class Main {
	
	static int A, B, C, X, Y;
	
    public static void main(String[] args) throws IOException 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        
        A = Integer.parseInt(tokenizer.nextToken()); // 양념
        B = Integer.parseInt(tokenizer.nextToken()); // 후라이드
        C = Integer.parseInt(tokenizer.nextToken()); // 반반
        X = Integer.parseInt(tokenizer.nextToken());
        Y = Integer.parseInt(tokenizer.nextToken());
        
    	out.write(solve() + "");	
        out.flush();
    }

    static int solve()
    {
    	int minPrice = 0;
    	
    	if(X != Y)
    	{
    		int max = Math.max(X, Y);
    		int min = Math.min(X, Y);
    		minPrice += ((A + B > 2 * C ? 2 * C : A + B) * min);
    		
    		int remain = max - min;
    		if(min == X)
    			minPrice += ((B > 2 * C ? 2 * C : B) * remain);
    		else
    			minPrice += ((A > 2 * C ? 2 * C : A) * remain);
    	}
    	else
    		minPrice += ((A + B > 2 * C ? 2 * C : A + B) * X);
    	
    	return minPrice;
    }
}
