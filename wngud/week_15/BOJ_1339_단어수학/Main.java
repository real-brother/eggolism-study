import java.util.*;

public class Main {
    /*설계 방식
     * 1. 알파벳, 알파벳의 자리수에 따른 우선순위, 사용 개수 정보 필요
     * - 3가지 정보를 저장하는 것이 어려웠다.
     * - 다르게 볼 필요
     *
     * 2. 알파벳의 자리수를 숫자로 더해주는 방식
     * - 알파벳 배열을 만든다.
     * - 알파벳 배열 자리에 자리수에 맞는 값을 더해준다.
     * - 자동으로 자리수와 개수를 동시에 카운팅하는 효과가 있다.
     *
     * 3. 알파벳은 중요하지 않다.
     * - 사실상 그리디만 하면 되기 때문에, 가장 높은 숫자부터 *9...를 곱한다.
     */

    /*디버깅
     * 1. 알파벳 최대 자리수 > 사용 개수 까지만 구함
     * 2. 알파벳 최대 자리수 > 알파벳 그다음 최대 자리수.. > 사용 개수
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        // 알파벳 마다 숫자 저장
        int[] arr = new int[26];

        for (int i = 0; i < N; i++) {
            String inputs = sc.nextLine();
            int tmp = 1;
            // 끝에서부터 찾기
            for (int j = inputs.length() - 1; j >= 0; j--) {
                char c = inputs.charAt(j);
                arr[c - 'A'] += tmp;
                tmp *= 10;
            }
        }

//        System.out.println(Arrays.toString(arr));

        // 점수 부여하기 -> 실제 알파벳은 중요하지 않다.
        Arrays.sort(arr);

        int point = 9;
        int answer = 0;
        // 최대 10개
        for (int i = 25; i >= 17; i--) {
            answer += arr[i] * point;
            point--;
        }

        System.out.println(answer);


    }
}
