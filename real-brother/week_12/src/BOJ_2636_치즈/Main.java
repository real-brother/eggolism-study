package BOJ_2636_치즈;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m;
    static List<Integer> countCheeseList = new ArrayList<Integer>();
    static int[][] cheeseMap;
    static boolean[][] visited;
    static int nowTime = 0;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {
    	n = sc.nextInt();
    	m = sc.nextInt();
    	cheeseMap = new int[n][m];
    	visited = new boolean[n][m];
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cheeseMap[i][j] = sc.nextInt();
			}
		}
    	// 치즈가 사라질때까지 반복
    	while (isCheeseExist()) {
    		nowTime++;

    		// 조각 개수 저장하기
    		int nowPiece = countCheese();
    		countCheeseList.add(nowPiece);
    		
    		// 치즈내부 구멍을 찾기
    		for (int i = 0; i < n; i++) {
    			for (int j = 0; j < m; j++) {
    				// 치즈 가장 자리의 공기라면 -> 해당 공기가 치즈 구멍인지 확인하기
    				if (cheeseMap[i][j] == 0) {
    					for (int l = 0; l < 4; l++) {
							int nowR = i + dr[l];
							int nowC = j + dc[l];
							if (nowR < 0 || nowR >= n || nowC < 0 || nowC >= m) continue;
							// 가장자리의 공기라면
							if (cheeseMap[nowR][nowC] == 1) {
								boolean isCheeseHole = findCheeseHole(nowR, nowC);
								// 치즈 구멍이라면 모두 2로 바꿔주기
								if (isCheeseHole) {
									settingCheeseHole(nowR, nowC);
								}
							}
						}
    				}
    			}
    		}
    		
    		// 전체 내부를 돌면서 녹을 치즈 index 저장 (동시성문 제)
    		// 0과 닿는 모든 1을 0으로 바꾸기
    		List<int[]> meltList = new ArrayList<int[]>();
    		for (int i = 0; i < n; i++) {
    			for (int j = 0; j < m; j++) {
    				if (cheeseMap[i][j] == 1) {
    					next: for (int l = 0; l < 4; l++) {
							int nowR = i + dr[l];
							int nowC = j + dc[l];
							// 가장자리의 공기라면 치즈 녹이기
							if (cheeseMap[nowR][nowC] == 0) {
								meltList.add(new int[] {i, j});
								continue next;
							}
						}
    				}
    			}
    		}
    		// 치즈 녹이기
    		for (int[] meltNow : meltList) {
    			cheeseMap[meltNow[0]][meltNow[1]] = 0;
    		}
    		
    		// 2로 되어있는 모든 것들을 0으로 되돌리기
    		for (int i = 0; i < n; i++) {
    			for (int j = 0; j < m; j++) {
    				if (cheeseMap[i][j] == 2) {
						cheeseMap[i][j] = 0;
    				}
    			}
    		} // Next While
    	}
    	
    	System.out.println(nowTime); // 치즈가 녹아서 없어지는 시간
		System.out.println(countCheeseList.get(countCheeseList.size()-1)); // 모두 녹기 1시간 전에 남아있는 조각
    }
    
    // 치즈 구멍인지 찾기 - bfs
	private static boolean findCheeseHole(int nowR, int nowC) {
    	visited = new boolean[n][m];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {nowR, nowC});
		visited[nowR][nowC] = true;
		
		while (!queue.isEmpty()) {
			int[] setNow = queue.poll();
			int setNowR = setNow[0];
			int setNowC = setNow[1];
			for (int i = 0; i < 4; i++) {
				int nr = setNowR + dr[i];
				int nc = setNowC + dc[i];
				// 범위 밖으로 나간다면 치즈 구멍이 아님 = 치즈 바깥 부분임
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) return false;
				if (!visited[nr][nc] && cheeseMap[nr][nc] == 0) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
		// 치즈 구멍이라면 전부 다 돌 것임
		return true;
	}

	// 치즈 구멍으로 모두 2로 세팅해주기 - bfs
	private static void settingCheeseHole(int nowR, int nowC) {
    	visited = new boolean[n][m];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {nowR, nowC});
		visited[nowR][nowC] = true;
		
		while (!queue.isEmpty()) {
			int[] setNow = queue.poll();
			int setNowR = setNow[0];
			int setNowC = setNow[1];
			for (int i = 0; i < 4; i++) {
				int nr = setNowR + dr[i];
				int nc = setNowC + dc[i];
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				if (!visited[nr][nc] && cheeseMap[nr][nc] == 0) {
					cheeseMap[nr][nc] = 2;
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	// 치즈 개수 세는 함수
	public static int countCheese() {
    	int count = 0;
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (cheeseMap[i][j] == 1) count++;
			}
		}
    	return count;
    }
	
    // 치즈가 단 하나라도 존재하는지? - while문 조건
    public static boolean isCheeseExist() {
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (cheeseMap[i][j] == 1) return true;
			}
		}
    	return false;
    }
}
