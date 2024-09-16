package BOJ_10810;

import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] nums  = new int[N];
		for(int m = 0; m < M; m++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			int k = sc.nextInt();
			for(int l = i; l <= j ; l++) {
				nums[l-1] = k;
			}
		}
		for(int x : nums) System.out.print(x + " ");
	}
}
