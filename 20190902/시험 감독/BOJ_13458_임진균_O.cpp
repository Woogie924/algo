#include <cstdio>

typedef long long int64;

int N; // 시험장의 개수
int A[1000000]; // 각 시험장에 있는 응시자의 수
int B, C;

int64 solve();

int main(void)
{
	scanf("%d", &N);

	for (int i = 0; i < N; i++)
		scanf("%d", &A[i]);

	scanf("%d %d", &B, &C);

	printf("%lld", solve());

	return 0;
}

int64 solve()
{
	int64 count = 0;

	for (int i = 0; i < N; i++)
	{
		// 응시자는 최소 1명 이상이므로 총 감독관은 항상 필요하다.
		count++;

		// 부 감독관이 필요한 경우
		if (A[i] > B)
		{
			count += ((A[i] - B) / C);
			count += ((A[i] - B) % C == 0 ? 0 : 1);
		}
	}

	return count;
}