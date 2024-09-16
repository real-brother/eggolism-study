package BOJ_15685_드래곤커브;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] visited;

	public static void main(String[] args) {
		n = sc.nextInt();
		visited = new boolean[201][201];
		int answer = 0;
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int direction = sc.nextInt();
			int gen = sc.nextInt();

			generateGragonCurve(x, y, direction, gen);
		}

		// 모든 점 완탐하면서 정사각형 찾기
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				if (visited[i][j] && visited[i + 1][j] && visited[i][j + 1] && visited[i + 1][j + 1]) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}

	private static void generateGragonCurve(int x, int y, int direction, int gen) {
		int[] dragonR = { 1, 0, -1, 0 };
		int[] dragonC = { 0, -1, 0, 1 };

		if (x >= 0 && x <= 100 && y >= 0 && y <= 100) {
			visited[x][y] = true;
		}

		// 출발 방향과 세대수를 통해 -> 드래곤커브의 모든 방향을 얻어오기
		List<Integer> directionList = getDirections(direction, gen);
		int nextR = x;
		int nextC = y;

		// 있는 방향에 따라서 좌표 true처리 해주기
		for (Integer dir : directionList) {
			nextR += dragonR[dir];
			nextC += dragonC[dir];
			if (nextR >= 0 && nextR <= 100 && nextC >= 0 && nextR <= 100) {
				visited[nextR][nextC] = true;
			}
		}
	}

	public static List<Integer> getDirections(int dir, int gen) {
		List<Integer> directions = new ArrayList<>();
		directions.add(dir);

		while (gen-- > 0) {
			// 이전까지의 드래곤커브 방향을 역으로 바꿔서 그대로 뒤에 붙여주기
			for (int i = directions.size() - 1; i >= 0; i--) {
				int direction = (directions.get(i) + 1) % 4;
				directions.add(direction);
			}
		}
		return directions;
	}
}