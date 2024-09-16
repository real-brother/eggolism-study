package W16_BOJ_12852_1로만들기2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/* 문제) 12852_1로 만들기2
		 * 3으로 나누어 떨어지면 3으로 나눈다.
		 * 2로 나누어 떨어지면 2로 나눈다.
		 * 1을 뺀다.
		 * 
		 * 1을 만드는데 사용하는 연산 횟수의 최소값, 연산 결과들 출력
		 */
		
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt();
		
		int[] dp = new int[x+1];
		int[] log = new int[x+1];
		
		dp[1] = 0;
		
		for(int i=2; i<=x; i++) {
			// 먼저 1 뺀 횟수 저장
			dp[i] = dp[i-1]+1;
			log[i] = 1;
			
			// 2로 나누어떨어지면 현재 값과 2로 나눴을 때 횟수 비교
			if (i%2 == 0) {
				if (dp[i/2]+1 < dp[i]) {
					log[i] = 2;
				}
				dp[i] = Math.min(dp[i], dp[i/2]+1);
				
			}
			
			// 3으로 나누어떨어지면 현재 값과 3으로 나눴을 때 횟수 비교
			if (i%3 == 0) {
				if (dp[i/3]+1 < dp[i]) {
					log[i] = 3;
				}
				dp[i] = Math.min(dp[i], dp[i/3]+1);
				
			}
		}
		
//		System.out.println(Arrays.toString(log));
		
		// 흠 로그는 어떻게 구해??
		List<Integer> result = new ArrayList<>();
		
		int pointer = x;
		
		result.add(pointer);
		
		while (pointer != 1) {
			if (log[pointer] == 1) {
				pointer--;
				result.add(pointer);
			}
			else if (log[pointer] == 2) {
				pointer /= 2;
				result.add(pointer);
			}
			else {
				pointer /= 3;
				result.add(pointer);
			}
		}
		
		System.out.println(dp[x]);
		for(int n : result) {
			System.out.print(n+" ");
		}

	}

}
