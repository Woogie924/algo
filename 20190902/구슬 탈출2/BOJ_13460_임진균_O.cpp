#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

using namespace std;

struct Pos
{
	int x;
	int y;

	Pos() {}
	Pos(int x, int y) : x(x), y(y) {}
};

int N, M; // 보드의 세로, 가로 크기
char board[10][10]; // 보드

Pos red, blue;

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };

int solve(int moved);
void leanBoard(int dir);

int main(void)
{
	// N, M을 입력받는다.
	scanf("%d %d", &N, &M);

	// 보드의 상태를 입력받는다.
	for (int i = 0; i < N; i++)
	{
		char temp[10 + 1];
		scanf("%s", temp);

		for (int j = 0; j < M; j++)
		{
			board[i][j] = temp[j];

			if (board[i][j] == 'R')
			{
				board[i][j] = '.';
				red = Pos(i, j);
			}
			else if (board[i][j] == 'B')
			{
				board[i][j] = '.';
				blue = Pos(i, j);
			}
		}
	}

	int result = solve(0);
	printf("%d", result != 987654321 ? result : -1);

	return 0;
}

int solve(int moved)
{
	// 기저 사례 : 10번 이상을 움직였거나, 파란 구슬이 구멍에 빠졌다면 실패이다.
	if ((moved > 10) || (blue.x == -1 && blue.y == -1))
		return 987654321;

	// 기저 사례 : 빨간 구슬만 구멍으로 빼냈다면 성공이다.
	if (red.x == -1 && red.y == -1)
		return 0;

	int minMoved = 987654321;
	for (int dir = 0; dir < 4; dir++)
	{
		// 구슬의 원본 상태를 저장한다.
		Pos prevRed = red;
		Pos prevBlue = blue;

		// 보드를 기울인다.
		leanBoard(dir);

		// 구슬의 위치에 변화가 없다면 이번 시도는 무시하고 다른 방향으로 시도한다.
		if (prevRed.x == red.x && prevRed.y == red.y &&
			prevBlue.x == blue.x && prevBlue.y == blue.y)
			continue;

		minMoved = min(minMoved, solve(moved + 1) + 1);

		// 구슬을 원본 상태로 되돌린다.
		red = prevRed;
		blue = prevBlue;
	}

	return minMoved;
}

// 보드를 dir방향으로 기울인다.
void leanBoard(int dir)
{
	// 어느 구슬을 먼저 움직여야 하는지 선정한다.
	// bid1, bid2 순서로 구슬을 움직인다. 
	Pos *bid1, *bid2;
	switch (dir)
	{
		// 북
	case 0:
		bid1 = (red.x < blue.x ? &red : &blue);
		bid2 = (red.x < blue.x ? &blue : &red);
		break;

		// 동 
	case 1:
		bid1 = (red.y > blue.y ? &red : &blue);
		bid2 = (red.y > blue.y ? &blue : &red);
		break;

		// 남
	case 2:
		bid1 = (red.x > blue.x ? &red : &blue);
		bid2 = (red.x > blue.x ? &blue : &red);
		break;

		// 서
	case 3:
		bid1 = (red.y < blue.y ? &red : &blue);
		bid2 = (red.y < blue.y ? &blue : &red);
		break;
	}

	// bid1 구슬을 움직인다.
	while (1)
	{
		if (board[bid1->x][bid1->y] == 'O')
		{
			bid1->x = -1;
			bid1->y = -1;
			break;
		}

		int nx = bid1->x + dx[dir];
		int ny = bid1->y + dy[dir];

		if (nx < 0 || nx >= N || ny < 0 || ny >= M ||
			board[nx][ny] == '#')
			break;

		bid1->x = nx;
		bid1->y = ny;
	}

	// bid2 구슬을 움직인다.
	while (1)
	{
		if (board[bid2->x][bid2->y] == 'O')
		{
			bid2->x = -1;
			bid2->y = -1;
			break;
		}

		int nx = bid2->x + dx[dir];
		int ny = bid2->y + dy[dir];

		if (nx < 0 || nx >= N || ny < 0 || ny >= M ||
			board[nx][ny] == '#' || (nx == bid1->x && ny == bid1->y))
			break;

		bid2->x = nx;
		bid2->y = ny;
	}
}