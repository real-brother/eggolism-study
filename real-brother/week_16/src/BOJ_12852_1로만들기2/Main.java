package BOJ_12852_1로만들기2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N = sc.nextInt();
    static int[] dp, path;
	public static void main(String[] args) {
        dp = new int[N + 1]; // 최소 연산 횟수를 저장하는 배열
        path = new int[N + 1]; // 경로를 추적하기 위한 배열

        dp[1] = 0; // 1은 이미 1이므로 연산 횟수 0

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            path[i] = i - 1;

            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                path[i] = i / 2;
            }

            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                path[i] = i / 3;
            }
        }

        // 최소 연산 횟수 출력
        System.out.println(dp[N]);

        // 경로 출력
        List<Integer> sequence = new ArrayList<>();
        for (int i = N; i != 0; i = path[i]) {
            sequence.add(i);
        }
        for (int num : sequence) {
            System.out.print(num + " ");
        }
    }
}
