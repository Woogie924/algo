#include <cstdio>
#include <cstring>
#include <vector>
#include <algorithm>

using namespace std;

int D, W, K;
int flim[13][20];

int solve();
bool doChemicalProcess(int toPick, vector<int> &picked);
void change(int d, int to);
bool check();

int main(void)
{	
	int T;
	scanf("%d", &T);

	for (int tc = 0; tc < T; tc++)
	{
		scanf("%d %d %d", &D, &W, &K);

		for (int i = 0; i < D; i++)
			for (int j = 0; j < W; j++)
				scanf("%d", &flim[i][j]);

		printf("#%d %d\n", tc + 1, solve());
	}

	return 0;
}

// 약품을 최대 K개까지 투입해 본다.
int solve()
{
	int minCount = -1;
	for (int toPick = 0; toPick <= K; toPick++)
	{
		vector<int> picked;
		if (doChemicalProcess(toPick, picked))
		{
			minCount = toPick;
			break;
		}
	}

	return minCount;
}

// 약품 투입 횟수가 toPick일 때 보호 필름이 성능 검사를 통과하는지 여부를 반환한다.
bool doChemicalProcess(int toPick, vector<int> &picked)
{
	// 기저 사례 : 각각의 막에 약품을 모두 투입했다면 성능 검사를 실시한다.
	if (toPick == 0)
		return check();

	int smallest = picked.empty() ? 0 : picked.back() + 1;
	for (int next = smallest; next < D; next++)
	{
		int temp[20] = { 0 };
		memcpy(temp, flim[next], sizeof(int) * W);

		// 약품의 특성 t는 0(A) 또는 1(B)이다.
		for (int t = 0; t < 2; t++)
		{
			picked.push_back(next);
			change(next, t); // d번째 막에 약품을 투입한다. 
			bool ret = doChemicalProcess(toPick - 1, picked);
			memcpy(flim[next], temp, sizeof(int) * W); // 원래 상태로 복구시킨다.
			picked.pop_back();

			if (ret)
				return true;
		}
	}

	return false;
}

// d번째 막의 특성을 to로 변경한다.
void change(int d, int to)
{
	for (int w = 0; w < W; w++)
		flim[d][w] = to;
}

// 현재 필름에 대해서 성능 검사를 실시하고 통과 여부를 반환한다.
bool check()
{
	for (int w = 0; w < W; w++)
	{
		int t = flim[0][w];	// 현재 선택된 특성
		int count = 0;		// 현재 선택된 특성의 연속된 개수
		int maxCount = -1;	// 동일한 특성의 셀들이 연속적으로 최대 몇개 있는지?

		for (int d = 0; d < D; d++)
		{
			if (t != flim[d][w])
			{
				count = 1;
				t = (t == 0 ? 1 : 0);
			}
			else
			{
				count++;
				maxCount = max(maxCount, count);
			}
		}

		// 성능 검사에 실패한 경우
		if (maxCount < K)
			return false;
	}

	return true;
}