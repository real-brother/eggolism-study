package BOJ_1932_정수삼각형;

import java.util.Scanner;

/* 
 *   입력받으면서 DP 진행
 *   D[r][c] += Math.max(D[r-1][c], D[r-1][c-1])
 *   배열이 다 채워지면 마지막 줄만 완탐하면서 가장 큰 값 춢력
 */

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] D = new int[n][n];
		
		int C = 1;
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < C; c++) {
				D[r][c] = sc.nextInt();
				
				// 인덱스 에러 방지
				if(r != 0) {
					if(c != 0) D[r][c] += Math.max(D[r-1][c], D[r-1][c-1]);
					else D[r][c] += D[r-1][c];
				}
			}
			C++;
		}
		
		int max = 0;
		for(int c = 0; c < n; c++) if(max < D[n-1][c]) max = D[n-1][c];
		
		System.out.println(max);
	}

}