package BOJ_11726_2xN타일링;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Long> s = new ArrayList<>();
		s.add((long) 0);
		s.add((long) 1);
		s.add((long) 2);
		// 타일링 규칙: 피보나치 수열
		for (int i = 3; i < n+1; i++) {
			// 오버플로우 방지를 위해 나머지 연산을 계속 수행
			s.add((s.get(i-2) + s.get(i-1)) % 10007);
		}
		System.out.println(s.get(n));
	}
}
