import java.util.*;

public class Main {

    /* 설계 방식
    * 1. 가장 큰 정사각형
    * - 큰 사각형을 구해야 하니 연속적인 변의 길이를 DP로 누적할 필요가 있다.
    * - 정사각형을 만들어야 하니, 탐색하는 앞의 3개중 가장 작은 변의 길이에 + 1을 해야 한다.
    * - 3곳 모두가 1(특정 숫자) 이상이 되어야 정사각형의 요건으로 그대로 +1이 된다.
    * - 3곳 모두가 아닐 경우는 최소값에서 +1로 연장 해야 한다.
     */

    /*디버깅
     * 1. 모든 배열이 0일 때
     * - 정답을 마지막에 조합하다보니 1이 나온다.
     * 2. 매트릭스와 DP 배열 +1과 for문 처음과 끝 조심
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();

        int[][] matrix = new int[N+1][M+1];
        for (int i = 1; i < matrix.length; i++) {
            String inputs = sc.nextLine();
            for (int j = 1; j < matrix[0].length; j++) {
                int num = Integer.parseInt(inputs.substring(j-1, j));

                matrix[i][j] = num;
            }
        }

        int[][] dp = new int[N + 1][M + 1];

        // DP
        int maxCount = 0;
        for (int i = 1; i <  matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {

                if (matrix[i][j] == 1) {
                    // 세개 중 가장 작은 수의 + 1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    maxCount = Math.max(maxCount, dp[i][j]);
                }

            }
        }

//		for (int i = 0; i < dp.length; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}

        System.out.println((maxCount) * (maxCount));


    }
}
