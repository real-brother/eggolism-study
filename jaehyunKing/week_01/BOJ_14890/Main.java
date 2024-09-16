package BOJ_14890;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int L = sc.nextInt();
		int[][] map = new int[N][N];
		int count = 0;
		//배열 생성
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		//가로축 검사
		for(int i = 0; i < N; i++) {
			int stack = 0;
			loop: for(int j = 1; j < N; j++) {
				int diff = map[i][j-1] - map[i][j];
				if(diff > 1 || diff < -1) break;
				else if(diff == 1) {
					if(j+L-1 < N && j >= stack) {
						for(int k = j; k < j+L; k++) {
							if(map[i][j] == map[i][k]) {}
							else {
								break loop;
							}
						}
					}else break loop;
					stack = j+L;
				}
				else if(diff == -1) {
					if(j-L >= 0 && j-L >= stack) {
						for(int k = j-1; k >= j-L; k--) {
							if(map[i][j-1] == map[i][k]) {}
							else {
								break loop;
							}
						}
					} else break loop;
					stack = j;
				}
				if(j == N-1) count++;
			}
		}
		
		//세로축 검사
		for(int i = 0; i < N; i++) {
			int stack = 0;
			loop: for(int j = 1; j < N; j++) {
				int diff = map[j-1][i] - map[j][i];
				if(diff > 1 || diff < -1) break;
				else if(diff == 1) {
					if(j+L-1 < N && j >= stack) {
						for(int k = j; k < j+L; k++) {
							if(map[j][i] == map[k][i]) {}
							else {
								break loop;
							}
						}
						 
					} else break loop;
					stack = j+L;
				}
				else if(diff == -1) {
					if(j-L >= 0 && j-L >= stack) {
						for(int k = j-1; k >= j-L; k--) {
							if(map[j-1][i] == map[k][i]) {}
							else {
								break loop;
							}
						}

					} else break loop;
					stack = j;
				}
				if(j == N-1) count++;
			}
		}
	System.out.println(count);
	}
}

//2차원 배열 한 줄 씩 탐색
//1차이 -> L만큼 낮은 숫자가 연속이어야함
//diff로 이전 값 - 현재 값 비교
//stack 이전은 경사로를 만들지 못하게