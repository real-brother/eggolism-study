package BOJ_1253_좋다;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		List<Integer> numbers = new ArrayList<>();
		
		for(int i = 0; i < N; i++) numbers.add(sc.nextInt());
		
		Collections.sort(numbers);
		
		int count = 0;
		
		for(int i = 0; i < numbers.size(); i++) {
			loop :	for(int j = 0; j < numbers.size(); j++) {
				
				int left = 0;
				int right = numbers.size()-1;
				
				while(left <= right) {
					
					int mid = (left + right) / 2;
					
					if(numbers.get(j) + numbers.get(mid) == numbers.get(i) && j != i && j != mid && i != mid) {
						count++;
						break loop;
					}
					
					if(numbers.get(j) + numbers.get(mid) < numbers.get(i)) left = mid + 1;
					else right = mid - 1;
					
				}
			}
		}
		System.out.println(count);
	
	}
}
