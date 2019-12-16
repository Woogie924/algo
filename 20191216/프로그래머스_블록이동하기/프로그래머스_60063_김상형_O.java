import java.util.LinkedList;
import java.util.Queue;

public class KAKAO2020_7 {
   static int[] dy = {0,1,0,-1};
   static int[] dx = {1,0,-1,0};
   static int N;
   static  boolean[][][] visited;
   
   public static void main(String[] args) {
      
      int[][] board = {{0,0,0,1,1},
                   {0,0,0,1,0},
                   {0,1,0,1,1},
                   {1,1,0,0,1},
                   {0,0,0,0,0}
      };
      
      int a = solution(board);
      System.out.println(a);
   }
   
   public static int solution(int[][] board) {
      N = board.length;
      System.out.println(N);
      Drone d = new Drone(new Pos(0,0), new Pos(0,1), 0);
      visited = new boolean[board.length][board.length][2]; //0 가로상태 1 세로상태
      Queue<Drone> q = new LinkedList<>();
      int answer = 0;
      
      q.offer(d);
      visited[d.main.y][d.main.x][0] = true;
      visited[d.sub.y][d.sub.x][0] = true;
      
      
      while(!q.isEmpty()) {
         
         int qs = q.size();
         
         for(int i=0; i<qs; ++i) {
            Drone curr = q.poll();
           
            //종료조건
            if((curr.main.y==N-1 && curr.main.x==N-1) || (curr.sub.y==N-1 && curr.sub.x==N-1)) {
               return answer;
            }
            
            
            //4방향에대한 탐색
            for(int dir = 0; dir < 4; ++dir) {
               
               int my = curr.main.y + dy[dir];
               int mx = curr.main.x + dx[dir];
               int sy = curr.sub.y + dy[dir];
               int sx = curr.sub.x + dx[dir];       
               if(my<0 || mx<0 || my>=N || mx>=N || sy<0 || sx<0 || sy>=N || sx>=N) continue; //out of bound
               if(visited[my][mx][curr.state] && visited[sy][sx][curr.state]) continue;
               if(board[my][mx]==1 || board[sy][sx]==1) continue;               
               
               visited[my][mx][curr.state]=true;
               visited[sy][sx][curr.state]=true;
               
               q.offer(new Drone(new Pos(my, mx), new Pos(sy, sx), curr.state));
            }
            
            if(curr.state==0) { //가로 상태
               
               //main 축 기준 반시계
               if(check(curr.main.y-1, curr.main.x, curr.main.y, curr.main.x, 1, board) && rtcheck(curr.main.y, curr.main.x, board, 0)) {
                  visited[curr.main.y-1][curr.main.x][1]=true;
                  visited[curr.main.y][curr.main.x][1]=true;
                  q.offer(new Drone(new Pos(curr.main.y-1, curr.main.x), new Pos(curr.main.y, curr.main.x), 1));
               }
               //main 축 기준 시계방향
               if(check(curr.main.y+1, curr.main.x, curr.main.y, curr.main.x, 1, board) && rtcheck(curr.main.y, curr.main.x, board,3)) {
                  visited[curr.main.y+1][curr.main.x][1] = true;
                  visited[curr.main.y][curr.main.x][1] = true;
                  q.offer(new Drone(new Pos(curr.main.y, curr.main.x), new Pos(curr.main.y+1, curr.main.x), 1));
               }
               //sub 축 기준  시계
               if(check(curr.sub.y-1, curr.sub.x, curr.sub.y, curr.sub.x, 1, board) && rtcheck(curr.sub.y, curr.sub.x, board, 1)) {
                  visited[curr.sub.y-1][curr.sub.x][1] = true;
                  visited[curr.sub.y][curr.sub.x][1] = true;
                  q.offer(new Drone(new Pos(curr.sub.y-1, curr.sub.x), new Pos(curr.sub.y, curr.sub.x),1));
               }
               //sub 축 기준 반시계
               if(check(curr.sub.y+1, curr.sub.x, curr.sub.y, curr.sub.x, 1, board) && rtcheck(curr.sub.y, curr.sub.x, board, 2)) {
                  visited[curr.sub.y+1][curr.sub.x][1] = true;
                  visited[curr.sub.y][curr.sub.x][1] = true;
                  q.offer(new Drone(new Pos(curr.sub.y, curr.sub.x), new Pos(curr.sub.y+1, curr.sub.x),1));
               }
            }
            else if(curr.state==1) {//세로
               //main 축 기준 시계
               if(check(curr.main.y, curr.main.x-1, curr.main.y, curr.main.x, 0, board) && rtcheck(curr.main.y, curr.main.x, board, 2)) {
                  visited[curr.main.y][curr.main.x-1][0]  = true;
                  visited[curr.main.y][curr.main.x][0] = true;
                  q.offer(new Drone(new Pos(curr.main.y, curr.main.x-1), new Pos(curr.main.y, curr.main.x),0));
               }
               //main 축 기준 반시계
               if(check(curr.main.y, curr.main.x+1, curr.main.y, curr.main.x, 0, board) && rtcheck(curr.main.y, curr.main.x, board, 3)) {
                  visited[curr.main.y][curr.main.x+1][0]  = true;
                  visited[curr.main.y][curr.main.x][0] = true;
                  q.offer(new Drone(new Pos(curr.main.y, curr.main.x), new Pos(curr.main.y, curr.main.x+1),0));
               }
               //sub 축 기준 반시계
               if(check(curr.sub.y, curr.sub.x-1, curr.sub.y, curr.sub.x, 0, board) && rtcheck(curr.sub.y, curr.sub.x, board, 1)) {
                   visited[curr.sub.y][curr.sub.x-1][0] = true;
                   visited[curr.sub.y][curr.sub.x][0] = true;
                   q.offer(new Drone(new Pos(curr.sub.y, curr.sub.x-1), new Pos(curr.sub.y, curr.sub.x),0));
                }
                //sub 축 기준 시계
                if(check(curr.sub.y, curr.sub.x+1, curr.sub.y, curr.sub.x, 0, board) && rtcheck(curr.sub.y, curr.sub.x, board, 0)) {
                   visited[curr.sub.y][curr.sub.x+1][0] = true;
                   visited[curr.sub.y][curr.sub.x][0] = true;
                   q.offer(new Drone(new Pos(curr.sub.y, curr.sub.x), new Pos(curr.sub.y, curr.sub.x+1),0));
                }
            }
         }
         
         answer ++;
      }      
      
      return answer;
   }
   //회전 도착 방향에 대한 check
   private static boolean check(int y, int x, int y1, int x1,  int state, int[][] board) {//y x 회전하는 거 y1 x1은 축
      if(y<0 || x <0 || x>=N ||y>=N) return false;
      if(visited[y][x][state] && visited[y1][x1][state]) return false;
      if(board[y][x]==1 || board[y1][x1]==1) return false;
   return true;   
   
   }
   //도는 방향에 벽이 있는지 없는지 check
   private static boolean rtcheck(int y, int x, int[][] board, int rnum) {//축을 집어넣어야됨
     
      if(rnum==0) { // 2시방향
         if(y-1<0 || x+1<0 || x+1>=N || y-1>=N) return false;
         if(board[y-1][x+1]==1) return false;
      }else if(rnum==1) {//11시방향
         if(y-1<0 || x-1<0 || x-1>=N || y-1>=N) return false;
         if(board[y-1][x-1]==1) return false;
      }else if(rnum==2) {//7시방향
         if(y+1<0 || x-1<0 || x-1>=N || y+1>=N) return false;
         if(board[y+1][x-1]==1) return false;
      }else {//4시방향
         if(y+1<0 || x+1<0 || x+1>=N || y+1>=N) return false;
         if(board[y+1][x+1]==1) return false;
      }
      return true;
   }
static class Pos{
      int y;
      int x;
      
      public Pos(int y, int x) {
         super();
         this.y = y;
         this.x = x;
      }

   @Override
   public String toString() {
      return "Pos [y=" + y + ", x=" + x + "]";
   }
      
   }
   
   static class Drone{
      Pos main; //중심축
      Pos sub;
      int state; //false가로 true세로
      
      public Drone(Pos main, Pos sub, int state) {
         super();
         this.main = main;
         this.sub = sub;
         this.state = state;
      }

   @Override
   public String toString() {
      return "Drone [main=" + main + ", sub=" + sub + ", state=" + state + "]";
   }
      
      
   }

}