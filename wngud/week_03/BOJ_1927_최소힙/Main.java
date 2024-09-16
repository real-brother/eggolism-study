import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// 내림차순
class myComparator implements Comparator<Integer> {
	
	@Override
	public int compare(Integer o1, Integer o2) {
		if ((int) o1 > (int) o2) return 1; // 앞이 더 큰 것 -> 내림차순
		else if ((int) o1 < (int) o2) return -1;
		else return 0;
	}
}


public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int total = Integer.parseInt(br.readLine());
		
		// 힙 시작
		myComparator comparator = new myComparator();
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(comparator);

		
		for (int i = 0; i < total; i++) {
			int input = Integer.parseInt(br.readLine());
			
			if (heap.isEmpty() && input == 0) {
				System.out.println(0);
			} else if (input == 0) {
				System.out.println(heap.poll());
			} else {
				heap.add(input);
			}
			
			
		}
		
	}
}
