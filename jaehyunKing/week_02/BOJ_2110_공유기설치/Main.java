package BOJ_2110_공유기설치;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	/* 아이디어
	 * 뭘 이분 탐색 -> 최대 거리
	 * 집 마다 거리 다른걸 어떻게? -> 차를 이용
	 * 이분 탐색 조건? -> 설치 가능 공유기 개수와 생성 가능한 공유기 개수 비교
	 */ 
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int C = sc.nextInt();
		
		// 집의 위치를 등록할 리스트 생성
		List<Integer> Home = new ArrayList<>();
		
		// 집의 위치를 입력받아서 리스트에 등록
		for(int i = 0; i < N; i++) Home.add(sc.nextInt());
		
		// 집의 위치를 오름차순으로 정렬
		Collections.sort(Home);
		
		// 왼쪽을 0, 오른쪽을 집의 최대 좌표로 설정
		int left = 0;
		int right = Home.get(Home.size()-1);
		
		// 이분 탐색
		while(left <= right) {
			
			int mid = (left + right) / 2;
			
			// mid의 길이로 측정했을 때 나오는 집의 개수를 count에 할당해준다.
			int count = 1;
			int before = 0;
			
			// 현재 집과 공유기를 직전에 설치했던 집과의 거리가 mid 이상이면 공유기를 설치하고 해당 index를 저장한다.
			for(int i = 1; i < Home.size(); i++) {
				if(Home.get(i) - Home.get(before) >= mid) {
					count++;
					before = i;
				}
			}
			
			// 집의 개수가 C 이상이면 left를 mid + 1로 옮겨준다
			if(count >= C) left = mid + 1;
			else right = mid - 1;
		}
		
		// 거리(개수)가 12(4) 13(4) 14(3) 15(3) 16(2) 이라고 가정했을 때 C = 3이라면
		// left는 최종적으로 16에 위치하게 된다 그러므로 여기서 1을 빼준 left - 1이 답이 된다.
		System.out.println(left - 1);
	}
}
