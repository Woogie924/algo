#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

int N;
int S[20][20];

int solve(int toPick, vector<int> &picked);

int main(void)
{
	scanf("%d", &N);

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			scanf("%d", &S[i][j]);

	vector<int> picked;
	printf("%d", solve(N / 2, picked));

	return 0;
}

// 스타트 팀과 링크 팀의 능력치 차이의 최소값을 반환한다.
int solve(int toPick, vector<int> &picked)
{
	// 스타트 팀의 멤버를 모두 결정한 경우
	if (toPick == 0)
	{
		// 참이면 스타트 팀, 거짓이면 링크 팀
		vector<bool> check = vector<bool>(N, false);
		for (int i = 0; i < (N / 2); i++)
			check[picked[i]] = true;

		// 스타트 팀과 링크 팀을 생성한다.
		vector<int> teamStart, teamLink;
		for (int member = 0; member < N; member++)
		{
			if (check[member])
				teamStart.push_back(member);
			else
				teamLink.push_back(member);
		}

		// 스타트 팀과 링크 팀의 능력치를 계산한다.
		int abilityStart = 0;
		int abilityLink = 0;
		for (int i = 0; i < N / 2; i++)
		{
			for (int j = 0; j < N / 2; j++)
			{
				abilityStart += S[teamStart[i]][teamStart[j]];
				abilityLink += S[teamLink[i]][teamLink[j]];
			}
		}

		return abs(abilityStart - abilityLink);
	}

	int minDiff = 0x7FFFFFFF;
	int smallest = picked.empty() ? 0 : picked.back() + 1;
	for (int next = smallest; next < N; next++)
	{
		picked.push_back(next);
		minDiff = min(minDiff, solve(toPick - 1, picked));
		picked.pop_back();
	}

	return minDiff;
}