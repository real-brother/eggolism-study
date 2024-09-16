package BOJ_2839_설탕배달;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sugar = sc.nextInt();
		int bag = 0;
		
		// 남은 설탕이 있는 동안
		while (sugar >= 0) {
			// 5의 배수라면 몫으로 마무리 해버리기
			if (sugar % 5 == 0) {
				bag += (sugar / 5);
				System.out.println(bag);
				break;
			}
			
			// 5의 배수가 아니라면 3씩 계속 빼주기
			sugar -= 3;
			bag++;
			
		}
		// 0이 안맞춰진다면 -1리턴
		if (sugar < 0) {
			System.out.println(-1);
		}
	}

}