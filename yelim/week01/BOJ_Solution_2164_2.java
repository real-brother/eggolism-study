package Stack_Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_Solution_2164_2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 큐
		Queue<Integer> queue = new LinkedList<>();
		
		// 반복문 돌면서 큐에 1부터 n까지 숫자 저장
		for(int i=0; i<n; i++) {
			queue.add(i+1);
		}
		
		// 큐에 수가 하나 남을 때까지 반복
		while (queue.size() > 1) {
			queue.remove();	// 맨 위 카드 버리기
			queue.add(queue.poll());	// 맨 위 카드 빼서 맨 아래에 넣기
		}
		
		System.out.println(queue.peek());

	}

}
