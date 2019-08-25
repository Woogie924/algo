import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Union
{
	ArrayList<Pos> nations = new ArrayList<Pos>();
	int people;	// 총 인구수
}

class Pos
{
	int x;
	int y;
	
	public Pos() {}
	public Pos(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int N, L ,R;
	static int A[][] = new int[50][50];
	
	static final int dx[] = {-1, 0, 1, 0};
	static final int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
	
		tokenizer = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokenizer.nextToken()); // 땅의 크기
		L = Integer.parseInt(tokenizer.nextToken()); // 국경선을 공유할 최소 인구 차이
		R = Integer.parseInt(tokenizer.nextToken()); // 국경선을 공유할 최대 인구 차이
		
		// 초기 인구 상태 입력
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++)
				A[i][j] = Integer.parseInt(tokenizer.nextToken());
		}
		
		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		int count = 0; // 인구 이동이 발생한 총 횟수
		
		while(true)
		{
			boolean moved = false; // 하루동안 인구 이동이 한번 이라도 발생했는지?
			boolean discovered[][] = new boolean[50][50];
			ArrayList<Union> unions = new ArrayList<Union>();
			
			// 모든 연합을 찾는다.
			for(int i = 0 ; i < N ; i++)
			{
				for(int j = 0 ; j < N ; j++)
				{
					if(!discovered[i][j])
					{
						Union union = new Union();
						
						Queue<Integer> q = new LinkedList<Integer>();
						q.add(i);
						q.add(j);
						discovered[i][j] = true;
						
						// 연합을 구성하는 나라들을 모두 찾는다.
						while(!q.isEmpty())
						{
							int x = q.poll();
							int y = q.poll();
							
							union.nations.add(new Pos(x, y));
							union.people += A[x][y];
							
							for(int dir = 0 ; dir < 4 ; dir++)
							{
								int nx = x + dx[dir];
								int ny = y + dy[dir];
								
								if(nx < 0 || nx >= N || ny < 0 || ny >= N
										|| discovered[nx][ny])
									continue; 
									
								int diff = Math.abs(A[x][y] - A[nx][ny]);
								if(diff < L || diff > R)
									continue;
									
								q.add(nx);
								q.add(ny);
								discovered[nx][ny] = true;
							}
						}
						
						unions.add(union);
					}
				}
			}
			
			int unionsSize = unions.size();
			for(int i = 0 ; i < unionsSize ; i++)
			{
				// 연합을 구성하는 나라의 수가 1이라면 인구 이동이 발생하지 않는다.
				int nationsSize = unions.get(i).nations.size();
				if(nationsSize == 1)
					continue;
				
				// 인구 이동을 반영한다.
				for(int k = 0 ; k < nationsSize ; k++)
				{
					int x = unions.get(i).nations.get(k).x;
					int y = unions.get(i).nations.get(k).y;
					
					A[x][y] = unions.get(i).people / nationsSize;
				}
				
				moved = true;
			}

			if(!moved)
				break;
			
			count++;
		}
	
		return count;
	}
}