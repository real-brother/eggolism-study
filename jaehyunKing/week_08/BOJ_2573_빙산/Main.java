package BOJ_2573_빙산;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] graph;
	
	// minus 배열을 따로 만들어서
	// 한 번에 빙산을 녹여준다
	// 동시다발적으로 녹아야되는데 한 빙산이 0이 되어서
	// 녹지 못하는 사태를 방지
	static int[][] minus;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new int[N][M];
		minus = new int[N][M];
		
		for(int r = 0; r < N; r++) for(int c = 0; c < M; c++) graph[r][c] = sc.nextInt();
		
		// 빙산이 분리되는 시간
		int year = 0;
		
		// 빙산이 분리되었는지 확인
		// 다 녹을 때까지 분리되지 않으면 0을 출력
		boolean notDivide = false;
		
		loop : while(true) {
			int count = 0;
			visited = new boolean[N][M];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(!visited[r][c] && graph[r][c] != 0) {
						count++;
						
						// 빙산이 두 덩어리 이상이면 while문을 빠져나감
						if(count >= 2) break loop;
						bfs(r, c);

					}
				}
			}
			// 빙산이 한 덩어리도 없으면 notDivide = true
			// 나중에 0을 출력
			if(count == 0) {
				notDivide = true;
				break;
			}
			year++;
		}
		
		if(notDivide) System.out.println(0);
		else System.out.println(year);

	}
	
	static void bfs(int now_y, int now_x) {
		Queue<int[]> queue = new LinkedList<>();
		visited[now_y][now_x] = true;
		queue.offer(new int[] {now_y, now_x});
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			for(int i = 0; i < 4; i++) {
				int move_y = num[0] + dr[i];
				int move_x = num[1] + dc[i];
				if(0 <= move_y && move_y < N && 0 <= move_x && move_x < M) {
					
					// 사방탐색 -> 주변이 0이면 minus 배열의 현재 위치에 -1
					if(graph[move_y][move_x] == 0) minus[num[0]][num[1]]--;
					
					// 방문하지 않고, 0이 아니라면 bfs
					else if(!visited[move_y][move_x]){
						visited[move_y][move_x] = true;
						queue.offer(new int[]{move_y, move_x});
					}
					
				}
			}
		}
		
		// 빙산 녹이기
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				
				// minus배열 값이 0이 아니면 더 해줌
				if(minus[r][c] != 0) {
					graph[r][c] += minus[r][c];
					
					// graph(빙산)이 음수이면 0으로 변경
					if(graph[r][c] < 0) graph[r][c] = 0;
					
					// minus 배열을 초기화
					minus[r][c] = 0;
				}
			}
		}
	}

}