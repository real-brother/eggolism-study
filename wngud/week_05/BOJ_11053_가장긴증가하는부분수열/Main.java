import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();

        int[] seq = new int[total];
        int[] dp = new int[total];

        // 배열 채우기
        for (int i = 0; i < total; i++) {
            seq[i] = sc.nextInt();
        }

        /*
         * 설계 방식
         * 1. 1부터 올라가는 방식으로 끝까지 dp를 채운다.
         * 2. 증가하는 숫자가 있으면 누적하여 저장한다.
         * 3. (중간에 끊긴 경우) 증가하는 숫자는 자신보다 작은 이전 숫자 중에서 큰 dp값을 저장한다.
         * 4. 중간에 감소하는 숫자가 있으면 1부터 다시 시작한다.
         */

        // if의 조건은
        // (1) 자신(i)보다 숫자가 작고,
        // (2) dp값은 현재 자신 보다 높은 값을 가진 것으로 덮어쓰기
        for (int i = 0; i < total; i++) {
            dp[i] = 1; // 최소 1로 초기화 -> 최소값이 1임

            // 0부터 해당 숫자(j번째)까지 계속 순회
            // 계속 순회하며 가장 큰 숫자가 자동적으로 덮어쓰기
            for (int j = 0; j < i; j++) {
                if (seq[j] < seq[i] && dp[j] >= dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        // 가장 큰 dp 값 구하기
        // 마지막의 값이 가장 큰 값이 아닐 수 있다.
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);

    }
}
