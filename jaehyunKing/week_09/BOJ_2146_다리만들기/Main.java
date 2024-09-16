package BOJ_2146_다리만들기;

/*
 *  bfs를 통해 각 영역(섬) 별로 숫자를 다르게 입력
 *  각 영역에서 bfs를 다시 돌린다
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N, areaNum, min;
	static int[][] graph;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		graph = new int[N][N];
		visited = new boolean[N][N];
		
		for(int r = 0; r < N; r++) for(int c = 0; c < N; c++) graph[r][c] = sc.nextInt();
		
		// bfs를 통해 각 영역(섬) 별로 숫자를 다르게 입력
		areaNum = 1;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(graph[r][c] != 0 && !visited[r][c]) {
					areaBfs(r, c);
					areaNum++;
				}
			}
		}
		
		areaNum = 1;
		min = Integer.MAX_VALUE;
		
		// 각 영역 별로 bfs 순회
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(graph[r][c] == areaNum) {
					bfs(r, c);
					areaNum++;
				}
			}
		}
		
		System.out.println(min);
		
	}
	
	// 영역 별로 숫자를 바꿔주는 메서드
	// 기본 bfs + graph[moveY][moveX] = areaNum;
	static void areaBfs(int nowY, int nowX) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {nowY, nowX});
		visited[nowY][nowX] = true;
		graph[nowY][nowX] = areaNum;
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			for(int i = 0; i < 4; i++) {
				int moveY = num[0] + dr[i];
				int moveX = num[1] + dc[i];
				if(0 <= moveY && moveY < N && 0 <= moveX && moveX < N) {
					if(!visited[moveY][moveX] && graph[moveY][moveX] != 0) {
						queue.offer(new int[] {moveY, moveX});
						visited[moveY][moveX] = true;
						graph[moveY][moveX] = areaNum;
					}
				}
			}
		}
	}
	
	static void bfs(int nowY, int nowX) {
		visited = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {nowY, nowX, 0});
		visited[nowY][nowX] = true;
		while(!queue.isEmpty()) {
			int[] nums = queue.poll();
			
			// nums[2] == 이동 횟수(다리 길이)
			if(nums[2] == min) continue;
			
			for(int i = 0; i < 4; i++) {
				int moveY = nums[0] + dr[i];
				int moveX = nums[1] + dc[i];
				if(0 <= moveY && moveY < N && 0 <= moveX && moveX < N) {
					if(!visited[moveY][moveX]) {
						
						// 같은 영역이면 이동 횟수(num[2])를 증가시키지 않고 이동
						if(graph[moveY][moveX] == areaNum) {
							queue.offer(new int[] {moveY, moveX, nums[2]});
						}
						else if(graph[moveY][moveX] == 0) {
							queue.offer(new int[] {moveY, moveX, nums[2]+1});
						}
						else if(graph[moveY][moveX] != areaNum) {
							if(min > nums[2]) min = nums[2];
						}
						visited[moveY][moveX] = true;
					}
				}
			}
		}
	}
}