package BOJ_9012;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i = 0; i < T; i++) {
			int count = 0;
			String s = sc.next();
			int flag = 1; //중복("NO")을 막기 위한 flag
			
			
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) == '(') count++;
				else count--;
				if(count < 0) {
					System.out.println("NO");
					flag = 0;
					break;
				}
			}
			if(flag == 1) {
				if(count == 0) System.out.println("YES");
				else System.out.println("NO");
			}
			else flag = 1;
		}
	}
}
