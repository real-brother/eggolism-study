package BOJ_1931_회의실배정;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] meetings = new int[N][2];

        for (int i = 0; i < N; i++) {
            meetings[i][0] = sc.nextInt();
            meetings[i][1] = sc.nextInt();
        }

	     // meetings 배열을 끝나는 시간 기준으로 정렬
	     // Comparator.comparingInt()를 사용하여 배열의 두 번째 요소(arr[1])를 기준으로 비교
	     // thenComparingInt()를 사용하여 두 번째 요소가 같은 경우 첫 번째 요소(arr[0])를 추가로 비교
	     Arrays.sort(meetings, Comparator.comparingInt((int[] arr) -> arr[1]).thenComparingInt(arr -> arr[0]));

        int ans = 1;
        int endTime = meetings[0][1];
        for (int i = 1; i < N; i++) {
            if (meetings[i][0] >= endTime) {
                ans++;
                endTime = meetings[i][1];
            }
        }

        System.out.println(ans);

    }
}
