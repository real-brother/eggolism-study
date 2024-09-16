package BOJ_14503_로봇청소기;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N, M, r, c, d;
	static int[][] maps, visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int count = 1;

	public static void main(String[] args) {

		N = sc.nextInt();
		M = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();

		maps = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				maps[i][j] = sc.nextInt();
			}
		}

		visited = new int[N][M];
		visited[r][c] = 1;

		// 로봇 청소기 작동
		while (true) {
			// flag <- 이번 로롯 청소기가 청소를 진행하면 1, 아니라면 0
			int flag = 0;
			for (int i = 0; i < 4; i++) {
				// 현재 방향 기준 왼쪽 방향으로 회전
				int nr = r + dr[(d + 3) % 4];
				int nc = c + dc[(d + 3) % 4];
				d = (d + 3) % 4;

				// 이동할 위치가 지도 내부이고, 아직 방문하지 않은 곳인지 확인
				if (0 > nr || nr >= N || 0 > nc || nc >= M)	continue;
				if (maps[nr][nc] == 0 && visited[nr][nc] == 0) {
					visited[nr][nc] = 1; 
					count++;
					r = nr;
					c = nc;
					flag = 1; //청소완료
					break;
				}
			}
			// 네 방향 모두 청소할 곳이 없을 때
			if (flag == 0) {
				// 현재 방향 기준으로 후진이 가능한지?
				if (maps[r - dr[d]][c - dc[d]] == 1) {
					System.out.println(count); // 청소 완료한 칸 수 출력
					break;
				} else {
					r -= dr[d]; // 후진
					c -= dc[d]; // 후진
				}
			}
		}
	}
}
