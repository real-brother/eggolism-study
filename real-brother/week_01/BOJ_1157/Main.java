package BOJ_1157;

import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String string = sc.nextLine();
		// 각 알파벳의 빈도수를 담는 리스트
		ArrayList<Integer> alphaList = new ArrayList<Integer>();
		for (int i = 0; i < 26; i++) {
			alphaList.add(0);
		}
		string = string.toUpperCase();
		
		for (char s : string.toCharArray()) {
			// 아스키코드를 변환하여 65를 빼면 (대문자 A기준) 인덱스가 매칭됨
			int ascode = (int) s;
			int alphabetIndex = ascode - 65;
			int updateAlphaNum = alphaList.get(alphabetIndex) + 1;
			alphaList.set(alphabetIndex, updateAlphaNum);
		}
		
//		System.out.println(alphaList);
//		System.out.println(maxNumber);
		
		
		int maxNumber = Collections.max(alphaList);
		ArrayList<Integer> ansList = new ArrayList<Integer>();
		for (int i = 0; i < 26; i++) {
			if (maxNumber == alphaList.get(i)) {
				ansList.add(i);
			}
		}
		
		if (ansList.size() == 1) {
			int acValue = ansList.get(0) + 65;
			System.out.println((char) acValue);
		} else {
			System.out.println('?');
		}
	}
}
