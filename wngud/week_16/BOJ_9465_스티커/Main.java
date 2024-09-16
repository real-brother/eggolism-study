import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int Test = sc.nextInt();

        for (int t = 0; t < Test; t++) {


            // 테스트 한개 시작
            int N = sc.nextInt();

            // 매트릭스 채우기
            int[][] matrix = new int[2][N]; // 마지막 행은 0

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < N; j++) {
                    int num = sc.nextInt();
                    matrix[i][j] = num;
                }
            }

            // dp 채우기
            int[][] dp = new int[3][N]; // 마지막 행은 0
            dp[0][0] = matrix[0][0];
            dp[1][0] = matrix[1][0];

            for (int i = 1; i < N; i++) {
                dp[0][i] = matrix[0][i] + Math.max(dp[1][i - 1], dp[2][i - 1]);
                dp[1][i] = matrix[1][i] + Math.max(dp[0][i - 1], dp[2][i - 1]);
                dp[2][i] = Math.max(dp[0][i - 1], dp[1][i - 1]);
            }

            // 출력해보기
//            for (int i = 0; i < dp.length; i++) {
//                System.out.println(Arrays.toString(dp[i]));
//            }

            // 정답 출력하기
            int answer = Math.max(dp[0][N - 1], Math.max(dp[1][N - 1], dp[2][N - 1]));
            System.out.println(answer);

        }


    }
}
