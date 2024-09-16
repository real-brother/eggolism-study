package BOJ_2565_전깃줄;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static List<int[]> pole = new ArrayList<int[]>();
    
    /*
     * A 전깃줄을 오름차순으로 정렬하며
     * B 전깃줄의 최장증가부분수열(LIS)의 길이를 구하면 끝
     */

    public static void main(String[] args) {
    	n = sc.nextInt();
    	for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			pole.add(new int[] {a,b});
		}

        // 첫 번째 요소를 기준으로 오름차순으로 정렬
        Collections.sort(pole, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        
        int[] dp = new int[n];
        int max = 0; // 최장 증가 길이

        // 최장 증가 부분수열 찾기
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pole.get(i)[1] > pole.get(j)[1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        
        System.out.println(n - max);
    }
}
