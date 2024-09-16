package Stack_Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_Solution_17608 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();	// 막대기 개수
		List<Integer> arr = new ArrayList<>();
		
		// 리스트에 저장
		for(int i=0; i<n; i++) {
			arr.add(i, sc.nextInt());
		}
		
		// 맨 오른쪽은 무조건 보이기 때문에 cnt를 1로 초기화
		int cnt = 1;
		int max = arr.get(n-1);	// 가장 오른쪽을 max값으로 설정
		
		// 오른쪽에서 두번째부터 맨 앞까지 돌면서 max보다 큰지 확인
		// max보다 크면 max에 저장하고 cnt++
		for(int i=n-2; i>=0; i--) {
			if (arr.get(i) > max) {
				max = arr.get(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);

	}

}
