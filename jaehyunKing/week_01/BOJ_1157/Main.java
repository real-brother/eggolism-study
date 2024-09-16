package BOJ_1157;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		s = s.toUpperCase();
		int[] count = new int[26];
		int index = 0;
		int max = 0;
		int max2 = 0;
		for(int i = 0; i < s.length(); i++) {
			count[s.charAt(i)-65]++;
		}
		for(int i = 0; i < 26; i++) {
			if(max < count[i]) {
				max = count[i];
				index = i;
			}
			else if(max == count[i]) max2 = count[i];
		}
		if(max == max2) System.out.println("?");
		else System.out.println((char)(index+65));
	}
}
