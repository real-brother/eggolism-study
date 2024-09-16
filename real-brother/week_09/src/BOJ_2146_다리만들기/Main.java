package BOJ_2146_다리만들기;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int[][] map;
	static int[][] testMap;
	static int landCount = 0;
	static boolean[][] visited;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };
	static int shortestBridge = Integer.MAX_VALUE;

	public static void main(String[] args) {
		n = sc.nextInt();
		map = new int[n][n];
		testMap = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 섬 초기화 해주기 - 각 섬로 숫자 붙여주기
		landinit();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0)	{
					// 섬이라면 -> 주변 탐색 실시
					// 탐색 완료 이휴 다리가 가능한 최솟값구하기
					testMap = new int[n][n];
					findBridge(i, j);
					int islandNum = map[i][j];
					findMinValue(islandNum);
				}
			}
		}
		System.out.println(shortestBridge);
	}	

	

	private static void findBridge(int i, int j) {
		visited = new boolean[n][n];
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {i, j});
		visited[i][j] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowR = now[0];
			int nowC = now[1];
			int nowDis = testMap[nowR][nowC];
			
			for (int a = 0; a < 4; a++) {
				int nr = nowR + dr[a];
				int nc = nowC + dc[a];
				
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				if (map[nr][nc] == 0 && !visited[nr][nc]) {
					queue.add(new int[] {nr, nc});
					testMap[nr][nc] = nowDis + 1;
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	private static void findMinValue(int islandNum) {
		int minValue = 10000;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (testMap[i][j] == 0) continue;
				for (int a = 0; a < 4; a++) {
					int nr = i + dr[a];
					int nc = j + dc[a];
					
					if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					if (map[nr][nc] != islandNum && testMap[nr][nc] == 0) {
						if (minValue > testMap[i][j]) {
							minValue = testMap[i][j];
						}
					}
				}
			}
		}
		if (shortestBridge > minValue) shortestBridge = minValue;
	}

	private static void landinit() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					landCount++;
					dfsLand(i, j);
				}
			}
		}
	}

	private static void dfsLand(int i, int j) {
		visited[i][j] = true;
		map[i][j] = landCount;
		
		for (int a = 0; a < 4; a++) {
			int nr = i + dr[a];
			int nc = j + dc[a];
			
			if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			if (map[nr][nc] == 1 && !visited[nr][nc]) {
				dfsLand(nr, nc);
			}
		}
	}
}
