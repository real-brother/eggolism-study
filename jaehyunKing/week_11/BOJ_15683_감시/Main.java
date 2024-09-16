package BOJ_15683_감시;

/*
 *  CCTV -> 8개 이하 -> 조합(조합이 아니라 중복순열인거같다)
 *  
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N, M, zeroCount, count, min;
	static int[][] graph, copy;
	static ArrayList<int[]> cctvs;
	static int[] dir;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new int[N][M];
		copy = new int[N][M];
		
		zeroCount = 0;
		cctvs = new ArrayList<>();
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				graph[r][c] = sc.nextInt();
				if(graph[r][c] != 0 && graph[r][c] != 6) {
					cctvs.add(new int[] {r, c});
				}
				if(graph[r][c] == 0) zeroCount++;
			}
		}
		
		dir = new int[cctvs.size()];
		min = Integer.MAX_VALUE;
		combination(0);
		
		System.out.println(min);

	}
	
	// CC개수만큼 방향을 선택해준다
	// 사실 5번(사방탐색하는 CC)은 안 넣는게 맞는데
	// 그냥 해보고 안 되면 빼려고 했는데 그냥 되길래 냅뒀다
	// 지금 생각해보면 5번 CC만 리스트로 따로 빼던지
	// 아니면 완탐 한 번 더 돌려서 내 코드 기준으로 zeroCount 수정해주고 감시가능한 구역
	// 수정해주면 될 꺼 같다
	static void combination(int idx) {
		if(idx == dir.length) {
			count = zeroCount;
			// 원본 배열 유지를 위해서 배열을 copy 떴다
			copy();
			for(int i = 0; i < dir.length; i++) cctv(cctvs.get(i)[0], cctvs.get(i)[1], dir[i]);
			if(count < min) min = count;
			return;
		}
		
		// 4가지 방향에 대한 중복 순열 
		dir[idx] = 0;
		combination(idx+1);
		
		dir[idx] = 1;
		combination(idx+1);
		
		dir[idx] = 2;
		combination(idx+1);
		
		dir[idx] = 3;
		combination(idx+1);	
	}
	
	// 0 상 1 우 2 하 3 좌
	static void cctv(int nowY, int nowX, int dir) {
		// CC 종류에 따라 구분
		if(copy[nowY][nowX] == 5) {
			up(nowY, nowX);
			down(nowY, nowX);
			left(nowY, nowX);
			right(nowY, nowX);
		}
		else if(copy[nowY][nowX] == 4) {
			if(dir == 0) {
				up(nowY, nowX);
				left(nowY, nowX);
				right(nowY, nowX);
			}
			else if(dir == 1) {
				up(nowY, nowX);
				right(nowY, nowX);
				down(nowY, nowX);
			}
			else if(dir == 2) {
				right(nowY, nowX);
				down(nowY, nowX);
				left(nowY, nowX);
			}
			else {
				down(nowY, nowX);
				left(nowY, nowX);
				up(nowY, nowX);
			}
		}
		else if(copy[nowY][nowX] == 3) {
			if(dir == 0) {
				up(nowY, nowX);
				right(nowY, nowX);
			}
			else if(dir == 1) {
				right(nowY, nowX);
				down(nowY, nowX);
			}
			else if(dir == 2) {
				down(nowY, nowX);
				left(nowY, nowX);
			}
			else {
				left(nowY, nowX);
				up(nowY, nowX);
			}
		}
		else if(copy[nowY][nowX] == 2) {
			if(dir % 2 == 0) {
				up(nowY, nowX);
				down(nowY, nowX);
			}
			else {
				left(nowY, nowX);
				right(nowY, nowX);
			}
		}
		else {
			if(dir == 0) up(nowY, nowX);
			else if(dir == 1) right(nowY, nowX);
			else if(dir == 2) down(nowY, nowX);
			else left(nowY, nowX);	
		}
		
	}
	
	// 감시 방향에 따른 감시 구역 표시 메서드 (7로 표시했다), 상
	static void up(int nowY, int nowX) {
		while(nowY + 1 < N && copy[nowY+1][nowX] != 6) {
			nowY++;
			if(copy[nowY][nowX] == 0) {
				copy[nowY][nowX] = 7;
				count--;
			}
		}
		
	}
	
	// 하
	static void down(int nowY, int nowX) {
		while(nowY - 1 >= 0 && copy[nowY-1][nowX] != 6) {
			nowY--;
			if(copy[nowY][nowX] == 0) {
				copy[nowY][nowX] = 7;
				count--;
			}
		}
	}
	
	// 좌
	static void left(int nowY, int nowX) {
		while(nowX - 1 >= 0 && copy[nowY][nowX-1] != 6) {
			nowX--;
			if(copy[nowY][nowX] == 0) {
				copy[nowY][nowX] = 7;
				count--;
			}
		}
	}
	
	// 우
	static void right(int nowY, int nowX) {
		while(nowX + 1 < M && copy[nowY][nowX+1] != 6) {
			nowX++;
			if(copy[nowY][nowX] == 0) {
				copy[nowY][nowX] = 7;
				count--;
			}
		}
	}
	
	// 배열 복사
	static void copy() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				copy[r][c] = graph[r][c];
			}
		}
	}

}