import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus
{
	int x;
	int y;
	int t;
	
	public Virus() {}
	public Virus(int x, int y, int t) 
	{
		this.x = x;
		this.y = y;
		this.t = t;
	}
}

public class Main {
	
	static BufferedReader in;
	static BufferedWriter out;
	
	static int N, M;
	static int map[][] = new int[50][50];
	static ArrayList<Virus> virusList = new ArrayList<Virus>();
	
	static final int dx[] = {-1, 0, 1, 0};
	static final int dy[] = {0, 1, 0, -1};
	
	static int remains;
	
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokenizer.nextToken()); // 연구소의 크기
		M = Integer.parseInt(tokenizer.nextToken()); // 놓을 수 있는 바이러스의 개수
		
		remains = N * N;
		for(int i = 0 ; i < N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++)
			{
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				
				if(map[i][j] == 2)
					virusList.add(new Virus(i, j, 0));
				else if(map[i][j] == 1)
					remains--;
			}
		}
		
		int result = solve(M, new ArrayList<Integer>());
		out.write((result != Integer.MAX_VALUE ? result : -1)+ "");
		out.flush();
	}
	
	// 전체 비활성화 바이러스 중에서 M개를 선택한 후에 퍼뜨려 본다.
	static int solve(int toPick, ArrayList<Integer> picked)
	{
		if(toPick == 0)
			return spreadVirus(picked);
		
		int minTime = Integer.MAX_VALUE;
		int size = virusList.size();
		
		int smallest = (picked.isEmpty() ? 0 : picked.get(picked.size() - 1) + 1);	
		for(int next = smallest ; next < size ; next++)
		{
			picked.add(next);
			minTime = Math.min(minTime, solve(toPick - 1, picked));
			picked.remove(picked.size() - 1);
		}
		
		return minTime;
	}
	
	static int spreadVirus(ArrayList<Integer> picked)
	{
		int time = 0;
		int visit = 0;
		boolean discovered[][] = new boolean[50][50];
		Queue<Virus> q = new LinkedList<Virus>();
		
		// 초기 상태 삽입
		for(int i = 0 ; i < M ; i++)
		{
			Virus virus = virusList.get(picked.get(i)); 
			q.add(virus);
			discovered[virus.x][virus.y] = true;
		}
		
		while(!q.isEmpty())
		{
			Virus virus = q.poll();
			int x = virus.x;
			int y = virus.y;
			int t = virus.t;
			
			visit++;
			
			// 이미 바이러스가 존재했던 칸이므로 확산 시간에 포함시키면 안된다.
			if(map[x][y] != 2)
				time = Math.max(time, t);
			
			for(int dir = 0 ; dir < 4 ; dir++)
			{
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N ||
						map[nx][ny] == 1 || discovered[nx][ny])
					continue;
				
				q.add(new Virus(nx, ny, t + 1));
				discovered[nx][ny] = true;
			}
		}

		return remains == visit ? time : Integer.MAX_VALUE;
	}
}