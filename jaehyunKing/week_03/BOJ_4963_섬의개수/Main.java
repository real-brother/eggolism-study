package BOJ_4963_섬의개수;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] ground;
	static boolean[][] visited;
	static int w, h;

	static int[] dr = { -1, 0, 1, 1, 1, 0, -1, -1 }; // 1시부터 시계방향
	static int[] dc = { 1, 1, 1, 0, -1, -1, -1, 0 };

	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {

			w = sc.nextInt();
			h = sc.nextInt();

			if (w == 0 && h == 0)
				break;

			ground = new int[h][w];
			visited = new boolean[h][w];

			for (int r = 0; r < h; r++) for (int c = 0; c < w; c++) ground[r][c] = sc.nextInt();

			int count = 0;

			for (int r = 0; r < h; r++) {
				for (int c = 0; c < w; c++) {
					if (ground[r][c] == 1 && !visited[r][c]) {
						count++;
						bfs(r, c);
					}
				}
			}

			System.out.println(count);
		}

	}

	static void bfs(int now_y, int now_x) {
		
		queue.offer(new int[]{now_y, now_x});

		while (!queue.isEmpty()) {
			
			int[] now = queue.poll();
			
			for (int i = 0; i < 8; i++) {
				int move_y = now[0] + dr[i];
				int move_x = now[1] + dc[i];
				if (0 <= move_y && move_y < h && 0 <= move_x && move_x < w) {
					if (ground[move_y][move_x] == 1 && !visited[move_y][move_x]) {
						queue.offer(new int[]{move_y, move_x});		
						visited[move_y][move_x] = true;
					}
				}
			}
		}
	}
}
