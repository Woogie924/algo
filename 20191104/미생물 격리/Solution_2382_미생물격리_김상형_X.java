import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
 
public class Solution {
    static int N, M, K;
 
    static int[][] board;
    static ArrayList<Micro> list = new ArrayList<>();
 
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; ++t) {
 
            String[] s = br.readLine().split("\\s");
 
            N = Integer.parseInt(s[0]);
            M = Integer.parseInt(s[1]);
            K = Integer.parseInt(s[2]);
 
            list.clear();
 
            board = new int[N][N];
             
            for (int i = 0; i < K; ++i) {
 
                s = br.readLine().split("\\s");
 
                int y = Integer.parseInt(s[0]);
                int x = Integer.parseInt(s[1]);
                int mCnt = Integer.parseInt(s[2]);
                int dir = Integer.parseInt(s[3]) - 1;
 
                list.add(new Micro(y, x, mCnt, dir));
                board[y][x] = i+1;
            }
 
 
            int time = 0;
            while (true) {
                if(time==M) break;
                move();
                time++;
            }
            int answer = 0;
            for(int i=0; i<list.size(); ++i) {
                answer += list.get(i).mCnt;
            }
            System.out.println("#"+(t+1)+" "+answer);
        }
 
    }
 
    private static void move() {
        board = new int[N][N];
        for(int i=0; i<list.size(); ++i) {
            Micro curr = list.get(i);
             
            if(curr.mCnt==0)continue;
             
            curr.y = curr.y+dy[curr.dir];
            curr.x = curr.x+dx[curr.dir];
             
        }
        for(int i=0; i<list.size(); ++i) {
            Micro curr = list.get(i);
            if(curr.mCnt==0) continue;
             
            if(check(curr)) {               
                 
                if(curr.dir<2)
                    curr.dir = (curr.dir+1)%2;
                else
                    curr.dir = (curr.dir+1)%2+2;
                curr.mCnt /= 2;
                board[curr.y][curr.x] = i+1;
                 
            }else if(board[curr.y][curr.x]>0) {
                 
                Micro placed = list.get(board[curr.y][curr.x]-1);
                if(placed.mCnt>curr.mCnt) {
                    placed.mCnt+=curr.mCnt;
                    curr.mCnt=0;
                }
                else {
                    curr.mCnt+=placed.mCnt;
                    placed.mCnt=0;
                    board[curr.y][curr.x] = i+1;
                }
            }
            else {
                board[curr.y][curr.x] = i+1;
            }
        }
    }
 
    static void print() {
        System.out.println("###############################");
        for (int[] m : board) {
            System.out.println(Arrays.toString(m));
        }
    }
    static boolean check(Micro curr) {
        if (curr.y == 0 || curr.y == N - 1 || curr.x == 0 || curr.x == N - 1) {
            return true;
        }
        return false;
    }
 
    static class Micro {
 
        int y;
        int x;
        int mCnt;
        int dir;
 
        public Micro(int y, int x, int mCnt, int dir) {
            super();
            this.y = y;
            this.x = x;
            this.mCnt = mCnt;
            this.dir = dir;
        }
 
    }
}