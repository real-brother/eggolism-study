package BOJ_2606_바이러스;

import java.util.*;

public class Main1 {
	static int count = 0;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		visited = new boolean[N+1];
		graph = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph[a].add(b);
			graph[b].add(a);
		}
		
		System.out.println(bfs(1));

	}
	
	public static int bfs(int start) {
		visited[start] = true;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			for(int i = 0; i < graph[x].size(); i++) {
				int y = graph[x].get(i);
				if(!visited[y]) {
					queue.offer(y);
					visited[y] = true;
					count++;
				}
			}
		}
		return count;
	}

}
