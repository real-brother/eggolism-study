package BOJ_12865_평범한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 물품의 개수
        int k = Integer.parseInt(st.nextToken()); // 배낭의 최대 무게

        // 각 물품의 무게와 가치를 저장할 배열 선언
        int[][] items = new int[n + 1][2]; // [물품 번호][0:무게, 1:가치]

        // dp를 위한 배열 선언
        int[][] dp = new int[n + 1][k + 1]; // [물품 번호][남은 무게]

        // 각 물품의 무게와 가치 입력 받음
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken()); // 무게
            items[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }

        // 다이나믹 프로그래밍을 통해 최대 가치를 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                int w = items[i][0]; // 현재 물품의 무게
                int v = items[i][1]; // 현재 물품의 가치

                // 현재 물품을 배낭에 넣을 수 있는 경우
                if (j >= w) {
                    // 현재 물품을 넣지 않았을 때의 가치와 넣었을 때의 가치 중 큰 값을 선택하여 저장
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                } else { // 현재 물품을 배낭에 넣을 수 없는 경우
                    // 이전 물품까지의 최대 가치를 그대로 유지
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 배낭에 담을 수 있는 최대 가치 출력
        System.out.println(dp[n][k]);
    }
}

