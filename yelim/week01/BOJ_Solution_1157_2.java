package Simulation;

import java.util.Scanner;

public class BOJ_Solution_1157_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String s = sc.nextLine();
		// 소문자로 다 바꿔주기
		s = s.toLowerCase();
		
		// 알파벳 개수 크기의 배열
		int[] arr = new int[26];
		
		// 아스키코드 활용해서 문자의 해당 인덱스(알파벳 순서) 1 증가
		// 'a' = 아스키코드 97번
		for(int i=0; i<s.length(); i++) {
			arr[s.charAt(i)-97]++;
		}
		
		int max = -1;
		char c = 0;
		
		for(int i=0; i<arr.length; i++) {
			// 최대값 찾기
			if (arr[i] > max) {
				max = arr[i];
				c = (char)(i+65); // 대문자로 출력('A' = 아스키 65)
			}
			else if (arr[i] == max) {	// max값이 여러개면 ? 출력
				c = '?';
			}
		}
		
		System.out.println(c);

	}

}
