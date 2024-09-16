package Simulation;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_Solution_10811 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();	// 바구니 개수
		int m = sc.nextInt();	// 역순으로 만들 횟수
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = i+1;
		}
		
		// 횟수만큼 반복
		for (int a=0; a<m; a++) {
			int[] copyArr = Arrays.copyOf(arr, n);	// 배열 카피
			int i = sc.nextInt();	// i번부터
			int j = sc.nextInt();	// j번까지 역순으로 만들기
			
			// j-1의 차가 2 이상일 경우 (인접해있지 않은 경우)
			if (j-i >= 2) {
				while (j-i >= 1) {
					// 인덱스가 i와 j보다 1 작으므로 1을 뺀 인덱스에 swap
					arr[i-1] = copyArr[j-1];
					arr[j-1] = copyArr[i-1];
					i++;
					j--;
				}
				
			}
			// 인접해있는 경우 반복 없이 둘만 바꿔주기
			else {
				arr[i-1] = copyArr[j-1];
				arr[j-1] = copyArr[i-1];
			}

		}
		
		for(int o:arr) {
			System.out.print(o+" ");
		}

	}

}
