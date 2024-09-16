package BOJ_11478_서로다른부분문자열의개수;

import java.util.*;

public class Main {

	public static void main(String[] size) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int inputSize = input.length();
        Set<String> setList = new HashSet();
        
        for (int i = 0; i < inputSize; i++) {
			for (int j = i+1; j <= inputSize; j++) {
				setList.add(input.substring(i, j));
			}
		}
        
        System.out.println(setList.size());

        
    }
}
