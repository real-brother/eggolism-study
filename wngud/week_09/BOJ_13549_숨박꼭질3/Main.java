import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    /*설계방식
     * 1. 좌표를 저장할지 or 배열에 표시하기
     * 2. 배열에 1인 경우 2배로 다 1표시(true)를 표시한다.
     * - 동시성 문제를 해결하기 위해 뒤부터 카운팅
     * 3. 배열에 1인 경우 +-를 한다.
     * - 동시성 문제를 해결하기 위해 새로우 배열 만들기*/

    /*예외 처리
     * 1. 뒤로 가는 경우
     * - 너무 숫자가 커져서 앞에서 미리 커팅을 함
     * - 뒤로 가는 경우는 -1밖에 없어서 단순 숫자를 빼면 됨
     * 2. N이 0인 경우 곱하기 문제*/

    static int[] line;

    static int N, K;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        line = new int[100000 + 1]; // K+1.. 근데 2배 갔다가 뒤로 가는게 좋을 수도
        line[N] = 1;

        int time = 0;

        // 뒤에 있는 경우는 그냥 빼기
        boolean go_on = true;
        if (N >= K) {
            System.out.println(N - K);
            go_on = false;
        }

        while (go_on) {

            // 1인 부분은 전부 2배씩 증가시킴
            // 동시성 문제 -> 뒤로부터 함 -> 앞에서부터 해도 됨 (어차피 0초)
            // 0 제외!!
//            for (int i = line.length-1; i > 0; i--) {
            for (int i = 1; i < line.length; i++) {
                if (line[i] == 1) {
                    int start = i;
                    // k까지 말고 끝까지 가기 -> length-1이 문제 -> length까지!!
                    while (start < line.length) {
                        line[start] = 1;
                        start *= 2;
                    }
                }
            }

            // 만약, K자리가 1이 되면 끝
            if (line[K] == 1) {
                System.out.println(time);
                break;
            }

            time++; // 0초 예외 때문에 여기에 추가
            // 1인 부분은 플마하기 -> time 추가
            // 동시성 문제!!
            int[] line2 = line.clone();
            for (int i = 0; i < line.length; i++) {
                if (line[i] == 1) {
                    // 하나씩만 해야 함!!
                    if ((i - 1) >= 0) line2[i - 1] = 1;
                    if ((i + 1) < line.length) line2[i + 1] = 1;

                }
            }

            line = line2;

            // 만약, K자리가 1이 되면 끝
            if (line[K] == 1) {
                System.out.println(time);
                break;
            }
        } // while 끝


    }

}