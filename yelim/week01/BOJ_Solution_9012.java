package Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_Solution_9012 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();	// 테스트 케이스
		sc.nextLine();	// 개행
		
		for(int i=0; i<t; i++) {
			
			// 스택 구현인듯
			int top = -1;	// top을 -1로 설정
			List<Character> arrList = new ArrayList<>();
			
			String s = sc.nextLine();	// 괄호 입력받기
			char[] charArr = s.toCharArray();	// 문자배열로 바꾸기
			
			for(int j=0; j<charArr.length; j++) {	// 문자배열 크기만큼 반복
				// 여는 괄호이면 리스트에 추가하고 top 1 증가시키기
				if (charArr[j] == '(') {
					arrList.add(charArr[j]);
					top++;
				}
				else {	// 닫는 괄호라면
					if (top >= 0) {	// top이 0보다 클 때 = 여는 괄호가 있을 때
						// top에 있는 여는 괄호 없애주기
						// = 올바른 수식
						arrList.remove(top);	
						top--;	// top 1 감소시키기
					}
					else {	// top이 0보다 작을 때 = 여는 괄호가 없을 때
						top--;	// top 1 감소시키기 = 올바른 수식이 아님
								// 닫는 괄호가 더 많을 때를 대비
 						break;	// 반복문 벗어나기
					}
				}
			}
			if (top < -1) {	// top이 -1보다 작을 때 (닫는 괄호가 더 많을 때)
				System.out.println("NO");
			}
			// 리스트가 비었을 때 = 수식이 올바를 때
			else if (arrList.size() == 0) {
				System.out.println("YES");
			}
			else {	// 리스트가 비어있지 않을 때 = 수식이 올바르지 않을 때
				System.out.println("NO");
			}
		}

	}

}
