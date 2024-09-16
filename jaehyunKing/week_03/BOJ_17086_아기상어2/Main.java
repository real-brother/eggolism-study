package BOJ_17086_아기상어2;

import java.util.*;

public class Main {
	// bfs
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		// 헷갈리니까 0번 인덱스 버림
		int[][] space = new int[N + 1][M + 1];

		// space 배열에 입력값 넣어줌
		for (int r = 1; r <= N; r++) for (int c = 1; c <= M; c++)space[r][c] = sc.nextInt();
		
		// 델타배열 1시부터 시계방향(총 8방향 이동)
		int[] dr = { -1, 0, 1, 1, 1, 0, -1, -1 };
		int[] dc = { 1, 1, 1, 0, -1, -1, -1, 0 };

		int max = 0;
		
		// 현재 위치(2차원 배열 순회)에서 퍼져나가면서 1을 찾고
		// 1을 찾았을 때 그 값이 max보다 크면 저장하고 다음 위치로, 아니면 그냥 다음 위치로
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				// 현재 위치가 바뀌면 싹 다 초기화
				boolean[][] visited = new boolean[N + 1][M + 1];
				int[][] space_count = new int[N + 1][M + 1];
				Queue<Integer> queue_y = new LinkedList<>();
				Queue<Integer> queue_x = new LinkedList<>();

				visited[r][c] = true;
				queue_y.offer(r);
				queue_x.offer(c);

				loop: while (!queue_y.isEmpty() && !queue_x.isEmpty()) {
					// 다음 가장자리(둘레) 확인
					int now_y = queue_y.poll();
					int now_x = queue_x.poll();
					if (space[now_y][now_x] == 1)break;

					for (int k = 0; k < 8; k++) {
						int move_y = now_y + dr[k];
						int move_x = now_x + dc[k];

						if (1 <= move_y && move_y <= N && 1 <= move_x && move_x <= M) {

							if (space[move_y][move_x] == 1) {
								if (max <= space_count[now_y][now_x]) max = space_count[now_y][now_x] + 1;
								break loop;
								
							} else if (!visited[move_y][move_x]) {
								visited[move_y][move_x] = true;
								queue_y.offer(move_y);
								queue_x.offer(move_x);
								space_count[move_y][move_x] = space_count[now_y][now_x] + 1;
							}
						}
					}
				}

			}
		}
		System.out.println(max);

	}

}
