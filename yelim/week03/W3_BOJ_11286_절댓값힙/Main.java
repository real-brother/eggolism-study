package W3_BOJ_11286_절댓값힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static List<Integer> heap = new ArrayList<>();
	static int pointer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		/* 문제) 절댓값 힙
		 * 배열에 정수 x (x ≠ 0)를 넣는다.
		 * 배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다. 
		 * 절댓값이 가장 작은 값이 여러개일 때는, 가장 작은 수를 출력하고, 그 값을 배열에서 제거한다.
		 * 프로그램은 처음에 비어있는 배열에서 시작하게 된다.
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		heap.add(0);	// 0번째 인덱스는 사용하지 않음. 0으로 고정
		
		for(int i=0; i<n; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if (x != 0) {
				push(x);
//				System.out.println(heap);
			}
			else {
				System.out.println(pop());
//				System.out.println("pop="+heap);
			}
			
		}
		
	}
	
	// 힙에 값 삽입하는 메소드
	public static void push(int data) {
		heap.add(data);	// 힙에 데이터 넣어주기
		pointer = heap.size()-1;	// 포인터는 힙 크기 -1(방금 삽입한 데이터의 인덱스)
		
		// 포인터가 1보다 크고(힙이 비어있지 않고), 부모노드가 삽입한 노드보다 클 때 swap
		// 절대값이라고 했으므로, 부모노드의 절댓값과 삽입한 노드의 절댓값을 비교.
		while (pointer>1 && Math.abs(heap.get(pointer/2))>=Math.abs(heap.get(pointer))) {
			// 만약, 두 값의 절댓값이 같다면
			if (Math.abs(heap.get(pointer/2)) == Math.abs(heap.get(pointer))) {
				// 한 번 더 조건 확인 -> 부모 노드가 더 작으면 break				
				if (heap.get(pointer/2) < heap.get(pointer)) {
					break;
				}
			}
			// swap
			int temp = heap.get(pointer);
			heap.set(pointer, heap.get(pointer/2));
			heap.set(pointer/2, temp);
			
			// 포인터를 삽입한 노드의 부모노드로 옮기고
			// 그 노드의 부모노드와 또 비교를 반복하도록 포인터 값 변경
			pointer /= 2;
		}
	}
	
	
	// 힙에서 값을 삭제하는 메소드
	public static int pop() {
		// 힙이 0번 인덱스 빼고 비어있다면 0 리턴
		if (heap.size() == 1) {
			return 0;
		}
		
		// 루트 노드의 값, 즉 가장 작은 값을 변수에 따로 저장
		int pop = heap.get(1);
		// 루트 노드에 말단 노드의 값 저장
		heap.set(1, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		
		pointer = 1;
		
		while (true) {
			int left = pointer*2;
			int right = pointer*2+1;
			
			// 리프노드 확인
			if (left >= heap.size()) {
				break;
			}
			
			// pointer가 가리키는 값보다 작을 경우 그 인덱스를 저장해줄 변수
			int next = pointer;
			
			// 왼쪽 값의 절댓값과 next가 가리키는 값의 절댓값 비교
			if (Math.abs(heap.get(next)) > Math.abs(heap.get(left))) {
				next = left;
			}
			else if (Math.abs(heap.get(next)) == Math.abs(heap.get(left))) {
				if (heap.get(next) > heap.get(left)) {
					next = left;
				}
			}
			
			
			// 오른쪽 값의 절댓값과 next가 가리키는 값의 절댓값 비교
			if (right<heap.size() && Math.abs(heap.get(next)) > Math.abs(heap.get(right))) {
				next = right;
			}
			else if (right<heap.size() && Math.abs(heap.get(next)) == Math.abs(heap.get(right))) {
				if (heap.get(next) > heap.get(right)) {
					next = right;
				}
			}
			
			// 더 작은 값이 없을 때
			if (next == pointer) {
				break;
			}
			
			// swap
			int temp = heap.get(pointer);
			heap.set(pointer, heap.get(next));
			heap.set(next, temp);
			
			// 포인터를 다음 인덱스로 옮겨주기
			pointer = next;
		}
		return pop;
	}

}
