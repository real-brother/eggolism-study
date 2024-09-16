package BOJ_21736_헌내기는친구가필요해;

import java.util.Scanner;

public class Main1 {

	// dfs
	static char[][] campus;
	static boolean[][] visited;
	static int count = 0;

	// N, M을 밑에 dfs() 함수에서 쓰기 위해 static으로 선언
	static int N;
	static int M;

	// 델타배열 -> dfs에서 움직일 때 사용
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N x M 배열
		N = sc.nextInt();
		M = sc.nextInt();
		
		// 헷갈리지 않게 0번 인덱스 버리기
		campus = new char[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		
		// 2차원 배열에서 현재 위치를 나타낼 변수
		int now_x = 0;
		int now_y = 0;
		
		// 2차원 배열에 입력받은 문자를 집어넣고 'I'(현재 위치)가 있으면 그 인덱스를 저장
 		for (int r = 1; r <= N; r++) {
			String S = sc.next();

			for (int c = 1; c <= M; c++) {
				char a = S.charAt(c - 1);
				campus[r][c] = a;
				if (a == 'I') {
					now_y = r;
					now_x = c;
				}
			}
		}
		int result = dfs(now_y, now_x);
		
		// count = 0 -> 만난 사람이 0명이면 TT를 출력
		if (result == 0)System.out.println("TT");
		else System.out.println(result);

	}

	static int dfs(int now_y, int now_x) {

		visited[now_y][now_x] = true;

			for (int i = 0; i < 4; i++) {
				int move_y = now_y + dr[i];
				int move_x = now_x + dc[i];
				
				//인덱스를 벗어나지 않기 위한 if문
				if (1 <= move_y && move_y <= N && 1 <= move_x && move_x <= M){
					// P 또는 O면 이동
					if (campus[move_y][move_x] == 'P' || campus[move_y][move_x] == 'O') {
						if (!visited[move_y][move_x]) {
							if (campus[move_y][move_x] == 'P')count++;
							visited[move_y][move_x] = true;
							dfs(move_y, move_x);

						}

					}
				}

			}
		
		return count;
	}

}
