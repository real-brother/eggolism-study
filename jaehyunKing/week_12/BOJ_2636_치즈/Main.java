package BOJ_2636_치즈;

/*
 *  bfs 한 번에 해결하고 싶어서 priorityQueue를 사용했다.
 *  단순하게 할 꺼면 그냥 전에 빙산처럼 계속 확인하는 방식이 
 *  더 단순하게 풀 수 있을 거 같다.(한 시간마다 완탐)
 *  근데 비슷하게 dfs + bfs로도 해봤는데 
 *  시간은 이게 20ms 더 걸린다(코드 보기에는 이게 깔끔한듯)
 */

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int N, M, time, cheeseCount;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] graph;
	static boolean[][] visited;
	static PriorityQueue<Point> pq;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new int[N][M];
		for(int r = 0; r < N; r++) for(int c = 0; c < M; c++) graph[r][c] = sc.nextInt();
		
		visited = new boolean[N][M];
		pq = new PriorityQueue<>();
		
		bfs(0, 0);
		
		System.out.println(time);
		System.out.println(cheeseCount);
	}
	
	// priorityQueue에 우선순위를 time으로 설정(밑에 클래스에서)
	// 시간 작은 것들이 먼저 나오게 된다
	static void bfs(int startY, int startX) {
		pq.offer(new Point(startY, startX, 0));
		while(!pq.isEmpty()) {
			Point now = pq.poll();
			
			// 1일 때 최대 시간보다 현재 시간이 크면 갱신
			// 그게 아니라면 시간이 같으므로(priorityQueue 때문에) cheeseCount 증가 
			if(graph[now.y][now.x] == 1) {
				if(time < now.time) {
					time = now.time;
					cheeseCount = 1;
				}
				else cheeseCount++;
			}
			
			for(int i = 0; i < 4; i++) {
				int moveY = now.y + dr[i];
				int moveX = now.x + dc[i];
				
				if(0 > moveY || moveY >= N || moveX < 0 || moveX >= M) continue;
				
				if(!visited[moveY][moveX]) {
					visited[moveY][moveX] = true;
					
					// 1이면 시간 증가, 0이면 시간 그대로
					if(graph[moveY][moveX] == 1) pq.offer(new Point(moveY, moveX, now.time + 1));
					else pq.offer(new Point(moveY, moveX, now.time));
				}
			}
		}
	}
	
	static class Point implements Comparable<Point>{
		int y;
		int x;
		int time;
		
		public Point(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}

		// 시간 순으로 정렬
		@Override
		public int compareTo(Point o) {
			return this.time - o.time;
		}
		
	}
}