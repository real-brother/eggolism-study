package BOJ_1966;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		
		for(int i = 0; i < test_case; i++) {
			Queue<Integer> queue = new LinkedList<>();
			int N = sc.nextInt();
			int docu_index = sc.nextInt();
			int docu_important = 0;
			for(int j = 0; j < N; j++) {
				int important = sc.nextInt();
				queue.offer(important);
				if(docu_index == j) docu_important = important;
				
			}
			
			int count = 0;
			int out_important = 9;

			loop :
			while(!queue.isEmpty()) {
				while(queue.contains(out_important)) {
					if(docu_important == out_important && docu_index == 0) {
							count++;
							break loop;
					}
					else if(queue.peek() == out_important) {
							queue.poll();
							count++;
					}
					else {
						queue.offer(queue.poll());
					}
					docu_index--;
					if(docu_index == -1) docu_index = queue.size()-1;
				}
				out_important--;

			}
			System.out.println(count);
		}
		
	}

}
