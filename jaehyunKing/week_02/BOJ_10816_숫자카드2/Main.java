package BOJ_10816_숫자카드2;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		List<Integer> number_list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) number_list.add(sc.nextInt());
		Collections.sort(number_list);
		
		StringBuilder sb = new StringBuilder();
		
		int M = sc.nextInt();
		for(int i = 0; i < M; i++) {
			int num = sc.nextInt();
			
			sb.append((numberCountMax(number_list, num) - numberCountMin(number_list, num)) + " ");

		}
		System.out.print(sb);
	}
	
	public static int numberCountMax(List<Integer> number_list, int num) {
		int left = 0;
		int right = number_list.size()-1;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(number_list.get(mid) > num) right = mid - 1;
			else left = mid + 1;
		}
		return left;
	}
	
	public static int numberCountMin(List<Integer> number_list, int num) {
		int left = 0;
		int right = number_list.size()-1;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(number_list.get(mid) >= num) right = mid - 1;
			else left = mid + 1;
		}
		return left;
	}

}