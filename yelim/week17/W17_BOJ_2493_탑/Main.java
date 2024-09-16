package W17_BOJ_2493_탑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/* 문제) 2493_탑
		 * 
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Stack<int[]> stack = new Stack<>();
		
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			// 스택이 빌 때까지
			while(!stack.isEmpty()) {
				// 스택의 마지막 값이 입력받은 값보다 크거나 같으면 해당 탑 번호 출력
				if(stack.peek()[1] >= num) {
					System.out.print(stack.peek()[0]+" ");
					break;
				}
				stack.pop();
			}
			// 스택이 비었으면 0 출력
			if (stack.isEmpty()) {
				System.out.print("0 ");
			}
			// 스택에 값 넣어주기
			stack.push(new int[] {i+1, num});
		}

	}

}
