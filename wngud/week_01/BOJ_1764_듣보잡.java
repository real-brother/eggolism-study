import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BOJ_1764_듣보잡 {
	public static void main(String[] args) {

		/*설계 아이디어
		* 1. 교집합을 구하는 것이니 Set 사용
		* 2. 사전순으로 출력해야 함
		* Set은 순서가 없는 자료구조이기 때문에 List로 변환하여 사용*/

		/*
		ss.addAll(ss2);	// (1 2 3 4) ∪ (3 4 5 6) = (1 2 3 4 5 6) 합집합
		ss.retainAll(ss2);	// (1 2 3 4) ∩ (3 4 5 6) = (3 4) 교집합
		ss.removeAll(ss2);	// (1 2 3 4) - (3 4 5 6) = (1 2) 차집합
		*/

		// 입력 받기
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		sc.nextLine();
		
		// 1. Set 선언
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		
		// 2. N 넣기
		for (int i = 0; i < N; i++) {
			String input = sc.nextLine();
			set1.add(input);
		}
		
		// 3. M 넣기
		for (int i = 0; i < M; i++) {
			String input = sc.nextLine();
			set2.add(input);
		}

		// 4. 교집합 구하기
		// 교집합 개수 출력
		set1.retainAll(set2); // retainAll은 set1을 업데이트해주는 방식 (새로운 반환X)
		System.out.println(set1.size());
		
		// 5. 리스트로 변환하고, 사전순으로 배열하기
		// 리스트 출력
		List<String> sorted_set1 = new ArrayList<>(set1);
		Collections.sort(sorted_set1); // Arrays.sort() 안됨

		for (String str : sorted_set1) {
			System.out.println(str);
		}



	}
}