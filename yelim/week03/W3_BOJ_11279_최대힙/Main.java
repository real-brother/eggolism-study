package W3_BOJ_11279_최대힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static List<Integer> heap = new ArrayList<>(); 
	static int pointer;

	public static void main(String[] args) throws IOException {
		/* 문제) 최대 힙
		 * 최대 힙을 이용하여 다음과 같은 연산을 지원하는 프로그램을 작성하시오.
		 * 배열에 자연수 x를 넣는다.
		 * 배열에서 가장 큰 값을 출력하고, 그 값을 배열에서 제거한다.
		 * 프로그램은 처음에 비어있는 배열에서 시작하게 된다.
		 */
		
		/* 힙 구현
		 * <push>
		 * 0번 인덱스는 0으로 고정시키고, 1번 인덱스부터 리스트에 값 push
		 * push하고, 부모 노드와 값 비교해서 더 크면 부모노드와 swap
		 * 
		 * <pop>
		 * 0이면 pop 해주는데, 인덱스 0번을 0으로 고정시켰기 때문에, 사이즈가 1일 경우 0 리턴
		 * 가장 큰 값, 즉 루트노드인 인덱스 1번을 저장해두고, 가장 마지막 노드의 값을 1번 인덱스에 저장
		 * 가장 마지막 노드 삭제
		 * 루트 노드가 된 값을 자식 노드들과 비교하여 자식 노드 중 큰 값이 있으면 swap
		 * swap 할 것이 없다면 break 
		 */
		
		// 또 버퍼야... 시간초과ㅠ
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		heap.add(0);	// 0번 인덱스는 사용하지 않음.
		
		for(int i=0; i<n; i++) {
			int x = Integer.parseInt(br.readLine());	// x값 입력받기
			// x가 0이 아니라면 push 해주기
			if (x != 0) {
				push(x);
//				System.out.println(heap);
			}
			
			// x가 0이라면 pop하고 출력
			else {
				System.out.println(pop());
			}
		}
		
	}
	
	// 힙에 데이터 삽입하는 메소드
	public static void push(int data) {
		heap.add(data);	// 데이터 삽입
		pointer = heap.size()-1;	// 포인터를 삽입한 데이터 인덱스 = 힙의 사이즈-1과 같음
		
		// 포인터가 1보다 크고(비어있지 않고), 부모노드보다 삽입한 데이터가 작은 경우 반복
		// 현재 노드의 인덱스를 2로 나누면 부모 노드의 인덱스
		while (pointer>1 && heap.get(pointer/2)<heap.get(pointer)) {
			// 부모노드와 삽입한 노드의 값을 swap
			int temp = heap.get(pointer/2);
			heap.set(pointer/2, heap.get(pointer));
			heap.set(pointer, temp);
			
			// 부모노드로 옮겨서 또 검사해주기
			// 이거 안넣었더니 틀림!! 1 2 3 4 이렇게 넣어주면 push가 [3, 4, 2, 1] 이렇게 됨..
			pointer /= 2;
		}
	}
	
	// 힙에 데이터를 삭제하는 메소드 (가장 큰 값(루트) 삭제)
	public static int pop() {
		
		// 힙의 사이즈가 1이면 0 리턴 (인덱스 0을 0으로 고정시켜주었기 때문에)
		if(heap.size()==1) {
			return 0;
		}
		
		// 루트 노드의 값을 따로 저장해두고, 루트노드에 말단 노드 값 넣어준 후, 말단 노드 삭제
		int pop = heap.get(1);
		heap.set(1, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		
		// 포인터는 루트노드로 초기화
		int pointer = 1;
		
		while(true) {
			int left = pointer*2;		// 왼쪽 자식 노드
			int right = pointer*2+1;	// 오른쪽 자식 노드
			
			// 리프노드 확인
			if (left >= heap.size()) {
				break;
			}
			
			// 큰 값이 있다면 저장할 변수
			int next = pointer;
			
			// 왼쪽 노드와 비교
			if (heap.get(next) < heap.get(left)) {
				next = left;
			}
			
			// 오른쪽 노드와 비교
			if (right < heap.size() && heap.get(next)<heap.get(right)) {
				next = right;
			}
			
			// 자식노드 중에 큰 값이 없으면 break
			if (next == pointer) {
				break;
			}
			
			// pointer 노드의 값과 두 노드 중 큰 값을 swap
			int temp = heap.get(pointer);
			heap.set(pointer, heap.get(next));
			heap.set(next, temp);
			pointer = next;
		}
		
		return pop;
		
	}

}
