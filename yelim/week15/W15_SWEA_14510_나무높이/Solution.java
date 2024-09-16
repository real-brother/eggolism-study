package W15_SWEA_14510_나무높이;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		/* 문제) 14510_나무 높이
		 * n개의 나무의 키가 주어짐. 하루에 한 나무에 물을 줄 수 있음.
		 * 홀수 번째 날에 물을 주면 키가 1 자라고, 짝수 번째 날은 2 자람.
		 * 모든 나무의 키가 처음에 가장 키가 컸던 나무와 같아지도록 할 수 있는 최소 날짜
		 */
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int n = sc.nextInt();
			int[] tree = new int[n];
			int max = 0;	// 가장 높은 나무의 키
			
			for(int i=0; i<n; i++) {
				tree[i] = sc.nextInt();
				
				// 최대값 구하기
				max = Math.max(max, tree[i]);
			}
			
			
			int odd = 0;
			int even = 0;
			
			for(int i=0; i<n; i++) {
				int diff = max - tree[i];
				
				// 2의 개수와 1의 개수 구해서 저장
				if (diff != 0) {
					even += diff/2;
					odd += diff%2;
				}
			}
			
			// 2의 개수가 1보다 많으면 1 두 개로 바꾸기
			if (even>odd) {
				while(even-odd > 1) {
					even--;
					odd += 2;
				}
			}
			
			int ans = 0;
			
			// 1이 많으면
			if (odd > even) {
				ans = odd*2 - 1;
			}
			// 2가 많으면 2의 개수에 *2
			else if (even > odd) {
				ans = even * 2;
			}
			// 같으면 두 개수 더해주기
			else {
				ans = odd + even;
			}
			
			System.out.printf("#%d %d%n", t, ans);
		}
	}

}
