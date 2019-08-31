#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct Pos
{
	int x;
	int y;

	Pos() {}
	Pos(int x, int y) : x(x), y(y) {}
};

int N, M;
int lab[8][8];

int wallCount = 0;
vector<Pos> viruses, blanks;

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };

int solve(vector<int> &picked);
int spreadViruses();

int main(void)
{
	scanf("%d %d", &N, &M);

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			scanf("%d", &lab[i][j]);

			if (lab[i][j] == 0)
				blanks.push_back(Pos(i, j));
			else if (lab[i][j] == 1)
				wallCount++;
			else
				viruses.push_back(Pos(i, j));
		}
	}

	vector<int> picked;
	printf("%d", solve(picked));

	return 0;
}

int solve(vector<int> &picked)
{
	// 벽을 세울 안전 영역 3개를 모두 선택한 경우
	if (picked.size() == 3)
		return spreadViruses();

	int maxSize = -1;
	int blanksSize = blanks.size();
	int smallest = picked.empty() ? 0 : picked.back() + 1;
	for (int next = smallest; next < blanksSize; next++)
	{
		picked.push_back(next);
		lab[blanks[next].x][blanks[next].y] = 1; // 벽을 세운다.
		maxSize = max(maxSize, solve(picked));
		lab[blanks[next].x][blanks[next].y] = 0; // 벽을 제거한다.
		picked.pop_back();
	}

	return maxSize;
}

int spreadViruses()
{
	bool discovered[8][8] = { 0 };
	queue<int> q;

	// 초기 상태 삽입
	int size = viruses.size();
	for (int i = 0; i < size; i++)
	{
		Pos pos = viruses[i];
		q.push(pos.x);
		q.push(pos.y);
		discovered[pos.x][pos.y] = true;
	}

	int spreaded = 0;
	while (!q.empty())
	{
		int x = q.front(); q.pop();
		int y = q.front(); q.pop();

		spreaded++;

		for (int dir = 0; dir < 4; dir++)
		{
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M ||
				lab[nx][ny] == 1 || discovered[nx][ny])
				continue;

			q.push(nx);
			q.push(ny);
			discovered[nx][ny] = true;
		}
	}

	return (N * M) - (wallCount + 3 + spreaded);
}