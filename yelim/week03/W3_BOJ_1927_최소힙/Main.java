package W3_BOJ_1927_최소힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static List<Integer> heap = new ArrayList<>();
	static int pointer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		/* 문제) 최소 힙
		 * 배열에 자연수 x를 넣는다.
		 * 배열에서 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.
		 * 프로그램은 처음에 비어있는 배열에서 시작하게 된다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		// 첫 번째 인덱스 값 0으로 고정. 사용 X
		heap.add(0);
		
		for(int i=0; i<n; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if (x != 0) {
				push(x);
//				System.out.println(heap);
			}
			else {
				System.out.println(pop());
			}
		}

	}
	
	// 힙에 push하는 메소드
	public static void push(int data) {
		heap.add(data);		// 힙에 데이터 추가
		
		pointer = heap.size()-1;	// 포인터를 방금 삽입한 마지막 노드에 위치
		
		// 부모노드가 삽입한 값보다 크면 swap
		while (pointer>1 && heap.get(pointer/2)>heap.get(pointer)) {
			int temp = heap.get(pointer/2);
			heap.set(pointer/2, heap.get(pointer));
			heap.set(pointer, temp);
			
			pointer /= 2;
		}
		
	}
	
	// 힙에서 가장 작은 값(루트) pop하는 메소드
	public static int pop() {
		// 힙의 사이즈가 0이라면 (인덱스 0은 고정되어 있으므로) 0 리턴
		if(heap.size()==1) {
			return 0;
		}
		
		// 가장 작은 값인 루트 노드의 값을 따로 저장해주기
		int pop = heap.get(1);
		// 루트 노드에 마지막 노드 값 넣어주기
		heap.set(1, heap.get(heap.size()-1));
		// 마지막 노드 삭제
		heap.remove(heap.size()-1);
		
		pointer = 1;
		
		while(true) {
			int left = pointer*2;
			int right = pointer*2+1;
			
			// 리프노드 확인
			if (left >= heap.size()) {
				break;
			}
			
			// 작은 값이 있다면 저장할 변수
			int next = pointer;
			
			// 왼쪽 자식노드와 비교해서 더 작으면 왼쪽값 넣어주기
			if (heap.get(next) > heap.get(left)) {
				next = left;
			}
			
			// 오른쪽 자식노드와 비교해서 더 작으면 오른쪽 값 넣어주기
			if (right<heap.size() && heap.get(next) > heap.get(right)){
				next = right;
			}
			
			// 더 작은 값이 없으면 break
			if (next == pointer) {
				break;
			}
			
			// swap
			int temp = heap.get(pointer);
			heap.set(pointer, heap.get(next));
			heap.set(next, temp);
			
			pointer = next;
		}
		return pop;
		
	}

}
