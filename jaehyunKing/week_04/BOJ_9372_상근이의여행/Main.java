package BOJ_9372_상근이의여행;

import java.util.Scanner;

/*
 *  편법
 *  국가 2개를 여행하려면 비행기 1개가 필요
 *  국가 3개를 여행하려면 비행기 2개가 필요
 *  국가 N개를 여행하려면 비행기 N-1개가 필요
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			for(int j = 0; j < M; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
			}
			System.out.println(N-1);
		}
	}

}