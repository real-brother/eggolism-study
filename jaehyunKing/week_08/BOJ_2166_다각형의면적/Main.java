package BOJ_2166_다각형의면적;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		// 10만 * 10만 때문에 long으로 선언 (100억)
		long[][] arr = new long[2][2];
		
		double sum = 0;
		
		for(int i = 0; i < N; i++) {
			if(i <= 1) {
				arr[i][1] = sc.nextLong();
				arr[i][0] = sc.nextLong();
			}
			else if(i >= 2) {
				long now_x = sc.nextLong();
				long now_y = sc.nextLong();
				// 신발 끈 공식
				sum += ((arr[0][1] * arr[1][0] + arr[1][1] * now_y + now_x * arr[0][0]) - (arr[1][1] * arr[0][0] + now_x * arr[1][0] + arr[0][1] * now_y)) * 0.5;
				arr[1][0] = now_y;
				arr[1][1] = now_x;
			}
		}
		
		// 절댓값을 위의 sum에서 해주면 파먹은 도형 넓이가 제대로 안 구해진다
		// 솔직히 넓이 공식은 보고했음
		sum = Math.abs(sum);
		
		System.out.printf("%.1f", sum);
		
	}
	
}