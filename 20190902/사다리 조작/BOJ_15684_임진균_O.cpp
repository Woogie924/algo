#include <cstdio>
#include <vector>

using namespace std;

struct Pos
{
	int h;
	int n;

	Pos() {}
	Pos(int h, int n) : h(h), n(n) {}
};

int N, M, H;
int ladder[30 + 2][10 * 2 + 1] = { 0 };
vector<Pos> hLines;

int solve();
bool pick(int toPick, vector<int> &picked);
bool check();

int main(void)
{	
	scanf("%d %d %d", &N, &M, &H);
	
	// 사다리 초기화
	for (int n = 1; n <= N; n++)
		for (int h = 0; h <= H + 1; h++)
			ladder[h][n * 2 - 1] = 1;

	// 초기 가로선 입력
	for (int i = 0; i < M; i++)
	{
		int a, b;
		scanf("%d %d", &a, &b);
		ladder[a][b * 2] = 1;
	}

	// 선택되지 않은 가로선을 벡터에 추가한다.
	for (int h = 1; h <= H; h++)
		for (int n = 1; n < N; n++)
			if (!ladder[h][2 * n])
				hLines.push_back(Pos(h, n));

	printf("%d", solve());

	return 0;
}

int solve()
{
	vector<int> picked;
	for (int i = 0; i <= 3; i++)
		if (pick(i, picked))
			return i;

	return -1;
}

// toPick개의 가로선을 추가로 선택하고, 사다리 조작의 성공여부를 반환한다.
bool pick(int toPick, vector<int> &picked)
{
	if (toPick == 0)
		return check();

	int smallest = picked.empty() ? 0 : picked.back() + 1;
	int size = hLines.size();
	for (int next = smallest; next < size; next++)
	{
		int h = hLines[next].h;
		int n = hLines[next].n;

		// 두 가로선이 연속하거나 서로 접하면 안된다.
		if (ladder[h][2 * n - 2] || ladder[h][2 * n + 2])
			continue;

		ladder[h][2 * n] = 1; // 새로운 가로선을 연결한다.
		picked.push_back(next);
		bool ret = pick(toPick - 1, picked);
		picked.pop_back();
		ladder[h][2 * n] = 0; // 연결한 가로선을 지운다.

		if (ret)
			return true;
	}

	return false;
}

// 사다리 조작의 성공여부를 반환한다.
bool check()
{
	for (int n = 1; n <= N; n++)
	{
		int temp = n;
		for (int h = 0; h <= H + 1; h++)
		{
			int index = temp * 2 - 1;

			// i + 1번 세로선과 연결된 경우
			if (ladder[h][index] && ladder[h][index + 1])
				temp++;
			// i - 1번 세로선과 연결된 경우
			else if (ladder[h][index] && ladder[h][index - 1])
				temp--;
		}

		if (temp != n)
			return false;
	}

	return true;
}