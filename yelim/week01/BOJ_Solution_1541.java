package Simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ_Solution_1541 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = Integer.MAX_VALUE;
		String s = sc.nextLine();
		
		// '-' 기준으로 파싱하여 배열에 저장
		String[] arr = s.split("-");
		
		// '-'로 파싱된 배열을 반복 돌며
		for(int i=0; i<arr.length; i++) {
			int temp = 0;

			// '+' 기준으로 파싱
			String[] add = arr[i].split("\\+");
			
			// '+' 기준으로 파싱된 숫자들을 다 더해주기
			for (int j=0; j<add.length; j++) {
				temp += Integer.parseInt(add[j]);
			}
			
			// 초기값 확인	?? 여기 잘 모르겠엉
			if (sum == Integer.MAX_VALUE) {
				sum = temp;
			}
			else {	// 남은 값 빼주기
				sum -= temp;
			}
		}
		
		System.out.println(sum);
		
		
	}

}
