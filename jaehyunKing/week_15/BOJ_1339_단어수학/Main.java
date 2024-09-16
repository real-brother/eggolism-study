package BOJ_1339_단어수학;

/*
 *  ABCD면 alpha배열에 A자리에 1000 B자리에 100 C자리에 10 D자리에 1을 추가한다. (아스키코드 사용)
 *  모든 입력에 대해서 진행하고,
 *  alpha배열에 저장된 숫자가 가장 큰 알파벳부터 숫자를 부여해준다 
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] alpha = new int[26]; // A -> 65
		boolean[] useAlpha = new boolean[26];
		
		for(int i = 0; i < N ; i++) {
			String S = sc.next();
			int length = S.length();
			for(int j = 0; j < length; j++) {
				char c = S.charAt(j);
				alpha[c-65] += Math.pow(10, (length-j-1));
			}
		}

		int sum = 0;
		
		// 9부터 1까지 숫자 부여
		for(int i = 9; i >= 1; i--) {
			int max = 0;
			int idx = 0;
			// 알파벳 개수
			for(int j = 0; j < 26; j++) {
				if(max < alpha[j]) {
					if(!useAlpha[j]) {
						max = alpha[j];
						idx = j;
					}
				}
			}
			if(max != 0) {
				sum += i * alpha[idx];
				useAlpha[idx] = true;
			}
			else break;
		}
		
		System.out.println(sum);

	}

}