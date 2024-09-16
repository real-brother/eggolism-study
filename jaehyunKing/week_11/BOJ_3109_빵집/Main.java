package BOJ_3109_빵집;

/*
 *  사실 분류를 보고 풀었다. 무조건 dp인줄 알고 분류 보면 dp겠지 하고 봤는데 그래프 탐색이길래
 *  처음에 냅다 bfs해서 그 열에 방문할 수 있는 개수 중 가장 작은 값이 답인 줄 알았다
 *  그러면 반례가 있어서 결국 dfs로 했다
 *  
 *  개인적인 이슈) 재귀함수에서 깔끔하게 return하는 법을 잘 모르겠다
 */

import java.util.Scanner;

public class Main {
	static int R, C, count;
	static int[][] graph;
	static int[] dr = {-1, 0, 1};
	static boolean makePipe;
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		
		graph = new int[R][C];
		for(int r = 0; r < R; r++) {
			String S = sc.next();
			for(int c = 0; c < C; c++) {
				if(S.charAt(c) == '.') graph[r][c] = 0;
				else graph[r][c] = 1;
			}
		}
		count = 0;
		for(int r = 0; r < R; r++) {
			makePipe = false;
			dfs(r, 0);
		}
		System.out.println(count);
	}
	
	static void dfs(int nowY, int nowX) {
		// 바로 밑의 graph[nowY][nowX] == 2가 visited대신인데
		// 연결 되었을 경우 리턴해서 엄한 데를 visited 처리하지 않게 해줌
		// 원상복구는 필요가 없는게 어차피 그 길로 가서 못 갔으면 다른 애들도 똑같이 못 감
		if(makePipe) return;
		
		graph[nowY][nowX] = 2;
		
		for(int i = 0; i < 3; i++) {
			int moveY = nowY + dr[i];
			int moveX = nowX + 1;
			
			if(moveX == C - 1) {
				count++;
				makePipe = true;
				return;
			}
			
			if(moveY < 0 || moveY >= R || moveX < 0 || moveX >= C) continue;
			
			if(graph[moveY][moveX] == 0) dfs(moveY, moveX);
		}
	}
}