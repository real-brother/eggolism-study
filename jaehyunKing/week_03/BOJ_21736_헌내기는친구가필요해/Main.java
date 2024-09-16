package BOJ_21736_헌내기는친구가필요해;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	// bfs
	static char[][] campus;
	static boolean[][] visited;
	//bfs를 위한 스택이용, 2차원 배열이기 때문에 x, y축을 나눠서 저장
	static Queue<Integer> queue_y = new LinkedList<>();
	static Queue<Integer> queue_x = new LinkedList<>();
	// N, M을 밑에 bfs() 함수에서 쓰기 위해 static으로 선언
	static int N;
	static int M;

	// 델타배열 -> bfs에서 움직일 때 사용
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
		int count = bfs(now_y, now_x);
		
		// count = 0 -> 만난 사람이 0명이면 TT를 출력
		if (count == 0)System.out.println("TT");
		else System.out.println(count);

	}

	static int bfs(int now_y, int now_x) {

		int count = 0;
		visited[now_y][now_x] = true;
		queue_y.offer(now_y);
		queue_x.offer(now_x);

		while (!queue_y.isEmpty() && !queue_x.isEmpty()) {
			now_y = queue_y.poll();
			now_x = queue_x.poll();

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
							queue_y.offer(move_y);
							queue_x.offer(move_x);
						}

					}
				}

			}
		}
		return count;
	}

}
