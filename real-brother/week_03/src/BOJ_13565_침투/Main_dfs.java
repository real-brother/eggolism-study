package BOJ_13565_침투;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_dfs {
	static int n, m;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static List<List<Integer>> cell;
	static Queue<int[]> queue = new ArrayDeque<int[]>();;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		sc.nextLine();
		visited = new boolean[n][m];
		cell = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			List<Integer> tempCell = new ArrayList<>();
			String text = sc.nextLine();
			for (int j = 0; j < m; j++) {
				int tempNum = Integer.parseInt(Character.toString(text.charAt(j)));
				tempCell.add(tempNum);
			}

			cell.add(tempCell);
		}

		for (int c = 0; c < m; c++) {
			visited = new boolean[n][m];
			List<List<Integer>> testCell = new ArrayList<>();
			testCell.addAll(cell);
			if (testCell.get(0).get(c) == 0) {
				cell.get(0).set(c, 2);
				dfs(0, c);

			}
		}

		boolean flag = false;
		for (int i = 0; i < m; i++) {
			if (cell.get(n - 1).get(i) == 2) {
				flag = true;
				break;
			}
		}

		System.out.printf("%s\n", flag ? "YES" : "NO");

	}

	// bfs로 연결되는 섬 모두 찾기
	static void dfs(int x, int y) {
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny])
				continue;

			if (cell.get(nx).get(ny) == 0) {
				cell.get(nx).set(ny, 2);
				dfs(nx, ny);
			}
			visited[nx][ny] = true;
		}

	}
}