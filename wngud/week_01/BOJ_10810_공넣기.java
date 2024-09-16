import java.util.Scanner;

public class BOJ_10810_공넣기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*문제
        * 1. 바구니는 N개이고 1부터 N까지
        * 2. 공은 많이있고 1부터 N까지
        * 3. M번 공을 넣으려고 함
        * 4. i번 바구니부터 j번 바구니까지 k번 번호가 적혀있는 공 넣기 반복
        */

        /*설계 아이디어
        * 1. 바구니를 만들고 입력대로 공을 넣으면 됨
        * 2. 새로운 공을 넣는 것은 입력값을 순서대로 계속 대입하면 됨
        * 3. 한번도 들어가지 않는 것은 초기값 0을 그대로 유지하면 됨*/

        // 입력 받기
        int max = sc.nextInt(); // 바구니의 최대값 숫자(N)
        int total = sc.nextInt(); // 전체 넣을 횟수(M)

        int[] numbers = new int[max]; // 바구니이자 출력값

        // 1. 바구니에 공 넣기
        // total만큼 반복
        for (int m = 0; m < total; m++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            int k = sc.nextInt();

            // i부터 j까지 k 숫자를 numbers에 저장
            // 인덱스로 인해 -1 해줌
            // 공이 있는 경우 새로운 공으로 대체하는 것은 계속 새로운 것을 대입하면 됨
            for (int n = i-1; n <= j-1; j++) {
                numbers[n] = k;
            }
        }

        // 2. 결과 출력하기
        // numbers 배열 출력
        for (int s = 0; s < max; s++) {
            System.out.print(numbers[s] + " ");
        }


    }
}