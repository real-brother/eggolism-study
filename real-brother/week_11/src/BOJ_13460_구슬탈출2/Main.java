package BOJ_13460_구슬탈출2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, holeR, holeC;
    static char[][] marbleMap;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][][][] visited;
    static int minAnswer = Integer.MAX_VALUE;
    static Marble blue, red;
    
    public static void main(String[] args) {
    	n = sc.nextInt();
    	m = sc.nextInt();
    	sc.nextLine();
    	marbleMap = new char[n][m];
    	visited = new boolean[n][m][n][m];
    	for (int i = 0; i < n; i++) {
    		char[] temp = sc.nextLine().toCharArray();
			for (int j = 0; j < m; j++) {
				marbleMap[i][j] = temp[j];
				if (marbleMap[i][j] == 'B') {
					blue = new Marble(0, 0, i, j, 0);
				} else if (marbleMap[i][j] == 'R') {
					red = new Marble(i, j, 0, 0, 0);
				} else if (marbleMap[i][j] == 'O') {
					holeR = i;
					holeC = j;
				} 
			}
		}
    	
    	minAnswer = gameStart();
    	
//    	printMap();
    	System.out.println(minAnswer == Integer.MAX_VALUE ? -1 : minAnswer);
	}

    // BFS를 이용하여 탐색.  (최단거리를 찾아야하기 때문, 10 이하)
 	public static int gameStart() {
 		Queue<Marble> queue = new ArrayDeque<>();
 		queue.add(new Marble(red.rr, red.rc, blue.br, blue.bc, 1));
 		visited[red.rr][red.rc][blue.rr][blue.rc] = true;
 		
 		while(!queue.isEmpty()) {
 			Marble marble = queue.poll();
 			
 			int curRedr = marble.rr;
 			int curRedc = marble.rc;
 			int curBluer = marble.br;
 			int curBluec = marble.bc;
 			int curTurn = marble.turn;
 		
 			// 이동 횟수가 10 초과시 실패
 			if(curTurn > 10) {  
 				break;
 			}
 			
 			for(int i = 0; i < 4; i++) {
 				int newRedr = curRedr;
 				int newRedc = curRedc;
 				int newBluer = curBluer;
 				int newBluec = curBluec;
 				
 				boolean isRedHole = false;
 				boolean isBlueHole = false;
 				
 				// 빨간 구슬 이동
 				// 벽을 만날 때까지 해당 방향으로 계속 
 				while(marbleMap[newRedr + dr[i]][newRedc + dc[i]] != '#') { 
 					newRedr += dr[i];
 					newRedc += dc[i];
 					
 					// 이동 중 구멍을 만나면, flag값 기록
 					if(newRedr == holeR && newRedc == holeC) { 
 						isRedHole = true;
 						break;
 					}
 				}
 				
 				// 파란 구슬 이동
 				// 벽을 만날 때까지 해당 방향으로 계속
 				while(marbleMap[newBluer + dr[i]][newBluec + dc[i]] != '#') { 
 					newBluer += dr[i];
 					newBluec += dc[i];
 					
 					// 이동 중 구멍을 만나면, flag값 기록 
 					if(newBluer == holeR && newBluec == holeC) { 
 						isBlueHole = true;
 						break;
 					}
 				}
 				
 				// 파란 구슬이 구멍에 들어가면 무조건 실패 -> 다음 큐 확인
 				if(isBlueHole) {  
 					continue;  
 				}
 				
 				// 빨간 구슬만 구멍에 빠지면 성공 
 				if(isRedHole) { 
 					return curTurn;
 				}
 				
 				// 둘 다 구멍에 빠지지 않았는데 이동할 위치가 같은 경우 -> 위치 조정
 				// 빨간, 파란 구슬이 모두 같은 방향으로 벽까지 가기 때문에, 같은 좌표로 이동할 수 있음. 하지만, 한 좌표에는 하나의 구슬 만 있어야함 
 				if(newRedr == newBluer && newRedc == newBluec) {
 					if(i == 0) { // 위쪽으로 기울이기 
 						// 더 큰 x값을 가지는 구슬이 뒤로 감 
 						if(curRedr > curBluer) newRedr -= dr[i]; 
 						else newBluer -= dr[i];
 					} else if(i == 1) { // 오른쪽으로 기울이기 
 						// 더 작은 y값을 가지는 구슬이 뒤로 감 
 						if(curRedc < curBluec) newRedc -= dc[i];
 						else newBluec -= dc[i];	
 					} else if(i == 2) { // 아래쪽으로 기울이기 
 						// 더 작은 x값을 가지는 구슬이 뒤로 감 
 						if(curRedr < curBluer) newRedr -= dr[i]; 
 						else newBluer -= dr[i];
 					} else { // 왼쪽으로 기울이기 
 						// 더 큰 y값을 가지는 구슬이 뒤로 감 
 						if(curRedc > curBluec) newRedc -= dc[i]; 
 						else newBluec -= dc[i];	
 					}
 				}
 				
 				// 두 구슬이 이동할 위치가 처음 방문하는 곳일 때만 큐에 추가 
 				if(!visited[newRedr][newRedc][newBluer][newBluec]) {
 					visited[newRedr][newRedc][newBluer][newBluec] = true;
 					queue.add(new Marble(newRedr, newRedc, newBluer, newBluec, curTurn+1));
 				}
 			}
 		}
 		
 		return -1;
 	}


	static class Marble {
		int rr;
		int rc;
		int br;
		int bc;
		int turn;
		
		Marble(int rr, int rc, int br, int bc, int turn) {
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
			this.turn = turn;
		}
	}
}

