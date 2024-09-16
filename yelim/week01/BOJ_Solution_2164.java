package Stack_Queue;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_Solution_2164 {
	
	// 시간초과...!!

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		List<Integer> arr = new ArrayList<>();
		
		// 리스트에 숫자 저장
		for(int i=0; i<n; i++) {
			arr.add(i, i+1);
		}
		System.out.println(arr);
		
		// 하나 남을 때까지
		while (arr.size() > 1) {
			arr.remove(0);	// 제거
			int temp = arr.get(0);	// 뒤로 보낼 값 저장
			arr.remove(0);	// 뒤로 보낼 값 제거
			arr.add(arr.size(), temp);	// 맨 뒤에 값 저장
		}
		
		System.out.println(arr.get(0));
		
	}

}
