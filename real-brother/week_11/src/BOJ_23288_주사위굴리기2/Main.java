package BOJ_23288_주사위굴리기2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, k;
    static int[][] map;
    //     2
    //  4  1(윗면) 3(동)
    //     5
    //     6(밑면)
    static int[] dice = {2, 4, 1, 3, 5, 6};
    static int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dc = {0, 1, 0, -1};
    static int direction = 1; // 처음에는 동쪽으로 움직임
    static int nowR = 0, nowC = 0;
    static int score = 0;
    
    public static void main(String[] args) {
    	n = sc.nextInt();
    	m = sc.nextInt();
    	k = sc.nextInt();
    	sc.nextLine();
    	map = new int[n][m];
    	
    	for (int i = 0; i < n; i++) {
			String[] tempString = sc.nextLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = tempString[j].charAt(0) - 48;
			}
		}
    	
    	int turn = 1;
    	while (turn <= k) {
    		moveDice();
    		int A = dice[5]; // 주사위 밑면
    		int B = map[nowR][nowC]; // 현재 지도에 있는 숫자
    		
    		if (A > B) {
    			direction++;
    			if (direction == 4) direction-=4;
    		}
    		else if (A < B) {
    			direction--;
    			if (direction == -1) direction+=4;
    		}
    		turn++;
    	}
    	System.out.println(score);
	}
    
    
    static void moveDice() {
		int nr = nowR + dr[direction];
		int nc = nowC + dc[direction];
		// 범위 밖이라면 방향 반대로해서 가기
		if(nr <0 || nr >= n || nc < 0 || nc >= m) {
			direction += 2;
			if (direction >= 4) direction -= 4;
			nr = nowR + dr[direction];
			nc = nowC + dc[direction];
			
		};
		rollDice(direction, nr, nc);
		nowR = nr; nowC = nc;
		// 점수 구하기 (BFS)
		getScore(nowR, nowC);
	}

	private static void getScore(int nowR, int nowC) {
		int scoreCount = 1;
		int originNum = map[nowR][nowC];
		boolean[][] visited = new boolean[n][m];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {nowR, nowC});
		visited[nowR][nowC] = true;
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nr = now[0];
			int nc = now[1];
			for (int i = 0; i < 4; i++) {
				int cr = nr + dr[i];
				int cc = nc + dc[i];
				if (cr < 0 || cr >= n || cc < 0 || cc >=m) continue;
				if (!visited[cr][cc] && map[cr][cc] == originNum) {
					scoreCount++;
					queue.add(new int[] {cr, cc});
					visited[cr][cc] = true;
				}
			}
		}
		score += (originNum * scoreCount);
	}

	// 주사위 굴리기 ((데굴
	// 0 1 2 3 (북 동 남 서)
	static void rollDice(int dir, int r, int c) {
		int tmp = dice[2];
		switch(dir) {
		case 0:
			dice[2] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[0];
			dice[0] = tmp;
			break;
		case 1:
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[3];
			dice[3] = tmp;
			break;
		case 2:
			dice[2] = dice[0];
			dice[0] = dice[5];
			dice[5] = dice[4];
			dice[4] = tmp;
			break;
		case 3:
			dice[2] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[1];
			dice[1] = tmp;
			break;
		}
	}
}
