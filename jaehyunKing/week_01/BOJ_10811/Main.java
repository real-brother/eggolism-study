package BOJ_10811;

import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] basket = new int[N];
		for(int i = 0; i < N; i++) {
			basket[i] = i + 1;
		}
		for(int i = 0; i < M; i++) {
			int I = sc.nextInt();
			int J = sc.nextInt();
			int spare = 0;
			int b = 0;
			for(int k = I-1; k < J-1-b; k++){
				spare = basket[k];
				basket[k] = basket[J-1-b];
				basket[J-1-b] = spare;
				b++;
			}
		}
		for(int x : basket) System.out.print(x+" ");
	}
}
