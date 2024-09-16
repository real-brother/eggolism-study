package BOJ_16236_아기상어;

/*
 *  아기상어의 위치로부터 bfs를 통해 먹이를 탐색
 *  조건에 맞는 먹이를 먹을 때의 시간을 time에 더 함
 *  먹이를 못 먹을 때까지 위 과정을 반복
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, startY, startX, sharkLevel, time, eatCount;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] graph;
	static boolean[][] visited;
	static boolean callMother;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		graph = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				graph[r][c] = sc.nextInt();
				if(graph[r][c] == 9) {
					startY = r;
					startX = c;
					graph[r][c] = 0;
				}
			}
		}
		
		callMother = false;
		sharkLevel = 2;
		time = 0;
		eatCount = 0;
		
		// 먹이를 먹을 수 없으면 탈출
		while(true) {
			bfs(startY, startX);
			if(callMother) break;
		}
		
		System.out.println(time);
	}
	
	static void bfs(int nowY, int nowX) {
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[N][N];
		visited[nowY][nowX] = true;
		queue.offer(new int[] {nowY, nowX, 1});
		
		int fishFind = Integer.MAX_VALUE;
		
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			
			// num[2] -> 이동 횟수
			if(fishFind < num[2]) continue;
			
			for(int i = 0; i < 4; i++) {
				int moveY = num[0] + dr[i];
				int moveX = num[1] + dc[i];
				
				if(moveY < 0 || N <= moveY || 0 > moveX || N <= moveX) continue;
					
				if(!visited[moveY][moveX] && graph[moveY][moveX] <= sharkLevel) {
					if(graph[moveY][moveX] == 0 || graph[moveY][moveX] == sharkLevel) {
						visited[moveY][moveX] = true;
						queue.offer(new int[] {moveY, moveX, num[2] + 1});
					}
					else if(graph[moveY][moveX] < sharkLevel) {
						visited[moveY][moveX] = true;
						
						// 조건에 맞는 먹이를 고르게 함
						if(fishFind > num[2]) {
							fishFind = num[2];
							startY = moveY;
							startX = moveX;
						}
						else if(fishFind == num[2]) {
							if(startY > moveY) {
								fishFind = num[2];
								startY = moveY;
								startX = moveX;
							}
							else if(startY == moveY) {
								if(startX > moveX) {
									fishFind = num[2];
									startY = moveY;
									startX = moveX;
								}
							}
						}
					}
				}
			}
		}
		// 먹을 물고기가 없으면
		if(fishFind == Integer.MAX_VALUE) callMother = true;
		else {
			// 있으면 물고기 없애고,
			// 시간, 먹은 횟수, 상어 크기 조정
			graph[startY][startX] = 0;
			time += fishFind;
			eatCount++;
			if(sharkLevel == eatCount) {
				sharkLevel++;
				eatCount = 0;
			}
		}
		
	}

}