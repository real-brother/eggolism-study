package BOJ_11053_가장긴증가하는부분수열;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] numList = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < numList.length; i++) {
        	numList[i] = sc.nextInt();
		}
        
        // 각 숫자들로부터 가장 긴 증가하는 부분 수열의 길이를 계산
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
                // 현재 숫자가 이전 숫자보다 크고, 현재 숫자를 마지막으로 하는 부분 수열의 길이가
                // 이전 숫자를 마지막으로 하는 부분 수열의 길이보다 길 경우에만 업데이트
				if (numList[i] > numList[j] && dp[i] < dp[j]) {
					dp[i] = dp[j];
				}
			}
            // 현재 숫자를 마지막으로 하는 부분 수열의 길이를 1 증가시킴
			dp[i]++;
		}
        
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
