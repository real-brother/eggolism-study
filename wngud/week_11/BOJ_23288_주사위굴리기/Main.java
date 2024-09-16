public class Main {
	
	/*디버깅
	 * 1. 아랫면과 매트릭스의 숫자를 비교해야 하는데,
	 * 윗면을 기준으로 비교했다.
	 */

	static int[][] matrix;
	static int N, M;

	static int[] cubeArr;

	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		int K = sc.nextInt();

		// 매트릭스 입력
		matrix = new int[N][M];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}

		// K번 동안 이동
		int answer = 0;
		int cubeR = 0; // (0, 0)부터 시작
		int cubeC = 0;
		int cubeD = 0; // 큐브의 방향 변수, 동서남북, 시작은 0(동)
		cubeArr = new int[] { 1, 2, 3, 4, 5, 6 }; // 큐브 전개도

		while (K-- > 0) {

			// 1. 이동 방향으로 이동하기
			int nr = cubeR + dr[cubeD];
			int nc = cubeC + dc[cubeD];

			// 1) 실패할 경우 -> 반대로 전개도 변경 -> 이동방향도 변화한가? (예제O)
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
				// cubeD 반대로 바꾸기
				cubeD = flipD(cubeD);
				
				// 원래 자리에서 반대로 이동		
				cubeR += dr[cubeD];
				cubeC += dc[cubeD];

				// 전개도 변경 -> 반대 방향으로 전개도 바꿔야 함
				changeArr(cubeD);
			}

			// 2) 성공한 경우 -> 전개도 변경
			else {
				// 자리 이동
				cubeR = nr;
				cubeC = nc;

				// 전개도 변경
				changeArr(cubeD);
			}
			// 2. 점수 획득
			int count = bfs(cubeR, cubeC);
			answer += count;

			// 3. 이동방향 변경
			int cubeDownSide = cubeArr[5]; // 아랫면이 기준!!
			int matrixSide = matrix[cubeR][cubeC];
			
			// 방향 무조건 +1 하면 안됨
			if (cubeDownSide > matrixSide) {
				cubeD = rotation(cubeD, 0);
			} else if (cubeDownSide < matrixSide) {
				cubeD = rotation(cubeD, 1);
			}			

		}

		// 정답 출력하기
		System.out.println(answer);

	}

	public static void changeArr(int dir) {

		int[] cubeArr2 = new int[6];
		
		// 동
		if (dir == 0) {
			cubeArr2[0] = cubeArr[3];
			cubeArr2[1] = cubeArr[1];
			cubeArr2[2] = cubeArr[0];
			cubeArr2[3] = cubeArr[5];
			cubeArr2[4] = cubeArr[4];
			cubeArr2[5] = cubeArr[2];
		}

		// 서
		else if (dir == 1) {
			cubeArr2[0] = cubeArr[2];
			cubeArr2[1] = cubeArr[1];
			cubeArr2[2] = cubeArr[5];
			cubeArr2[3] = cubeArr[0];
			cubeArr2[4] = cubeArr[4];
			cubeArr2[5] = cubeArr[3];
		}

		// 남
		else if (dir == 2) {
			cubeArr2[0] = cubeArr[1];
			cubeArr2[1] = cubeArr[5];
			cubeArr2[2] = cubeArr[2];
			cubeArr2[3] = cubeArr[3];
			cubeArr2[4] = cubeArr[0];
			cubeArr2[5] = cubeArr[4];
		}

		// 북
		else if (dir == 3) {
			cubeArr2[0] = cubeArr[4];
			cubeArr2[1] = cubeArr[0];
			cubeArr2[2] = cubeArr[2];
			cubeArr2[3] = cubeArr[3];
			cubeArr2[4] = cubeArr[5];
			cubeArr2[5] = cubeArr[1];
		}
		
		// 이거 빼먹음
		cubeArr = cubeArr2;

	}

	public static int bfs(int r, int c) {

		int count = 1; // 시작 점수
		int[][] visited = new int[N][M];

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });
		visited[r][c] = 1;

		while (!queue.isEmpty()) {

			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] == 1)
					continue;

				// 점수가 같다면
				if (matrix[nr][nc] == matrix[r][c]) {
					count++;

					queue.add(new int[] { nr, nc });
					visited[nr][nc] = 1;
				}

			}

		} // while 끝

		return matrix[r][c] * count;

	}
	
	public static int rotation(int now, int status) {
		
		// 0213
		
		if (status == 0) {
			if (now == 0) return 2;
			else if (now == 2) return 1;
			else if (now == 1) return 3;
			else if (now == 3) return 0;
		}
		
		else {
			if (now == 0) return 3;
			else if (now == 3) return 1;
			else if (now == 1) return 2;
			else if (now == 2) return 0;
		}
		return -1;
	}
	
	public static int flipD(int now) {
		
		if (now == 0) return 1;
		else if (now == 1) return 0;
		else if (now == 2) return 3;
		else if (now == 3) return 2;
		
		return -1;
		
	}
	
	

}