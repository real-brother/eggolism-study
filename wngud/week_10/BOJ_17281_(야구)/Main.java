import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] matrix;
	static int[] sel;
	static int[] visited;

	static int total, answer;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		total = sc.nextInt();

		matrix = new int[total][9];
		for (int i = 0; i < total; i++) {
			for (int j = 0; j < 9; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}

		// 순열 만들기
		sel = new int[9];
		visited = new int[9];
		answer = 0;

		perm(0);

		// 정답 출력
		System.out.println(answer);

	}

	// 3번째가 0이 아닌 경우를 빠르게 백트래킹 하자
	public static void perm(int sidx) {

		// 백트래킹
		if (sidx < 9 && sel[3] != -1 && sel[3] != 0) {
			return;
		}

		if (sidx == 9) {
//			System.out.println(Arrays.toString(sel));

			// 선택 완료
			Baseball();

			return;
		}

		for (int i = 0; i < 9; i++) {
			if (visited[i] != 1) {
				// 사용
				sel[sidx] = i;
				visited[i] = 1;
				perm(sidx + 1);

				// 초기화
				sel[sidx] = -1; // 백트래킹 때문에 사용
				visited[i] = 0;
			}

		}
	}

	static void Baseball() {

		int count = 0;
		int player = -1;
//		System.out.println(Arrays.toString(sel));

		for (int i = 0; i < total; i++) {
			// 이닝마다 넣어줘야 함!!
			// 끝난 번호 유지해야 함!!
			int out = 0;
			int[] base = new int[3];

			while (out < 3) {

				// 타선 시작
				player = (player + 1) % 9;
				int hit = matrix[i][sel[player]]; // 순열로 파악
//					System.out.print(hit + " ");

				if (hit == 0) {
					// 아웃
					out++;

				} else if (hit == 1) {
					// 1루타
					if (base[2] == 1)
						count++;

					for (int b = 2; b >= 1; b--) {
						base[b] = base[b - 1]; // 한칸씩 이동
					}

					base[0] = 1;

				} else if (hit == 2) {
					// 2루타
					if (base[2] == 1)
						count++;
					if (base[1] == 1)
						count++;

					base[2] = base[0];
					base[1] = 1;
					base[0] = 0;

				} else if (hit == 3) {
					// 3루타
					if (base[2] == 1)
						count++;
					if (base[1] == 1)
						count++;
					if (base[0] == 1)
						count++;

					base = new int[3];
					base[2] = 1;

				} else if (hit == 4) {
					// 홈런
					count++;
					if (base[2] == 1)
						count++;
					if (base[1] == 1)
						count++;
					if (base[0] == 1)
						count++;

					base = new int[3];
				}

				// 한 타선 끝
				if (out == 3) {
					break;
				}

			} // 반복 끝 (1이닝 끝)
		} // 경기 끝

		answer = Math.max(count, answer);
//		System.out.println("count: " + count);

	}

}