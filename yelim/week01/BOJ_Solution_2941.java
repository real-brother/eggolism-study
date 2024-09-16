package Simulation;

import java.util.Scanner;

public class BOJ_Solution_2941 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		// 크로아티아 알파벳을 arr에 저장
		String[] arr = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		
		// 문자열에 크로아티아 알파벳이 있으면 해당 문자를 ?로 변환
		for(int i=0; i<arr.length; i++) {
			if (s.contains(arr[i])) {
				s = s.replace(arr[i], "?");
			}
		}
		System.out.println(s.length());	// 길이 반환
	}

}
