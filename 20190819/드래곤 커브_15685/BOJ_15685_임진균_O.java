import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class DragonCurve
{
	int x, y; // 시작 점
	// 방향 정보, |동:0|북:1|서:2|남:3|
	LinkedList<Integer> dList = new LinkedList<Integer>();
	int g; // 세대
}

public class Main
{
	static BufferedReader in;
	static BufferedWriter out;

	static int N; // 드래곤 커브의 개수
	static DragonCurve dragonCurves[] = new DragonCurve[20]; // 드래곤 커브 정보
	static int map[][] = new int[100 + 1][100 + 1]; // 드래곤 커브를 표시할 지도
	
	static final int dx[] = {1, 0, -1, 0};
	static final int dy[] = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		// 드래곤 커브의 개수를 입력받는다.
		N = Integer.parseInt(in.readLine());
		
		// 드래곤 커브 정보를 입력받는다.
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			dragonCurves[i] = new DragonCurve();
			dragonCurves[i].x = Integer.parseInt(tokenizer.nextToken());
			dragonCurves[i].y = Integer.parseInt(tokenizer.nextToken());
			dragonCurves[i].dList.addLast(Integer.parseInt(tokenizer.nextToken()));
			dragonCurves[i].g = Integer.parseInt(tokenizer.nextToken());
		}
	
		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		int count = 0;
		
		// 모든 드래곤 커브를 최종 세대까지 진화시킨다.
		for(int i = 0 ; i < N ; i++)
			evolve(dragonCurves[i]);
		
		// 모든 드래곤 커브를 하나의 지도에 표시한다.
		for(int i = 0 ; i < N ; i++)
			mark(dragonCurves[i]);
		
		// 크기가 1*1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수를 헤아린다.
		for(int y = 0 ; y < 100 ; y++)
		{
			for(int x = 0 ; x < 100 ; x++)
			{
				if(map[y][x] == 1 && map[y][x + 1] == 1 &&
						map[y + 1][x] == 1 && map[y + 1][x + 1] == 1)
					count++;
			}
		}
		
		return count;
	}
	
	// dragonCurve를 최종 세대까지 진화시킨다.
	static void evolve(DragonCurve dragonCurve)
	{
		for(int gen = 1 ; gen <= dragonCurve.g ; gen++)
		{
			// temp에 지금까지의 드래곤 커브 방향 정보를 복사한다.
			Stack<Integer> temp = new Stack<Integer>();

			int size = dragonCurve.dList.size();
			for(int i = 0 ; i < size ; i++)
				temp.add(dragonCurve.dList.get(i));
			
			// 실제로 진화 시킨다.
			while(!temp.isEmpty())
			{
				int nextD = (temp.pop() + 1) % 4;
				dragonCurve.dList.addLast(nextD);
			}
		}
	}
	
	static void mark(DragonCurve dragonCurve)
	{
		int x = dragonCurve.x;
		int y = dragonCurve.y;
		map[y][x] = 1;
		
		int size = dragonCurve.dList.size();
		for(int j = 0 ; j < size ; j++)
		{
			int d = dragonCurve.dList.getFirst();
			dragonCurve.dList.removeFirst();
			
			x = x + dx[d];
			y = y + dy[d];
			
			map[y][x] = 1;
		}
	}
}