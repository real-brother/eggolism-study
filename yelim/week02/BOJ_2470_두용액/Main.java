package BOJ_2470_두용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		/* 문제)
		 * 두 개의 용액을 합했을 때 0에 가장 가까운 용액의 특성값 구하기
		 */
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 용액의 수 입력받기
		int n = Integer.parseInt(st.nextToken());
		// 용액의 값 저장할 배열
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		// 용액의 값 입력받아 배열에 저장
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 용액 값 배열 오름차순 정렬하기
		Arrays.sort(arr);
		
		int left = 0;	// 용액 배열 최소값 인덱스 
		int right = arr.length-1;	// 용액 배열 최대값 인덱스
		
		// 두 용액을 더한 값의 절대값을 저장할 변수
		// 첫 비교를 위해 max 값으로 초기화
		int gap = Integer.MAX_VALUE;
		
		// 이전 두 용액의 gap과 비교할 변수
		int temp = 0;
		
		// 두 용액의 합을 저장할 변수
		int sum = 0;
		
		int min1 = 0;	// 첫 번째 최소값
		int min2 = 0;	// 두 번째 최소값
		

		// left가 right를 넘지 않는 동안 반복
		while (left < right) {
			sum = arr[left]+arr[right];	// 두 용액의 합 구하기
			temp = Math.abs(sum);	// 절대값 저장
			
			// 두 용액의 합의 절대값이 이전 gap보다 작은지 검사
			if (temp < gap) {
				gap = temp;			// gap 갱신
				min1 = arr[left];	// 최소값1 저장
				min2 = arr[right];	// 최소값2 저장
			}
			
			// 합이 0보다 작으면 합이 커져야 하므로 최소값 인덱스 오른쪽으로 이동
			if (sum < 0) {
				left++;
			}
			
			// 합이 0보다 크면 합이 작아져야 하므로 최대값 인덱스 왼쪽으로 이동
			else {
				right--;
			}
		}
		
//		System.out.println("gap="+gap);

		
		System.out.println(min1+" "+min2);

	}

}
