package BOJ_17135_캐슬디펜스;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n, m, d;
	static int[][] originGameMap;
	static int[][] testGameMap;
	static int maxKill = 0;
	static boolean[] archerVisited;
	static int killCount = 0;
	static PriorityQueue<Enemy> pq;
	static List<Enemy> enemyList = new ArrayList<>();

	public static void main(String[] args) {
		n = sc.nextInt();
		m = sc.nextInt();
		d = sc.nextInt();
		originGameMap = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				originGameMap[i][j] = sc.nextInt();
				
				if (originGameMap[i][j] == 1) {
        			enemyList.add(new Enemy(i, j, 0));
        		}
			}
		}

		archerVisited = new boolean[m];
		locateArcher(0, 0);
		System.out.println(maxKill);
	}

	private static void locateArcher(int start, int depth) {
		if (depth == 3) {
			// 궁수 자리 조합 3개 구해서 -> test배열 복사하고 -> 게임 돌려보기
			testGameMap = new int[n][m];
			for (int i = 0; i < n; i++) {
				testGameMap[i] = originGameMap[i].clone();
			}
			simulateGame();
			maxKill = Math.max(maxKill, killCount);
			return;
		}

		for (int i = start; i < m; i++) {
			if (!archerVisited[i]) {
				archerVisited[i] = true;
				locateArcher(start, depth + 1);
				archerVisited[i] = false;
			}
		}
	}

	private static void simulateGame() {
		killCount = 0;
		
		List<Enemy> copyEnemyList = new ArrayList<>();
		for (int i = 0; i < enemyList.size(); i++) {
			int r = enemyList.get(i).r;
			int c = enemyList.get(i).c;
			
			copyEnemyList.add(new Enemy(r, c, 0));
		}
		
		while (true) {
			// 남아 있는 적이 없다면 게임 끝
			if (copyEnemyList.isEmpty()) break;
			
			// 궁수들이 동시에 공격할 수 있으므로 궁수가 선택했는지 저장하는 리스트
			List<Enemy> selectedEnemyList = new ArrayList<>();
			
			for (int i = 0; i < m; i++) {
				// 각 궁수 자리별로 공격
				if (archerVisited[i]) {
					// 우선 순위 체크 - 가까우면서, 왼쪽에 있는 적
					pq = new PriorityQueue<>(new Comparator<Enemy>() {
						@Override
						public int compare(Enemy o1, Enemy o2) {
							if (o1.dist == o2.dist) {
								return o1.c - o2.c;
							}
							return o1.dist - o2.dist;
						}
					});
					
					// 범위 안에 있는 적 위치 추가
					for (int j = 0; j < copyEnemyList.size(); j++) {
						Enemy enemy = copyEnemyList.get(j);
						int enemy_dist = Math.abs(n - enemy.r) + Math.abs(i - enemy.c);
						if (enemy_dist <= d) {
							pq.add(new Enemy(enemy.r, enemy.c, enemy_dist));
						}
					}
					
					// 공격범위 안에 남아있는 적이 있다면
					if (!pq.isEmpty()) {
					    boolean flag = false;
					    // 우선순위에 따라 가장 가까운 적을 선택하여 꺼냄
					    Enemy poll = pq.poll();
					    // 선택한 적이 이미 selectedEnemyList에 있는지 확인
					    for (int j = 0; j < selectedEnemyList.size(); j++) {
					        Enemy f = selectedEnemyList.get(j);
					        // 이미 선택한 적과 동일한 위치의 적이 있다면
					        if (poll.r == f.r && poll.c == f.c) {
					            flag = true;
					        }
					    }
					    
					    // 선택한 적이 selectedEnemyList에 없으면 추가
					    if (!flag) selectedEnemyList.add(new Enemy(poll.r, poll.c, 0));
					}

				}
			}
			
			// 선택한 적들을 제거하기
			for (int j = 0; j < selectedEnemyList.size(); j++) {
				Enemy select = selectedEnemyList.get(j);
				
				for (int k = copyEnemyList.size() - 1; k >= 0; k--) {
					Enemy f = copyEnemyList.get(k);
					
					if (f.r == select.r && f.c == select.c) {
						copyEnemyList.remove(k);
						killCount++;
					}
				}
			}
			
			// 적 한줄 아래로 움직이기
			for (int j = copyEnemyList.size() - 1; j >= 0; j--) {
				copyEnemyList.get(j).r += 1;
				
				// 적의 위치가 궁수의 줄까지 내려왔을 경우 -> 제거
				if (copyEnemyList.get(j).r == n) copyEnemyList.remove(j);
			}
		}
	}

	// 적 클래스
	static class Enemy {
		int r;    
		int c;
		int dist;
		
		public Enemy(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
}
