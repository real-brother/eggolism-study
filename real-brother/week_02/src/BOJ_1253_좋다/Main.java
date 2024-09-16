package BOJ_1253_좋다;

import java.util.*;

public class Main {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 수의 개수 입력
        int N = scanner.nextInt();

        // 수열 입력
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt();
        }

        // 좋은 수의 개수 계산
        int goodNumberCount = countGoodNumbers(N, numbers);

        // 결과 출력
        System.out.println(goodNumberCount);

        scanner.close();
    }

	private static int countGoodNumbers(int N, int[] numbers) {
        Arrays.sort(numbers); // 수열을 정렬

        int count = 0;

        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;

            while (left < right) {
                int sum = numbers[left] + numbers[right];

                // 현재 수와 두 포인터의 합이 같고, 두 포인터가 현재 수와 다른 위치에 있는 경우
                if (sum ==  numbers[i]) {
                	if (i != left && i != right) {
                		count++;
						break;
					} else if (left == i) {
						left++;
					} else {
						right--;
					}
                } else if (sum < numbers[i]) {
                    // 합이 현재 수보다 작으면 left 포인터를 오른쪽으로 이동
                    left++;
                } else {
                    // 합이 현재 수보다 크면 right 포인터를 왼쪽으로 이동
                    right--;
                }
            }
        }

        return count;
    }
}