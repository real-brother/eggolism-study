package BOJ_12852_1로만들기_2;

import java.util.Scanner;

// 배열을 채울 때 이전에 어디서 왔는지를 기록해서
// 해당 위치로 이동 -> 1이 될 때까지 반복

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] D = new int[N+1];
		int[] before = new int[N+1];
		
		for(int i = 2; i <= N; i++) D[i] = Integer.MAX_VALUE; 
		D[1] = 0;
		
		for(int i = 1; i <= N; i++) {
			
			if(i % 3 == 0 && D[i] > D[i/3] + 1) {
				D[i] = D[i/3] + 1;
				before[i] = i / 3;
			}
			
			if(i % 2 == 0 && D[i] > D[i/2] + 1) {
				D[i] = D[i/2] + 1;
				before[i] = i / 2;
			}
			
			if(D[i] > D[i-1] + 1) {
				D[i] = D[i-1] + 1;
				before[i] = i - 1;
			}
		}
		
		
		System.out.println(D[N]);
		while(true) {
			System.out.print(N + " ");
			if(N == 1) break;
			N = before[N];
		}
	}
}