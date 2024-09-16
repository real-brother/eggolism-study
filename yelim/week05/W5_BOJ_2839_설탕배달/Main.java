package W5_BOJ_2839_설탕배달;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/* 문제) 설탕배달
		 * 봉지는 3킬로그램 봉지와 5킬로그램 봉지가 있다.
		 * 상근이가 설탕을 정확하게 N킬로그램 배달해야 할 때, 
		 * 봉지 몇 개를 가져가면 되는지 그 수를 구하는 프로그램을 작성하시오.
		 * 
		 * 상근이가 배달하는 봉지의 최소 개수를 출력
		 */

		Scanner sc = new Scanner(System.in);
		
		int total = sc.nextInt();
		int cnt = 0;
		
		while (true) {
			// 5로 나누어지는지 확인
			// 나누어지면 cnt에 5로 나눈 값을 더해주고 출력 후 break
			if (total%5==0) {
				cnt += total/5;
				System.out.println(cnt);
				break;
			}
			// 5로 나누어지지 않는데, total이 0보다 작으면(정확하게 나누어떨어지지 않으면)
			// -1 출력 후 break
			else if (total<0){
				System.out.println(-1);
				break;
			}
			// 5로 나누어 떨어지지 않으면 total에서 3을 빼주고 cnt++
			total -= 3;
			cnt++;
		}
	}

}
