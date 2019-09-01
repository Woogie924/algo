#include <cstdio>
#include <cstring>
#include <vector>
#include <queue>

using namespace std;

struct Pos
{
	int x;
	int y;

	Pos() {}
	Pos(int x, int y) : x(x), y(y) {}
};

char field[12][6];

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, 1, 0, -1 };

int solve();
bool popAllPuyos();
void fallAllPuyos();

int main(void)
{	
	// 초기 필드 상태를 입력받는다.
	for (int i = 0; i < 12; i++)
	{
		char temp[6 + 1];
		scanf("%s", temp);

		for (int j = 0; j < 6; j++)
			field[i][j] = temp[j];
	}
	
	printf("%d", solve());

	return 0;
}

int solve()
{
	int combo = 0; // 몇 연쇄인지?
	while (popAllPuyos())
	{
		combo++;
		fallAllPuyos();
	}

	return combo;
}

// 터뜨릴 수 있는 뿌요를 모두 찾아서 터뜨린다.
// 뿌요를 하나라도 터뜨렸는지 여부를 반환한다.
bool popAllPuyos()
{
	vector<Pos> popList;
	bool discovered[12][6] = { 0 };

	for (int sx = 12 - 1; sx >= 0; sx--)
	{
		for (int sy = 0; sy < 6; sy++)
		{
			if (field[sx][sy] == '.' || discovered[sx][sy])
				continue;

			queue<Pos> q;
			q.push(Pos(sx, sy));
			discovered[sx][sy] = true;

			vector<Pos> findList;
			while (!q.empty())
			{
				Pos puyo = q.front(); q.pop();
				int &x = puyo.x;
				int &y = puyo.y;

				// 발견한 뿌요를 벡터에 추가한다.
				findList.push_back(puyo);

				for (int dir = 0; dir < 4; dir++)
				{
					int nx = x + dx[dir];
					int ny = y + dy[dir];

					if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6 ||
						field[nx][ny] != field[x][y] || discovered[nx][ny])
						continue;

					q.push(Pos(nx, ny));
					discovered[nx][ny] = true;
				}
			}

			if (findList.size() >= 4)
			{
				while (!findList.empty())
				{
					popList.push_back(findList.back());
					findList.pop_back();
				}
			}
		}
	}

	// 터뜨릴 수 있는 뿌요를 모두 터뜨린다.
	for (int i = 0; i < popList.size(); i++)
	{
		Pos &puyo = popList[i];
		field[puyo.x][puyo.y] = '.';
	}

	return popList.empty() ? false : true;
}

void fallAllPuyos()
{
	for (int y = 0; y < 6; y++)
	{
		char temp[12];
		int idx = 12 - 1;
		memset(temp, '.', sizeof(temp));

		for (int x = 12 - 1; x >= 0; x--)
			if (field[x][y] != '.')
				temp[idx--] = field[x][y];

		for (int x = 12 - 1; x >= 0; x--)
			field[x][y] = temp[x];
	}
}