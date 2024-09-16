package BOJ_1654_랜선자르기;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		/* 문제)
		 * n개의 랜선
		 * k개의 길이가 제각각인 랜선
		 * 기존 k개의 랜선으로 n개의 랜선을 만들 수는 없음.
		 * 만들 수 있는 최대 랜선의 길이
		 */
		
		Scanner sc = new Scanner(System.in);
		
		int k = sc.nextInt();	// 이미 가지고 있는 개수 k
		int n = sc.nextInt();	// 필요한 랜선의 개수 n
		long[] arr = new long[k];
		//아니 long으로 안했다가 계속 틀림;; 이거 맞아?
		
		// 랜선 길이는 자연수이기 때문에 1로 초기화
		long min = 1;
		long max = 0;	// 최대값은 이따 설정할거니까
		long mid = 0;	// mid도 설정할거니까
		
		int cnt = 0;	// 얻을 수 있는 랜선 개수 저장할 변수
		
		// k의 길이 입력받아서 배열에 저장
		// 최대값 저장
		for(int i=0; i<k; i++) {
			arr[i] = sc.nextInt();
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		
		max++;	// ?? 가장 긴 랜선이 최대길이가 될 수 있음..
		
		// 이분 탐색
		while (min < max) {
			mid = (min+max)/2;	// 중간 길이 값 저장
			cnt = 0;	// 개수 0으로 초기화
			for(int i=0; i<k; i++) {
				cnt += (arr[i]/mid);	// 나누기로 몇 개의 랜선 얻을 수 있는지 계산 후 더해주기
			}
			// 필요한 개수보다 얻을 수 있는 개수가 적을 경우
			// 최대값을 줄여서 자를 길이를 감소시킨다.
			if (cnt < n) {
				max = mid;
			}
			// 많거나 같을 경우
			// 최소값을 증가시켜서 자를 길이를 증가시킨다.
			else  {
				min = mid+1;
			}
			
		}
		
		// 마지막에 1 더해주니까 빼주기!
		System.out.println(min-1);
		
	}

}
