package BOJ_12100_2048;

/*
 *  중복순열을 통해 이동할 5가지 방향을 정함(4^5 = 1024)
 *  해당 방향으로 이동(현재 위치에 올 숫자를 확정하는 방식)
 *  5번 이동 후에 최대 숫자를 골라준다
 */

import java.util.Scanner;

public class Main {
	static int N, max;
	static int[] direction;
	static int[][] graph, copy;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		graph = new int[N][N];
		copy = new int[N][N];
		
		for(int r = 0; r < N; r++) for(int c = 0; c < N; c++) graph[r][c] = sc.nextInt();
		
		// 중복순열을 통해 저장한 방향을 넣어줄 배열
		direction = new int[5];
		max = 0;
		perm(0);

		System.out.println(max);
	}
	
	// 방향 5개를 정할 중복순열
	static void perm(int idx) {
		
		// 방향 5개를 정했으면
		if(idx == 5) {
			copy();
			for(int i = 0; i < 5; i++) move(direction[i]);
			findMax();
			return;
		}
		
		for(int i = 0; i <= 3; i++) {
			direction[idx] = i;
			perm(idx+1);
		}
	}
	
	// 해당 방향대로 움직임
	static void move(int direction) {
		if(direction == 0) { //  상
			// 여기는 그냥 구현
			for(int c = 0; c < N; c++) {
				for(int r = 0; r < N-1; r++) {
					for(int r2 = r+1; r2 < N; r2++) {
						// r c로 정한 현재 위치에 올 숫자를 정해주는 식으로 진행
						// 현재 자리가 0이면 아래쪽에서 가장 가까운 0이 아닌 숫자를 현재 자리로 옮긴다
						if(copy[r][c] == 0 && copy[r2][c] != 0) {
							copy[r][c] = copy[r2][c];
							copy[r2][c] = 0;
						}
						// 현재 자리와 아래쪽에서 가장 가까운 숫자가 같으면 2배를 곱해주고 break(한 번에 한 번만 합칠 수 있음)
						else if(copy[r][c] == copy[r2][c] && copy[r2][c] != 0) {
							copy[r][c] *= 2;
							copy[r2][c] = 0;
							break;
						}
						// 0이 아니고, 현재 숫자랑 아래쪽 숫자가 다르면 break
						else if(copy[r][c] != copy[r2][c] && copy[r2][c] != 0) break;
					}
				}
			}
		}
		else if(direction == 1) { //  하
			for(int c = 0; c < N; c++) {
				for(int r = N-1; r >= 1; r--) {
					for(int r2 = r-1; r2 >= 0; r2--) {
						if(copy[r][c] == 0 && copy[r2][c] != 0 ) {
							copy[r][c] = copy[r2][c];
							copy[r2][c] = 0;
						}
						else if(copy[r][c] == copy[r2][c] && copy[r2][c] != 0) {
							copy[r][c] *= 2;
							copy[r2][c] = 0;
							break;
						}
						else if(copy[r][c] != copy[r2][c] && copy[r2][c] != 0) break;
						
					}
				}
			}
		}
		else if(direction == 2) { //  좌
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N-1; c++) {
					for(int c2 = c+1; c2 < N; c2++) {
						if(copy[r][c] == 0 && copy[r][c2] != 0) {
							copy[r][c] = copy[r][c2];
							copy[r][c2] = 0;
						}
						else if(copy[r][c] == copy[r][c2] && copy[r][c2] != 0) {
							copy[r][c] *= 2;
							copy[r][c2] = 0;
							break;
						}
						else if(copy[r][c] != copy[r][c2] && copy[r][c2] != 0) break;
					}
				}
			}
		}
		else { //  우
			for(int r = 0; r < N; r++) {
				for(int c = N-1; c >= 1; c--) {
					for(int c2 = c-1; c2 >= 0; c2--) {
						if(copy[r][c] == 0 && copy[r][c2] != 0) {
							copy[r][c] = copy[r][c2];
							copy[r][c2] = 0;
						}
						else if(copy[r][c] == copy[r][c2] && copy[r][c2] != 0) {
							copy[r][c] *= 2;
							copy[r][c2] = 0;
							break;
						}
						else if(copy[r][c] != copy[r][c2] && copy[r][c2] != 0) break;
					}
				}
			}
		}
	}
	
	// 사용할 배열 복사
	static void copy() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				copy[r][c] = graph[r][c];			
			}
		}
	}
	
	// 완전탐색하면서 가장 큰 숫자 찾기
	static void findMax() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(max < copy[r][c]) max = copy[r][c];
			}
		}
	}
}