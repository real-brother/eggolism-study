import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] dp = new int[N + 1]; // 실제 숫자
        int[] log = new int[N + 1];
        dp[1] = 0;

        // 한번 갔다가
        for (int i = 2; i <= N; i++) {

            // 무조건 되는 경우인 -1로 세팅
            dp[i] = dp[i - 1] + 1;
            log[i] = i - 1;

            // 3으로 되는 경우
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                log[i] = i / 3;
            }

            // 2로 되는 경우
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                log[i] = i / 2;
            }

        }

        // 정답 출력하기
        System.out.println(dp[N]);

        // 과정 출력하기
        StringBuilder sb = new StringBuilder();
        sb.append(N).append(" ");
        while (N != 1) {
            sb.append(log[N]).append(" ");
            N = log[N];
        }

        System.out.println(sb);


        // 다시 오기
//        StringBuilder sb = new StringBuilder();
//        sb.append(N).append(" ");
//        while (N != 1) {
//            // 케이스 채우기
//            int target = dp[N - 1]; // 초기값 -1
//            int targetIndex = N - 1;
//
//            if (N % 2 == 0 && target > dp[N / 2]) {
//                targetIndex = N / 2;
//            }
//
//            if (N % 3 == 0 && target > dp[N / 3]) {
//                targetIndex = N / 3;
//            }
//
//            // N값 최신화
//            sb.append(targetIndex).append(" ");
//            N = targetIndex;
//        }
//
//        System.out.println(sb);


    }
}
