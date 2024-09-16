public class Main {
	/*설계 방식
	 * 1. 25개 자리에서 7개를 뽑는다. -> 조합 사용
	 * - 자리를 뽑을 때 본인파가 아닌 경우에는 파라미터로 추가해서, 
	 * - 적인 파가 4이상이면 백트래킹으로 제거한다.
	 * 2. 만들어진 조합을 가지고 BFS를 돌려서 7개가 전부 연결되어있는지 확인한다. 
	 * -> 조합에만 이동할 수 있도록 하여 count가 7이면 성공.
	 */

	static char[][] matrix;
	static int[] visited;
	static int[][] depthMatrix;
	static int[][] enemyMatrix;

	static int answer;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 입력 받기
		matrix = new char[5][5];
		for (int i = 0; i < 5; i++) {
			String inputs = sc.nextLine();
			for (int j = 0; j < 5; j++) {
				matrix[i][j] = inputs.charAt(j); // 실수!!
			}
		}

		// 순회
		answer = 0;
		visited = new int[25];
		comb(0, 0, 0);

		// 정답 출력하기
		System.out.println(answer);

	}


	static void comb(int idx, int depth, int enemy) {

		// 조건 1 -> 적의 수 (백트래킹)
		if (enemy == 4) {
			return;
		}

		if (depth == 7) {
			// 조건 2 -> 연결되어 있는지 (BFS)
			boolean result = bfs(visited, idx); // idx 안쓰기로 함
			if (result) {
				answer++;
			}
			return;
		}

		if (idx == 25) {
			return;
		}
		

		visited[idx] = 1;
		if (matrix[idx / 5][idx % 5] == 'Y') {
			comb(idx + 1, depth + 1, enemy + 1); // 적의 숫자 추가
		} else {
			comb(idx + 1, depth + 1, enemy);
		}
		visited[idx] = 0;
		comb(idx + 1, depth, enemy);

	}

	static boolean bfs(int[] visited, int idx) {

		// 1인 인덱스 찾기
		for (int n = 0; n < 5; n++) {
			for (int m = 0; m < 5; m++) {
				int num = visited[n*5 + m];

				if (num != 1) {
					continue;
				}

				// 1인 인덱스 찾기 완료
				Queue<int[]> queue = new LinkedList<>();
				int[][] visited2 = new int[5][5];

				queue.add(new int[] { n, m, 1 });
				visited2[n][m] = 1;

				while (!queue.isEmpty()) {

					int[] cur = queue.poll();

					for (int s = 0; s < 4; s++) {
						int nr = cur[0] + dr[s];
						int nc = cur[1] + dc[s];

						if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5)
							continue;

						// 가지 않았던 곳(BFS 기본)과 visited가 1이었던 곳(조합)만 이동 가능
						if (visited2[nr][nc] != 0 || visited[nr * 5 + nc] == 0)
							continue;

						queue.add(new int[] { nr, nc, cur[2] + 1 });
						visited2[nr][nc] = cur[2] + 1;

					}

				}

				// 도는 것 끝
				// visited가 찍힌 개수 확인
				int count = 0;
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (visited2[i][j] > 0) {
							count++;
						}
							
					}
				}
				
				if (count == 7) {
					return true;
				}
				
				// n과 m 한개로만 찾으면 됨
				break;

			}
		}

		return false;

	}
}