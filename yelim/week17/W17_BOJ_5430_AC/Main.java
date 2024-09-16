package W17_BOJ_5430_AC;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
	
	static StringBuilder sb;

	public static void main(String[] args) {
		/* 문제) 5430_AC
		 * 
		 * R-순서 뒤집기, D-첫번째 수 버리기
		 */
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t=0; t<T; t++) {
			sb = new StringBuilder();
			
			String s = sc.next();	// 수행할 함수
			
			// 함수 char형 배열로 변환
			char[] cArr = s.toCharArray();
			
			int n = sc.nextInt();	// 정수의 개수
			
			String nums = sc.next();	// 정수
			
			// 대괄호 빼고 ,로 구분하여 배열에 정수 저장
			String[] arr = nums.substring(1, nums.length()-1).split(",");
			
			// 덱 생성
			Deque <Integer> deque = new ArrayDeque<>();
			
			// 정수 배열 크기만큼 반복 돌면서 덱에 저장해주기
			// 빈 문자열이 아닐 때! --> inputMismatch 뜸
			for(int i=0; i<arr.length; i++) {
				if (!arr[i].equals("")) {
					deque.add(Integer.parseInt(arr[i]));
				}
			}
			
			ac(cArr, deque);
			
			System.out.println(sb);
			
		}

	}
	
	// 함수 수행하는 메서드
	public static void ac(char[] cmd, Deque<Integer> deque) {
		
		// 방향 확인할 변수
		int dir = 1;	// 1이면 first, 2면 last
		
		// 명령어 개수만큼 반복
		for(int i=0; i<cmd.length; i++) {
			// R이면 방향 바꿔주기
			if (cmd[i] == 'R') {
				if (dir==1) {
					dir = 2;
				} else {
					dir = 1;
				}
			}
			
			// D이면 
			else if (cmd[i] == 'D') {
				// 정방향일 때
				if (dir==1) {
					// 맨 앞에 삭제할 요소가 없으면 error 출력 후 return 
					if (deque.pollFirst() == null) {
						sb.append("error");
						return;
					}
				}
				
				// 역방향일 때
				else {
					// 맨 뒤에 삭제할 요소가 없으면 error 출력 후 return
					if (deque.pollLast() == null) {
						sb.append("error");
						return;
					}
				}
			}
		}
		
		// error가 아닐 때 출력형식 저장하기
		
		// 대괄호로 시작
		sb.append("[");
		
		// deque의 사이즈가 0보다 클 때
		if (deque.size() > 0) {
			// 정방향
			if (dir==1) {
				// 앞에서부터 요소 제거하면서 저장
				sb.append(deque.pollFirst());
				
				while(!deque.isEmpty()) {
					sb.append(",").append(deque.pollFirst());
				}
			}
			// 역방향
			else {
				// 뒤에서부터 요소 제거하면서 저장
				sb.append(deque.pollLast());
				
				while(!deque.isEmpty()) {
					sb.append(",").append(deque.pollLast());
				}
			}
		}
		
		// 대괄호 닫아서 마무리
		sb.append("]");
	}
 
}
