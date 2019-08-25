import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class CubePiece
{
	char plane[];

	public CubePiece() {}
	public CubePiece(char U, char D, char R, char L, char F, char B) {
		plane = new char[6];
		
		plane[0] = U;
		plane[1] = D;
		plane[2] = R;
		plane[3] = L;
		plane[4] = F;
		plane[5] = B;
	}
}

class Cube
{
	CubePiece pos[][][] = new CubePiece[3][3][3];
	
	public Cube()
	{
		pos[0][0][0] = new CubePiece('w', '-', '-', 'g', '-', 'o');
		pos[0][0][1] = new CubePiece('-', '-', '-', 'g', '-', 'o');
		pos[0][0][2] = new CubePiece('-', 'y', '-', 'g', '-', 'o');		
		pos[0][1][0] = new CubePiece('w', '-', '-', '-', '-', 'o');
		pos[0][1][1] = new CubePiece('-', '-', '-', '-', '-', 'o');
		pos[0][1][2] = new CubePiece('-', 'y', '-', '-', '-', 'o');
		pos[0][2][0] = new CubePiece('w', '-', 'b', '-', '-', 'o');
		pos[0][2][1] = new CubePiece('-', '-', 'b', '-', '-', 'o');
		pos[0][2][2] = new CubePiece('-', 'y', 'b', '-', '-', 'o');
		
		pos[1][0][0] = new CubePiece('w', '-', '-', 'g', '-', '-');
		pos[1][0][1] = new CubePiece('-', '-', '-', 'g', '-', '-');
		pos[1][0][2] = new CubePiece('-', 'y', '-', 'g', '-', '-');
		pos[1][1][0] = new CubePiece('w', '-', '-', '-', '-', '-');
		pos[1][1][1] = new CubePiece('-', '-', '-', '-', '-', '-');
		pos[1][1][2] = new CubePiece('-', 'y', '-', '-', '-', '-');
		pos[1][2][0] = new CubePiece('w', '-', 'b', '-', '-', '-');
		pos[1][2][1] = new CubePiece('-', '-', 'b', '-', '-', '-');
		pos[1][2][2] = new CubePiece('-', 'y', 'b', '-', '-', '-');
		
		pos[2][0][0] = new CubePiece('w', '-', '-', 'g', 'r', '-');
		pos[2][0][1] = new CubePiece('-', '-', '-', 'g', 'r', '-');
		pos[2][0][2] = new CubePiece('-', 'y', '-', 'g', 'r', '-');
		pos[2][1][0] = new CubePiece('w', '-', '-', '-', 'r', '-');
		pos[2][1][1] = new CubePiece('-', '-', '-', '-', 'r', '-');
		pos[2][1][2] = new CubePiece('-', 'y', '-', '-', 'r', '-');
		pos[2][2][0] = new CubePiece('w', '-', 'b', '-', 'r', '-');
		pos[2][2][1] = new CubePiece('-', '-', 'b', '-', 'r', '-');
		pos[2][2][2] = new CubePiece('-', 'y', 'b', '-', 'r', '-');
	}
}

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		int T = Integer.parseInt(in.readLine());
		for(int tc = 0 ; tc < T ; tc++)
		{
			Cube cube = new Cube(); // 새로운 큐브를 하나 생성한다.
			int N = Integer.parseInt(in.readLine()); // 큐브를 돌린 횟수
			
			tokenizer = new StringTokenizer(in.readLine());
			for(int i = 0 ; i < N ; i++)
			{
				String temp = tokenizer.nextToken();
				char plane = temp.charAt(0);
				char rotDir = temp.charAt(1);
				
				rotateCube(cube, plane, rotDir);
			}
			
			// 큐브를 모두 돌린 후의 윗 면의 색상을 출력한다.
			for(int x = 0 ; x < 3 ; x++)
			{
				for(int y = 0 ; y < 3 ; y++)
					out.write(cube.pos[x][y][0].plane[0] + "");
				out.newLine();
			}
		}

		out.flush();
	}

	// cube의 plane면을 rotDir방향으로 회전시킨다.
	static void rotateCube(Cube cube, char plane, char rotDir)
	{
		switch(plane)
		{
			// 윗면, 아랫면
			case 'U':
			case 'D':
			{
				int z = (plane == 'U' ? 0 : 2);
				CubePiece temp = null;
				
				if(plane == 'D')
					rotDir = (rotDir == '+' ? '-' : '+');
				
				if(rotDir == '+')
				{
					temp = cube.pos[0][0][z];
					cube.pos[0][0][z] = cube.pos[2][0][z];
					cube.pos[2][0][z] = cube.pos[2][2][z];
					cube.pos[2][2][z] = cube.pos[0][2][z];
					cube.pos[0][2][z] = temp;
					
					temp = cube.pos[0][1][z];
					cube.pos[0][1][z] = cube.pos[1][0][z];
					cube.pos[1][0][z] = cube.pos[2][1][z];
					cube.pos[2][1][z] = cube.pos[1][2][z];
					cube.pos[1][2][z] = temp;
					
					for(int x = 0 ; x < 3 ; x++)
						for(int y = 0 ; y < 3 ; y++)
							rotateCubePiece(cube.pos[x][y][z], 1);
				}
				else
				{
					temp = cube.pos[0][0][z];
					cube.pos[0][0][z] = cube.pos[0][2][z];
					cube.pos[0][2][z] = cube.pos[2][2][z];
					cube.pos[2][2][z] = cube.pos[2][0][z];
					cube.pos[2][0][z] = temp;
					
					temp = cube.pos[0][1][z];
					cube.pos[0][1][z] = cube.pos[1][2][z];
					cube.pos[1][2][z] = cube.pos[2][1][z];
					cube.pos[2][1][z] = cube.pos[1][0][z];
					cube.pos[1][0][z] = temp;
					
					for(int x = 0 ; x < 3 ; x++)
						for(int y = 0 ; y < 3 ; y++)
							rotateCubePiece(cube.pos[x][y][z], 2);
				}

				break;
			}
			
			// 앞면, 뒷면
			case 'F':
			case 'B':
			{
				int x = (plane == 'F' ? 2 : 0);
				CubePiece temp = null;
				
				if(plane == 'B')
					rotDir = (rotDir == '+' ? '-' : '+');
				
				if(rotDir == '+')
				{
					temp = cube.pos[x][0][0];
					cube.pos[x][0][0] = cube.pos[x][0][2];
					cube.pos[x][0][2] = cube.pos[x][2][2];
					cube.pos[x][2][2] = cube.pos[x][2][0];
					cube.pos[x][2][0] = temp;
					
					temp = cube.pos[x][1][0];
					cube.pos[x][1][0] = cube.pos[x][0][1];
					cube.pos[x][0][1] = cube.pos[x][1][2];
					cube.pos[x][1][2] = cube.pos[x][2][1];
					cube.pos[x][2][1] = temp;

					for(int z = 0 ; z < 3 ; z++)
						for(int y = 0 ; y < 3 ; y++)
							rotateCubePiece(cube.pos[x][y][z], 3);
				}
				else
				{
					temp = cube.pos[x][0][0];
					cube.pos[x][0][0] = cube.pos[x][2][0];
					cube.pos[x][2][0] = cube.pos[x][2][2];
					cube.pos[x][2][2] = cube.pos[x][0][2];
					cube.pos[x][0][2] = temp;

					temp = cube.pos[x][1][0];
					cube.pos[x][1][0] = cube.pos[x][2][1];
					cube.pos[x][2][1] = cube.pos[x][1][2];
					cube.pos[x][1][2] = cube.pos[x][0][1];
					cube.pos[x][0][1] = temp;

					for(int z = 0 ; z < 3 ; z++)
						for(int y = 0 ; y < 3 ; y++)
							rotateCubePiece(cube.pos[x][y][z], 4);
				}

				break;
			}
				
			// 왼쪽면, 오른쪽면
			case 'L':
			case 'R':
			{
				int y = (plane == 'L' ? 0 : 2);
				CubePiece temp = null;
				
				if(plane == 'R')
					rotDir = (rotDir == '+' ? '-' : '+');
				
				if(rotDir == '+')
				{
					temp = cube.pos[0][y][0];
					cube.pos[0][y][0] = cube.pos[0][y][2];
					cube.pos[0][y][2] = cube.pos[2][y][2];
					cube.pos[2][y][2] = cube.pos[2][y][0];
					cube.pos[2][y][0] = temp;
	
					temp = cube.pos[1][y][0];
					cube.pos[1][y][0] = cube.pos[0][y][1];
					cube.pos[0][y][1] = cube.pos[1][y][2];
					cube.pos[1][y][2] = cube.pos[2][y][1];
					cube.pos[2][y][1] = temp;
					
					for(int z = 0 ; z < 3 ; z++)
						for(int x = 0 ; x < 3 ; x++)
							rotateCubePiece(cube.pos[x][y][z], 5);
				}
				else
				{
					temp = cube.pos[0][y][0];
					cube.pos[0][y][0] = cube.pos[2][y][0];
					cube.pos[2][y][0] = cube.pos[2][y][2];
					cube.pos[2][y][2] = cube.pos[0][y][2];
					cube.pos[0][y][2] = temp;

					temp = cube.pos[1][y][0];
					cube.pos[1][y][0] = cube.pos[2][y][1];
					cube.pos[2][y][1] = cube.pos[1][y][2];
					cube.pos[1][y][2] = cube.pos[0][y][1];
					cube.pos[0][y][1] = temp;
					
					for(int z = 0 ; z < 3 ; z++)
						for(int x = 0 ; x < 3 ; x++)
							rotateCubePiece(cube.pos[x][y][z], 6);
				}
				
				break;
			}
		}
	}
	
	// 큐브 조각을 rot방향으로 회전시킨다.
	static void rotateCubePiece(CubePiece cubePiece, int rot)
	{
		char plane[] = cubePiece.plane;
		
		switch(rot)
		{
			// z축을 기준으로 +방향 : 앞 -> 왼 -> 뒤 -> 오른 -> 앞
			case 1:
			{
				char temp = plane[4];
				plane[4] = plane[2];
				plane[2] = plane[5];
				plane[5] = plane[3];
				plane[3] = temp;

				break;
			}
			
			// z축을 기준으로 -방향 : 앞 -> 오른 -> 뒤 -> 왼 -> 앞
			case 2:
			{
				char temp = plane[4];
				plane[4] = plane[3];
				plane[3] = plane[5];
				plane[5] = plane[2];
				plane[2] = temp;
				
				break;
			}

			// x축을 기준으로 -방향 : 위 -> 오른 -> 아래 -> 왼 -> 위
			case 3:
			{
				char temp = plane[0];
				plane[0] = plane[3];
				plane[3] = plane[1];
				plane[1] = plane[2];
				plane[2] = temp;
			
				break;
			}
				
			// x축을 기준으로 +방향 : 위 -> 왼 -> 아래 -> 오른 -> 위
			case 4:
			{
				char temp = plane[0];
				plane[0] = plane[2];
				plane[2] = plane[1];
				plane[1] = plane[3];
				plane[3] = temp;

				break;
			}
			
			// y축을 기준으로 +방향 : 위 -> 앞 -> 아래-> 뒤 -> 위
			case 5:
			{
				char temp = plane[0];
				plane[0] = plane[5];
				plane[5] = plane[1];
				plane[1] = plane[4];
				plane[4] = temp;
	
				break;
			}		
			
			// y축을 기준으로 -방향 : 위 -> 뒤 -> 아래 -> 앞 -> 위
			case 6:
			{
				char temp = plane[0];
				plane[0] = plane[4];
				plane[4] = plane[1];
				plane[1] = plane[5];
				plane[5] = temp;

				break;
			}
		}
	}
}