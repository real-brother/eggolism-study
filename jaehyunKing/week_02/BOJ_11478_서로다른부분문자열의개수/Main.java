package BOJ_11478_서로다른부분문자열의개수;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String[] S = sc.next().split("");
		
		Map<String, Integer> map = new HashMap<>();
		
		for(int i = 0; i < S.length; i++) {
			String str = "";
			for(int j = i; j < S.length; j++) {
				str += S[j];
				map.put(str, 0);
			}
		}
		System.out.println(map.size());

	}

}
