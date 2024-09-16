public class Main {

	/*
	 * 설계방식 
	 * 1. 부분집합으로 vistied를 만든다. 
	 * 2. 원본 visited를 복사한 두 번째 vitied는 0인 경우에만 연결되는지 확인한다. (1이 벽)
	 * 3. 원본 visited를 복사한 첫 번째 visited는 1인 경우에만 가도록 한다. (0이 벽)
	 * 4. 전부 다 돌았는지 평가 
	 * - 두 방문의 합을 비교하여 전체 순환을 평가한다. -> 두 번 다 돌아야 하기 때문에 비효율 
	 * - N이 10개 이하밖에 안되니까, visited가 전부 0 또는 1이 되었는지만 판단 -> 백트래킹 역할
	 */
	
	/*예외 처리
	 * 1. 전부 다 한쪽 팀이 된 경우
	 * 2. 한칸 더 크게 만들기에 오류가 많음
	 */

	static ArrayList<ArrayList<Integer>> graph;
	static int[] population;

	static int[] visited;

	static int n;
	static int total, count1, count2; // 초기화 조심
	static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		// 인구 입력 받기
		population = new int[n + 1];
		total = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());
			population[i] = num;
			total += num;
		}

		// 그래프 입력 받기
		graph = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 1; i <= n; i++) {
			String[] inputs = br.readLine().split(" ");
			for (int j = 1; j < inputs.length; j++) {
				int num = Integer.parseInt(inputs[j]);
				graph.get(i).add(num);
			}
		}

		// BFS 돌리기
		answer = Integer.MAX_VALUE;
		visited = new int[n + 1];
		comb(1);

		// 정답 출력하기
		// 아무 변화가 없으면 -1이다.
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}


	}

	public static void comb(int idx) {
		
		// 부분집합 만들기가 끝난 경우
		if (idx == n+1) {
		
			count1 = 0;
			count2 = 0;
			
			// 첫번째 bfs 돌리기
			boolean check1 = bfs(1, 0);
			if (!check1) return;
			// 두번째 bfs 돌리기
			boolean check2 = bfs(0, 1);
			if (!check2) return;
			
	
			// 토탈이 둘 중에 하나로 몰려있으면, 한 팀이 전부 다 돈 것이니 제외시켜야 함
			if (total == count1 || total == count2) return;
			
			// 기존 answer 보다 작다면 최신화
			answer = Math.min(answer, Math.abs(count1 - count2));

			return;
		}
		
		// comb만들기	
		if (visited[idx] != 1) {
			visited[idx] = 1;
			comb(idx+1);
			visited[idx] = 0;
			comb(idx+1);
		}

	}

	// 방문한 숫자와 방문하지 않는 숫자 제공
	public static boolean bfs(int vi, int notVi) {

		// 새로운 visited를 만든다.
		// 만들어진 visited를 손상시키면 안된다.
		int[] visited2 = Arrays.copyOf(visited, visited.length);

		// BFS를 위한 큐
		Queue<Integer> queue = new LinkedList<>();

		// 한 곳에서만 돌게 만들어서 전부다 순환이 되는지 확인
		for (int i = 1; i <= n; i++) {
			if (visited2[i] == notVi) {

				// 한번도 방문하지 않는 곳부터 시작
				queue.add(i);
				visited2[i] = vi;
				if (vi == 1) count1 += population[i]; // 점수 추가
				else if (vi == 0) count2 += population[i]; // 점수 추가

				while (!queue.isEmpty()) {
					int cur = queue.poll();

					for (int j = 0; j < graph.get(cur).size(); j++) {
						int tmp = graph.get(cur).get(j);
						if (visited2[tmp] == notVi) {
							queue.add(tmp);

							visited2[tmp] = vi;
							if (vi == 1) count1 += population[tmp]; // 점수 추가
							else if (vi == 0) count2 += population[tmp]; // 점수 추가
						}
					}
				} // while 끝

				// 새로운 큐 하나만 하고 끝내기
				break;
			}
		}
	
		
		// 전체 순회 판별하기
		// 전부 0이거나 전부 1이면 방문에 성공한 것임
		int tmp = 0;
		for (int i = 1; i <= n; i++) {
			tmp += visited2[i];
		}
		
		if (tmp == n || tmp == 0) return true;
		else return false;
		
	}

}