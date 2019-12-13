import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        boolean[] discovered = new boolean[(1 << weak.length)];
        int answer = 0;
        Arrays.sort(dist);
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        int level = 0;
        int size = 0;
        int visited;
        int limit;
        int ci; // curindex
        int curV;
        boolean resultflag = false;
        end: while (!q.isEmpty()) {

            level++;
            if (level > dist.length)
                break;
            size = q.size();

            for (int step = 0; step < size; step++) {
                visited = q.poll();
                for (int i = 0; i < weak.length; i++) {
                    if ((visited & 1 << i) == 1 << i)// 방문한경우
                        continue;
                    // 안방문햇으면 시계방향 반시계방향 확인

                    limit = dist[dist.length - level];
                    ci = i;
                    curV = visited + (1 << i);// 내꺼하고
                    while (limit > 0)// 시계방향
                    {
                        if (ci == weak.length - 1) {
                            ci = 0;
                            limit -= Math.abs(weak[0] + n - weak[weak.length - 1]);
                        } else
                            limit -= Math.abs(weak[ci] - weak[(++ci) % weak.length]);// 현재꺼랑 다음꺼의 차이를 뺀다.

                        // limit가 0이상이면 갈수잇는곳 그미만이면 못감
                        if (limit >= 0) {
                            if ((curV & 1 << ci) == 1 << ci)
                                continue;// 방문한곳잇으면 그넘어를 확인
                            curV = curV | 1 << ci;
                        }
                        if (curV == (1 << weak.length) - 1) {
                            resultflag = true;
                            break end;
                        } // 종결조건

                    }
                    if (!discovered[curV]) {
                        discovered[curV] = true;
                        q.add(curV);
                    }

                    limit = dist[dist.length - level];
                    ci = i;
                    curV = visited + (1 << i);// 내꺼하고

                    while (limit > 0)// 반시계방향
                    {
                        if (ci == 0) {
                            ci = weak.length - 1;
                            limit -= Math.abs(weak[0] + n - weak[weak.length - 1]);
                        } else
                            limit -= Math.abs(weak[ci] - weak[(--ci + weak.length) % weak.length]);// 현재꺼랑 이전꺼의 차이를 뺀다.

                        // limit가 0이상이면 갈수잇는곳 그미만이면 못감
                        if (limit >= 0) {
                            if ((curV & 1 << ci) == 1 << ci)
                                continue;// 방문한곳잇으면 그넘어를 확인
                            curV = curV | 1 << ci;
                        }
                        if (curV == (1 << weak.length) - 1) {
                            resultflag = true;
                            break end;
                        } // 종결조건

                    }
                    if (!discovered[curV]) {
                        discovered[curV] = true;
                        q.add(curV);
                    }
                }
            }
        }
        if (resultflag)
            return level;
        else
            return -1;
    }

    public static void main(String[] args) {
        int n = 12;
        int[] weak1 = { 1, 5, 6, 10 };
        int[] weak2 = { 1, 3, 4, 9, 10 };
        int[] weak3 = { 1, 5, 10, 12, 22, 25 };
        int[] weak4 = { 10, 11, 0, 1, 2, 3 };

        int[] dist1 = { 1, 3, 2, 4 };
        int[] dist2 = { 3, 0, 0 };
        int[] dist3 = { 4, 3, 2, 1 };
        int[] dist4 = { 3, 2, 2 };
        Solution s = new Solution();
//      System.out.println(s.solution(n, weak1, dist1));
        System.out.println(s.solution(50, weak3, dist3));
    }
}
