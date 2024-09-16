import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int Test = sc.nextInt();

		for (int t = 0; t < Test; t++) {
			

		int N = sc.nextInt();
		int K = sc.nextInt();

		// 걸리는 시간 (N)
		int[] time = new int[N + 1]; // 실제 숫자
		for (int i = 1; i <= N; i++) {
			time[i] = sc.nextInt();
		}
		
		// 순서 (K)
		List<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}

		int[] degree = new int[N + 1];
		int[] dp = new int[N + 1];

		for (int i = 0; i < K; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			graph.get(from).add(to);

			degree[to]++;
		}

		// 마지막 건물
		int target = sc.nextInt();

		// 위상 정렬
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				queue.add(i);
				dp[i] = time[i]; // DP에 초기값 추가
			}
		}

		// 큐 돌리기
		while (!queue.isEmpty()) {

			// 노드 선택 -> 진입 차수 0
			// 건물 완성 -> 이전 것과 비교
			int cur = queue.poll();

			// 주변 노드 탐색
			for (int i = 0; i < graph.get(cur).size(); i++) {
				int nr = graph.get(cur).get(i);
				degree[nr]--;

				// 앞의 것을 갱신!!
				dp[nr] = Math.max(dp[nr], dp[cur] + time[nr]);

				// 점검
				if (degree[nr] == 0) {
					queue.add(nr);
				}

			}

		}

		// 순서 출력
		System.out.println(dp[target]);

		}
		
	}
}
