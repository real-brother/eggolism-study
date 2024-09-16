package BOJ_1920_수찾기;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		List<Integer> number_list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) number_list.add(sc.nextInt());
		Collections.sort(number_list);
		
		int M = sc.nextInt();
		
		for(int i = 0; i < M; i++) {
			int input_number = sc.nextInt();
			if(findNumber(number_list, input_number)) System.out.println("1");
			else System.out.println("0");
		}

	}
	
	public static boolean findNumber(List<Integer> number_list, int input_number) {
		
		int left = 0;
		int right = number_list.size()-1;
		int find_number = 0;
		
		while(left <= right) {
			int mid = (left + right)/ 2;
			find_number = number_list.get(mid);
			if(find_number == input_number) return true;
			else if(find_number > input_number) right = mid - 1;
			else left = mid + 1;
		}
		return false;
	}

}
