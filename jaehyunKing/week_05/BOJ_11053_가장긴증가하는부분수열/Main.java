package BOJ_11053_가장긴증가하는부분수열;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] nums = new int[N];
		int[] lengths = new int[N];
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
			for(int j = 0; j < i; j++) {
				if(nums[i] > nums[j] && lengths[i] <= lengths[j]) lengths[i] = lengths[j]+1;
				if(lengths[i] > max) max = lengths[i];
			}
		}
		
		System.out.println(max + 1);
	}

}