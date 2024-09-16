package Stack_Queue;

import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_Solution_11866 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		// 계속 삭제해줘야 하는데, ArrayList는 인덱스를 계속 옮겨줘야 해서 시간낭비
		LinkedList<Integer> list = new LinkedList<>();
		
		// 1부터 숫자 저장해주기
		for(int i=0; i<n; i++) {
			list.add(i+1);
		}

		// 삭제하는 인덱스의 값 저장해주기 위한 배열 (정답 배열)
		int[] arr = new int[list.size()];
		int idx = 0;	// 삭제하는 인덱스의 값 저장	
		int cIdx = 0;	// 현재 위치

		// list가 비어있을 때까지 반복
		while (list.size() != 0) {
			cIdx += k-1;	// 현재 위치를 k-1(삭제해줄 인덱스)위치에
			if (cIdx < list.size()) {	// 현재 인덱스가 리스트 사이즈보다 작을 때
				arr[idx] = list.remove(cIdx);	// 현재 인덱스를 삭제하고 배열에 저장
			}
			else {	// 현재 인덱스가 리스트 사이즈보다 클 때
				while (cIdx >= list.size()) {	// 리스트 사이즈보다 작아질 때까지
					cIdx -= list.size();	// 현재 인덱스에서 리스트 사이즈를 빼준다.
				}
				arr[idx] = list.remove(cIdx);	// 현재 인덱스 삭제하고 배열에 저장
			}
			idx++;	// 정답 배열 인덱스 1 증가
		}
		
		
		// 출력
		System.out.print("<");
		for(int i=0; i<n-1; i++) {
			System.out.print(arr[i]+", ");
		}
		System.out.print(arr[n-1]);
		System.out.print(">");

	}

}
