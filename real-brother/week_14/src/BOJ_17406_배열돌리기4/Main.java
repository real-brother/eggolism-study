package BOJ_17406_배열돌리기4;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N, M, K;
	static int[][] cycle;
	static int[][] map;
	static int minValue = Integer.MAX_VALUE;
	/*
	 * 백트래킹 아닌걸로 가져오려했는데 또트래킹 문제네
	 * 1. 회전 연산 순서를 순열로 만들어 준다.
	 * 2. 각 순열별로 회전 연산을 수행한다.
	 * 3. 각 회전 연산 별로 배열의 최솟값을 구해준다. 
	 */

	public static void main(String[] args) throws Exception {
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		cycle = new int[K][3];

		for (int k = 0; k < K; k++) {
			cycle[k][0] = sc.nextInt() - 1;
			cycle[k][1] = sc.nextInt() - 1;
			cycle[k][2] = sc.nextInt();
		}

		generatePerm(0, new int[K], new boolean[K]);

		System.out.println(minValue);
	}

	public static void generatePerm(int cnt, int[] arr, boolean[] visited) {
		if (cnt == K) {
			startRotate(arr);
			return;
		}
		for (int i = 0; i < K; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			arr[cnt] = i;
			generatePerm(cnt + 1, arr, visited);
			visited[i] = false;
		}
	}

	public static void startRotate(int[] arr) {
		// 각 경우별로 배열 깊은 복사 
		int[][] testArray = new int[N][M];
		for (int i = 0; i < N; i++) {
			testArray[i] = map[i].clone();
		}

		for (int k = 0; k < K; k++) {
			int r = cycle[arr[k]][0];
			int c = cycle[arr[k]][1];
			int S = cycle[arr[k]][2];

			for (int s = 1; s <= S; s++) {
				// 위
				int upTemp = testArray[r - s][c + s];
				for (int y = c + s; y > c - s; y--) {
					testArray[r - s][y] = testArray[r - s][y - 1];
				}
				// 오른쪽
				int rightTemp = testArray[r + s][c + s];
				for (int x = r + s; x > r - s; x--) {
					testArray[x][c + s] = testArray[x - 1][c + s];
				}
				testArray[r - s + 1][c + s] = upTemp;
				// 아래
				int leftTemp = testArray[r + s][c - s];
				for (int y = c - s; y < c + s; y++) {
					testArray[r + s][y] = testArray[r + s][y + 1];
				}
				testArray[r + s][c + s - 1] = rightTemp;
				// 왼쪽
				for (int x = r - s; x < r + s; x++) {
					testArray[x][c - s] = testArray[x + 1][c - s];
				}
				testArray[r + s - 1][c - s] = leftTemp;
			}
		}
		
		// 최솟값 구해서 갱신해주기 
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += testArray[i][j];
			}
			if (minValue > sum)	minValue = sum;
		}
	}
}