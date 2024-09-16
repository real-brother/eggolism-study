package BOJ_2164;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			if(queue.size() == 1) System.out.print(queue.poll());
			else{
				queue.poll();
				queue.offer(queue.poll());
			}
		}
	}

}
