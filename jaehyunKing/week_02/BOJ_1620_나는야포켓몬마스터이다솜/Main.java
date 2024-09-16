package BOJ_1620_나는야포켓몬마스터이다솜;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int test_case = sc.nextInt();
		
		Map<Integer, String> pokemons_name = new HashMap<>();
		Map<String, Integer> pokemons_num = new HashMap<>();
		
		for(int i = 1; i <= N; i++) {
			String name = sc.next();
			pokemons_name.put(i, name);
			pokemons_num.put(name, i);
		}
		
		
		for(int i = 0; i < test_case; i++) {
			String S = sc.next();
			try { 
				int key = Integer.parseInt(S);
				System.out.println(pokemons_name.get(key));
			} catch (NumberFormatException e){
				System.out.println(pokemons_num.get(S));
				
			}
		}

	}

}
