import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_블록이동하기_60063_박정호_O {
   static int[] dr = { 1, -1, 0, 0 };
   static int[] dc = { 0, 0, 1, -1 };
   static int N;
   static Queue<Dron> q = new LinkedList<>();
   static ArrayList<Check> list = new ArrayList<>();
   static boolean flag = false;
   static int answer;

   static class Check {
      int r1;
      int c1;
      int r2;
      int c2;
      int level;

      public Check(int r1, int c1, int r2, int c2, int level) {
         this.r1 = r1;
         this.c1 = c1;
         this.r2 = r2;
         this.c2 = c2;
         this.level = level;
      }

      @Override
      public String toString() {
         return "Check [r1=" + r1 + ", c1=" + c1 + ", r2=" + r2 + ", c2=" + c2 + ", level=" + level + "]";
      }
   }

   static class Dron {
      int r1;
      int c1;
      int r2;
      int c2;
      int state;

      public Dron(int r1, int c1, int r2, int c2, int state) {
         this.r1 = r1;
         this.c1 = c1;
         this.r2 = r2;
         this.c2 = c2;
         this.state = state;
      }
   }

   public static void main(String[] args) {
      int[][] board = { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 },
            { 0, 0, 0, 0, 0 } };
      N = board.length;
      q.add(new Dron(0, 0, 0, 1, 0));
      list.add(new Check(0, 0, 0, 1, 0));
      Dron dron = null;
      int level = 1;
      here:while (!q.isEmpty()) {
         int qSize = q.size();
         for (int i = 0; i < qSize; ++i) {
            dron = q.poll();
            check(level, dron, board);
            if(flag)
               break here;
         }
         level++;
      }
      System.out.println(answer);
   }

   private static void check(int level, Dron dron, int[][] board) {
      int r1 = dron.r1;
      int r2 = dron.r2;
      int c1 = dron.c1;
      int c2 = dron.c2;
      int state = dron.state;
      // 가로
      if (state == 0) {
         // 상, 왼쪽 기준 반시계, 오른쪽 기준 시계
         if (r1 > 0 && board[r1 - 1][c1] == 0 && board[r2 - 1][c2] == 0) {
            // 상
            go(r1 - 1, c1, r2 - 1, c2, 0, level);
            // 왼쪽 기준 반시계
            go(r1 - 1, c1, r1, c1, 1, level);
            // 오른쪽 기준 시계
            go(r2 - 1, c2, r2, c2, 1, level);
         }
         // 하, 왼쪽 기준 시계, 오른쪽 기준 반시계
         if (r1 < N - 1 && board[r1 + 1][c1] == 0 && board[r2 + 1][c2] == 0) {
            // 하
            go(r1 + 1, c1, r2 + 1, c2, 0, level);
            // 왼쪽 기준 시계
            go(r1, c1, r1 + 1, c1, 1, level);
            // 오른쪽 기준 반시계
            go(r2, c2, r2 + 1, c2, 1, level);
         }
         // 좌
         if (c1 > 0 && board[r1][c1 - 1] == 0) {
            go(r1, c1 - 1, r2, c2 - 1, 0, level);
         }
         // 우
         if (c2 < N - 1 && board[r2][c2 + 1] == 0) {
            go(r1, c1 + 1, r2, c2 + 1, 0, level);
         }
      }
      // 세로
      else {
         // 상
         if (r1 > 0 && board[r1 - 1][c1] == 0) {
            go(r1 - 1, c1, r2 - 1, c2, 1, level);
         }
         // 하
         if (r2 < N - 1 && board[r2 + 1][c2] == 0) {
            go(r1 + 1, c1, r2 + 1, c2, 1, level);
         }
         // 좌, 위쪽 기준 시계, 아래쪽 기준 반시계
         if (c1 > 0 && board[r1][c1 - 1] == 0 && board[r2][c2 - 1] == 0) {
            // 좌
            go(r1, c1 - 1, r2, c2 - 1, 1, level);
            // 위쪽 기준 시계
            go(r1, c1 - 1, r1, c1, 0, level);
            // 아래쪽 기준 반시계
            go(r2, c2 - 1, r2, c2, 0, level);
         }
         // 우, 위쪽 기준 반시계, 아래쪽 기준 시계
         if (c1 < N - 1 && board[r1][c1 + 1] == 0 && board[r2][c2 + 1] == 0) {
            // 우
            go(r1, c1 + 1, r2, c2 + 1, 1, level);
            // 위쪽 기준 반시계
            go(r1, c1, r1, c1 + 1, 0, level);
            // 아래쪽 기준 시계
            go(r2, c2, r2, c2 + 1, 0, level);
         }
      }

   }

   private static void go(int r1, int c1, int r2, int c2, int state, int level) {
      if(flag)
         return;
      if ((r1 == N - 1 && c1 == N - 1) || (r2 == N - 1 && c2 == N - 1)) {
         answer = level;
         flag = true;
         return;
      }
      Check check = null;
      for (int i = 0; i < list.size(); ++i) {
         check = list.get(i);
         if (check.r1 == r1 && check.c1 == c1 && check.r2 == r2 && check.c2 == c2)
            return;
      }
      list.add(new Check(r1, c1, r2, c2, level));
      q.add(new Dron(r1, c1, r2, c2, state));

   }

}