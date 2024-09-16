package BOJ_1300_K번째수;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static int k;
	
	public static void main(String[] args) {
		n = sc.nextInt();
		k = sc.nextInt();
		
		// B[k] = x라는 말의 정확한 의미 -> x보다 작거나 같은 수의 개수는 최소 k개
		
		// 목표 = x보다 작은 원소들의 개수를 찾는것
		// (x / 각 단의 숫자) 전체의 합 = x보다 작은 원소들의 개수 = k
		// 이분탐색으로 1~k사이를 이분탐색하며 결과값이 k값과 일치하는지 찾으면됨
		
		long start = 1;
		long end = k; //k의 최대값이 n^2이기 때문에 end값을 k로 설정
		
		while (start < end) {
			long mid = (start + end ) / 2;
			long count = 0;
			
			// 각 단의 숫자로 나눈값을 누적합해준다
			for (int i = 1; i <= n; i++) {
				count += Math.min(mid / i, n);
			}
			
			// 이분탐색 범위 조정
			if (k <= count)	end = mid; 
			else start = mid + 1;
		}
		
		System.out.println(start);
	}
}