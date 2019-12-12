import java.util.ArrayList;

public class KAKAO_6 {
   
   static int answer = Integer.MAX_VALUE;
   static boolean[] visited; //dfs �湮 üũ��
   static ArrayList<Integer> seq; // seq  dist�� permutaion���� �̾Ƽ� ����.
   static ArrayList<Integer> nWeak; //nWeak ��ȯ�ϴ� weakPoint�� �������� ���� �����ϴ� �迭
   
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
	  // �ʱ�ȭ
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
         for(int i=0; i<weak.length; ++i) { //nWeak �ʱ�ȭ
            nWeak.add(weak[i]);
         }
         
         for(int i=0; i<nWeak.size(); ++i) { //nWeak ��ȸ            
            answer = Math.min(answer, circularSearch()); //��ȸ���� ��Ŭ��ġ���غ�
            int tmp = nWeak.remove(0);
            nWeak.add(tmp+n); //�������� �����ϱ� ������ ���������� ������ +n
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
      int check = 0; //���� üũ��
      int count = 0; // �ε���
      int key = (1<<nWeak.size())-1;//��� ������ �湮�������� key��
      for(int i=0; i<seq.size(); ++i) {
         int tmp = seq.get(i) + nWeak.get(count); //weak + seq�� ���� ���� ���� ���� ã���� vistied üũ
         for(int j=0; j<nWeak.size(); ++j) {
            if((check & (1<<j))>0) continue; //�̹� �湮������ continue
            if(tmp>=nWeak.get(j)) {
               check = check | (1<<j); 
               count++;
            }
         }
         if(check==key) return i+1; //Ű�� ��ġ�ϸ� ����        
      }
      return Integer.MAX_VALUE;      
   }
}