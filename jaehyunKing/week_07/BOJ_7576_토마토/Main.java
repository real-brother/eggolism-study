package BOJ_7576_토마토;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 *  익은 토마토가 숫자 1
 *  1은 하루마다 상하좌우로 증식하는 데(0 -> 1)
 *  얼마 만에 모든 토마토를 익게할 수 있는지 구하는 문제
 *  불가능하면 -1 출력  
 *  내가 한 방식은 모든 숫자가 1인 칸에서 bfs를 도는 방식
 *  일수가 가장 큰 값을 구해주면 됨
 */
public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] graph;
	static boolean[][] visited;
	static Queue<int[]> queue;
	static int max, N, M;
	static boolean can;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		graph = new int[N][M];
		visited = new boolean[N][M];
		queue = new LinkedList<>();
		
		// 순회하면서 입력값을 받아준다
		// 입력값이 1이면 큐에 y좌표, x좌표, 일(day)수 를 넣어준다
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				graph[r][c] = sc.nextInt();
				if(graph[r][c] == 1) {
					queue.offer(new int[]{r, c, 0});
					visited[r][c] = true;
				}
			}
		}
		
		// can이 true -> 다 익었다
		// false 다 익지 않았다
		can = true;
		max = 0;
		
		//bfs를 돌아준다.
		bfs();
		
		// 모두 익었는지 확인
		// 0이 있다면 바로 빠져나온다
		// can을 false로 바꿔준다
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(graph[r][c] == 0) {
					can = false;
					break;
				}
			}
		}
		
		// can이 true면 max를 출력
		// 아니라면 -1을 출력
		if(can) System.out.println(max);
		else System.out.println(-1);


	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			for(int i = 0; i < 4; i++) {
				int move_y = num[0] + dr[i];
				int move_x = num[1] + dc[i];
				
				if(0 <= move_y && move_y < N && 0 <= move_x && move_x < M) {
					if(!visited[move_y][move_x] && graph[move_y][move_x] == 0) {
						
						// 일수를 1 증가시켜서 큐에 넣어준다
						queue.offer(new int[] {move_y, move_x, num[2] + 1});
						visited[move_y][move_x] = true;
						graph[move_y][move_x] = 1;
						
						// 일수가 max보다 크면 max에 넣어준다.
						if(max < num[2]+1) max = num[2]+1;	
					}
				}
			}
		}
	}
}