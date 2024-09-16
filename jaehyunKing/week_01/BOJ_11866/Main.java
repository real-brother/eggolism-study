package BOJ_11866;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		System.out.print("<");
		while(!queue.isEmpty()) {
			for(int i = 1; i < K; i++)queue.offer(queue.poll());
			if(queue.size() == 1) System.out.print(queue.poll());
			else System.out.print(queue.poll()+", ");
		}
		System.out.print(">");
	}
}
