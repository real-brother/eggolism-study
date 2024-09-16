package BOJ_11399_ATM;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] timeList = new int[n];
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			timeList[i] = sc.nextInt();
		}
		Arrays.sort(timeList);
		
		// 사람들을 오름차순으로 정렬해야 기다리는 시간의 총합을 최소화 할 수 있음
		
		int totalTime = 0;
		int temp = 0;
		for (int i = 0; i < timeList.length; i++) {
			temp += timeList[i];
			totalTime += temp;
		}
		System.out.println(totalTime);
	}
}