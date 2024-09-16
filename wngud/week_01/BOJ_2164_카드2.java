import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2164_카드2 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받기
		int input = Integer.parseInt(bf.readLine());

		// 1. 큐 만들기
		// 늦게 들어간 것이 늦게 나가게 (LIFO)
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= input; i++) {
			queue.offer(i);
		}
		
		// 2. 동작하기
		// 마지막 카드 한개까지가 종료조건
		while (queue.size() != 1) {
			queue.poll();
			queue.offer(queue.poll());
		}

		// 3. 마지막 남은 것 하나 출력
		System.out.println(queue.peek());

	}

}