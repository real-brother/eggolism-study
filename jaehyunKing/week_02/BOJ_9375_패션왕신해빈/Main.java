package BOJ_9375_패션왕신해빈;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		
		for(int i = 0; i < T; i++) {
			int n = sc.nextInt();
			Map<String, Integer> map = new HashMap<>();
			List<String> type_list = new ArrayList<>();
			
			for(int j = 0; j < n; j++) {
				sc.next(); //이름은 버림
				String type = sc.next();
				if(map.containsKey(type))map.put(type, map.get(type) + 1);
				else {
					map.put(type, 1);
					type_list.add(type);
				}
			}
			
			int result = 1;
			for(int j = 0; j < type_list.size(); j++) {
				result *= (map.get(type_list.get(j))+1);
			}
			result -= 1;
			System.out.println(result);
		}

	}

}
