package BOJ_17135_캐슬디펜스;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 *  15c3이어서 조합으로 하는게 맞다고 판단 
 *  -> 삼성 역량테스트 문제가 구현, bfs, dfs, 조합, 순열 정도 밖에 없는 거 같음 
 *  궁수의 위치를 조합으로 정하고 
 *  모든 궁수를 기준으로 bfs를 돌면서 공격할 적을 찾는다
 *  bfs를 전부 돌면 적을 처리한다
 *  이 떄 killCount로 궁수의 공격으로 제거한 적을 계산한다.
 *  이것을 최대 15c3(455)만큼 반복
 */

public class Main {
	static int N, M, D, enemyY, enemyX, enemyCount, enemyCnt, killCount, maxKillCount;
	static int[][] graph, copy;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 0}; // 상 좌 우
	static int[] dc = {0, -1, 1};
	static Queue<int[]> enemies;
	static boolean noEnemy;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		graph = new int[N+1][M];
		copy = new int[N+1][M];
		enemies = new LinkedList<>();
		
		// 적의 개수를 미리 세줌
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				graph[r][c] = sc.nextInt();
				if(graph[r][c] == 1) enemyCount++;
			}
		}
		maxKillCount = 0;
		
		combination(0, 0);
		
		System.out.println(maxKillCount);
	}
	
	static void combination(int idx, int count) {
		
		// 궁수 3명을 정하면
		if(count == 3) {
			makeCopy();
			killCount = 0;
			enemyCnt = enemyCount;
			
			// 적의 개수가 0이 될 때까지 반복, 2가 궁수
			while(enemyCnt != 0) {
				for(int i = 0; i < M; i++) if(copy[N][i] == 2) bfs(N, i);
				killEnemy();
				relocateEnemy();
			}
			if(maxKillCount < killCount) maxKillCount = killCount;
			return;
		}
		
		if(idx == M) return;
		
		graph[N][idx] = 2;
		combination(idx+1, count+1);
		graph[N][idx] = 0;
		
		combination(idx+1, count);
	}
	
	// 적을 찾는 bfs
	static void bfs(int nowY, int nowX) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {nowY, nowX, 0});
		visited = new boolean[N+1][M];
		visited[nowY][nowX] = true;
		int findTime = Integer.MAX_VALUE;
		enemyX = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			int num[] = queue.poll();
			for(int i = 0; i < 3; i++) {
				int moveY = num[0] + dr[i];
				int moveX = num[1] + dc[i];
				
				if(findTime < num[2] || D < num[2]) continue;
				
				if(copy[num[0]][num[1]] == 1 && enemyX > num[1]) {
					findTime = num[2];
					enemyY = num[0];
					enemyX = num[1];
					continue;
				}
				
				if(0 > moveY || moveY >= N || 0 > moveX || moveX >= M) continue;
				
				if(!visited[moveY][moveX]) {
					visited[moveY][moveX] = true;
					queue.offer(new int[] {moveY, moveX, num[2] + 1});
				}
				
			}
		}
		// 적을 찾았다면 죽일 적들을 모아두는 queue에 넣어둔다.(같은 적을 공격할 수 있어서)
		if(enemyX != Integer.MAX_VALUE) enemies.offer(new int[] {enemyY, enemyX});
	}
	
	// 큐에서 하나씩 적을 꺼내가면서 처리
	// killCount를 통해서 적을 몇 명 처리했는지 계산
	static void killEnemy() {
		while(!enemies.isEmpty()) {
			int[] enemy = enemies.poll();
			if(copy[enemy[0]][enemy[1]] != 0) {
				copy[enemy[0]][enemy[1]] = 0;
				enemyCnt--;
				killCount++;
			}
		}
	}
	
	// 적을 한 칸씩 밑으로 보냄
	static void relocateEnemy() {
		for(int r = N-1; r >= 0; r--) {
			for(int c = 0; c < M; c++) {
				if(copy[r][c] == 1) {
					copy[r][c] = 0;
					if(r+1 == N) enemyCnt--;
					else copy[r+1][c] = 1;
				}
			}
		}
	}
	
	// 원래 배열 유지를 위해 복사한 배열을 사용
	static void makeCopy() {
		for(int r = 0; r <= N; r++) {
			for(int c = 0; c < M; c++) {
				copy[r][c] = graph[r][c];
			}
		}
	}

}