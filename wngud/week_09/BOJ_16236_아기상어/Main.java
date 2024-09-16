public class Main {
	
	/*설계 방식
	 * 1. BFS로 돌면서 조건에 맞는 가까운 것을 먹고 새로운 BFS로 움직임
	 * 2. 시간에서 불가능한 상황이면 바로 끝내야 함.
	 * - tmpTime으로 BFS를 돌다가 먹이를 찾으면 time에 추가하고
	 * - 먹이를 결국 못찾고 끝나면 tmpTime가 버려지도록 함
	 */
	
	/*디버깅
	 * 1. BFS의 탐색을 '상좌우하'로 했는데, depth가 깊어지면 크게 의미가 없었음
	 * 2. 후보군을 찾고, 그 후보군 중에서 r과 c가 가장 높은 것을 찾아서 반환함
	 * 
	 */

	static int[][] matrix;
	static int N;
	static int sharkSize, sharkEat, time;

	static int[] dr = { -1, 0, 0, 1 }; // 상좌우하!! -> 여기서 한계점 발생
	static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 입력 받기
		N = sc.nextInt();

		int[] sharkIdx = new int[2];

		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = sc.nextInt();
				matrix[i][j] = num;

				if (num == 9) {
					matrix[i][j] = 0; // 상어 위치 없애기
					sharkIdx[0] = i;
					sharkIdx[1] = j;
				}
			}
		}

		// 입력 받기 끝 //

		sharkSize = 2;
		sharkEat = 0;
		time = 0;

		// 1. 주변에 물고기 찾기
		bfs(sharkIdx[0], sharkIdx[1]);

		// 정답 출력하기
		System.out.println(time);
	}

	// 정확히 불가능할 때의 시간을 구해야 해서 tmpTime으로 쌓아놨다가 가능하면 추가하고 아니면 버림
	public static void bfs(int r, int c) {

		int[][] visited = new int[N][N];
		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[] { r, c });
		visited[r][c] = 1;

		int tmpTime = 0;

		boolean is_on = true;

		while (is_on) {

			tmpTime++;
			int qsize = queue.size();
			if (qsize == 0)
				is_on = false;

			List<int[]> tmp = new ArrayList<>();
			while (qsize-- > 0) {

				int[] cur = queue.poll();

				for (int s = 0; s < 4; s++) {
					int nr = cur[0] + dr[s];
					int nc = cur[1] + dc[s];

					// 상어 사이즈보다 크면 지나갈 수 없음
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (visited[nr][nc] == 1 || matrix[nr][nc] > sharkSize)
						continue;

					// 완료 -> 우선은 위쪽과 왼쪽을 고려하긴 했음
					// 단순 이동과 먹는 것을 구분해야 함
					queue.add(new int[] { nr, nc });
					visited[nr][nc] = 1;

					// 사이즈보다 작은 것을 먹을 수 있음 (대신 0보다는 커야 함)
					// 후보군 모으기!!
					if (matrix[nr][nc] > 0 && sharkSize > matrix[nr][nc]) {
						tmp.add(new int[] { nr, nc });
					}
				} // 4번 돌기 완료 ++ 전체 돌아야 함

			} // 한단계 점검 완료

			// 후보군에 들어 있는게 있다면
			if (tmp.size() > 0) {

				int nr = Integer.MAX_VALUE;
				int nc = Integer.MAX_VALUE;

				for (int i = 0; i < tmp.size(); i++) {
					if (tmp.get(i)[0] < nr) {
						nr = tmp.get(i)[0];
						nc = tmp.get(i)[1];
					} else if (tmp.get(i)[0] == nr) {
						if (tmp.get(i)[1] < nc) {
							nr = tmp.get(i)[0];
							nc = tmp.get(i)[1];
						}
					}
				}

				// 새로 뽑음
				matrix[nr][nc] = 0;
				sharkEat++;

				if (sharkEat == sharkSize) {
					sharkEat = 0;
					sharkSize++;
				}
				// 먹고나서는 현재 위치에서 다시 시작

				time += tmpTime;

				bfs(nr, nc);
				return;

			} // 가능한 이동 완료
		}

	}

}