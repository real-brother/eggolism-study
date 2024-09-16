package BOJ_4179_불;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int r;
	static int c;
	static int escapeTime = Integer.MAX_VALUE;
	static char[][] maze;
	static int[][] jhmaze;
	static int[][] firemaze;
	static boolean[][] visited;
	static int[] jhStart;
	// 불이 여러개일 수 있음
	static List<int[]> fireStart;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	

	public static void main(String[] args) {
		r = sc.nextInt();
		c = sc.nextInt();
        sc.nextLine();
		maze = new char[r][c];
		jhmaze = new int[r][c];
		firemaze = new int[r][c];
		fireStart = new ArrayList<int[]>();
		for (int i = 0; i < r; i++) {
			String temp = sc.nextLine();
			for (int j = 0; j < c; j++) {
				maze[i][j] = temp.charAt(j);
				if (maze[i][j] == 'J') 	jhStart = new int[] {i, j};
				if (maze[i][j] == 'F')  fireStart.add(new int[] {i, j});
			}
		}
		
		// 1칸짜리 케이스일 경우
		if (r == 1 && c == 1) {
			System.out.println(1);
			return;
		}
		
		// 불, 지훈이 퍼져나가는 2개의 2차원 배열을 각각 시뮬레이션하기
		if (fireStart.size() > 0) {
			fireMazeSimulation();
		}
		
		jhMazeSimulation();
		
		// 모든 모서리를 보면서 지훈이 값이 불보다 작은 값이 있다면 탈출 성공
		findAllEdges();
		System.out.println(escapeTime == Integer.MAX_VALUE ? "IMPOSSIBLE" : escapeTime);
		
	}
	
	
	
	private static void findAllEdges() {
		// 첫 줄
		for (int i = 0; i < c; i++) {
			int jh = jhmaze[0][i];
			int fire = firemaze[0][i];
			if (jh > 0 && fire >= 0) {
				if (jh <= fire || fire == 0) {
					if (escapeTime > jh) escapeTime = jh;
				}
			}
		}
		
		// 마지막 줄
		for (int i = 0; i < c; i++) {
			int jh = jhmaze[r-1][i];
			int fire = firemaze[r-1][i];
			if (jh > 0 && fire >= 0) {
				if (jh <= fire || fire == 0) {
					if (escapeTime > jh) escapeTime = jh;
				}
			}
		}
		// 첫 열
		for (int i = 0; i < r; i++) {
			int jh = jhmaze[i][0];
			int fire = firemaze[i][0];
			if (jh > 0 && fire >= 0) {
				if (jh <= fire || fire == 0) {
					if (escapeTime > jh) escapeTime = jh;
				}
			}
		}
		
		// 마지막 열
		for (int i = 0; i < r; i++) {
			int jh = jhmaze[i][c-1];
			int fire = firemaze[i][c-1];
			if (jh > 0 && fire >= 0) {
				if (jh <= fire || fire == 0) {
					if (escapeTime > jh) escapeTime = jh;
				}
			}
		}
	}

	private static void fireMazeSimulation() {
		visited = new boolean[r][c];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		for (int[] fs : fireStart) {
			queue.add(fs);
		}
		int[] startFireLoc = queue.peek();
		firemaze[startFireLoc[0]][startFireLoc[1]] = 0;
		visited[startFireLoc[0]][startFireLoc[1]] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowTime = firemaze[now[0]][now[1]];
			for (int i = 0; i < 4; i++) {
				int nowR = now[0] + dr[i];
				int nowC = now[1] + dc[i];
				
				if (nowR < 0 || nowR >= r || nowC < 0 || nowC >= c) continue;
				
				if ((maze[nowR][nowC] == '.' || maze[nowR][nowC] == 'J' ) && !visited[nowR][nowC]) {
					visited[nowR][nowC]= true;
					firemaze[nowR][nowC] = nowTime + 1;
					queue.add(new int[] {nowR, nowC});
				}
			}
		}
	}
	
	private static void jhMazeSimulation() {
		visited = new boolean[r][c];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(jhStart);
		jhmaze[jhStart[0]][jhStart[1]] = 1;
		visited[jhStart[0]][jhStart[1]] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowTime = jhmaze[now[0]][now[1]];
			for (int i = 0; i < 4; i++) {
				int nowR = now[0] + dr[i];
				int nowC = now[1] + dc[i];
				
				if (nowR < 0 || nowR >= r || nowC < 0 || nowC >= c) continue;
				
				if ((maze[nowR][nowC] == '.') && !visited[nowR][nowC]) {
					visited[nowR][nowC]= true;
					jhmaze[nowR][nowC] = nowTime + 1;
					queue.add(new int[] {nowR, nowC});
				}
			}
		}
	}
}
