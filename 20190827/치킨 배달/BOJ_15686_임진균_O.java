import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pos
{
	int x;
	int y;
	
	public Pos() {}
	public Pos(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int N, M;
	
	static ArrayList<Pos> restaurants = new ArrayList<Pos>(); // 전체 치킨집 리스트
	static ArrayList<Pos> houses = new ArrayList<Pos>(); // 전체 집 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokenizer.nextToken()); // 도시의 크기
		M = Integer.parseInt(tokenizer.nextToken()); // 영업을 유지할 치킨집의 최대 수
		
		// 도시의 상태
		for(int i = 1 ; i <= N ; i++)
		{
			tokenizer = new StringTokenizer(in.readLine());
			for(int j = 1 ; j <= N ; j++)
			{
				int temp = Integer.parseInt(tokenizer.nextToken());
				
				if(temp == 1)
					houses.add(new Pos(i, j));
				else if(temp == 2)
					restaurants.add(new Pos(i, j));
			}
		}
		
		out.write(solve() + "");
		out.flush();
	}

	static int solve()
	{
		int minDistSum = Integer.MAX_VALUE;
		
		// 치킨집을 최대 M개까지 골라본다.
		for(int toPick = 1 ; toPick <= M ; toPick++)
			minDistSum = Math.min(minDistSum, pickRestaurant(toPick, new ArrayList<Integer>()));
		
		return minDistSum;
	}
	
	// 치킨집을 toPick개 골랐을 때, 도시의 치킨 거리의 최솟값을 반환한다.
	static int pickRestaurant(int toPick, ArrayList<Integer> picked)
	{
		// 치킨집을 모두 선택했다면 도시의 치킨 거리를 실제로 계산해본다.
		if(toPick == 0)
			return getChickenDistOfCity(picked);
		
		int minDistSum = Integer.MAX_VALUE; // 도시의 치킨 거리의 최솟값
		int smallest = picked.isEmpty() ? 0 : picked.get(picked.size() - 1) + 1;
		for(int next = smallest ; next < restaurants.size(); next++)
		{
			picked.add(next);
			minDistSum = Math.min(minDistSum, pickRestaurant(toPick - 1, picked));
			picked.remove(picked.size() - 1);
		}
		
		return minDistSum;
	}
	
	// 선택된 치킨집을 기준으로 도시의 치킨 거리를 계산하여 반환한다.
	static int getChickenDistOfCity(ArrayList<Integer> picked)
	{
		ArrayList<Pos> notClosed = new ArrayList<Pos>(); // 폐업시키지 않을 치킨집 리스트
		for(int i = 0 ; i < picked.size() ; i++)
			notClosed.add(restaurants.get(picked.get(i)));

		int distSum = 0;
		for(int i = 0 ; i < houses.size() ; i++)
		{
			int dist = Integer.MAX_VALUE; // 치킨 거리
			
			for(int j = 0 ; j < notClosed.size(); j++)
				dist = Math.min(dist, getDist(houses.get(i), notClosed.get(j)));
			
			distSum += dist;
		}
		
		return distSum;
	}
	
	// p1 지점과 p2 지점의 거리를 반환한다.
	static int getDist(Pos p1, Pos p2)
	{
		return (Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y));
	}
}