#include <cstdio>
#include <cstring>
#include <list>
#include <algorithm>

using namespace std;

struct Cell
{
	int x;
	int y;
	int X; // 생명력
	int T; // 세포가 생성된 시각 또는 세포가 활성화된 시각

	Cell() {}
	Cell(int x, int y, int X, int T) : x(x), y(y), X(X), T(T) {}
};

int N, M; // 초기 상태에서 줄기 세포가 분포된 영역의 가로, 세로 크기
int K; // 배양 시간
int map[300 + 50 + 300][300 + 50 + 300];

list<Cell> unactive; // 비활성 상태인 세포
list<Cell> active; // 활성 상태인 세포

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };

int solve();
bool compare(const Cell &c1, const Cell &c2);

int main(void)
{	
	int T;
	scanf("%d", &T);

	for (int tc = 0; tc < T; tc++)
	{
		// 각종 초기화
		memset(map, 0, sizeof(map));
		unactive.clear();
		active.clear();

		scanf("%d %d %d", &N, &M, &K);

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				int x = i + 300;
				int y = j + 300;

				scanf("%d", &map[x][y]);

				// 초기 상태의 줄기 세포들은 비활성 상태이다.
				if (map[x][y] > 0)
					unactive.push_back(Cell(x, y, map[x][y], 0));
			}
		}

		printf("#%d %d\n", tc + 1, solve());
	}

	return 0;
}

int solve()
{
	list<Cell>::iterator it;

	for (int t = 1; t <= K; t++)
	{
		// 1. 활성 상태로 변경이 필요한 세포들을 찾아서 변경해준다.
		it = unactive.begin();
		while (it != unactive.end())
		{
			Cell cell = *it;

			// X시간이 지나 활성 상태로 변경이 필요한 경우
			if (t - cell.T == cell.X)
			{
				active.push_back(Cell(cell.x, cell.y, cell.X, t));
				it = unactive.erase(it);
				continue;
			}

			it++;
		}

		// 2. 활성화 세포들을 생명력의 오름차순으로 정렬한다.
		active.sort(compare);

		// 3. 번식이 필요한 세포들을 찾아서 번식시킨다.
		//    또한, 소멸이 필요한 세포들을 찾아서 소멸시킨다.
		it = active.begin();
		while (it != active.end())
		{
			Cell cell = *it;

			// 활성화된 줄기 세포는 첫 1시간 동안 번식한다.
			if (t - cell.T == 1)
			{
				for (int dir = 0; dir < 4; dir++)
				{
					int nx = cell.x + dx[dir];
					int ny = cell.y + dy[dir];

					if (map[nx][ny] > 0)
						continue;

					map[nx][ny] = cell.X;
					unactive.push_back(Cell(nx, ny, cell.X, t));
				}
			}

			// 활성 상태가 된 순간으로 부터 X시간이 지났다면 해당 세포를 소멸시킨다.
			if (t - cell.T == cell.X)
			{
				it = active.erase(it);
				continue;
			}

			it++;
		}
	}

	return unactive.size() + active.size();
}

bool compare(const Cell &c1, const Cell &c2)
{
	return (c1.X > c2.X);
}