package BOJ_23288_주사위굴리기2;

/*
 *  문제에서 시키는대로 구현한다
 *  문제 읽다가 어지러워서 주사위 굴러가는 거부터 만들고 시작
 *  주사위 배열과 이동 방향을 그냥 만들어줬다
 *  메서드가 뭔가 많아져서 그냥 메서드를 많이 써보려고 해봤다
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, dir, nowY, nowX, count, sum;
	static int[][] graph;
	static int[][] dice;
	static int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dc = {0, 1, 0, -1};	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		int K = sc.nextInt();
		
		graph = new int[N][M];
		for(int r = 0; r < N; r++) for(int c = 0; c < M; c++) graph[r][c] = sc.nextInt();
		
		// 초기 세팅 메서드
		initSetting();
		
		// oneMove를 K번 실행
		while(K-->0) oneMove();
		
		System.out.println(sum);
	}
	
	static void oneMove() {
		// 다음 이동 칸이 범위 밖이면 방향 전환
		if(0 > nowY + dr[dir] || nowY + dr[dir] >= N || 0 > nowX + dc[dir] || nowX + dc[dir] >= M) dir = (dir + 2) % 4;
		// 주사위 굴리기 메서드
		rollDice();
		// 방향 변경 메서드
		selectDirection();
		// 현재 숫자와 같은 숫자 찾는 bfs
		bfs();
		// 점수 합산
		sum += graph[nowY][nowX] * count;
	}
	
	// 평범한 bfs
	static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		visited[nowY][nowX] = true;
		queue.offer(new int[] {nowY, nowX});
		count = 0;
		while(!queue.isEmpty()) {
			int[] num = queue.poll();
			
			if(graph[num[0]][num[1]] == graph[nowY][nowX]) count++;
			
			for(int i = 0; i < 4; i++) {
				int moveY = num[0] + dr[i];
				int moveX = num[1] + dc[i];
				if(0 <= moveY && moveY < N && 0 <= moveX && moveX < M) {
					if(!visited[moveY][moveX] && graph[moveY][moveX] == graph[nowY][nowX]) {
						visited[moveY][moveX] = true;
						queue.offer(new int[] {moveY, moveX});
					}
				}
			}
		}
	}
	
	// 초기 세팅
	static void initSetting() {
		dice = new int[4][3];
		dice[0][1] = 2;
		dice[1][0] = 4;
		dice[1][1] = 1;
		dice[1][2] = 3;
		dice[2][1] = 5;
		dice[3][1] = 6;	
		dir = 3;
		nowY = 0;
		nowX = 0;
		sum = 0;
	}
	
	// 방향 전환(시계, 반시계)
	static void selectDirection() {
		if(dice[3][1] > graph[nowY][nowX]) dir = (dir + 1) % 4;
		else if(dice[3][1] < graph[nowY][nowX]) dir = (dir + 3) % 4;
	}
	
	// 주사위를 굴린다 -> 방향에 따라 해당 메서드로 이동
	static void rollDice() {
		if(dir == 0) upDice();
		else if(dir == 1) rightDice();
		else if(dir == 2) downDice();
		else leftDice();
	}
	
	// 위로 구른다
	static void upDice() {
		int tmp = dice[0][1];
		for(int r = 0; r < 3; r++) {
			dice[r][1] = dice[r+1][1];
		}
		dice[3][1] = tmp;
		nowY--;
	}
	
	// 하
	static void downDice() {
		int tmp = dice[3][1];
		for(int r = 3; r >= 1; r--) {
			dice[r][1] = dice[r-1][1];
		}
		dice[0][1] = tmp;
		nowY++;
	}
	
	// 좌
	static void leftDice() {
		int tmp = dice[1][0];
		for(int c = 0; c < 2; c++) {
			dice[1][c] = dice[1][c+1];
		}
		dice[1][2] = dice[3][1];
		dice[3][1] = tmp;
		nowX--;
	}
	
	// 우
	static void rightDice() {
		int tmp = dice[1][2];
		for(int c = 2; c >= 1; c--) {
			dice[1][c] = dice[1][c-1];
		}
		dice[1][0] = dice[3][1];
		dice[3][1] = tmp;
		nowX++;
	}
	
}