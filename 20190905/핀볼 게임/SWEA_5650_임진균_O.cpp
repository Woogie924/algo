#include <cstdio>
#include <algorithm>

using namespace std;

struct Pos
{
	int x;
	int y;

	Pos() {}
	Pos(int x, int y) : x(x), y(y) {}

	bool operator==(const Pos &pos)
	{
		return (this->x == pos.x && this->y == pos.y);
	}
};

int N;
int board[100][100];

Pos wormholes[10 + 1][2];

// nextDir[b][from] :
// 핀볼이 from 방향으로 블록 b를 만났을 때, 다음에 진행할 방향을 저장한다.
const int nextDir[5 + 1][4] = 
{
	{-1, -1, -1, -1},	// 쓰레기 값
	{2, 3, 1, 0},		// 1번 블록
	{1, 3, 0, 2},		// 2번 블록
	{3, 2, 0, 1},		// 3번 블록
	{2, 0, 3, 1},		// 4번 블록
	{2, 3, 0, 1}		// 5번 블록
};

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };

int solve();
int simulate(Pos start, int startDir);
Pos goWarmhole(int sx, int sy);

int main(void)
{	
	int T;
	scanf("%d", &T);

	for (int tc = 0; tc < T; tc++)
	{
		// 각종 초기화
		for (int i = 6; i <= 10; i++)
			for (int j = 0; j < 2; j++)
				wormholes[i][j] = Pos(-1, -1);

		scanf("%d", &N);

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				scanf("%d", &board[i][j]);

				int &space = board[i][j];

				if (space >= 6 && space <= 10)
				{
					if (wormholes[space][0] == Pos(-1, -1))
						wormholes[space][0] = Pos(i, j);
					else
						wormholes[space][1] = Pos(i, j);
				}
			}
		}

		printf("#%d %d\n", tc + 1, solve());
	}

	return 0;
}

int solve()
{
	int maxScore = -1;

	for (int sx = 0; sx < N; sx++)
	{
		for (int sy = 0; sy < N; sy++)
		{
			if (board[sx][sy] != 0)
				continue;

			for (int sdir = 0; sdir < 4; sdir++)
				maxScore = max(maxScore, simulate(Pos(sx, sy), sdir));
		}
	}
	
	return maxScore;
}

int simulate(Pos start, int startDir)
{
	int score = 0;

	int x = start.x, y = start.y;	// 핀볼의 초기 위치
	int dir = startDir;				// 핀볼의 초기 방향

	while (1)
	{
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		// 경계를 벗어나기 때문에 정반대 방향으로 튕기는 경우
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
		{
			score++;
			nx = x;
			ny = y;

			dir = (dir + 2) % 4;

			if (board[nx][ny] >= 1 && board[nx][ny] <= 5)
			{
				score++;
				dir = nextDir[board[nx][ny]][dir];
			}
			else if (board[nx][ny] >= 6 && board[nx][ny] <= 10)
			{
				Pos pos = goWarmhole(nx, ny);
				nx = pos.x;
				ny = pos.y;
			}
		}
		// 특정 블록에 부딪혀 정반대 방향으로 튕기는 경우
		else if (board[nx][ny] >= 1 && board[nx][ny] <= 5 && 
			(nextDir[board[nx][ny]][dir] == (dir + 2) % 4))
		{
			score++;
			nx = x;
			ny = y;

			dir = (dir + 2) % 4;

			if (board[nx][ny] >= 1 && board[nx][ny] <= 5)
			{
				score++;
				dir = nextDir[board[nx][ny]][dir];
			}
			else if (board[nx][ny] >= 6 && board[nx][ny] <= 10)
			{
				Pos pos = goWarmhole(nx, ny);
				nx = pos.x;
				ny = pos.y;
			}
		}
		// 특정 블록에 부딪혀 직각으로 튕기는 경우
		else if (board[nx][ny] >= 1 && board[nx][ny] <= 5)
		{
			score++;
			dir = nextDir[board[nx][ny]][dir];
		}
		// 다음 위치에 웜홀이 있는 경우
		else if (board[nx][ny] >= 6 && board[nx][ny] <= 10)
		{
			Pos pos = goWarmhole(nx, ny);
			nx = pos.x;
			ny = pos.y;
		}
		
		// (nx, ny)가 블랙홀이거나, 초기 위치인 경우
		if ((nx == start.x && ny == start.y) || board[nx][ny] == -1)
			break;

		x = nx;
		y = ny;
	}

	return score;
}

Pos goWarmhole(int x, int y)
{
	Pos &wormhole1 = wormholes[board[x][y]][0];
	Pos &wormhole2 = wormholes[board[x][y]][1];

	return wormhole1 == Pos(x, y) ? wormhole2 : wormhole1;
}