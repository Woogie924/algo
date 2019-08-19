import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 드래곤커브 {
	static int N;
	static Loc cur;
	static int[][] map;
	static List<Integer> list;
	static int[] dy = {0,-1,0,1}; // 동 북 서 남
	static int[] dx = {1,0,-1,0};
	static int result;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		int x,y,d,g;
		StringTokenizer st;
		list = new ArrayList<Integer>();
		map = new int[101][101];
		for(int i = 0 ; i < 101;i++)
			Arrays.fill(map[i], 0);
		result = 0;
		
		for(int tc = 0 ; tc < N ; tc++)
		{
			list.clear();
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			list.add(d);
			map[y][x] = 1;
			y = y+dy[d];
			x = x+dx[d];
			cur = new Loc(x,y);
			map[cur.y][cur.x] = 1;// 처음 시작하는곳 찍어줌
			for(int i = 0 ; i < g ; i++)
			{
				int size = list.size();
				for(int j = size-1 ; j >=0 ; j--)
				{
					int newd = (list.get(j)+1+4)%4;
					list.add(newd);
					cur.x = cur.x+dx[newd];
					cur.y = cur.y+dy[newd];
					map[cur.y][cur.x] = 1; 
					/*System.out.printf("cur.y : %d cut.x : %d\n",cur.y,cur.x);
					for(int k = 0 ; k < 6; k++)
					{
						System.out.println(Arrays.toString(map[k]));
					}*/
				}
			}
			
			//0 동 북 서 남
			
		}
		int sum;
		for(int i = 0 ; i< 100; i++)
		{
			for(int j = 0 ; j < 100;j++)
			{
				sum = 0;
				sum+= map[i][j];
				sum+= map[i+1][j];
				sum+= map[i][j+1];
				sum+= map[i+1][j+1];
				if(sum == 4) result++;
			}
		}
		System.out.println(result);
	}
	static class Loc
	{
		int x;
		int y;
		public Loc(int x,int y)
		{
			this.x =x;
			this.y= y;
		}
	}
}