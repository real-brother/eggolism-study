import java.util.Scanner;

public class BOJ_10811_바구니뒤집기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*설계 아이디어
        * 1. 지정된 범위의 역순 만들기
        * 양쪽 끝 인덱스 변수를 설정하고 temp를 이용하여 스왑진행
        * 스왑의 종료조건은 변수가 같아지거나(홀수개) 둘이 교차되는 상황(짝수개)*/

        // 입력 받기
        int max = sc.nextInt(); // 전체 바구니의 수(N)
        int total = sc.nextInt(); // 총 역순을 행할 수(M)

        // 1. 바구니 채우기
        int[] numbers = new int[max+1]; // 인덱스 편하게 하려고 +1

        // 초기 바구니 1부터 숫자 채우기
        for (int i = 1; i <= max; i++) {
            numbers[i] = i;
        }

        // 2. 바구니 역순으로 만들기
        // total번 반복
        // 역순은 왼쪽 인덱스가 오른쪽 인덱스를 같거나 커지기 전까지 계속 스왑 진행
        for (int i = 1; i <= total; i++) {
            int temp = 0; // 스왑을 위한 임시 변수
            int a = sc.nextInt(); // 역순 시작 (왼쪽)
            int b = sc.nextInt(); // 역순 끝 (오른쪽)

            // 스왑 반복
            while (a < b) {
                temp = numbers[a];
                numbers[a] = numbers[b];
                numbers[b] = temp;

                a++;
                b--;
            } // 스왑 끝

        }

        // 3. 결과 출력
        // 바구니 배열 출력
        for (int k = 1; k <= max; k++) {
            System.out.print(numbers[k] + " ");
        }


    }
}