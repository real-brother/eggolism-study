import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 설계 방식
 * 1. 한칸 앞으로 갈 때마다 그 칸은, 이전 칸 둘 중 하나로 넣어야 한다.
 * 2. 본인의 값에 둘 중 최소값을 저장한다.
 * 3. 처음에 3칸 모두를 저장하고 시작해야 한다.
 * */

public class Main {
    static int[][] matrix;
    static int[][] dp;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());

        matrix = new int[total][3];
        dp = new int[total][3];

        // 초기값 세팅하기
        String[] setting = br.readLine().split(" ");
        dp[0][0] = Integer.parseInt(setting[0]);
        dp[0][1] = Integer.parseInt(setting[1]);
        dp[0][2] = Integer.parseInt(setting[2]);

        matrix[0][0] = Integer.parseInt(setting[0]);
        matrix[0][1] = Integer.parseInt(setting[1]);
        matrix[0][2] = Integer.parseInt(setting[2]);

        // 순회하기
        // 1부터 시작
        for (int i = 1; i < total; i++) {
            String[] inputs = br.readLine().split(" ");

            // 매트릭스 채우기
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        // DP 채우기
        // 1부터 시작
        for (int i = 1; i < total; i++) {
            dp[i][0] = matrix[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = matrix[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = matrix[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        System.out.println(Math.min(dp[total-1][0], Math.min(dp[total-1][1], dp[total-1][2])));

    }
}
