package W16_BOJ_1406_에디터;

import java.util.LinkedList;
import java.util.Scanner;

public class Main_시간초과 {

	public static void main(String[] args) {
		/* 문제) 1406_에디터
		 * 
		 * L: 커서를 왼쪽으로 한 칸 옮김
		 * D: 커서를 오른쪽으로 한 칸 옮김
		 * B: 커서 왼쪽에 있는 문자 삭제
		 * P $: $라는 문자를 커서 왼쪽에 추가
		 */
		
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();
		int n = sc.nextInt();
		
		LinkedList<Character> list = new LinkedList<>();
		
		for(int i=0; i<s.length(); i++) {
			list.add(s.charAt(i));
		}
		
		int pointer = s.length()-1;
		
		for(int i=0; i<n; i++) {
			char c = sc.next().charAt(0);
			
			if (c == 'L') {
				if (pointer >= 0) {
					pointer--;
				}
			}
			
			else if (c == 'D') {
				if (pointer < list.size()-1) {
					pointer++;
				}
			}
			
			else if (c == 'B') {
				if (pointer >= 0) {
					list.remove(pointer);
					pointer--;
				}
			}
			
			else {
				char add = sc.next().charAt(0);
				
				list.add(pointer+1, add);
				pointer++;
			}
			
		}
		
		for(char ans : list) {
			System.out.print(ans);
		}
		
		

	}

}
