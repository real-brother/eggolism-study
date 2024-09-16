package BOJ_2805_나무자르기;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Integer> woods = new ArrayList<>();
				
		int N = sc.nextInt();
		int target_wood = sc.nextInt();
		for(int i = 0; i < N; i++) woods.add(sc.nextInt());
		
		System.out.print(cut_height(woods, target_wood));
			
		}

	public static long cut_height(List<Integer> woods, int target_wood) {
		long left = 0;
		long result = 0;
		long right = 1000000000;
		
		while(left <= right) {
			long total_wood = 0;
			long cut = (left + right) / 2;
			
			for(int i = 0; i < woods.size(); i++) {
				if(woods.get(i) > cut) total_wood += woods.get(i) - cut;
			}
			
			//if(total_wood == target_wood) return cut;
			if(total_wood < target_wood) right = cut - 1;
			else {
				left = cut + 1;
				result = left - 1;
			}
		}
		return result;
	}

}