import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17143_이진호_X
{
	static int R,C,M;
	static int[][][] map;//map에 상어 인덱스 배열 추가 
	static int[][] sharks;
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1][R+C+2];//상어는 최대 R+C만큼 뭉쳐있다 배열처음에는 수를 저장
		sharks = new int[M+1][5];//y위치 // x위치  // 속력 // 이동방향 // 크기
		for(int i = 0 ; i < M ; i++)
		{
			st = new StringTokenizer(br.readLine());
			sharks[i][0] = Integer.parseInt(st.nextToken());//y위치
			sharks[i][1] = Integer.parseInt(st.nextToken());//x위치
			sharks[i][2] = Integer.parseInt(st.nextToken());//속력
			sharks[i][3] = Integer.parseInt(st.nextToken());//이동방향
			sharks[i][4] = Integer.parseInt(st.nextToken());//크기
			map[sharks[i][0]][sharks[i][1]][0]++;
			map[sharks[i][0]][sharks[i][1]][map[sharks[i][0]][sharks[i][1]][0]] = i;
		}
		int fisherman = 0;
		int fishPoint = 0;
		
		/*System.out.println("=================");
		System.out.println(0+"초후"+fishPoint);
		System.out.println("=================");
		for(int a = 1 ; a <= R ; a++)
		{
			for(int b = 1 ; b <= C ; b++)
			{
				System.out.print(map[a][b][0]+"\t");
			}
			System.out.println();
		}
		System.out.println("=================");*/
		
		
		for(int i = 1 ; i <= C; i++)
		{
			fisherman++;//낚시왕 이동
			fishPoint += fishing(fisherman);//낚시
			moving();//상어이동
			check();//한칸에 상어한마리이상 확인
			/*System.out.println("=================");
			System.out.println(i+"초후"+fishPoint);
			System.out.println("=================");
			for(int a = 1 ; a <= R ; a++)
			{
				for(int b = 1 ; b <= C ; b++)
				{
					System.out.print(map[a][b][0]+"\t");
				}
				System.out.println();
			}
			System.out.println("=================");*/
		}
		System.out.print(fishPoint);
	}
	private static int fishing(int x)
	{
		for(int i= 1 ; i <= R;i++)
		{
			if(map[i][x][0]>=1) //해당칸에 상어가 존재한다.
			{
//				System.out.println(i+"  "+x);
				map[i][x][0]--;
				sharks[map[i][x][1]][0] = 0;
				return sharks[map[i][x][1]][4];
			}
		}
		return 0;
	}
	private static void check()
	{
		for(int i = 1 ; i <= R ; i++)
		{
			for(int j = 1 ; j <= C ; j++)
			{
				if(map[i][j][0]>1)
				{
					eating(map[i][j]);
					//map[i][j][0] = 1;
				}
			}
		}
	}
	private static void eating(int[] sharkArr)
	{
		int winner = 1;
//		int nextShark = 2;
		int size = sharkArr[0];
		for(int i = 2 ; i <= size ; i++)
		{
			if(sharks[sharkArr[winner]][4]<sharks[sharkArr[i]][4])
			{
				winner = i;
				sharks[sharkArr[winner]][0] =-1;
			}
			else
			{
				sharks[sharkArr[i]][0] = -1;
			}
			sharkArr[0]--;
		}//이긴놈  찾앗다.
		
		sharkArr[1] = sharkArr[winner];
	}
	static int[] dx = {0,0,0,1,-1};//1: 상 2: 하 3:우 4: 좌
	static int[] dy = {0,-1,1,0,0};//1: 상 2: 하 3:우 4: 좌 0<->1 
	private static void moving()
	{
		for(int i = 0 ; i < M ; i++)
		{
			if(sharks[i][0]<0) continue;//죽은애들
			swim(sharks[i],i);//상어 한마리가 움직여서 이동
		}
		
	}
	private static void swim(int[] shark,int si)
	{
		//원래 잇던데 줄이고
		map[shark[0]][shark[1]][0]--;
		
		if(shark[3]<=2)//상하
		{
			int ty = shark[0];
			int distance = shark[2];
			int dir = shark[3];
			for(int i= 0 ; i < distance ; i++)//간다음 방향을 바꾼다.
			{//붙어있는데 반대를 보는경우
				ty = ty+dy[dir];
				if(ty>=R||ty<=1)
				{
					dir = dir==1? 2:1;
				}
				if(ty>R||ty<1)
				{
					ty = ty+dy[dir];
					ty = ty+dy[dir];
				}
			}
//			System.out.println(ty+"   "+shark[1]);
			map[ty][shark[1]][0] +=1;
			int index = map[ty][shark[1]][0];
			map[ty][shark[1]][index] = si;
			shark[0] = ty;
			shark[3] = dir;
		}
		
		else if(shark[3]>=3)//좌우
		{
			int tx = shark[1];
			int distance = shark[2];
			int dir = shark[3];
			for(int i= 0 ; i < distance ; i++)//간다음 방향을 바꾼다.
			{
				tx = tx+dx[dir];
				if(tx>=C||tx<=1)
				{
					dir = dir==3? 4:3;
				}
				if(tx>C||tx<1)//시작시 넘어간겨우
				{
					tx = tx+dx[dir];
					tx = tx+dx[dir];
				}
			}
			map[shark[0]][tx][0] +=1;
			int index = map[shark[0]][tx][0];
			map[shark[0]][tx][index] = si;
			shark[1] = tx;
			shark[3] = dir;
		}

	}

}
