package BOJ_11279_최대힙;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
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