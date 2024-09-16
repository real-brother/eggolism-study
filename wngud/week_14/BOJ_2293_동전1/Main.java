import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] coins = new int[N+1];
        for (int i = 1; i <= N; i++) {
            coins[i] = sc.nextInt(); // 실제 숫자
        }

        // DP
        int[][] dp = new int[N + 1][K + 1]; // 실제 숫자

        for (int i = 1; i <= N; i++) {
            // 행방향
            for (int j = 0; j <= K; j++) {

                // 앞자리 1로 초기값 -> 아무것도 사용하지 않는 경우
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                // 동전이 만들어지지 않는 경우는 위의 것 복사
                if (j - coins[i] < 0) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }

                dp[i][j] = dp[i-1][j] + dp[i][j-coins[i]]; // 이전 최대 + 해당 코인 없는 경우의 경우의 수

            }
        }

        // 출력해보기
//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        System.out.println(dp[N][K]);

    }
}
