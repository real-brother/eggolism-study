package BOJ_1927_최소힙;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 우선순위 큐를 선언, 우선순위 큐는 기본적으로 최소힙(작은 값이 우선순위를 가짐)
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		int N = sc.nextInt();
		
		for(int i = 0; i < N; i++){
			int order = sc.nextInt();
			// 입력받은 숫자가 0일 때, 큐가 비어있으면 0을 출력 그 외에는
			// 큐에서 최소값 하나를 출력
			if(order == 0) {
				if(queue.isEmpty())System.out.println(0);
				else System.out.println(queue.poll());
			}
			// 0이 아니면 입력값을 큐에 넣음
			else queue.offer(order);
		}
	}
}
