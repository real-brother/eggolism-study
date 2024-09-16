package BOJ_10026_적록색약;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_bfs {
	static int n;
	static int start_r = -1, start_c = -1;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int normalCount = 0;
	static int colorBlindCount = 0;
	static boolean[][] visited;
	static List<List<String>> normalPicture;
	static List<List<String>> colorBlindicture;
	static Queue<int[]> queue = new ArrayDeque<int[]>();;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.nextLine();
		visited = new boolean[n][n];
		normalPicture = new ArrayList<>();
		colorBlindicture = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			List<String> tempNormalPicture = new ArrayList<>();
			List<String> tempBlindPicture = new ArrayList<>();
			String text = sc.nextLine();
			for (int j = 0; j < n; j++) {
				tempNormalPicture.add(Character.toString(text.charAt(j)));

				// 색약을 위해 초록색을 빨간색으로 넣기
				if (Character.toString(text.charAt(j)).equals("G")) {
					tempBlindPicture.add("R");
				} else {
					tempBlindPicture.add(Character.toString(text.charAt(j)));
				}
			}

			normalPicture.add(tempNormalPicture);
			colorBlindicture.add(tempBlindPicture);
		}

		visited = new boolean[n][n];
		// 모든 요소를 false로 초기화
		for (int i = 0; i < n; i++) {
		    for (int j = 0; j < n; j++) {
		        visited[i][j] = false;
		    }
		}

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (!visited[r][c]) {
					bfs(r, c, normalPicture.get(r).get(c), normalPicture);
					normalCount++;
				}
			}
		}

		visited = new boolean[n][n];
		// 모든 요소를 false로 초기화
		for (int i = 0; i < n; i++) {
		    for (int j = 0; j < n; j++) {
		        visited[i][j] = false;
		    }
		}

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (!visited[r][c]) {
					bfs(r, c, colorBlindicture.get(r).get(c), colorBlindicture);
					colorBlindCount++;
				}
			}
		}

		System.out.printf("%d %d", normalCount, colorBlindCount);
	}

	// bfs로 연결되는 색 모두 찾기
	static void bfs(int x, int y, String findColor, List<List<String>> picture) {
		queue.add(new int[] { x, y });
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			x = temp[0];
			y = temp[1];

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny])
					continue;

				if (picture.get(nx).get(ny).equals(findColor)) {
					queue.add(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}
	}
}