package BOJ_11268_절댓값힙;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		PriorityQueue<Integer> queue = new PriorityQueue<>(
				(o1, o2)-> {
					if(Math.abs(o1) == Math.abs(o2)) {
						return o1 - o2;
					}
					return Math.abs(o1) - Math.abs(o2);
					
				}
		);
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			int order = sc.nextInt();
			if(order == 0) {
				if(queue.isEmpty())sb.append(0+"\n");
				else sb.append(queue.poll()+"\n");
			}
			else queue.offer(order);
		}
		System.out.println(sb);
	}
}