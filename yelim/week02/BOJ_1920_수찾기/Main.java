package BOJ_1920_수찾기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static int binarySearch(int[] arr, int key) {
		// 이분탐색
		int left = 0;	// 왼쪽 값 인덱스 0으로 초기화
		int right = arr.length - 1;	// 오른쪽 값 인덱스 마지막으로 초기화

		
		while (left <= right) {
			int mid = (left + right) / 2;
			if (key == arr[mid]) {
				return 1;
			} else if (key > arr[mid]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) {

		/*
		 * 문제 정수의 개수 n n개의 정수 (A) 정수의 개수 m m개의 정수 이 수들이 A 안에 존재하는지 알아보기
		 */

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] arr = new int[n]; // A 배열

		// A에 들어갈 정수 입력받기
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		int m = sc.nextInt();
		int[] arr2 = new int[m];

		// A에 있는지 확인할 값들 입력받기
		for (int i = 0; i < m; i++) {
			arr2[i] = sc.nextInt();
		}

		// sort 해주기
		Arrays.sort(arr);

		// 이분탐색
		for (int i = 0; i < arr2.length; i++) {
			int result = binarySearch(arr, arr2[i]);
			System.out.println(result);
		}

	}

}
