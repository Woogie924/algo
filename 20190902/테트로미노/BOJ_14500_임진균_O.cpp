#include <cstdio>
#include <algorithm>

using namespace std;

// 모든 테트로미노를 정의한다.
const int tetrominos[19][4][2] =
{
	// xxxx
	{{0, 0}, {0, 1}, {0, 2}, {0, 3}},
	{{0, 0}, {1, 0}, {2, 0}, {3, 0}},

	// xx
	// xx 
	{{0, 0}, {0, 1}, {1, 0}, {1, 1}},

	// x
	// xxx
	{{0, 0}, {1, 0}, {2, 0}, {2, 1}},
	{{0, 0}, {0, 1}, {0, 2}, {1, 0}},
	{{0, 0}, {0, 1}, {1, 1}, {2, 1}},
	{{0, 0}, {1, 0}, {1, -1}, {1, -2}},
	{{0, 0}, {1, 0}, {2, 0}, {2, -1}},
	{{0, 0}, {1, 0}, {1, 1}, {1, 2}},
	{{0, 0}, {1, 0}, {2, 0}, {0, 1}},
	{{0, 0}, {0, 1}, {0, 2}, {1, 2}},

	// xx
	//  xx
	{{0, 0}, {1, 0}, {1, 1}, {2, 1}},
	{{0, 0}, {0, 1}, {1, 0}, {1, -1}},
	{{0, 0}, {1, 0}, {1, -1}, {2, -1}},
	{{0, 0}, {0, 1}, {1, 1}, {1, 2}},

	//  x 
	// xxx
	{{0, 0}, {0, 1}, {0, 2}, {1, 1}},
	{{0, 0}, {1, 0}, {2, 0}, {1, -1}},
	{{0, 0}, {1, 0}, {1, -1}, {1, 1}},
	{{0, 0}, {1, 0}, {2, 0}, {1, 1}}
};

int N, M;
int map[500][500];

int solve();
bool checkBoundary(int x, int y, int t);
int getSum(int x, int y, int t);

int main(void)
{
	scanf("%d %d", &N, &M);

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			scanf("%d", &map[i][j]);

	printf("%d", solve());

	return 0;
}

int solve()
{
	int maxSum = -1;

	for (int x = 0; x < N; x++)
	{
		for (int y = 0; y < M; y++)
		{
			for (int t = 0; t < 19; t++)
			{
				if (!checkBoundary(x, y, t))
					continue;

				maxSum = max(maxSum, getSum(x, y, t));
			}
		}
	}

	return maxSum;
}

bool checkBoundary(int x, int y, int t)
{
	for (int i = 0; i < 4; i++)
	{
		int nx = x + tetrominos[t][i][0];
		int ny = y + tetrominos[t][i][1];

		if (nx < 0 || nx >= N || ny < 0 || ny >= M)
			return false;
	}

	return true;
}

int getSum(int x, int y, int t)
{
	int sum = 0;

	for (int i = 0; i < 4; i++)
	{
		int nx = x + tetrominos[t][i][0];
		int ny = y + tetrominos[t][i][1];
		sum += map[nx][ny];
	}

	return sum;
}