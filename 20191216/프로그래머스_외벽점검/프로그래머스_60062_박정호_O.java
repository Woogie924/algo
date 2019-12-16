import java.util.ArrayList;
import java.util.Arrays;

public class 프로그래머스_외벽점검_60062 {

	static Link[] link;
	static boolean[] node;
	static boolean[] visit;
	static int weak_length;
	static int dist_length;
	static int cnt = Integer.MAX_VALUE;

	static class Link {
		int start;
		int end;
		int length;

		public Link(int start, int end, int length) {
			this.start = start;
			this.end = end;
			this.length = length;
		}
	}

	public static int solution(int n, int[] weak, int[] dist) {
		Arrays.sort(weak);
		Arrays.sort(dist);

		weak_length = weak.length;
		dist_length = dist.length;

		link = new Link[weak_length];
		node = new boolean[weak[weak_length - 1] + 1];
		visit = new boolean[dist_length];

		for (int i = 0; i < weak_length; ++i) {
			if (i < weak_length - 1) {
				link[i] = new Link(weak[i], weak[i + 1], weak[i + 1] - weak[i]);
			} else {
				link[i] = new Link(weak[i], weak[0], n - weak[i] + weak[0]);
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		go(list, dist);
		if (cnt != Integer.MAX_VALUE)
			return cnt;
		return -1;
	}

	private static void go(ArrayList<Integer> list, int[] dist) {
		if (list.size() == dist_length) {
			System.out.println(list);
			check(list);
			return;
		}

		for (int i = 0; i < dist_length; ++i) {
			if (visit[i])
				continue;
			visit[i] = true;
			list.add(dist[i]);
			go(list, dist);
			visit[i] = false;
			list.remove(list.size() - 1);
		}

	}

	private static void check(ArrayList<Integer> list) {
		for (int i = 0; i < weak_length; ++i) {
			int list_idx = 0; // list의 index
			int link_idx = i; // link의 index
			int length = list.get(list_idx);
			while (true) {
				link_idx %= weak_length;
				if(node[link[link_idx].start]) {
					link_idx++;
					continue;
				}
				while(link[link_idx].length < length) {
					node[link[link_idx].start] = true;
					length -= link[link_idx].length;
					link_idx++;
					link_idx %= weak_length;
				}
				if (link[link_idx].length == length) {
					node[link[link_idx].start] = true;
					node[link[link_idx].end] = true;
					list_idx++;
					link_idx+=2;
				}
				else if (link[link_idx].length > length) {
					node[link[link_idx].start] = true;
					link_idx++;
					list_idx++;
				}
				if(isfull()) {
					// 현재까지 사용된 list_idx만큼 셈
					cnt = Math.min(cnt, list_idx);
					break;
				}
				if(list_idx>=dist_length) {
					break;
				}
				length = list.get(list_idx);
			}
			for (int j = 0; j < node.length; ++j)
				node[j] = false;
		}
	}

	private static boolean isfull() {
		for(int i=0; i<weak_length; ++i) {
			if(!node[link[i].start])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int n = 12;
//		int[] weak = { 1, 5, 6, 10 };
//		int[] dist = { 1, 2, 3, 4 };
		int[] weak = { 1, 3, 4, 9, 10 };
		int[] dist = { 3, 5, 7 };
		System.out.println(solution(n, weak, dist));
	}

}
