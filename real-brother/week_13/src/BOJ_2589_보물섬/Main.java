package BOJ_2589_보물섬;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n, m, nowLength, maxTreasureLength = Integer.MIN_VALUE;
	static char[][] treasureMap;
	static int[][] testMap;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) {
		n = sc.nextInt();
		m = sc.nextInt();
		sc.nextLine();
		treasureMap = new char[n][m];
		testMap = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			char[] temp = sc.nextLine().toCharArray();
			for (int j = 0; j < m; j++) {
				treasureMap[i][j] = temp[j];
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (treasureMap[i][j] == 'L') {
					testMap = new int[n][m];
					visited = new boolean[n][m];
					bfs(i,j, 0);
					nowLength = getMaxLength();
					maxTreasureLength = Math.max(nowLength, maxTreasureLength);
				}
			}
		}
		System.out.println(maxTreasureLength);
	}

	private static int getMaxLength() {
		int maxValue = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				maxValue = Math.max(maxValue, testMap[i][j]);
			}
		}
		return maxValue;
	}

	private static void bfs(int i, int j, int length) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {i, j});
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowR = now[0];
			int nowC = now[1];
			int nowLength = testMap[nowR][nowC];
			
			for (int d = 0; d < 4; d++) {
				int nextR = nowR + dr[d];
				int nextC = nowC + dc[d];
				if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) continue;
				if (treasureMap[nextR][nextC] == 'W') continue;
				if (!visited[nextR][nextC]) {
					testMap[nextR][nextC] = nowLength + 1;
					visited[nextR][nextC] = true;
					queue.add(new int[] {nextR, nextC});
				}
			}
		}
	}
}