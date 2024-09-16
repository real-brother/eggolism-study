package W16_BOJ_1406_에디터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		/* 문제) 1406_에디터
		 * 
		 * L: 커서를 왼쪽으로 한 칸 옮김
		 * D: 커서를 오른쪽으로 한 칸 옮김
		 * B: 커서 왼쪽에 있는 문자 삭제
		 * P $: $라는 문자를 커서 왼쪽에 추가
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		int n = Integer.parseInt(br.readLine());
		
		Stack<Character> st1 = new Stack<>();
		Stack<Character> st2 = new Stack<>();
		
		// 첫번째 스택에 다 넣어주기
		for(int i=0; i<str.length(); i++) {
			st1.push(str.charAt(i));
		}
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			char c = s.charAt(0);	// 명령어
			
			// 왼쪽으로 커서 이동하면 두번째 스택에 넣어주기
			if (c == 'L') {
				if (!st1.isEmpty()) {
					st2.push(st1.pop());
				}
			}
			
			// 오른쪽으로 커서 이동하면 첫번째 스택에 넣어주기
			else if (c == 'D') {
				if (!st2.isEmpty()) {
					st1.push(st2.pop());
				}
			}
			
			// B 이면 첫번째 스택 상단 값 제거
			else if (c == 'B') {
				if (!st1.isEmpty()) {
					st1.pop();
				}
			}
			
			// P 이면 첫번째 스택에 값 넣어주기
			else {
				char add = s.charAt(2);
				st1.push(add);
			}
			
		}
		
		// 두번째 스택으로 값 옮겨주기
		while (!st1.isEmpty()) {
			st2.push(st1.pop());
		}
		
		// 두번째 스택 값 출력
		while (!st2.isEmpty()) {
			sb.append(st2.pop());
		}
		
		System.out.println(sb);

	}

}
