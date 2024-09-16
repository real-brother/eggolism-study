import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
         * 설계방식
         * 1. 공략해야 할 숫자는 회의시간이 끝나는 타이밍이다.
         * 2. 일반적인 상황에서 회의시간이 빨리 끝나면 (다음 회의를 잡을 수 있어) 최대로 할 수 있다.
         * 3. 그 다음으로는 회의를 빨리 시간하는 시작하는 순으로 하게
         * 4. 회의 시간과 회의 끝 시간이 같은 경우에 문제 발생
         */

        /*
         * 예외상황
         * 이 문제에서는 회의 시간과 종료 시간이 같은 경우에 발생할 확률이 높음
         * */


        Scanner sc = new Scanner(System.in);
        int total =sc.nextInt();

        int[][] matrix = new int[total][2];

        // 1. 회의 정보 채우기
        for (int i = 0; i < matrix.length; i++) {

            matrix[i][0] = sc.nextInt();
            matrix[i][1] = sc.nextInt();
        }

        // 2. 오름차순으로 정렬하기
        // 두번째 열이 기준
        // 반례 때문에 첫번째 열도 기준으로 추가
        Arrays.sort(matrix, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        // 순회하기
        // 회의 종료 조건은 turn을 전부다 돌았을 경우이다.
        int turn = 0; // 회의 차례 저장
        int end = 0; // 회의 종료 시간 저장
        int count = 0; // 회의 한 횟수 저장
        while(turn < total) {
            if (matrix[turn][0] >= end) {
                end = matrix[turn][1];
                count++;
            }
            turn++;
        }

        System.out.println(count);

    }
}
