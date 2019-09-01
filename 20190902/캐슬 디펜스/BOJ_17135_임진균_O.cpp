#include <cstdio>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int N, M, D;
int board[15 + 1][15] = { 0 };
int enemy = 0;

const int dx[3] = { 0, -1, 0 }; // 서, 북, 동
const int dy[3] = { -1, 0, 1 };

int solve(vector<int> &picked);
int simulate(const vector<int> &picked);
int attack(const vector<int> &picked, int map[][15]);
void move(int map[][15]);

int main(void)
{	
	scanf("%d %d %d", &N, &M, &D);

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			scanf("%d", &board[i][j]);

			if (board[i][j] == 1)
				enemy++;
		}
	}

	vector<int> picked;
	printf("%d", solve(picked));

	return 0;
}

int solve(vector<int> &picked)
{
	// 기저 사례 : 궁수 3명을 모두 배치한 경우
	if (picked.size() == 3)
		return simulate(picked);

	int maxKilled = -1;
	int smallest = picked.empty() ? 0 : picked.back() + 1;
	for (int next = smallest; next < M; next++)
	{
		picked.push_back(next);
		maxKilled = max(maxKilled, solve(picked));
		picked.pop_back();
	}

	return maxKilled;
}

int simulate(const vector<int> &picked)
{
	// 초기 격자판 상태를 복사한다.
	int map[15 + 1][15]; // 변수명을 뭐라고 지어야 할까...
	memcpy(map, board, sizeof(board));

	int killed = 0;
	for (int i = 0; i < N; i++)
	{
		killed += attack(picked, map);
		move(map);

		// 모든 적을 다 제거한 경우
		if (enemy == killed)
			break;
	}

	return killed;
}

// 궁수들이 범위 내에 있는 적을 공격한다.
int attack(const vector<int> &picked, int map[][15])
{
	int minDist[3] = { 0x7FFFFFFF, 0x7FFFFFFF, 0x7FFFFFFF };
	int ex[3] = { -1, -1, -1 };
	int ey[3] = { -1, -1, -1 };

	queue<int> q;
	bool discovered[3][15 + 1][15] = { 0 };

	// 궁수가 서있는 곳이 초기 상태가 된다.
	for (int i = 0; i < picked.size(); i++)
	{
		q.push(i);
		q.push(N);
		q.push(picked[i]);
		q.push(0);
		discovered[i][N][picked[i]] = true;
	}
	
	while (!q.empty())
	{
		int archer = q.front(); q.pop();
		int x = q.front(); q.pop();
		int y = q.front(); q.pop();
		int dist = q.front(); q.pop();

		if (dist >= minDist[archer])
			continue;

		// 가장 가까운 적을 찾았다면 표시해 놓는다.
		if (map[x][y])
		{
			minDist[archer] = dist;
			ex[archer] = x;
			ey[archer] = y;
			continue;
		}

		for (int dir = 0; dir < 3; dir++)
		{
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M ||
				(dist + 1) > D || discovered[archer][nx][ny])
				continue;

			q.push(archer);
			q.push(nx);
			q.push(ny);
			q.push(dist + 1);
			discovered[archer][nx][ny] = true;
		}
	}

	// 제거된 적을 격자판에서 지운다.
	int killed = 0;
	for (int i = 0; i < 3; i++)
	{
		if (ex[i] != -1 && ey[i] != -1)
		{
			if (map[ex[i]][ey[i]])
			{
				map[ex[i]][ey[i]] = 0;
				killed++;
			}
		}
	}

	return killed;
}

// 적들을 아래로 한 칸 이동시킨다.
void move(int map[][15])
{
	for (int i = N - 1; i > 0; i--)
		for (int j = 0; j < M; j++)
			map[i][j] = map[i - 1][j];

	for (int j = 0; j < M; j++)
		map[0][j] = 0;
}