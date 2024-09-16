package BOJ_11279_최대힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n =	Integer.parseInt(br.readLine());
		// 반대방향으로 우선순위큐 구현 -> 최대힙
		PriorityQueue<Long> queue=new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i < n; i++) {
			long val = Integer.parseInt(br.readLine());
			if (val > 0) {
				queue.add(val);
			} else if (val == 0) {
				if (queue.isEmpty())
					System.out.println(0);
				else
					System.out.println(queue.poll());
			}
		}
	}
}