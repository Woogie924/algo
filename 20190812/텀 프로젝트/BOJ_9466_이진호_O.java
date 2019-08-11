import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 입력
 * T 테스트케이스
 * N 사람수
 * 각 사람별 선택 배열
 * ::
 * 출력
 * 프로젝트 팀에 속하지 못한 학생들 수
 * 
 */
public class 텀프로젝트
{
	static int[] arr;
	static int T, N;
	static int[] visited;
	static int 왕따count;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++)
		{
			N = Integer.parseInt(br.readLine());
			왕따count = N;
			arr = new int[N + 1];
			visited = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++)
			{
				arr[i] = Integer.parseInt(st.nextToken());
			}
		for (int i = 1; i <= N; i++)
		{
			if(visited[i]==2) continue;
			solve(i, 0,new int[N]);// 현재 위치
		}
		System.out.println(왕따count);
		}
	}
	//0 안간데 1 현재 진행중 2 갓던데
	private static void solve(int here,int depth,int[] record)
	{
		// record에 없는데 visited된곳
		if (visited[here] ==1) {
			for(int i = depth ; here!=record[i]; i--)
			{
				왕따count--;
			}
			return;
		}
			if (visited[here]!=2)
			{
				visited[here] = 1;//여기서 하지말자
				record[depth] = here;
				solve(arr[here], depth+1,record);
				visited[here] = 2;
				return;
			}
		
	
	}

}
