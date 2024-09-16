package BOJ_1005_ACM_Craft;

// 밑에 주석 뺴고는 일반적인 위상 정렬과 같음

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, target;
	static int[] buildTime, count;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			int K = sc.nextInt();
			
			buildTime = new int[N+1];
			graph = new ArrayList[N+1];
			for(int i = 1; i <= N; i++) {
				buildTime[i] = sc.nextInt();
				graph[i] = new ArrayList<>();
			}
			
			count = new int[N+1];
			for(int i = 0; i < K; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				graph[a].add(b);
				count[b]++;
			}
			
			target = sc.nextInt();
			System.out.println(acmCraft());
		}
		
	}
	
	static int acmCraft() {
		Queue<Integer> queue = new LinkedList<>();
		int[] timeBefore = new int[N+1];
		for(int i = 1; i <= N; i++) if(count[i] == 0) queue.offer(i);
		
		while(!queue.isEmpty()) {
			int now = queue.poll();

			if(now == target) return timeBefore[now] + buildTime[now];
			
			for(int i = 0; i < graph[now].size(); i++) {
				int next = graph[now].get(i);
				count[next]--;
				// 다음 노드로 갈 수 있는 현재 노드의 시간 중에 가장 큰 값을 저장
				// 1 -> 4, 2 -> 4, 3 -> 4라면
				// timeBefore[4] = 1, 2, 3이 끝났을 때 중 가장 큰 값
				if(timeBefore[next] < timeBefore[now] + buildTime[now]) timeBefore[next] = timeBefore[now] + buildTime[now];
				if(count[next] == 0) queue.offer(next);
			}
		}
		return -1;
	}
}