package BOJ_16236_아기상어;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int[][] room;
	static int[][] eatableRoom;
	static int[][] roomDistance;
	static int time;
	static int babySharkSize = 2;
	static int babySharkStomach = 0;
	static int nowR;
	static int nowC;
	static List<int[]> candiLoc = new ArrayList<>();
	static int[] dr = {-1, 0, 1,0};
	static int[] dc = {0, 1, 0,-1};

	public static void main(String[] args) {
		n = sc.nextInt();
		room = new int[n][n]; 
		eatableRoom = new int[n][n];
		candiLoc = new ArrayList<>();
		time = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				room[i][j] = sc.nextInt();
				if (room[i][j] == 9) {
					nowR = i;
					nowC = j;
				}
			}
		}
		// 상어위치 알았으면 0으로 바꿔주기해야함 - 안하면 틀림~
		room[nowR][nowC] = 0;
		
		// 먹을 수 있는 물고기가 있을 때까지 while
		while (isThereEatableFish()) {
			// 가장 가까운 거리 구하기
			int minDistance = findMinValueWithoutZero(eatableRoom);
			candiLoc = new ArrayList<>();
			// 가장 가까운 거리에 있는 위치들 찾기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (eatableRoom[i][j] == minDistance) {
						candiLoc.add(new int[] { i, j });
					}
				}
			}
			// 갈수 있는 곳이 없다면 break 해야함 
			if (minDistance == 0) break;

			// 가까운 위치에 따라서 하나를 고르기 or while문 중단하기
			if (candiLoc.size() == 1) { // 먹을 수 있는 물고기가 1마리라면
				int[] fishLocation = candiLoc.get(0);
				eatFish(fishLocation[0], fishLocation[1], minDistance);
			} else if (candiLoc.size() == 0) {
				break;
			} else {
				// 거리가 가장 가까운 물고기를 선택하기 위해 먼저 정렬
				candiLoc.sort((a, b) -> {
					if (a[0] != b[0]) // 행 기준으로 비교
						return Integer.compare(a[0], b[0]);
					else // 행이 같으면 열 기준으로 비교
						return Integer.compare(a[1], b[1]);
				});
				// 가장 위에 있는 물고기, 그리고 가장 왼쪽에 있는 물고기 선택
				int[] fishLocation = candiLoc.get(0);
				eatFish(fishLocation[0], fishLocation[1], minDistance);
			}
		}

		System.out.println(time);
	}

	// 해당 위치의 물고기를 냠냠한다 -> 상어의 뱃속, 크기도 체크한다
	private static void eatFish(int i, int j, int distance) {
		room[i][j] = 0;
		babySharkStomach++;
		time += distance;
		
		nowR = i;
		nowC = j;

		if (babySharkSize == babySharkStomach) {
			babySharkSize++;
			babySharkStomach = 0;
		}
		
//		System.out.printf("%d초 (%d %d 냠) -> 상어 크기: %d, 뱃속: %d\n",time, i, j, babySharkSize, babySharkStomach);

	}

	// 먹을 수 있는 물고기를 찾고, 그 물고기까지의 거리를 동시에 저장
	private static boolean isThereEatableFish() {
		eatableRoom = new int[n][n];
		roomDistance = new int[n][n];
		int eatableCount = 0;
		
		// 갈 수 있는 곳에 거리를 구해두기 - 자기보다 레벨이 높은 친구들은 못지나가기 때
		bfsSettingRoomDistance();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (room[i][j] < babySharkSize && room[i][j] > 0) {
					// 먹을 수 있다면 -> 해당 물고기까지의 거리 저장하기
					eatableRoom[i][j] = roomDistance[i][j];
					eatableCount++;
				}
			}
		}

		// 먹을 수 있는 물고기가 없으면 어머니를 부르자
		if (eatableCount > 0)
			return true;
		else
			return false;
	}
	
	// bfs를 통해 상어가 갈 수 있는 거리 구해두
	private static void bfsSettingRoomDistance() {
		boolean[][] visited = new boolean[n][n];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {nowR, nowC});
		
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int now_r = now[0];
			int now_c = now[1];
			int nowDis = roomDistance[now_r][now_c];
			visited[now_r][now_c] = true;
			
			for (int i = 0; i < 4; i++) {
				int nr = now_r + dr[i];
				int nc = now_c + dc[i];
				
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				
				if (room[nr][nc] <= babySharkSize && !visited[nr][nc]) {
					roomDistance[nr][nc] = nowDis+1;
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
		
		
		
	}

	// 0이 아닌 것들 중에서 최솟값
	public static int findMinValueWithoutZero(int[][] array) {
		return Arrays.stream(array).flatMapToInt(Arrays::stream).filter(x -> x != 0).min().orElse(0);
	}
}