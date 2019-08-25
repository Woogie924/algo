import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Tree
{
	int x;
	int y;
	int age;
	
	public Tree() {}
	public Tree(int x, int y, int age) 
	{
		this.x = x;
		this.y = y;
		this.age = age;
	}
}

class Main {

	static int N, M, K;
	static int A[][] = new int[10 + 1][10 + 1];
	static int map[][] = new int[10 + 1][10 + 1];
	static LinkedList<Tree> trees = new LinkedList<Tree>();
	
	static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int dy[] = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokenizer.nextToken()); // 땅 크기
		M = Integer.parseInt(tokenizer.nextToken()); // 구매한 나무의 수
		K = Integer.parseInt(tokenizer.nextToken()); // 몇 년후?
		
		// S2D2가 추가하는 양분의 양
		for(int i = 1 ; i <= N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 1 ; j <= N ; j++)
				A[i][j] = Integer.parseInt(tokenizer.nextToken());
		}
		
		// 양분을 초기화한다.
		for(int i = 1 ; i <= N ; i++)
			for(int j = 1 ; j <= N ; j++)
				map[i][j] = 5;
		
		// 구매한 나무를 리스트에 삽입한다.
		for(int i = 0 ; i < M ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(tokenizer.nextToken()); // 나무의 x좌표
			int y = Integer.parseInt(tokenizer.nextToken()); // 나무의 y좌표
			int z = Integer.parseInt(tokenizer.nextToken()); // 나무의 나이
			
			trees.add(new Tree(x, y, z));
		}
		
		// 나무 리스트를 나이순으로 정렬한다.
		Collections.sort(trees, new Comparator<Tree>() {
			public int compare(Tree o1, Tree o2) 
			{
				return o1.age - o2.age;
			}
		});
		
		out.write(solve() + "");
		out.flush();
	}
	
	static int solve()
	{
		for(int t = 0 ; t < K ; t++)
		{
			if(trees.size() == 0)
				break;
			
			// 봄
			ArrayList<Tree> deadTrees = new ArrayList<Tree>();
			Iterator<Tree> iter = trees.iterator();
			while(iter.hasNext())
			{
				Tree tree = iter.next();
				
				// 양분이 부족한 경우
				if(map[tree.x][tree.y] - tree.age < 0)
				{
					// 죽은 나무들은 새로운 양분이 된다.
					deadTrees.add(tree);
					iter.remove();
				}
				// 양분이 충분한 경우
				else
				{
					map[tree.x][tree.y] -= tree.age;
					tree.age++;
				}
			}

			// 여름
			int size = deadTrees.size();
			for(int i = 0 ; i < size ; i++)
				map[deadTrees.get(i).x][deadTrees.get(i).y] += deadTrees.get(i).age / 2;
			
			// 가을
			ArrayList<Tree> nextTrees = new ArrayList<Tree>();
			iter = trees.iterator();
			while(iter.hasNext())
			{
				Tree tree = iter.next();
				
				// 나무의 나이가 5의 배수라면 인접한 8개 칸에 나이가 1인 나무가 생긴다.
				if(tree.age % 5 == 0)
				{
					for(int dir = 0 ; dir < 8 ; dir++)
					{
						int nx = tree.x + dx[dir];
						int ny = tree.y + dy[dir];
						
						if(nx <= 0 || nx > N || ny <= 0 || ny > N)
							continue;
						
						nextTrees.add(new Tree(nx, ny, 1));
					}
				}
			}
			
			size = nextTrees.size();
			for(int i = 0 ; i < size ; i++)
				trees.addFirst(nextTrees.get(i));
			
			// 겨울
			for(int x = 1 ; x <= N ; x++)
				for(int y = 1 ; y <= N ; y++)
					map[x][y] += A[x][y];
		}

		return trees.size();
	}
}