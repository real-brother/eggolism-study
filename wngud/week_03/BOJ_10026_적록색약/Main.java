import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static char[][] graph;
	static int[][] visited;

	static int M;

	static void bfs(int y, int x, char c) {
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
					if (graph[ny][nx] == c) {
						queue.add(new int[] { ny, nx });
						visited[ny][nx] = 1;
					}
				}

			}
		} // while 끝

	}
	
	static void bfs2(int y, int x) {
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
					if (graph[ny][nx] == 'R' || graph[ny][nx] == 'G') {
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
		sc.nextLine();

		graph = new char[M][M];
		visited = new int[M][M];

		// 1. 그래프 채우기
		for (int i = 0; i < M; i++) {
			String inputs = sc.nextLine();
			for (int j = 0; j < M; j++) {
				char input = inputs.charAt(j);
				graph[i][j] = input;
			}
		}

		// 2. 그래프 순환하기
		// R G B RG 순으로
		ArrayList<Integer> answers = new ArrayList<>();
		char[] list = new char[] { 'R', 'G', 'B' };

		for (char c : list) {
			int count = 0;
			visited = new int[M][M];

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j] != 1 && graph[i][j] == c) {
						bfs(i, j, c);
						count++;
					}
				}
			} // 한개 순회 끝
			answers.add(count);
		}
		
		// RG
		int count = 0;
		visited = new int[M][M];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] != 1 && (graph[i][j] == 'R' || graph[i][j] == 'G')) {
					bfs2(i, j);
					count++;
				}
			}
		} // 한개 순회 끝
		answers.add(count);

		// 3. 정답 출력하기		
		int answer1 = answers.get(0) + answers.get(1) + answers.get(2);  
		int answer2 = answers.get(2) + answers.get(3);
		
		System.out.println(answer1 + " " + answer2);
		
	}
}
