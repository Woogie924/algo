import java.util.ArrayList;

public class KAKAO_6 {
   
   static int answer = Integer.MAX_VALUE;
   static boolean[] visited; //dfs 방문 체크용
   static ArrayList<Integer> seq; // seq  dist를 permutaion으로 뽑아서 저장.
   static ArrayList<Integer> nWeak; //nWeak 순환하는 weakPoint를 직선으로 새로 저장하는 배열
   
   public static void main(String[] args) {
	  int n = 50;
	  int[] weak = {1,5,10,12,22,25};
	  int[] dist = {4,3,2,1};
      int a = solution(n, weak, dist);
      System.out.println(a);
   }
   
   public static int solution(int n, int[] weak, int[] dist) {
      visited = new boolean[dist.length];
      seq = new ArrayList<>();
      nWeak = new ArrayList<>();
	  int ans = 0;
	  // 초기화
      dfs(0,n,weak,dist);
      
      ans = answer;
      if(ans == Integer.MAX_VALUE) {	
    	  ans = -1;
      }
      return ans;      
   }
   
   private static void dfs(int depth, int n, int[] weak, int[] dist) {
      if(depth==dist.length) {
         nWeak.clear();
         for(int i=0; i<weak.length; ++i) { //nWeak 초기화
            nWeak.add(weak[i]);
         }
         
         for(int i=0; i<nWeak.size(); ++i) { //nWeak 순회            
            answer = Math.min(answer, circularSearch()); //순회마다 서클서치를해봄
            int tmp = nWeak.remove(0);
            nWeak.add(tmp+n); //직선으로 생각하기 때문에 시작점보다 작으면 +n
         }         
      }
            
      for(int i=0; i<dist.length; ++i) {
         if(visited[i]) continue;
         visited[i] = true;
         seq.add(dist[i]);
         dfs(depth+1, n, weak, dist);
         seq.remove(seq.size()-1);
         visited[i] = false;         
      }
   }

   private static int circularSearch() {
      int check = 0; //현재 체크값
      int count = 0; // 인덱스
      int key = (1<<nWeak.size())-1;//모든 정점을 방문했을때의 key값
      for(int i=0; i<seq.size(); ++i) {
         int tmp = seq.get(i) + nWeak.get(count); //weak + seq의 범위 보다 작은 수를 찾으면 vistied 체크
         for(int j=0; j<nWeak.size(); ++j) {
            if((check & (1<<j))>0) continue; //이미 방문했으면 continue
            if(tmp>=nWeak.get(j)) {
               check = check | (1<<j); 
               count++;
            }
         }
         if(check==key) return i+1; //키와 일치하면 리턴        
      }
      return Integer.MAX_VALUE;      
   }
}