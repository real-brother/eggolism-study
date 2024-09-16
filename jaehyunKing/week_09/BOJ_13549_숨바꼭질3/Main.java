package BOJ_13549_숨바꼭질3;

/*
 *  배열을 bfs로 이동
 *  더 짧은 시간으로 현재 위치에 올 수 있으면 갱신
 *  목적지에 방문한 시간보다 현재 시간이 크면 백트래킹 
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, K;
	static int[] dc = {-1, 1};
	static int[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		visited = new int[100001];
		for(int i = 0; i <= 100000; i++) visited[i] = Integer.MAX_VALUE;
		
		// 출발지, 목적지
		int N = sc.nextInt();
		K = sc.nextInt();
		
		bfs(N);
		
		System.out.println(visited[K]);
	}
	
	static void bfs(int start) {
		Queue<int[]> queue = new LinkedList<>();
		visited[start] = 0;
		queue.offer(new int[] {start, 0});
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int time = now[1];
			
			// 현재 시간이 기존에 목적지에 도달한 시간보다 크면 백트래킹
			if(time >= visited[K]) continue;
			
			if(now[0] == K && time < visited[K]) visited[K] = time;
			
			// 현재 위치에 도달하는 기존 시간보다 현재 시간이 더 작으면 갱신
			if(now[0] * 2 <= 100000 && visited[now[0] * 2] > now[1]) {
				visited[now[0] * 2] = time;
				queue.offer(new int[] {now[0] * 2, time});
			}
			for(int i = 0; i < 2; i++) {
				int next = now[0] + dc[i];
				if(0 <= next && next <= 100000 && visited[next] > time+1) {
					visited[next] = time + 1;
					queue.offer(new int[] {next, time + 1});
				}
			}
			
		}
	}

}