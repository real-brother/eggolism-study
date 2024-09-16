package BOJ_1654_랜선자르기;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int N = sc.nextInt();
		
		
		// max_lan_length -> 랜선의 최대 길이 = 답
		// max_length -> 입력 받은 랜선 중 최대 길이의 랜선
		long max_lan_length = Integer.MIN_VALUE;
		long max_length = Integer.MIN_VALUE;
		
		List<Long> line_lengths = new ArrayList<>();
		
		// 입력 받은 랜선 중 최대 길이의 랜선을 max_length에 할당
		for(int i = 0; i < K; i++) {
			long length = sc.nextInt();
			line_lengths.add(length);
			if(max_length < length) max_length = length;
		}
		
		// lan_count -> 해당 길이로 잘랐을 때 나오는 총 랜선의 개수
		// left right는 이분 탐색에서 사용 할 변수
		int lan_count = 0;
		long left = 1;
		long right = max_length;
		
		// 이분 탐색 사용
		while(left <= right) {
			// 총 랜선의 개수를 0으로 초기화 해준다.
			lan_count = 0;
			
			// 진짜 가장 중요 -> int로 선언하면 left + right에서 overflow 발생
			long lan_length = (left + right) / 2;
			
			// 리스트에 저장한 랜선을 꺼내서 랜선의 길이만큼 나누고 그 몫(만들 수 있는 랜선의 개수)을 총 랜선의 개수에 더 함 
			for(int i = 0; i < line_lengths.size(); i++) lan_count += line_lengths.get(i) / lan_length;
			
			// 탐색 위치 조정
			if(lan_count >= N) left = lan_length+1;
			else right = lan_length-1;
			
		}
		// 최대 랜선의 길이를 넣어준다 left는 목표한 랜선의 길이보다 1개 더 많이 만들 때 중 최소값이므로 -1을 해준다.
		// 예를 들면 목표가 5개라고 침 랜선의 길이(나오는 개수) 11(4) 12(5) 13(5) 14(5) 15(5) 16(6)
		// 이렇게 되면 16이 left이고 그 앞에 있는 15가 답인데 이는 left - 1이다
		max_lan_length = left - 1;
		
		System.out.print(max_lan_length);

	}

}
