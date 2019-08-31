#include <cstdio>
#include <cstring>
#include <algorithm>

using namespace std;

struct Block
{
	int size;
	bool merged;

	Block() {}
	Block(int size) : size(size), merged(false) {}
};

int N, M; // 보드의 크기
Block board[20][20]; // 보드
int maxBlockSize = -1; // 최대 5번 이동 시켜서 얻을 수 있는 가장 큰 블록

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };

void solve(int moved);
void moveBlocks(int dir);
void moveBlock(int sx, int sy, int dir);
int getMaxSizeBlock();

int main(void)
{
	// 지도의 크기 N을 입력받는다.
	scanf("%d", &N);

	// 보드의 상태를 입력받는다.
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			int temp;
			scanf("%d", &temp);
			board[i][j] = Block(temp);
		}
	}

	solve(0);
	printf("%d", maxBlockSize);

	return 0;
}

void solve(int moved)
{
	// 기저 사례 : 5번 넘게 움직인 경우
	if (moved > 5)
		return;

	// 가장 큰 블록을 갱신한다.
	maxBlockSize = max(maxBlockSize, getMaxSizeBlock());

	// 원래 보드 상태를 저장한다.
	Block prevBoard[20][20];
	memcpy(prevBoard, board, sizeof(board));

	for (int dir = 0; dir < 4; dir++)
	{
		// 해당 방향으로 블록들을 움직인다.
		moveBlocks(dir);

		solve(moved + 1);

		// 보드를 원래 상태로 되돌린다.
		memcpy(board, prevBoard, sizeof(prevBoard));
	}
}

void moveBlocks(int dir)
{
	switch (dir)
	{
		// 북쪽으로
		case 0:
		{
			for (int sy = 0; sy < N; sy++)
				for (int sx = 0; sx < N; sx++)
					if(board[sx][sy].size > 0)
						moveBlock(sx, sy, dir);

			break;
		}

		// 동쪽으로
		case 1:
		{
			for (int sx = 0; sx < N; sx++)
				for (int sy = N - 1; sy >= 0; sy--)
					if (board[sx][sy].size > 0)
						moveBlock(sx, sy, dir);

			break;
		}

		// 남쪽으로
		case 2:
		{
			for (int sy = 0; sy < N; sy++)
				for (int sx = N - 1; sx >= 0; sx--)
					if (board[sx][sy].size > 0)
						moveBlock(sx, sy, dir);

			break;
		}

		// 서쪽으로
		case 3:
		{
			for (int sx = 0; sx < N; sx++)
				for (int sy = 0; sy < N; sy++)
					if (board[sx][sy].size > 0)
						moveBlock(sx, sy, dir);

			break;
		}
	}

	for (int x = 0; x < N; x++)
		for (int y = 0; y < N; y++)
			board[x][y].merged = false;
}

void moveBlock(int sx, int sy, int dir)
{
	int x = sx, y = sy;

	// (sx, sy) 좌표의 블록을 dir방향으로 끝까지 이동시킨다.
	while (1)
	{
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		// 경계를 벗어난 경우
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
			break;

		// 합쳐야 되는 경우
		if ((board[nx][ny].size == board[x][y].size) && 
			!board[nx][ny].merged && !board[x][y].merged)
		{
			board[nx][ny].size *= 2;
			board[nx][ny].merged = true;
			board[x][y] = Block(0);
			break;
		}
		// 한 칸 이동할 수 있는 경우
		else if (board[nx][ny].size == 0)
		{
			board[nx][ny] = board[x][y];
			board[x][y] = Block(0);
			x = nx;
			y = ny;
		}
		// 합치지도 못하고 이동도 불가능할 경우
		else
			break;
	}
}

int getMaxSizeBlock()
{
	int maxBlockSize = -1;

	for (int x = 0; x < N; x++)
		for (int y = 0; y < N; y++)
			maxBlockSize = max(maxBlockSize, board[x][y].size);

	return maxBlockSize;
}