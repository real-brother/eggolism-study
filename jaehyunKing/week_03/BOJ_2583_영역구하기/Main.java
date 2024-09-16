package BOJ_2583_영역구하기;

import java.util.*;

public class Main {
	static int[][] board;
	static boolean[][] visited;
	static int N, M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		int K = sc.nextInt();
		
		board = new int[M+1][N+1];
		visited = new boolean[M+1][N+1];
		
		int ground_count = 0;
		List<Integer> result = new ArrayList<>();
		
		for(int i = 0; i < K; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
		
			for(int r = y1+1; r <= y2; r++)for(int c = x1+1; c <= x2; c++) board[r][c] = 1;	
		}
		
		for(int r = 1; r <= M; r++) {
			for(int c = 1; c <= N; c++) {
				if(!visited[r][c] && board[r][c] == 0) {
					ground_count++;
					result.add(bfs(r,c));
				}
			}
		}
		Collections.sort(result);
		
		System.out.println(ground_count);
		for(int x : result) System.out.print(x + " ");

	}
	
	static int bfs(int now_y, int now_x) {
		
		int[] dr = {-1, 1, 0, 0}; //상하좌우
		int[] dc = {0, 0, -1, 1};
		
		int count = 1;
		Queue<int[]> queue = new LinkedList<>();
		visited[now_y][now_x] = true;
		queue.offer(new int[] {now_y, now_x});
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int i = 0; i < 4; i++) {
				int move_y = now[0] + dr[i];
				int move_x = now[1] + dc[i];
				if(1 <= move_y && move_y <= M && 1 <= move_x && move_x <= N) {
					if(!visited[move_y][move_x] && board[move_y][move_x] == 0) {
						count++;
						visited[move_y][move_x] = true;
						queue.offer(new int[]{move_y, move_x});
					}
				}
			}
		}
		return count;
		
	}
}