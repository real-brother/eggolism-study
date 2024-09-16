package BOJ_1620_포켓몬마스터;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		/* 문제)
		 * 포켓몬 개수 n개, 문제 개수 m개
		 * n개의 줄에 1번부터 n번까지 포켓몬 이름 입력
		 * m개 줄에 문제
		 * 번호 -> 이름으로
		 * 이름 -> 번호로 대답.
		 */
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();	// 포켓몬 개수
		int m = sc.nextInt();	// 문제 개수
		sc.nextLine();
		
		// key(번호), value(이름) 을 가지는 map 생성
		Map<Integer, String> pokemon = new HashMap<>();
		
		// key(이름), value(번호) 를 가지는 map 생성
		Map<String, Integer> pokemon2 = new HashMap<>();
		
		// n만큼 돌면서 키가 번호인 map에 번호와 value값 입력받아 저장
		// 키가 이름인 map2에 map1의 value값을 키에 저장하고 번호 저장
		for(int i=1; i<=n; i++) {
			pokemon.put(i, sc.nextLine());
			pokemon2.put(pokemon.get(i), i);
		}
		
		// 이름 결과값과 번호 결과값
		String result1;
		int result2;
		
		// 문제 개수만큼 반복
		for(int i=0; i<m; i++) {
			// 문자열로 입력받기
			String s = sc.nextLine();
			// 문자열의 첫 글자가 숫자라면
			if (Character.isDigit(s.charAt(0))){
				// 해당 번호를 키로 가진 value값을 반환
				result1 = pokemon.get(Integer.parseInt(s));
				System.out.println(result1);
			}
			// 문자열이라면
			else {
				// 해당 이름을 키로 가진 value값을 반환
				result2 = pokemon2.get(s);
				System.out.println(result2);
			}
			
		}

	}

}
