#include <cstdio>
#include <algorithm>

using namespace std;

int paper[10][10]; // 종이 상태
int remains[5 + 1]; // 사용 가능한 색종이의 수

int minCount = 0x7FFFFFFF; // 모든 1을 덮는데 필요한 색종이의 최소 개수

void solve(int x, int y, int count);
bool check(int x, int y, int size);
void setPaper(int x, int y, int size, int value);

int main(void)
{	
	for (int i = 0; i < 10; i++)
		for (int j = 0; j < 10; j++)
			scanf("%d", &paper[i][j]);

	for (int i = 1; i <= 5; i++)
		remains[i] = 5;

	solve(0, 0, 0);
	printf("%d", (minCount != 0x7FFFFFFF ? minCount : -1));

	return 0;
}

void solve(int x, int y, int count)
{
	// 기저 사례 : 한 행의 끝에 도달한 경우
	if (y == 10)
	{
		solve(x + 1, 0, count);
		return;
	}

	// 기저 사례 : 종이의 내용이 0으로 되어있는 경우
	if (paper[x][y] == 0)
	{
		solve(x, y + 1, count);
		return;
	}

	// 기저 사례 : 종이의 모든 영역을 검사한 경우
	if (x == 10)
	{
		minCount = min(minCount, count);
		return;
	}

	for (int next = 1; next <= 5; next++)
	{
		if (remains[next] == 0)
			continue;

		// 색종이의 크기가 next일 때 붙이지 못한다면,
		// 크기가 더 큰 색종이는 확인할 필요도 없다.
		if (!check(x, y, next))
			break;

		remains[next]--;
		setPaper(x, y, next, 0);
		solve(x, y + 1, count + 1);
		setPaper(x, y, next, 1);
		remains[next]++;
	}
}

bool check(int x, int y, int size)
{
	for (int i = x; i < x + size; i++)
		for (int j = y; j < y + size; j++)
			if (i < 0 || i >= 10 || j < 0 || j >= 10 || paper[i][j] == 0)
				return false;

	return true;
}

void setPaper(int x, int y, int size, int value)
{
	for (int i = x; i < x + size; i++)
		for (int j = y; j < y + size; j++)
			paper[i][j] = value;
}