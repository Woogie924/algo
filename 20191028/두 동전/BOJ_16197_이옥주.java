import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16197_이옥주 {
   static int row, col;
   static int result = -1;
   static int min = Integer.MAX_VALUE;
   static boolean[][] visited1;
   static boolean[][] visited2;
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String[] str = br.readLine().split(" ");
      row = Integer.parseInt(str[0]);
      col = Integer.parseInt(str[1]);
      String[][] arr = new String[row][col];
      visited1 = new boolean[row][col];
      visited2 = new boolean[row][col];
      int count = 0;
      boolean positionFlag = false;
      int oneX = 0, oneY=0, twoX=0, twoY=0;
      
      for(int i=0; i<row; i++) {
         arr[i] = br.readLine().split("");
         
         for(int j=0; j<col; j++) {
            if(arr[i][j].equals("o") && positionFlag==false) {
               oneX = i;
               oneY = j;
               visited1[i][j] = true;
               positionFlag = true;
            }
            else if(arr[i][j].equals("o")) {
            	visited2[i][j] = true;
               twoX = i;
               twoY = j;
            }
         }
      }  // 입력 받기
      
      dfs(arr, oneX, oneY, twoX, twoY, count, visited1, visited2);
      if(count>10) result = -1;
      System.out.println(result);
   }

   static int[] x= {0,0,-1,1};
   static int[] y= {-1,1,0,0};
   static int temp1X, temp1Y, temp2X, temp2Y;
   private static void dfs(String[][] arr, int oneX, int oneY, int twoX, int twoY, int count, boolean[][] visited1, boolean[][] visited2) {
	  if(count+1 >= min || count+1 >10) return;
	   
      for(int i=0; i<4; i++) {
         temp1X = oneX + x[i]; 
         temp1Y = oneY + y[i]; 
         temp2X = twoX + x[i]; 
         temp2Y = twoY + y[i];
         
         // 둘 다 떨어질 때
         if((temp1X<0 || temp1X>=row || temp1Y<0 || temp1Y>=col) && (temp2X<0 || temp2X>=row || temp2Y<0 || temp2Y>=col)) 
            continue;
         
         // 둘 중 하나만 떨어질 때
         else if((temp1X<0 || temp1X>=row || temp1Y<0 || temp1Y>=col) || (temp2X<0 || temp2X>=row || temp2Y<0 || temp2Y>=col)) {
            if(min > count+1) {
               min = count+1;
               result = count+1;
               return;
            }
         }
         
         else {   //안떨어질때
        	// 둘 다 벽이면
            if(arr[temp1X][temp1Y].equals("#") && arr[temp2X][temp2Y].equals("#")) continue;  
            
            //둘 다 들렸던 곳이면
            else if(visited1[temp1X][temp1Y] && visited2[temp2X][temp2Y]) continue;  
            
            //1번이 벽이거나 들렸던 곳이면
            else if(arr[temp1X][temp1Y].equals("#") || visited1[temp1X][temp1Y]) {  
            	visited2[temp2X][temp2Y] = true;
            	dfs(arr, oneX, oneY, temp2X, temp2Y, count+1, visited1, visited2);
            	visited2[twoX+x[i]][twoY+y[i]] = false;
            }
            
            //2번이 벽이거나 들렸던 곳이면
            else if(arr[temp2X][temp2Y].equals("#") || visited2[temp2X][temp2Y]) { 
            	visited1[temp1X][temp1Y] = true;
            	dfs(arr, temp1X, temp1Y, twoX, twoY, count+1, visited1, visited2);
            	visited1[oneX+x[i]][oneY+y[i]] = false;
            }
            
            else {  //둘 다 이동할 때
            	visited1[temp1X][temp1Y] = true;
            	visited2[temp2X][temp2Y] = true;
               dfs(arr, temp1X, temp1Y, temp2X, temp2Y, count+1, visited1, visited2);
	           	visited1[oneX+x[i]][oneY+y[i]] = false;
	        	visited2[twoX+x[i]][twoY+y[i]] = false;
            }
         }
      }
   }
}