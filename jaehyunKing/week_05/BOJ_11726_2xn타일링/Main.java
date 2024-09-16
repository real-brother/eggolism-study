package BOJ_11726_2xn타일링;

import java.util.Scanner;

public class Main {
	static int memoization[];

	public static void main(String[] args) {
		
		// 1이 1인 2가 2인 피보나치 수열 -> 임의로 0도 1이 되게함
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		memoization = new int[n+1];
		
		System.out.println(fibo(n));
	}

	static int fibo(int n) {
		if(n <= 1) return 1;
		if(memoization[n] != 0) return memoization[n];
		memoization[n] = (fibo(n-1) + fibo(n-2)) % 10007;
		return memoization[n];
	}
}