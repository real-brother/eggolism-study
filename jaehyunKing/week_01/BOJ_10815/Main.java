package BOJ_10815;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//숫자 카드 개수 N개
		int N = sc.nextInt();
		
		//탐색에는 Set가 빨라서 Set 사용
		Set<Integer> nums = new HashSet<>();
		
		//결과를 담을 리스트 생성
		List<Integer> result = new ArrayList<>();
		
		// 숫자 카드 N개 생성
		for(int i= 0 ; i < N; i++) nums.add(sc.nextInt());
	
		//숫자 카드 개수 N개
		int M  = sc.nextInt();
		
		// 가지고 있는 지(중복) 확인(중복이면 1 아니면 0)
		for(int i = 0; i < M; i++) {
			if(nums.contains(sc.nextInt()))result.add(1);
			else result.add(0);
		}
		for(int x : result) System.out.println(x);
	}
}
