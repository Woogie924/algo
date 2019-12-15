package swea2117_홈방범서비스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class swea2117_홈방범서비스 {
	static int N;
	static int M;
	static int [][] MAP;
	static ArrayList<position> list=new ArrayList<>();
	static int res=0;
	static int maxCount=0;
	static int K;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Test_Case= Integer.parseInt(br.readLine());
		String [] input;
		for(int tc=1; tc<=Test_Case; tc++) {
			res=0;
			maxCount = Integer.MIN_VALUE;
			list.clear();
			
			input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			MAP = new int[N][N];
			
			for(int i=0;i<N;i++) {
				input= br.readLine().split(" ");
				for(int j=0; j<N;j++) {
					MAP[i][j] = Integer.parseInt(input[j]);
					if(MAP[i][j] ==1) {
						//집
						list.add(new position(i,j));
					}
				}
			}
			K=0;
			int count =0;
			int temp=0;
			int cost = 0;
			for(int k=1; ; k++) {
				cost = k*k + (k-1)*(k-1);
				temp = list.size() * M - cost;
				if(temp<0) {
					break;
				}else {
					K=k;
				}
			}
			for(int k=1; k<=K ; k++) {
				cost = k*k + (k-1)*(k-1);

				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						count=0;
						for(int idx=0; idx<list.size(); idx++) {
							int dist = Math.abs(i - list.get(idx).row) + Math.abs(j - list.get(idx).column);
							if(k> dist) {
								count++;
							}
						}
						temp = M * count - cost;

						//이익(temp)가 손해가 아니고, 
						//서비스 제공 갯수(count)가 최대일때
						if(temp>=0 && count >=maxCount) {
							maxCount = count;
							res= temp;
						}
					}
				}
			}
			
			System.out.println("#"+tc+" "+maxCount);
			/*
			 * 서비스 영역의 크기 K
			 * 운영 비용 = K * K + (K - 1) * (K - 1)
			 * 
			 * 
			 * 손해를 보지 않으면서 홈방범 서비스를
			 * 가장 많은 집들에 제공하는 서비스 영역
			 * 이익 = (집 갯수 * M) - 운영 비용
			 */
		}
	}
	
	
	static class position{
		int row;
		int column;
		position(int row, int column){
			this.row = row;
			this.column = column;
		}
	}
}
