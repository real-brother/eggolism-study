package BOJ_1987_알파벳;

/*
 *  겹치는 알파벳 없이 최대 몇 칸을 갈 수 있는지 확인하는 문제
 *  dfs를 사용하고
 *  알파벳을 아스키코드 (A = 65)로 바꾸어서 visitedAlpha(사용 여부)를 체크해준다.
 */

import java.util.Scanner;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] graph;
	static int R, C, max;
	static boolean[] visitedAlpha;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		
		graph =  new char[R][C];
		for(int r = 0; r < R; r++) {
			String S = sc.next();
			for(int c = 0; c < C; c++) {
				graph[r][c] = S.charAt(c);
			}
		}
		
		max = 0;
		visitedAlpha = new boolean[26];
		visitedAlpha[graph[0][0]-65] = true;
		dfs(0, 0, 1);
		
		System.out.println(max);
		
	}
	
	static void dfs(int nowY, int nowX, int count) {
		if(max < count) max = count;
		
		for(int i = 0; i < 4; i++) {
			int moveY = nowY + dr[i];
			int moveX = nowX + dc[i];
			if(0 <= moveY && moveY < R && 0 <= moveX && moveX < C) {
				if(!visitedAlpha[graph[moveY][moveX]-65]) {
					visitedAlpha[graph[moveY][moveX]-65] = true;
					dfs(moveY, moveX, count+1);
					visitedAlpha[graph[moveY][moveX]-65] = false;
				}
			}
		}
		
	}

}