import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader reader;
	static BufferedWriter writer;
	
	static int A;
	static int P;
	
	static int discovered[];
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		// A, P 입력
		A = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		// discovered 배열 생성 및 초기화
		discovered = new int[300000];
		Arrays.fill(discovered, -1);
		
		writer.write(solve() + "\n");
		writer.flush();
	}
	
	// 특정 원소에 3번째 방문되는 순간 반복문을 탈출하여 discovered 배열에서 양수인 것의 개수를 헤아리면 된다.
	public static int solve()
	{
		int ret = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		
		// 초기 상태 삽입
		q.add(A);
		discovered[A] = 1;
		
		while(!q.isEmpty())
		{
			int D = q.poll();
			
			if(discovered[D] == -1)
				break;
			
			int nextD = getNextD(D);
			q.add(nextD);
			
			if(discovered[nextD] == -1)
				discovered[nextD] = 1;
			else
				discovered[nextD]--;
		}

		int size = discovered.length;
		for(int i = 0 ; i < size ; i++)
			if(discovered[i] > 0)
				ret++;
		
		return ret;
	}
	
	// D[n-1]이 value일 때, D[n]을 계산하여 반환한다.
	public static int getNextD(int value)
	{
		int ret = 0;
		while(value > 0)
		{
			ret += ((int)Math.pow(value % 10, P));
			value /= 10;
		}
		
		return ret;
	}

} 