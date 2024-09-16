import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {

	/*
	 * 디버깅 
	 * 1. 옮기고 나서 matrix[nr][nc] = 0 인 과정 
	 * - 아예 움직이지 않았어도 0으로 변경되는 것 수정
	 * 
	 * 2. 1번에서 옮기고 나서 둘이 같으면 패스해야 함
	 * - OR 연산자가 아니라 AND 연산자를 사용함
	 */

	static int[][] matrix;
	static int[][] matrix2;
	static int[][] visited;
	static int[] sel;

	static int N, answer;
	static int selR, selC;

	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = sc.nextInt();
				matrix[i][j] = num;
			}
		}

		// 1. 부분집합 구하기
		sel = new int[5];
		answer = 0;
		perm(0, 0);

		// 정답 출력하기
		System.out.println(answer);

	}

	// 중복 순열
	public static void perm(int idx, int sidx) {

		if (sidx == 5) {

			// 똑같은 숫자가 연속되면 백트래킹
			// 하나라도 더 하는게 그리디
//			boolean is_on = backtracking();

			// 2. 한 게임 시작
//			if (is_on) {

				// 매트릭스 복제
				matrix2 = new int[N][N];
				for (int i = 0; i < N; i++) {
					matrix2[i] = matrix[i].clone();
				}

				// 최대값 갱신
//				System.out.println("----------------");
				int maxNum = gameStart();
				answer = Math.max(maxNum, answer);

				// 출력해보기
//				System.out.println(Arrays.toString(sel));
//				System.out.println("max: " + maxNum);
//				System.out.println("최종 매트릭스");
//				for (int i = 0; i < N; i++) {
//					System.out.println(Arrays.toString(matrix2[i]));
//				}

//			}

			return;
		}

		if (idx == 4) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			sel[sidx] = i;
			perm(i, sidx + 1);
		}

	}

	public static boolean backtracking() {

		// 4번
		for (int i = 0; i < 4; i++) {
			if (sel[i] == sel[i + 1]) {
				return false;
			}
		}
		return true;

	}

	public static int gameStart() {

		// 5번 이동
		for (int i = 0; i < 5; i++) {
			int direction = sel[i];

			// 시작 좌표 설정 -> 메소드
			startPoint(direction);

			// 한 방향 이동 시작 (N*N번)
			int total = N * N;
			visited = new int[N][N]; // 중복 방지를 위해 합쳐진 곳 표시하는 매트릭스
			while (total-- > 0) {

				// 좌표 이동하여 다른 블록 선택할 수 있게 -> 메소드 -> 요즘 계속 뒤로 빼서 문제!!!! (아니다 {} 잘못 넣은듯)
				move(direction);

				// 블록 1개 선택 -> 블록이 없는 0이면 선택 X
				if (matrix2[selR][selC] == 0)
					continue;

//				System.out.println("dir: " + direction + " selR: " + selR + " selC: " + selC);
				// 블록 1개 이동 (최대 N-1번 이동 가능) -> N번으로 벽 나가게
				int nr = selR;
				int nc = selC;

				for (int j = 0; j < N; j++) {
					nr += dr[direction];
					nc += dc[direction];

					// 1) 밖으로 나가면 벽 만나기 이전으로 위치 -> 아예 이동하지 않는 경우가 있음!!
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						nr -= dr[direction];
						nc -= dc[direction];
						if (nr != selR || nc != selC) {
							matrix2[nr][nc] = matrix2[selR][selC];
							matrix2[selR][selC] = 0;
						}
//						System.out.println("dir: " + direction + " selR: " + selR + " selC: " + selC + " nr: " + nr + " nc: " + nc);
						break;
					}

					// 2) 다른 블록 만나면 비교 + 이전에 바뀌지 않은 부분 -> else if 넣어야지 벽에서 반영된 것 간섭X!!
					else if (matrix2[nr][nc] > 0) {
						// 성공 시 합체
						if (visited[nr][nc] != 1 && matrix2[nr][nc] == matrix2[selR][selC]) {
							matrix2[nr][nc] *= 2;
							matrix2[selR][selC] = 0;
							visited[nr][nc] = 1; // 중복 방지
							break;
						}
						// 실패 시 한칸 회귀 후 위치 -> 아예 이동하지 않는 경우가 있음!!
						else {
							nr -= dr[direction];
							nc -= dc[direction];

							if (nr != selR || nc != selC) { // 또는 조건!!
								matrix2[nr][nc] = matrix2[selR][selC];
								matrix2[selR][selC] = 0;
							}
							break;
						}

					}

				} // 블록 1개 끝까지 이동 완료

			} // 25번 끝 (while끝)

		} // 5번 이동 끝

		// 최대 숫자 구하기
		int maxValue = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				maxValue = Math.max(maxValue, matrix2[i][j]);
			}
		}

		return maxValue;

	}

	public static void startPoint(int dir) {

		if (dir == 0) {
			selR = -1;
			selC = 0;
		} else if (dir == 1) {
			selR = N;
			selC = 0;
		} else if (dir == 2) {
			selR = 0;
			selC = -1;
		} else if (dir == 3) {
			selR = 0;
			selC = N;
		}
	}

	// N*N번 호출된다는 전제
	// 블록 탐색 인덱스 처리 -> 반대로 이동 (vs. 블록 한 개 선택되고 이동)
	public static void move(int dir) {

		// 상
		if (dir == 0) {
			selR++;
			if (selR >= N) {
				selR = 0;
				selC++;
			}
		}

		// 하
		else if (dir == 1) {
			selR--;
			if (selR < 0) {
				selR = N - 1;
				selC++;
			}
		}

		// 좌
		if (dir == 2) {
			selC++;
			if (selC >= N) {
				selR++;
				selC = 0;
			}
		}

		// 우
		if (dir == 3) {
			selC--;
			if (selC < 0) {
				selR++;
				selC = N - 1;
			}
		}

	}

}