package BOJ_14502_연구소;

/*
 *  입력받을 때 미리 0의 개수(countZero)를 세준다
 *  조합으로 벽을 세울 자리 3개를 고른다
 *  bfs 탐색
 *  바이러스 감염될 때 마다 countZero에서 하나씩 빼준다
 *  countZero가 제일 클 떄가 답
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, countZero, max;
	static int[][] graph;
	static boolean[][] visited;
	static List<int[]> virus;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new int[N][M];
		virus = new ArrayList<>();
		
		// 벽을 무조건 3개 세우기 때문에 미리 빼줌
		countZero = -3;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				graph[r][c] = sc.nextInt();
				if(graph[r][c] == 2) virus.add(new int[] {r, c});
				if(graph[r][c] == 0) countZero++;
			}
		}
		
		max = 0;
		combination(0, 0, 0);
		System.out.println(max);
	}
	
	static void combination(int nowY, int nowX, int count) {
		
		if(count == 3) {
			bfs();
			return;
		}
		
		if(nowX == M) {
			nowX = 0;
			nowY++;
			if(nowY == N) return;
		}
		
		if(graph[nowY][nowX] == 0) {
			graph[nowY][nowX] = 1;
			combination(nowY, nowX+1, count+1);
			graph[nowY][nowX] = 0;
		}
		
		combination(nowY, nowX+1, count);
	}
	
	static void bfs() {
		int temp = countZero;
		visited = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<>();
		for(int[] x : virus) queue.offer(x);
		
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			for(int i = 0; i < 4; i++) {
				int moveY = num[0] + dr[i];
				int moveX = num[1] + dc[i];
				if(0 <= moveY && moveY < N && 0 <= moveX && moveX < M) {
					if(!visited[moveY][moveX] && graph[moveY][moveX] == 0) { 
						visited[moveY][moveX] = true;
						temp--;
						queue.offer(new int[]{moveY, moveX});
					}
				}
			}
		}
		if(max < temp) max = temp;
	}
}