package BOJ_1931_회의실배정;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] meetings = new int[N][2];
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			meetings[i][0] = sc.nextInt();
			meetings[i][1] = sc.nextInt();
		}
		
		Arrays.sort(meetings, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		});
		
		int count = 1;
		int before = meetings[0][1];
		
		for(int i = 1; i < N; i++) {
			if(before <= meetings[i][0]) {
				before = meetings[i][1];
				count++;
			}
		}
		
		System.out.println(count);

	}

}