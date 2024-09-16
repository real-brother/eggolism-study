package BOJ_9465_스티커;

import java.util.Scanner;

/*
 *  인접한 칸의 안 골랐을 때, 대각선 스티커는 골랐을 때, 안 골랐을 때의 최대값을 가져온다.
 */

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int[][][] sticker = new int[2][N][2];
			
			for(int c = 0; c < N; c++) sticker[0][c][1] = sc.nextInt();
			for(int c = 0; c < N; c++) sticker[1][c][1]= sc.nextInt();
			
			// 0이 안 골랐을 때 1이 골랐을 때
			for(int c = 1; c < N; c++) {
				for(int r = 0; r < 2; r++) {
					if(r == 0) {
						// 풀고 보니까 여기가 이상함
						// 이전 꺼를 선택했을 때를 안 가져옴 -> 쓸데없이 뭔가 더 계산한 거 같다..
						sticker[r][c][0] = Math.max(sticker[r][c-1][0], Math.max(sticker[1][c-1][0], sticker[1][c-1][1]));
						sticker[r][c][1] = Math.max(sticker[r][c-1][0], Math.max(sticker[1][c-1][0], sticker[1][c-1][1])) + sticker[r][c][1];
					}
					else {
						sticker[r][c][0] = Math.max(sticker[r][c-1][0], Math.max(sticker[0][c-1][0], sticker[0][c-1][1]));
						sticker[r][c][1] = Math.max(sticker[r][c-1][0], Math.max(sticker[0][c-1][0], sticker[0][c-1][1])) + sticker[r][c][1];
					}
				}
			}
			
			int num1 = Math.max(sticker[0][N-1][0], sticker[0][N-1][1]);
			int num2 = Math.max(sticker[1][N-1][0], sticker[1][N-1][1]);
			System.out.println(Math.max(num1, num2));
		}

	}

}