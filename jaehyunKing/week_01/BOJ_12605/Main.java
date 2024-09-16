package BOJ_12605;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		Stack<String> stack = new Stack<>();
		sc.nextLine();
		for(int i = 0; i < N; i++) {
			String[] arr = sc.nextLine().split(" ");
			
			for(int j = 0; j < arr.length; j++) stack.push(arr[j]);
			
			System.out.print("Case #" + (i+1) +":");
			while(!stack.isEmpty()) System.out.print(" "+stack.pop());
			System.out.println();
		}
	}
}
