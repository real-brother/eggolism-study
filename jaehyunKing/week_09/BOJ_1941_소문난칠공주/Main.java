package BOJ_1941_소문난칠공주;

/*
 *  N이 작아서 조합으로 7자리를 고른 후
 *  하나로 연결되어있는 지 확인
 *  약간 게리맨더링 느낌
 */

import java.util.Scanner;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] graph;
	static boolean[][] visited;
	static boolean[][] new_visited;
	static int result, count;
	static boolean flag;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		graph = new char[5][5];
		
		for(int r = 0; r < 5; r++) {
			String S = sc.next();
			for(int c = 0; c < 5; c++) {
				graph[r][c] = S.charAt(c);
			}
		}
		
		visited = new boolean[5][5];
		
		combination(0, 0, 0, 0);
		
		System.out.println(result);

	}
	
	static void combination(int nowY, int nowX, int countS, int countY) {
		
		// Y가 3개 이하여야한다
		if(countY > 3) return;
		
		// 7개를 고르면 dfs로 연결 여부 확인
		if(countS + countY == 7) {
			new_visited = new boolean[5][5];
			for(int r = 0; r < 5; r++) {
				for(int c = 0; c < 5; c++) {
					if(visited[r][c]) {
						flag = false;
						count = 0;
						dfs(r, c);
						if(flag) result++;
						return;
					}
				}
			}
		}
		
		// 위치 조정
		if(nowX == 5) {
			nowY++;
			nowX = 0;
			if(nowY == 5) return;
		}
		
		visited[nowY][nowX] = true;
		if(graph[nowY][nowX] == 'S') combination(nowY, nowX+1, countS+1, countY);
		else combination(nowY, nowX+1, countS, countY+1);
		
		visited[nowY][nowX] = false;
		combination(nowY, nowX+1, countS, countY);
	}
	
	// dfs를 할 때 조합으로 고른 자리만 확인하면 됨(visited = true)
	// new_visited로 무한루프를 막음
	static void dfs(int nowY, int nowX) {
		count++;
		if(count == 7) flag = true;
		new_visited[nowY][nowX] = true;
		for(int i = 0; i < 4; i++) {
			int moveY = nowY + dr[i];
			int moveX = nowX + dc[i];
			if(0 <= moveY && moveY < 5 && 0 <= moveX && moveX < 5) {
				if(visited[moveY][moveX] && !new_visited[moveY][moveX]) dfs(moveY, moveX);
			}
		}
	}

}