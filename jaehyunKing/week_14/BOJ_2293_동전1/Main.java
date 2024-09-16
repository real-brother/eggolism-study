package BOJ_2293_동전1;

// 동전을 1가지 종류만 사용할 때부터 n가지를 사용할 때 까지 증가하면서 배열 갱신

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[] coins = new int[n];
		int[] D = new int[k+1];
		for(int i = 0; i < n; i++) coins[i] = sc.nextInt();
		
		D[0] = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 1; j <= k; j++) {
				if(j - coins[i] >= 0) {
					D[j] += D[j-coins[i]];
				}
			}
		}
		
		System.out.println(D[k]);

	}

}