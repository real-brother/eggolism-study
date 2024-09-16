package BOJ_1744_수묶기;

/*
 *  입력받을 때 양수 리스트와 음수(+ 0)리스트로 나눴다
 *  음수는 정렬 후 작은 수 * 작은 수(절댓값이 큰)
 *  양수는 정렬 후 큰 수 * 큰 수를 해주는 것이 가장 큰 값이 나옴
 *  다만 양수에서는 1이 있을 때는 곱하지 말고 더하는 것이 더 크다
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		List<Integer> plus = new ArrayList<>();
		List<Integer> minus = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			int num = sc.nextInt();
			if(num > 0) plus.add(num);
			else minus.add(num);
		}
		
		Collections.sort(plus, Collections.reverseOrder());
		Collections.sort(minus);
		
		int sumPlus = 0;
		int sumMinus = 0;
		
		for(int i = 0; i < plus.size(); i++) {
			// 남은 숫자가 2개 이상 있을 때
			if(i < plus.size()-1) {
				// 1이 있을 때는 곱하지 않고 더 한다
				if(plus.get(i) == 1 || plus.get(i+1) == 1) {
					sumPlus += plus.get(i) + plus.get(i+1);
				}
				else sumPlus += plus.get(i) * plus.get(i+1);
				// 숫자를 2개 사용했으니 i++
				i++;
			}
			else sumPlus += plus.get(i);
		}
		
		for(int i = 0; i < minus.size(); i++) {
			// 남은 숫자가 2개 이상 있을 때
			if(i < minus.size()-1) {
				sumMinus += minus.get(i) * minus.get(i+1);
				// 숫자를 2개 사용했으니 i++
				i++;
			}
			else sumMinus += minus.get(i);
		}
		
		System.out.println(sumPlus + sumMinus);
	}

}