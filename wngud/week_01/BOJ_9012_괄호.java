import java.util.Scanner;

public class BOJ_9012_괄호 {
	public static void main(String[] args) {

		/* 설계 아이디어
		 * 1. `(`와 `)`의 갯수를 찾아서 둘의 값 비교
		 * 단순 개수는 `)(`와 같은 예외 케이스를 판별하지 못함
		 * -> 개수 + 순서가 중요
		 *
		 * 2. `)`가 `(`보다 갯수가 많아지는 경우가 발생하면 안됨 (예외 발생)
		 * 만약 그렇다면 `is_false` 실행
		 */

		// 입력 받기
		Scanner sc = new Scanner(System.in);

		int total = sc.nextInt(); // 전체 문제 개수(T)
		sc.nextLine();

		// 1. 완성된 괄호인지 확인하기
		// 전체 케이스 시작
		for (int T = 0; T < total; T++) {

			// 1개 줄 세팅
			String input = sc.nextLine(); // 입력 받기
			boolean is_false = false;
			int lft = 0; // `(` 의미
			int rgt = 0; // `)` 의미

			// 1개 줄 시작
			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) == '(') {
					lft++;
				} else {
					rgt++;
					
					// 예외 판별
					if (rgt > lft) {
						is_false = true; // 문제 발생
						break; // 더 이상 볼 필요가 없음
					}
				}
			
			}
			
			// 2. 완성된 괄호 판별
			// 개수가 같아야 하고, 예외가 없었어야 함
			if (lft == rgt && is_false == false) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			// 1개 줄 끝
			
		} // 전체 케이스 끝

	}
}