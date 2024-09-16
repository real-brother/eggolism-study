package W16_BOJ_9465_스티커;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/* 문제) 9465_스티커
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			
			int[][] sticker = new int[2][n+1];
			int[][] score = new int[2][n+1];
			
			// 스티커 점수 입력받기
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=n; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 스티커의 1열과 점수의 1열은 동일
			score[0][1] = sticker[0][1];
			score[1][1] = sticker[1][1];
			
			// 최대값 비교하며 점수 저장
			for(int i=2; i<=n; i++) {
				score[0][i] = Math.max(score[1][i-1], score[1][i-2]) + sticker[0][i];
				score[1][i] = Math.max(score[0][i-1], score[0][i-2]) + sticker[1][i];
			}
			
			System.out.println(Math.max(score[0][n], score[1][n]));
			
		}

	}

}
