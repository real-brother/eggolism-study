package BOJ_1764;


import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		
		//탐색 속도가 빠른 Set을 사용, 
		//여기서는 contains() 메서드를 사용할 때 시간이 빠름
		Set<String> listen = new HashSet<>();
		List<String> both = new ArrayList<>();
		
		//들도 못한 사람 N명 -> Set에 저장
		for(int i = 0; i < N; i++) listen.add(sc.next());
		
		//보도 못한 사람을 입력받아 Set과 비교 후에 Set에 존재한다면
		//both 리스트에 추가
		for(int i = 0; i < M; i++) {
			String s = sc.next();
			if(listen.contains(s)) both.add(s);
		}
		
		//Collection에 있는 sort를 사용(String은 기본적으로 구현되어있음
		Collections.sort(both);
		System.out.println(both.size());
		for(String x : both) System.out.println(x);
	}
}
