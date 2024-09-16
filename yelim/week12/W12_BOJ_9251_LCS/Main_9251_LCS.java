package W12_BOJ_9251_LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9251_LCS {
	
	public static void main(String[] args) throws IOException {
		/* 문제) LCS
		 * 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
		 * 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		
		int len1 = c1.length;
		int len2 = c2.length;

		int[][] dp = new int[len1+1][len2+1];
		
		for(int i=1; i<=len1; i++) {
			for(int j=1; j<=len2; j++) {
				
				// c1[i-1]과 c2[j-1]이 같다면
				if (c1[i-1] == c2[j-1]) {
					dp[i][j] = dp[i-1][j-1]+1;
				}
				
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
				
			}
		}
		
		System.out.println(dp[len1][len2]);
		
	}

	
}
