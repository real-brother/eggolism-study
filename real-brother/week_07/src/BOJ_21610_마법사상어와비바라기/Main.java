package BOJ_21610_마법사상어와비바라기;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int m;
	static int[][] arrayMap;
	static boolean[][] visited;
	static int[] drCloud = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dcCloud = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] drBug = {-1, -1, 1, 1};
	static int[] dcBug = {-1, 1, 1, -1};
	static Queue<int[]> cloudList;

	public static void main(String[] args) {
		n = sc.nextInt();
		m = sc.nextInt();
		arrayMap = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arrayMap[i][j] = sc.nextInt();
			}
		}
		
		cloudList = new ArrayDeque<int[]>();
		cloudList.add(new int[] {n-1, 0});
		cloudList.add(new int[] {n-1, 1});
		cloudList.add(new int[] {n-2, 0});
		cloudList.add(new int[] {n-2, 1});
		
		for (int i = 0; i < m; i++) {
			int direction = sc.nextInt();
			int dCount = sc.nextInt();
			visited = new boolean[n][n];
			
			moveCloud(direction - 1, dCount);
			itsRainingOutside();
			waterCopyBug();
			generateCloud();
		}
		
		int answer = calSumArray();
		System.out.println(answer);
	}
	
	private static int calSumArray() {
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result += arrayMap[i][j];
			}
		}
		
		return result;
	}

	// 물의 양이 2이상인 칸에 구름 생성
	private static void generateCloud() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arrayMap[i][j] >= 2 && !visited[i][j]) {
					arrayMap[i][j] -= 2;
					cloudList.add(new int[] {i, j});

//					System.out.printf("%d %d\n", i, j);
				}
			}
		}
	}

	// 물 복사 버그 구현
	private static void waterCopyBug() {
		int size = cloudList.size();
		for (int i = 0; i < size; i++) {
			int[] cloud = cloudList.poll();
			int cloudR = cloud[0];
			int cloudC = cloud[1];
			
			for (int j = 0; j < 4; j++) {
				int nr = cloudR + drBug[j];
				int nc = cloudC + dcBug[j];
				
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				
				//대각선에 물이 있다면
				if (arrayMap[nr][nc] > 0) {
					arrayMap[cloudR][cloudC]++;
				}
			}
		}
	}

	// 구름 위치에 비가 +1씩 옴
	private static void itsRainingOutside() {
		for (int i = 0; i < cloudList.size(); i++) {
			int[] cloud = cloudList.poll();
			int cloudR = cloud[0];
			int cloudC = cloud[1];
			
			arrayMap[cloudR][cloudC]++;
			visited[cloudR][cloudC] = true;
			cloudList.add(cloud);
		}
	}
	
	// 구름의 위치를 옮겨주기 - 위치 보정해주기
	private static void moveCloud(int direction, int dCount) {
		for (int i = 0; i < cloudList.size(); i++) {
			int[] cloud = cloudList.poll();
			int cloudR = cloud[0];
			int cloudC = cloud[1];
			cloudR += drCloud[direction] * dCount;
			cloudC += dcCloud[direction] * dCount;
			
			// n보다 큰 수가 더해지거나 빼질 수 있기 때문에 -> r, n이 범위 안에 있을때까지 연산해줘야함
			do {
				if (cloudR < 0) {cloudR += n;}
				else if (cloudR >= n) {cloudR -= n;}
				if (cloudC < 0) {cloudC += n;}
				else if (cloudC >= n) {cloudC -= n;}
			} while (cloudR < 0 || cloudR >= n || cloudC < 0 || cloudC >= n);
			
			cloudList.add(new int[] {cloudR, cloudC});
		}
	}
}