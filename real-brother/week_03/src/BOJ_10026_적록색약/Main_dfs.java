package BOJ_10026_적록색약;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_dfs {
	static int n;
	static int start_r = -1, start_c = -1;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int normalCount = 0;
	static int colorBlindCount = 0;
	static boolean[][] visited;
	static List<List<String>> normalPicture;
	static List<List<String>> colorBlindicture;

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
					dfs(r, c, normalPicture.get(r).get(c), normalPicture);
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
					dfs(r, c, colorBlindicture.get(r).get(c), colorBlindicture);
					colorBlindCount++;
				}
			}
		}

		System.out.printf("%d %d", normalCount, colorBlindCount);
	}

	// dfs로 연결되는 색 모두 찾기
	static void dfs(int x, int y, String findColor, List<List<String>> picture) {
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny])
				continue;

			if (picture.get(nx).get(ny).equals(findColor)) {
				dfs(nx, ny, findColor, picture);
			}
		}

	}
}