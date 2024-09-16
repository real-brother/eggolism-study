package BOJ_2805_나무자르기;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/* 문제)
		 * 나무의 수 n과 필요한 나무 길이 m
		 * 나무의 수만큼 높이 입력
		 * m미터의 나무를 가져가기 위해 절단기에 설정할 수 있는 높이의 최댓값 출력
		 */

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();	// 나무 개수
		int m = sc.nextInt();	// 필요한 나무 길이
		
		int min = 0;	// 나무 최소길이 초기화
		int max = 0;	// 나무 최대길이 초기화
		int mid = 0;	// 중간 값 초기화
		
		int[] arr = new int[n];	// 나무 길이 저장할 배열
		
		// 길이 입력받아 배열에 저장
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
			
			// 최대 길이 찾아서 max에 저장
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		
		// 최소값이 최대값보다 작을 동안 반복
		while (min < max) {
			// 중간 길이 구하기 (절단기 높이)
			mid = (min+max)/2;
			// 합 저장할 변수 초기화
			long sum = 0;
			// 길이만큼 반복 돌면서 
			for(int i=0; i<n; i++) {
				// 해당 나무 길이에서 절단기 높이를 뺀 값이 0보다 크면 (절단기보다 나무가 크면)
				if (arr[i]-mid > 0) {
					sum += (arr[i]-mid);	// 가져갈 수 있는 나무 길이 sum에 더하기
				}
			}
			// 가져갈 수 있는 나무 길이의 합이 필요한 길이보다 작을 경우
			// 절단기 높이를 낮춰준다.
			if (sum < m) {
				max = mid;
			}
			
			// 가져갈 수 있는 나무 길이의 합이 필요한 길이보다 크거나 같을 경우
			// 절단기 높이를 높여준다.
			else {
				min = mid+1;
			}

		}

		// 마지막에 1더해줬으니까 빼주기!
		System.out.println(min-1);
		
	}

}
