import java.util.*;

public class Solution {
    /*설계 방식
     * 1. 1과 2가 필요한 개수만큼을 구한다. -> 나무 자체는 구별할 필요가 없다.
     * - 나무 자체를 구별하지 않고 필요한 개수들만 잘 파악하려고 자료구조를 만드는 것이 핵심!
     *
     * 2. 홀수와 짝수 날을 비교하며 날짜 지나감을 더한다.
     * - 1이 먼저 소진되고 2만 남은 경우에, 1의 날짜에 2를 쪼개어 없애주는 것이 그리디하다.
     * - 단, 2가 1개 남은 경우에는 1을 2개로 쪼개지 말고 그대로 더하는게 그리디 하다.
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int Test = sc.nextInt();

        for (int t = 0; t < Test; t++) {


            int N = sc.nextInt();

            int[] arr = new int[N];
            int max = 0;
            for (int i = 0; i < N; i++) {
                int num = sc.nextInt();
                arr[i] = num;
                max = Math.max(max, num);
            }

            // 1. 필요한 1과 2 구하기
            int one = 0;
            int two = 0;
            for (int i = 0; i < N; i++) {
                int num = max - arr[i];

                // 경우1
                if (num == 0) continue;

                // 경우2
                if (num == 1) one++;

                // 경우3
                if (num >= 2) {
                    two += num / 2;

                    // 경우4
                    if (num % 2 == 1) one++;
                }
            }


            // 2. 홀수 짝수 카운팅
            boolean isOdd = true;
            int answer = 0;

            while (one != 0 || two != 0) {
//            System.out.println();
//            System.out.println("one = " + one);
//            System.out.println("two = " + two);

                // 홀수날
                if (isOdd) {
                    // 1이 있는 경우
                    if (one > 0) {
                        one--;
                    }
                    // 1이 없는 경우 -> 2의 것이라도 하는게 이득?
                    else {
                        // 2를 건드는 경우는 2개 이상인 경우만
                        if (two >= 2) {
                            two--;
                            one++;
                        }
                    }
                }

                // 짝수날
                else {
                    if (two > 0) {
                        two--;
                    }
                }

                // 아무것도 안하는 날까지 포함하여 answer++;
                answer++;

                // 날짜 바꾸기
                isOdd = !isOdd;

            }

            // 정답 출력하기
            System.out.println("#" + (t+1) + " " + answer);

        }

    }
}
