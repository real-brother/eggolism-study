package Simulation;

import java.util.Scanner;

public class BOJ_Solution_10810 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] arr = new int[n];	
				
		for(int a=0; a<m; a++) {
			int i = sc.nextInt();	// i번부터
			int j = sc.nextInt();	// j번까지
			int k = sc.nextInt();	// k번 공을 넣기
			
			// i번부터 j번까지 반복 돌며 k 저장
			for(int b=(i-1); b<j; b++) {
				arr[b] = k;
			}
		}
		
		for(int x : arr) {
			System.out.print(x+" ");
		}

	}

}
