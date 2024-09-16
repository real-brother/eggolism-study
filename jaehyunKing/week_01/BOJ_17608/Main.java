package BOJ_17608;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			stack.push(sc.nextInt());
		}
		
		int max = 0;
		int count = 0;
		while(!stack.isEmpty()) {
			int num = stack.pop();
			if(num > max) {
				max = num;
				count++;
			}
			
		}
		System.out.println(count);
	}

}
