import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[][] matrix = new int[N][2];

        int max = 0;
        for (int i = 0; i < N; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            matrix[i][0] = from;
            matrix[i][1] = to;

            // 최대값 찾기
            if (from > max) max = from;
            if (to > max) max = to;
        }

        // 앞을 기준으로 정렬하기
        Arrays.sort(matrix, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });

        // 최장 증가 부분 수열
        int[] dp = new int[N];
        int answer = 1;

        // 처음부터 끝까지 dp 테이블 채우기
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            // 본인부터 이전 것까지 전체 탐색
            for (int j = i - 1; j >= 0; j--) {
                // 큰 것만 찾아서 최신화
                if (matrix[i][1] > matrix[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            answer = Math.max(answer, dp[i]);

        }

        // 정답 출력하기
        System.out.println(N - answer);

    }

}

