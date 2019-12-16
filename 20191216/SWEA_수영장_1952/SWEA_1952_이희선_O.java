import java.io.BufferedReader;
import java.io.InputStreamReader;
 /*
  * 메모리 	24,408kb
  * 실행시간 	108ms
  * 
  */
public class Se1952_수영장 {
   static int day, month, month3, year;     //이용권 가격 (일, 달, 3달, 년)
   static int min;                          //최소비용
   static int[] plan;                       //이용계획
   static void solve(int mon, int charge) { //해당 달, 누적 요금
      if(mon>12) {
         min = Math.min(min, charge);
         return;
      }
      if(charge >= min) return;
      solve(mon+1, charge + Math.min(plan[mon]*day, month));
      solve(mon+3, charge + month3);
   }
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      for(int t=1; t<=T; t++) {        
         String[] s = br.readLine().split(" ");
         day = Integer.parseInt(s[0]);
         month = Integer.parseInt(s[1]);
         month3 = Integer.parseInt(s[2]);
         year = Integer.parseInt(s[3]);
         plan = new int[13];
         min = 987654321;
         s = br.readLine().split(" ");
         for(int i=1; i<13; i++) {
            plan[i] = Integer.parseInt(s[i-1]);
         }         
         solve(1, 0);
         min = Math.min(min, year);
         System.out.println("#"+t+" "+ min);
      }
   }
 
}