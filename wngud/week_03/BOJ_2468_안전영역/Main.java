import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[][] graph;
	static int[][] visited;

	static int M;
	static int water;

	static void bfs(int y, int x) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { y, x });
		visited[y][x] = 1;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int m = 0; m < 4; m++) {
				int nx = now[1] + dx[m];
				int ny = now[0] + dy[m];

				if (nx >= 0 && ny >= 0 && nx < M && ny < M && visited[ny][nx] != 1) {
					if (graph[ny][nx] > water) {
						queue.add(new int[] { ny, nx });
						visited[ny][nx] = 1;
					}
				}

			}
		} // while 끝

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		M = sc.nextInt();

		graph = new int[M][M];
		visited = new int[M][M];

		// 1. 그래프 채우기
		int max = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				int num = sc.nextInt();
				graph[i][j] = num;

				max = max < num ? num : max;
			}
		}

		// 2. 그래프 순환하기
		// 높이는 1부터 시작
		// 높이 max까지 순환
		ArrayList<Integer> answers = new ArrayList<>();
		water = 4; // 변경 필요


		for (int w = 1; w <= max; w++) {
			water = w;
			int count = 0;
			visited = new int[M][M];

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j] != 1 && graph[i][j] > water) {
						bfs(i, j);
						count++;
					}

				}
			} // 순회 끝
			answers.add(count);
		}

		// 3. 정답 출력하기
		int answer = 1;
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i) > answer) {
				answer = answers.get(i);
			}
		}
		

			System.out.println(answer);
	


	}
}
